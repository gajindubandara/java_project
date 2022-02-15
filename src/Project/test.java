package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        addProfile();

    }





    //Function to add new customers
    private static boolean addProfile() {

        String Name, Address, stringPno,stringNo = " ";
        int No = 0;
        long Pno=0;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Name: (Firstname & Lastname)");
            Name = sc.nextLine();
            boolean intCheckPno = false;
            while (intCheckPno != true) {
                try {
                    System.out.println("Enter Contact Number:");
                    stringPno = sc.nextLine();
                    Pno = Long.parseLong(stringPno);
                    intCheckPno = true;
                } catch (NumberFormatException e) {
                    System.out.println("Contact Number Must be a Numeric Value");
                }
            }
            System.out.println("Enter Address: ( NOTE - Don't use commas for the address ) ");
            Address = sc.nextLine();

            boolean intCheckNo = false;
            while (intCheckNo != true) {
                try {
                    System.out.println("Enter Account Number:");
                    stringNo = sc.nextLine();
                    No = Integer.parseInt(stringNo);
                    intCheckNo = true;
                } catch (NumberFormatException e) {
                    System.out.println("Account Number Must be a Numeric Value");
                }
            }

            if (intCheckNo ==true){
                File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
                Scanner scfile = new Scanner(myObj);
                boolean accCheck = false;
                String line = "";
                while (scfile.hasNextLine()) {
                    line = scfile.nextLine();
                    String[] arr = line.split(",");
                    if (arr[0].equals(stringNo)) {
                        accCheck = true;

                    }
                }
                if (accCheck == false) {
                    try {
                        FileWriter fw = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt", true);
                        fw.write(No + ",");
                        fw.write(Name + ",");
                        fw.write(Address + ",");
                        fw.write(Pno + "\n");
                        fw.close();
                        System.out.println("Customer added successfully!");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.err.println("File Write denied");
                        return false;
                    }
                } else {
                    System.out.println("There is an existing account for this account number.Please check again!");
                }
            }
        }catch (InputMismatchException | FileNotFoundException e) {
            System.out.println("Invalid data entry");
            return false;
        }

        return true;
    }
}
