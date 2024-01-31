/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * 
 */
import java.io.Serializable;
import java.util.Date;

public class Products  {
    private String productCode;
    private String productName;
    private String size;
    private String manufacturingDate;
    private String expirationDate;
    private double price; //  giá sản phẩm
    private int quantity;
    private int isDaily; // co phai san phan dung trong ngay ko hay la san pham co hsd lau dai 
    

    public Products() {
        
    }

    
    // Constructor để khởi tạo đối tượng Product
    public Products(String productCode, String productName, String size, String manufacturingDate, String expirationDate, double price, int quantity, int isDaily) {   
        this.productCode = productCode;
        this.productName = productName;
        this.size = size;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity=quantity;
        this.isDaily = isDaily;
    }

    public int getisIsDaily() {
        return isDaily;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

    public void setIsDaily(int isDaily) {
        this.isDaily = isDaily;
    }

    // Getter và setter cho các trường dữ liệu
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String  getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    public String productType() {
        return "Products{" +  productCode + ", " + productName + ", size=" + size + ", " + manufacturingDate + ", " + expirationDate + ", " + price + ", quantity=" + quantity + ", isDaily=" + isDaily + '}';
    }
   
       @Override
    public String toString() {
        return  productCode + ", " + productName + ", " + size + ", " + manufacturingDate + ", " + expirationDate + ", " + price+ ", "+quantity + ", "+ isDaily ;
    }




  
}

