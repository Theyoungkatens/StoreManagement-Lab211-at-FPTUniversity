/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.regexp.joni.constants.SyntaxProperties;


/**
 *
 * each patient is cared by two nurse and one nurse takes care of maximum 2
 * patients. PATIENT LIST NURSE , SIZE = 2 HASH MAP PATIENTNURSE KEY
 * PATIENTNAME, VALUE : NURSE NAME , NURSNEM . VALUE.SIZE = 2 NURSE CARE <= 2, 1, 0
 * patient HASHMAP PATIENT , GET LIST NURSE , SIZE 2
 *                                LIST NURSE ADD NURSE
 *                           -> HASHMAP NURSE , NURSE HASHMAP NURSE KEY, VALUE
 *
 */
public class Utilities { // Các điều kiện ràng buộc dữ liệu để ở đây , ví dụ số tự nhiên mà nhập vào dạng chữ thì bắt nhập lại

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String sms, int min, int max) { // Check số int
        int n = 0;

        while (true) {
            try {
                System.out.print(sms+"  ");
                n = Integer.parseInt(sc.nextLine());
                if (n >= min && n < max) {
                    return n;
                }

            } catch (NumberFormatException e) {
                System.out.println();
            }
        }

        //   return n;
    }

    public static double getDouble(String prompt, double min, double max) {  // Check double , dùng cho nhập tiền $
        double number = 0;
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt);
                number = Double.parseDouble(sc.nextLine());

                if (number > min && number < max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!isValid);

        return number;
    }

    public static String getString(String sms, int opt, int min, int max) {    //Check chứ, ko cho bỏ trống 

        // opt=1 -----> Enter String in min & max
        //opt =2 -----> Enter String opt1 or opt2
        String s = "";
        while (true) {
            try {
                System.out.println(sms);
                s = sc.nextLine();

                if (opt == 1) {
                    if (!s.isEmpty() && s.length() > min && s.length() < max) {
                        return s;
                    }
                }
                if (opt == 2) {
                   

                    if (s.isEmpty()) {
                        System.out.println("Please enter a string of at least 1 character!!!");
                    }
                }
            } catch (Exception e) {
                System.out.println("PLease enter the valid information!");
            }

        }
    }

    public String getPhone(String msg) {     // Check phone number nếu đề yêu cầu
        String s = "";
        while (true) {
            try {
                System.out.println(msg);
                s = sc.nextLine();
                if (!s.isEmpty() && s.matches("^[0-9]{9,11}$")) {

                    return s;

                } else {
                    System.out.println("Please enter an valid phone number");
                }
            } catch (Exception e) {
                System.out.println("Please enter an valid phone number");
            }
        }
    }
   
            
    public static String inputDate(String msg, int opt) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate now = LocalDate.now();
    LocalDate check = null;
    String input = "";
    
    do {
        try {
            System.out.println(msg);
           input = sc.nextLine().trim();
            
            if (input.isEmpty()||input.equals("")) {
                return null;
            }
            
            check = LocalDate.parse(input, formatter);
            
            if (opt==1) {
                 if (check.isAfter(now)) {
                System.out.println("Please input a date before the current date!");
                continue;
            }
            }
            
            return check.format(formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please input a valid date (yyyy/MM/dd).");
        }
    } while (true);
}

   
}
