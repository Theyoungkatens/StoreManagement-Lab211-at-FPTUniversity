/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import models.Products;

/**
 *
 * @author Hp
 */

/*
 private String productCode;
    private String productName;
    private String size;
    private Date manufacturingDate;
    private Date expirationDate;
    private double price; //  giá sản phẩm
    private boolean isDaily; // co phai san phan dung trong ngay ko hay la san pham co hsd lau dai 
 */
public class ProductManager implements I_Collection {

    HashMap<String, Products> productMap = new HashMap<>();
    Utilities ulti = new Utilities();
    static Scanner sc = new Scanner(System.in);

    @Override
    public boolean add() {
        int getchoice = 9; // biến này để làm cái thăm dò xem có muốn nhập tiêp cai khác ko , nếu nhập 1 thì có , nhập 0 thì thoát ra menu chính luôn

        do {

            String ID ="";

            do {
                ID = ulti.getString("Enter product's Id (format: Pxxxx) : ", 1, 0, 100);
                //  Check trùng và định dạng cho mỗi ID
                if (checkDuplicate(ID, 1)) {
                    System.out.println("Product's ID cannot be duplicate. Please enter another one !");
                }
                if (!checkProductID(ID)) {
                    System.out.println("Product's ID phai co dang Pxxxx (0 <= x < 10) !");
                }
            } while (checkDuplicate(ID, 1) || !checkProductID(ID));  //ID phải là duy nhất và đúng định dạng 
            String name = ulti.getString("Enter Product's name: ", 1, 0, 100);
            String size = ulti.getString("Enter Product's size: ", 1, 0, 100);
            String manufacturingDate = ulti.inputDate("Enter manufacturingDate: ", 1); // khuc nay can gioi han vi ngay san xuat phai < ngay hien tai
            String expirationDate = ulti.inputDate("Enter expirationDate: ", 0);

            double unitPrice = ulti.getDouble("Enter Product's price: ", 0.0, 1000000000);
             int quantity = ulti.getInt("Enter quantity: ", 0, 1000000);

            int isDaily = ulti.getInt("Is daily product? (True:1 False:0): ", 0, 2);

            Products product = new Products(ID, name, size, manufacturingDate, expirationDate, unitPrice,quantity, isDaily);

// Load data from file before add new a product
            productMap = loadProduct();
            productMap.put(ID, product);

            // Save car to file vehicle.txt 
            saveProduct(productMap);

            getchoice = ulti.getInt("Do you want to add more product ? (yes: 1 , no: 0)", 0, 2);
            if (getchoice == 0) {
                System.out.println("----------Back to main menu!!!-------------");
                break;
            }
        } while (getchoice != 0); // Do while o day de kiem tra xem co con muon add them ko , neu nhap 1 thi lam tiep , neu nhap 0 thi thoi ^^
        for (Products object : productMap.values()) {
            System.out.println(object);
        }
        return true;
    }

    @Override
    public boolean delete() {
   productMap=loadProduct(); // load data len productMap
        String ID = "";
        do {
            ID = ulti.getString("Enter Product's Id : ", 1, 0, 100);
            // check tinh dupplicate cho moi id 
            if (checkDuplicate(ID, 1) == false) {
                System.out.println("Product 's ID does not exits !");
            }
        } while (checkDuplicate(ID, 1) == false);   // id phai ton tai moi nhan 
        int getChoice = ulti.getInt("Do you want to delete this product? (Yes:1 , No:0)", 0, 2);
        if (getChoice == 0) {
            System.out.println("Delete that bai!!!");
            return true;
        }
       
        productMap.remove(ID);
        saveProduct(productMap);
        System.out.println("Delete thanh cong!!!");

        return true;
    }

