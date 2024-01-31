/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Products;

/**
 *
 * @author 
 */
// Code cho phan REPORT
public class ReportManager {

    ProductManager pro = new ProductManager();  // Khoi tao doi tuong pro để gọi các hàm của ProductManaget

    private HashMap<String, Products> productMap = pro.loadProduct(); 
    HashMap<String, Products> proMap = new HashMap<>();

    public void findExpiredProducts() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        System.out.println("Expired Products: san pham het han su dung : ");
        for (Products product : productMap.values()) {
            if (currentDate.after(stringToDate(product.getExpirationDate()))) {
                proMap.put(product.getProductCode(), product);
            }
        }
        pro.printAllsProduct(proMap);
    }

    public void findSellingProducts() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        System.out.println("Selling Products:");
        for (Products product : productMap.values()) {
            if (product.getQuantity() > 0 && !currentDate.after(stringToDate(product.getExpirationDate()))) {
                proMap.put(product.getProductCode(), product);
            }
        }
        pro.printAllsProduct(proMap);
    }

    public void findRunningOutProducts() {

        List<Products> list = new ArrayList<Products>();
        System.out.println("Running out Products (quantity <=3):");
        for (Products product : productMap.values()) {
            if (product.getQuantity() <= 3) {
                list.add(product);
                proMap.put(product.getProductCode(), product);
            }
        }
        displayDescendingByQuantity(list);
    }

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.parse(dateString);
    }

    public void displayDescendingByQuantity(List<Products> list) {

        // Sort order by quantity
        Collections.sort(list, new Comparator<Products>() {

            @Override
            public int compare(Products t, Products t1) {
                return Integer.compare(t.getQuantity(), t1.getQuantity());
            }
        });

        for (Products products : list) {
            System.out.println(products.productType());
        }
    }

  
}
