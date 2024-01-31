/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controlls.*;
import models.*;

/**
 *
 * @author Hp
 */
import java.io.Serializable;

public class ReceiptItem { // Cac item đưa vao các đơn xuất nhập
    private String proID;
   private String manufacturingDate;
    private String expirationDate;
    private int quantity; 

    public ReceiptItem(String product,  String manufacturingDate, String expirationDate, int quantity) {
        this.proID = product;
        this.quantity = quantity;
        this.expirationDate=expirationDate;
        this.manufacturingDate=manufacturingDate;
    }

    @Override
    public String toString() {
        return proID + ":"+manufacturingDate+ ":"+expirationDate+":"+ quantity ;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return proID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProID() {
        return proID;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    
}
