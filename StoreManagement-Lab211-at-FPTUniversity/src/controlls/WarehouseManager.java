/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import models.*;
import controlls.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import models.Products;
import models.exportReceipt1;
import models.importReceipt;
import models.ReceiptItem;

/**
 *
 * @author Hp
 */
public class WarehouseManager implements I_Receipt {

    ProductManager p = new ProductManager();
    HashMap<String, Products> proMap = p.loadProduct();
    HashMap<String, importReceipt> program = new HashMap<>();
    Utilities ulti = new Utilities();
    static Scanner sc = new Scanner(System.in);
     private int receiptCounter = 0;
  
    @Override

    public boolean createImport() throws IOException {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        List<importReceipt> importReceipts = new ArrayList<>();
        int getchoice = 9;

        do {
            String receiptCode = generateImportReceiptCode();
            if (checkDuplicate(receiptCode, 1)) {
                System.out.println("Receipt code cannot be duplicate. Please enter another one !");
                continue; // Bỏ qua lần lặp này và yêu cầu nhập lại.
            }

            // Lấy thời gian hiện tại và định dạng thành chuỗi "yyyy/MM/dd"
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = dateFormat.format(currentDate);

            // Thêm mục vào biên nhận
            int totalPrice = 0;
            do {
                String ID;
                do {
                    ID = ulti.getString("Enter product's Id (format: Pxxxx) : ", 1, 0, 100);
                    //  Check trùng và định dạng cho mỗi ID
                    if (!p.checkDuplicate(ID, 1)) {
                        System.out.println("No Product Found !!!");
                    }
                    if (!p.checkProductID(ID)) {
                        System.out.println("product's ID phai co dang Pxxxx (0 <= x < 10) !");
                    }
                } while (!p.checkDuplicate(ID, 1) || !p.checkProductID(ID));  //ID phải là duy nhất và đúng định dạng 
                String manufacturingDate = ulti.inputDate("Enter manufacturingDate: ",1);
                String expirationDate = ulti.inputDate("Enter expirationDate: ",0);
                int quantity = ulti.getInt("Enter quantity: ", 0, 1000000000);

                totalPrice += quantity * p.getPrice(ID);
                p.changeQuantityinStocks(ID, quantity, 1);

                ReceiptItem receiptItem = new ReceiptItem(ID, manufacturingDate, expirationDate, quantity);
                receiptItems.add(receiptItem);
                // Hỏi người dùng nếu muốn nhập thêm mục
                getchoice = ulti.getInt("Do you want to add more items? (Yes:1 No: 0): ", 0, 2);
                if (getchoice == 0) {

                    break;
                }
            } while (true);

            // Hiển thị danh sách các mục trong biên nhận
            System.out.println("Items in the Receipt:");

//            System.out.println(receiptCode+", "+formattedDate+", "+receiptItems);
            importReceipt r = new importReceipt(receiptCode, formattedDate, receiptItems, totalPrice);
            
            importReceipts=loadImportReceipt();
            importReceipts.add(r);
            saveImportReceipt(importReceipts);
            System.out.println(r.toString());

            // Lưu thông tin biên nhận vào danh sách hoặc file tùy chọn
            // Ở đây, bạn cần thay đổi phần này để lưu thông tin biên nhận vào danh sách hoặc file.
            getchoice = ulti.getInt("Do you want to create another import receipt? (yes: 1, no: 0): ", 0, 2);

        } while (getchoice != 0);

        return true;
    }

    @Override
    public boolean createExport() throws IOException {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        List<exportReceipt1> eReceipt1s = new ArrayList<>();
        int getchoice = 9;

        do {
            String receiptCode = generateExportReceiptCode();
            if (checkDuplicate(receiptCode, 1)) {
                System.out.println("Receipt code cannot be duplicate. Please enter another one !");
                continue; // Bỏ qua lần lặp này và yêu cầu nhập lại.
            }

            // Lấy thời gian hiện tại và định dạng thành chuỗi "yyyy/MM/dd"
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = dateFormat.format(currentDate);

            // Thêm mục vào biên nhận
            int totalPrice = 0;
            do {
                String ID;
                do {
                    ID = ulti.getString("Enter product's Id (format: Pxxxx) : ", 1, 0, 100);
                    //  Check trùng và định dạng cho mỗi ID
                    if (!p.checkDuplicate(ID, 1)) {
                        System.out.println("No Product Found !!!");
                    }
                    if (!p.checkProductID(ID)) {
                        System.out.println("product's ID phai co dang pxxxx (0 <= x < 10) !");
                    }
                } while (!p.checkDuplicate(ID, 1) || !p.checkProductID(ID));  //ID phải là duy nhất và đúng định dạng 
                String manufacturingDate = ulti.inputDate("Enter manufacturingDate: ",1);
                String expirationDate = ulti.inputDate("Enter expirationDate: ",0);
                int quantity = ulti.getInt("Enter quantity: ", 0, 1000000000);
                totalPrice += quantity * p.getPrice(ID);
                p.changeQuantityinStocks(ID, quantity, 2);
                ReceiptItem receiptItem = new ReceiptItem(ID, manufacturingDate, expirationDate, quantity);
                receiptItems.add(receiptItem);

                // Hỏi người dùng nếu muốn nhập thêm mục
                getchoice = ulti.getInt("Do you want to add more items? (Yes:1 No: 0): ", 0, 2);
                if (getchoice == 0) {
                    break;
                }
            } while (true);

            // Hiển thị danh sách các mục trong biên nhận
            System.out.println("Items in the Receipt:");

//            System.out.println(receiptCode+", "+formattedDate+", "+receiptItems);
            exportReceipt1 r = new exportReceipt1(receiptCode, formattedDate, receiptItems, totalPrice);

            eReceipt1s=loadExportReceipt();
            eReceipt1s.add(r);
            saveExportReceipt(eReceipt1s);
            System.out.println(r.toString());

            // Lưu thông tin biên nhận vào danh sách hoặc file tùy chọn
            // Ở đây, bạn cần thay đổi phần này để lưu thông tin biên nhận vào danh sách hoặc file.
            getchoice = ulti.getInt("Do you want to create another export receipt? (yes: 1, no: 0): ", 0, 2);

        } while (getchoice != 0);

        return true;
    }

