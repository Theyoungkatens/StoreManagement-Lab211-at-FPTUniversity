/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlls.I_Menu;
import controlls.ProductManager;
import controlls.ReportManager;
import controlls.Utilities;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controlls.WarehouseManager;

/**
 *
 * @author Hp
 */




public class main implements I_Menu {

    Utilities ulti = new Utilities();

    int choice;
    int choice1;

    public static void main(String[] args) {
        main m = new main();
        m.showMenu();
    }

    @Override
    public void showMenu() {
        ProductManager pro = new ProductManager();
        WarehouseManager w = new WarehouseManager();
        ReportManager r = new ReportManager();
        System.out.println("Hello Cảm Ơn Ban Đã Ủng Hộ Mình ");

        do {
            Menu menu = new Menu("<----------- MainMenu ------------>");
            menu.addNewOptiont("1. Manage products");
            menu.addNewOptiont("2. Manage Warehouse");
            menu.addNewOptiont("3. Report");
            menu.addNewOptiont("4. Store data to files");
            menu.addNewOptiont("5. Close the application");
            menu.printMenu();

            choice = menu.getChoice();
                                System.out.println("------------------------------");


            if (choice == 1) {
                //  Manage products
                do {
                    Menu menu1 = new Menu("1. Manage products");
                    menu1.addNewOptiont("       1.1. Add a product");
                    menu1.addNewOptiont("       1.2. Update product information.");
                    menu1.addNewOptiont("       1.3. Delete product.");
                    menu1.addNewOptiont("       1.4. Show all product.");
                    menu1.addNewOptiont("       1.5. Back to main menu");
                    menu1.printMenu();

                    choice1 = menu1.getChoice();

                    if (choice1 == 1) {
                          pro.add();
                    }
                    if (choice1 == 2) {
                          pro.update();
                    }
                    if (choice1 == 3) {
                          pro.delete();
                    }
                    if (choice1 == 4) {
                        
                         pro.showInfor();
                    }

                } while (choice1 != 5);
            }
           
            
            if (choice == 2) {
                choice1=-3;
                do {
                    Menu menu2 = new Menu("2. Manage Warehouse");
                    menu2.addNewOptiont("        2.1. Create an import receipt.");
                    menu2.addNewOptiont("        2.2. Create an export receipt.");
                    menu2.addNewOptiont("        2.3. Back to main menu");
                    menu2.printMenu();

                    choice1 = menu2.getChoice();

                    if (choice1 == 1) {
                        try {
                            w.createImport();
                        } catch (IOException ex) {
                        }
                    }
                    if (choice1 == 2) {
                        try {
                            w.createExport();
                        } catch (IOException ex) {
                        }
                    }
                   

                } while (choice1 != 3);
            }
                
                //Report
                 if (choice == 3) {
                do {
                    choice1=-3;
                    Menu menu3 = new Menu("3. Report");
                    menu3.addNewOptiont("      3.1. Products that have expired(Het HSD)");
                    menu3.addNewOptiont("      3.2. The products that the store is selling.");
                    menu3.addNewOptiont("      3.3. Products that are running out of stock (sorted in ascending order).");
                    menu3.addNewOptiont("      3.4. Import/export receipt of a product.");
                    menu3.addNewOptiont("      3.5. Back to main menu");
                    menu3.printMenu();

                    choice1 = menu3.getChoice();

                    if (choice1 == 1) {
                        try {
                            r.findExpiredProducts();
                        } catch (ParseException ex) {
                            
                        }
                    }
                    if (choice1 == 2) {
                        try {
                            r.findSellingProducts();
                        } catch (ParseException ex) {
                        }
                    }
                    if (choice1 == 3) {
                        r.findRunningOutProducts();
                    }
                    if (choice1 == 4) {
                    String ID;
                do {
                    ID = ulti.getString("Enter product's Id (format: Pxxxx) : ", 1, 0, 100);
                    //  Check trùng và định dạng cho mỗi ID
                    if (!pro.checkDuplicate(ID, 1)) {
                        System.out.println("No Product Found !!!");
                    }
                    if (!pro.checkProductID(ID)) {
                        System.out.println("product's ID phai co dang Pxxxx (0 <= x < 10) !");
                    }
                } while (!pro.checkDuplicate(ID, 1) || !pro.checkProductID(ID));  //ID phải là duy nhất và đúng định dạng 
                    w.displayAllsReceipt(ID);
                    }

                } while (choice1 != 5);
            }
                 if (choice==4) {
                    pro.printSave();
            }
        } while (choice != 5);

    }

}