    @Override
    public boolean update() {
        Scanner input = new Scanner(System.in);
        List<Products> productList = new ArrayList<>();
        System.out.println("---Update Product's Information----");

        String updateId = ulti.getString("Enter ID Of Product That You Want To Update: ", 1, 0, 10000000);
        boolean productFound = false;
        productMap = loadProduct();

        for (Products object : productMap.values()) {
            productList.add(object);
        }
        for (Products product : productList) {
            if (product.getProductCode().equals(updateId)) {
                productFound = true;
                boolean OK = false;
                //NAME
                System.out.print("Enter Name (current value: " + product.getProductName() + "): ");
                String newName = input.nextLine();
                if (newName.isEmpty()) {
                    product.setProductName(product.getProductName());
                } else {
                    product.setProductName(newName);
                }
      
//SIZE
                System.out.print("Enter SIZE (current value: " + product.getSize() + "): ");
                String size = "";
                do {
                    size = input.nextLine();

                    if (size.isEmpty()) {
                        product.setSize(product.getSize());
                        OK = true;
                    } else {
                        product.setSize(size);
                        OK = true;
                    }

                    // Xóa ký tự Enter không mong muốn từ bộ đệm
                    input.nextLine();
                } while (!OK);
                 
                String newNSX = "";
                do {
                    newNSX = ulti.inputDate("Enter Ngay San Xuat (current value: " + product.getExpirationDate() + "): ", 0);
                    if (newNSX == null) {
                        product.setExpirationDate(product.getExpirationDate());
                        OK = true;
                    } else {
                        product.setExpirationDate(newNSX);
                        OK = true;
                    };
                } while (!OK);
                String newHSD = "";
                do {
                    newHSD = ulti.inputDate("Enter Ngay Han su dung (current value: " + product.getExpirationDate() + "): ", 0);
                    if (newHSD == null) {
                        product.setExpirationDate(product.getExpirationDate());
                        OK = true;
                    } else {
                        product.setExpirationDate(newHSD);
                        OK = true;
                    };
                } while (!OK);
                //Price
                System.out.print("Enter new Product'price (current value: " + product.getPrice() + "): ");
                String price1 = "";

                double price = ulti.getDouble("Enter new price: ", 0.0, 100000000000000.0);
                if (price == -1) {
                    product.setPrice(product.getPrice());
                } else {
                    product.setPrice(price);
                }

                int isDaily = ulti.getInt("Is daily product? (True:1 False:0):  ", 0, 2);
                if (isDaily == -1) {
                    product.setIsDaily(product.getisIsDaily());
                } else {
                    product.setIsDaily(isDaily);
                }

                productMap = loadProduct();
                productMap.put(updateId, product);
                saveProduct(productMap);
                System.out.println("Product" + " after update: ");
                System.out.println(product.toString());
            }

        }
        if (!productFound) {
            System.out.println("ID Doesn't Exist!!!!");
        }

        return true;
    }
    
   // vi ham search de ko yeu cau nen minh de trong
    @Override
    public Products search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showInfor() {
        productMap = loadProduct();
        printAllsProduct(productMap);

    }

    @Override
    public boolean sort() {
        return true;
    }

    // Hàm lưu HashMap<Product> vào file .dat
  
