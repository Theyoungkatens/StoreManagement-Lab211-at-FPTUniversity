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

public class exportReceipt1 {

    protected String exportID;
    protected String creationTime;
    
    public List<ReceiptItem> items;
    private double totalPrice;

    public exportReceipt1(String code, String creationTime , List<ReceiptItem> items , double totalPrice) {
        this.exportID = code;
        this.creationTime = creationTime;
         this.items = items;
         this.totalPrice=totalPrice;
    }

    public exportReceipt1() {
    }
    
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (ReceiptItem item : items) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public String getCode() {
        return exportID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

  
    public void setCode(String code) {
        this.exportID = code;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public String getImportId() {
        return exportID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setImportId(String importId) {
        this.exportID = importId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return exportID + "; " + creationTime + "; " + items +"; "+ totalPrice ;
    }
    
    
}
