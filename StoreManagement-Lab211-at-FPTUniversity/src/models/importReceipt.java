/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Hp
 */
import controlls.*;
import models.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class importReceipt {

    protected String importId;
    protected String creationTime;
    public List<ReceiptItem> items;
    private double totalPrice;

    public importReceipt(String code, String creationTime , List<ReceiptItem> items , double totalPrice) {
        this.importId = code;
        this.creationTime = creationTime;
         this.items = items;
         this.totalPrice=totalPrice;
    }

    public importReceipt() {
    }
    

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (ReceiptItem item : items) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public void displayReceiptInfo() {
        System.out.println("Receipt Code: " + importId);
        System.out.println("Creation Time: " + creationTime);
        System.out.println("Items:");
        for (ReceiptItem item : items) {
            System.out.println(item+": " + item.getQuantity() + " units");
        }
        System.out.println("Total Quantity: " + getTotalQuantity());
    }

    public String getCode() {
        return importId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

  
    public void setCode(String code) {
        this.importId = code;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public String getImportId() {
        return importId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return importId + "; " + creationTime + "; " + items +"; "+ totalPrice ;
    }
    
    
}