    public void saveProduct(HashMap<String, Products> studentMap) {
        ArrayList<Products> productList = new ArrayList<>(studentMap.values());

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/output/product.dat"));
            String line;
            for (Products student : productList) {
                line = student.toString() + "\n";
                bw.write(line);
            }
            bw.close();
            System.out.println("Saved Successfully!!!");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

      // Hàm đọc HashMap<Product> từ file .dat
    public HashMap<String, Products> loadProduct() {
        HashMap<String, Products> studentMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/output/product.dat"));
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(",");
                String id = parts[0].trim();
                String name = parts[1].trim();
                String size = parts[2].trim();
                String maDate = parts[3].trim();
                String exDate = parts[4].trim();
                double price = Double.parseDouble(parts[5].trim());
                int quantity = Integer.parseInt(parts[6].trim());
                 int isDaily = Integer.parseInt(parts[7].trim());

                Products pro = new Products(id, name, size, maDate, exDate, price,quantity, isDaily);

                studentMap.put(id, pro);

            }
            br.close();
        } catch (Exception e) {
        }
        return studentMap;
    }


    public static boolean checkProductID(String str) { // check format Pxxxx cho ID 
        if (str.length() != 5) {
            return false;
        }

        if (str.charAt(0) != 'P') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Hàm kiểm tra xem id nhập vào có trùng với id của cai khác đã tồn tại trogn file ko . nếu trùng thì hàm trả về true => bắt nhập id khác ^^
    public boolean checkDuplicate(String s, int opt) {
        productMap = loadProduct();
        Products product = null;
        if (opt == 1) {
            productMap = loadProduct();
            for (Products c : productMap.values()) {  // check car is exits or not
                if (c.getProductCode().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }
        if (opt == 2) {
            productMap = loadProduct();
            for (Products c : productMap.values()) {  // check car is exits or not
                if (c.getProductName().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }

        return false;
    }
    public void printSave(){
        System.out.println("Data has been added");

    }
    
    // Ham lay ra price cua san pham , nham tinh total price cho cac don hang
    public double getPrice(String productCode) {
    productMap = loadProduct(); // Giả sử hàm này trả về một Map có key là mã sản phẩm và value là đối tượng Products
        
    // Kiểm tra xem sản phẩm có tồn tại trong productMap không
    if (productMap.containsKey(productCode)) {
        Products product = productMap.get(productCode);
        double price = product.getPrice(); //  getPrice() trong đối tượng Products để lấy giá sản phẩm
        return price;
    } else {
        System.out.println("Sản phẩm không tồn tại.");
        return 0.0; // Neu ko co san pham nao thi tra ve 0
    }
}
    
    // Ham nay dung de lien ket logic giữa đơn nhập và đơn xuất: nhập hàng => quantity của sp tăng , xuất hàng  => quantity của sp giảm
     public void changeQuantityinStocks(String productCode, int quantity, int opt) {
    productMap = loadProduct(); 
        
    // Kiểm tra xem sản phẩm có tồn tại trong productMap không
    if (productMap.containsKey(productCode)) {
        Products product = productMap.get(productCode);
        if (opt==1) {
            product.setQuantity(product.getQuantity()+quantity);
        }
        if (opt==2) {
            if (product.getQuantity()>0) {
                            product.setQuantity(product.getQuantity()-quantity);
            }
        }
        saveProduct(productMap);
    } else {
        System.out.println("Sản phẩm không tồn tại.");
      
    }
}
     
     
         // in ra 1 table 
    public void printAllsProduct(HashMap<String,Products> proMap) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        System.out.println("##########################################################################################################################");
        System.out.println("#ID        #NAME               #SIZE       #Manufacturing Date   #Expiration Date      #Quantity        #PRICE           #");
        System.out.println("##########################################################################################################################");

        for (Products p : proMap.values()) {
            String idstr = String.format("%-10s", "ID#");
            String id = String.format("%-10s", p.getProductCode());
            String name = String.format("%-18s", p.getProductName());
            String size = String.format("%-10s", p.getSize());
            String maDate = String.format("%-20s", p.getManufacturingDate()); // Right-align the price column
            String exDate = String.format("%-20s", p.getExpirationDate());
            String quantity = String.format("%-15s", p.getQuantity());
            String price = String.format("%-15s", p.getPrice());
            String isDaily = String.format("%-15s", p.getisIsDaily());
           

            System.out.println("#" + id + "# " + name + "# " + size + "# " + maDate + "# " + exDate + "# " + quantity + "# " + price + "#");
        }
                                                                                                                                                                                
        System.out.println("##########################################################################################################################");
        System.out.println("#                                                                                               TOTAL: " + proMap.size() + " product type[s]#");
        System.out.println("##########################################################################################################################");

        System.out.println("");
        System.out.println("");
    }

}