    private boolean checkDuplicate(String ID, int i) {
        return false;
    }

     public String generateImportReceiptCode() throws IOException {
        List<importReceipt> orders = loadImportReceipt();
        int orderCount = orders.size();
        return "I" + String.format("%07d", orderCount);
    }
public String generateExportReceiptCode() throws IOException {
        List<exportReceipt1> orders = loadExportReceipt();
        int orderCount = orders.size();
        return "E" + String.format("%07d", orderCount);
    }
  



    public void saveImportReceipt(List<importReceipt> programList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\output\\importReceipt.txt"));

            String line;
            for (importReceipt program1 : programList) {
                //programID + ", " + programName  + ", " + programDay + ", " + cost + ", " + time + ", " + location + ", " + startRegistationDate + ", " + endRegistationDate + ", " + content 
                line = program1.toString()+"\n";
                writer.write(line);
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public void saveExportReceipt(List<exportReceipt1> programList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\output\\exportReceipt.txt"));

            String line;
            for (exportReceipt1 program1 : programList) {
                line = program1.toString()+"\n";
                writer.write(line);
            }
            writer.close();
        } catch (Exception e) {
        }
    }



    public List<importReceipt> loadImportReceipt() {
        List<importReceipt> importReceipts = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\output\\importReceipt.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(";");
                String id = arr[0].trim();
                String date = arr[1].trim();
                String itemsStr = arr[2].trim();

                itemsStr = itemsStr.substring(1, itemsStr.length() - 1); // Loại bỏ dấu ngoặc vuông
                String[] itemsArr = itemsStr.split(",");

                List<ReceiptItem> receiptItems = new ArrayList<>();
                for (String itemStr : itemsArr) {
                    String[] itemParts = itemStr.split(":");
                    String productCode = itemParts[0].trim();
                    String manufacturingDate = itemParts[1].trim();
                    String expirationDate = itemParts[2].trim();
                    int quantity = Integer.parseInt(itemParts[3].trim());
                    ReceiptItem receiptItem = new ReceiptItem(productCode, manufacturingDate, expirationDate, quantity);
                    receiptItems.add(receiptItem);
                }

                double cost = Double.parseDouble(arr[3].trim());
                importReceipt importReceipt = new importReceipt(id, date, receiptItems, cost);
                importReceipts.add(importReceipt);
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
           receiptCounter = importReceipts.size();
        return importReceipts;
    }
    
     public static List<exportReceipt1> loadExportReceipt() {
        List<exportReceipt1> exportReceipts = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\output\\exportReceipt.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(";");
                String id = arr[0].trim();
                String date = arr[1].trim();
                String itemsStr = arr[2].trim();

                itemsStr = itemsStr.substring(1, itemsStr.length() - 1); // Loại bỏ dấu ngoặc vuông
                String[] itemsArr = itemsStr.split(",");

                List<ReceiptItem> receiptItems = new ArrayList<>();
                for (String itemStr : itemsArr) {
                    String[] itemParts = itemStr.split(":");
                    String productCode = itemParts[0].trim();
                    String manufacturingDate = itemParts[1].trim();
                    String expirationDate = itemParts[2].trim();
                    int quantity = Integer.parseInt(itemParts[3].trim());
                    ReceiptItem receiptItem = new ReceiptItem(productCode, manufacturingDate, expirationDate, quantity);
                    receiptItems.add(receiptItem);
                }

                double cost = Double.parseDouble(arr[3].trim());
                exportReceipt1 exportReceipt = new exportReceipt1(id, date, receiptItems, cost);
                exportReceipts.add(exportReceipt);
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return exportReceipts;
    }

   

  

     public void displayAllsReceipt(String ID) {
        List<exportReceipt1> exportReceipts = loadExportReceipt();
        List<importReceipt> importReceipts = loadImportReceipt();
        
         System.out.println("Import Receipt: ");
        for (importReceipt importReceipt : importReceipts) {
            for (ReceiptItem item : importReceipt.items) {
                if (item.getProID().equalsIgnoreCase(ID)) {
                    System.out.println(importReceipt);
                }
            }
        }
        
         System.out.println("Export Receipt: ");
        for (exportReceipt1 exportReceipt : exportReceipts) {
            for (ReceiptItem item : exportReceipt.items) {
                if (item.getProID().equalsIgnoreCase(ID)) {
                    System.out.println(exportReceipt);
                }
            }
        }
}
    
}
