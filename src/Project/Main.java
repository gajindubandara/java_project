package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Username:");
        String userID = scanner.next();
        System.out.println("Enter the Password:");
        String inputPassword = scanner.next();
        String filePassword = readCredentials(userID);

        if (inputPassword != null && inputPassword.equals(filePassword)) {
            System.out.println("logged In!!");
            String ans = "yes";
            while (ans.equals("yes")) {
                System.out.println("Choose the Option");
                System.out.println("1-Register A New Customer");
                System.out.println("2-Display All Customers");
                System.out.println("3-Find A Customer");
                System.out.println("4-Update Customer Profile");
                System.out.println("5-Delete A Customer");
                System.out.println("6-Create A Bill Record For A New Month ");
                System.out.println("7-Print A bill");
                System.out.println("8-Update Bill Information");
                System.out.println("9-Delete A Bill Record");
                System.out.println("10-Help");
                System.out.println(" ");
                System.out.println("Enter your option");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        addProfile();
                        break;
                    case 2:
                        readData();
                        break;
                    case 3:
                        findUser();
                        break;
                    case 4:
                        updateProfile();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 6:
                        addUnits();
                        break;
                    case 7:
                        printBill();
                        break;
                    case 8:
                        updateBill();
                        break;
                    case 9:
                        deleteBill();
                        break;
                    case 10:
                        help();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
                System.out.println(" ");
                System.out.println("*************************************");
                System.out.println("Enter 'yes' to go to the Main Menu");
                System.out.println("Enter 'no'  to Exit");
                System.out.println("*************************************");
                System.out.println(" ");

                ans = scanner.next();

            }
        } else {
            System.out.println("Invalid Username or Password");
        }
//        scanner.close();
    }

    //Function to check the login credentials
    public static String readCredentials(String user) {
        String password = "";
        try {
            File userFile = new File("/home/gaji/projects/Degree/java-project/src/Project/login.txt");
            Scanner readData = new Scanner(userFile);
            while (readData.hasNextLine()) {
                String line = readData.nextLine();
                String[] arr = line.split(",");
                if (arr[0].equals(user)) {
                    password = arr[1];
                }
            }
            readData.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return password;
    }


    //Function to add new customers
    private static boolean addProfile() {

        String Name, Address, stringPno, stringNo = " ";
        int No = 0;
        long Pno = 0;
        Scanner profileInfo = new Scanner(System.in);

        try {
            System.out.println("Enter Name: (Firstname & Lastname)");
            Name = profileInfo.nextLine();

            //Check if the entered contact number is in numeric format
            boolean intCheckPno = false;
            while (intCheckPno != true) {
                try {
                    System.out.println("Enter Contact Number:");
                    stringPno = profileInfo.nextLine();
                    Pno = Long.parseLong(stringPno);
                    intCheckPno = true;
                } catch (NumberFormatException e) {
                    System.out.println("Contact Number Must be a Numeric Value");
                }
            }
            System.out.println("Enter Address: ( NOTE - Don't use commas for the address ) ");
            Address = profileInfo.nextLine();

            //Check if the entered account number is in numeric format
            boolean intCheckNo = false;
            while (intCheckNo != true) {
                try {
                    System.out.println("Enter Account Number:");
                    stringNo = profileInfo.nextLine();
                    No = Integer.parseInt(stringNo);
                    intCheckNo = true;
                } catch (NumberFormatException e) {
                    System.out.println("Account Number Must be a Numeric Value");
                }
            }

            if (intCheckNo == true) {
                File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
                Scanner checkAcc = new Scanner(myObj);
                boolean accCheck = false;
                String line = "";
                while (checkAcc.hasNextLine()) {
                    line = checkAcc.nextLine();
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
                checkAcc.close();
            }
        } catch (InputMismatchException | FileNotFoundException e) {
            System.out.println("Invalid data entry");
            return false;
        }
        return true;
    }

    //Function to read all customers and print them
    private static void readData() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        try {
            Scanner readFile = new Scanner(myObj);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] arr = line.split(",");
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println("Account Number - " + arr[0]);
                System.out.println("Account Owners Name - " + arr[1]);
                System.out.println("Address - " + arr[2]);
                System.out.println("Contact Number " + arr[3]);
                System.out.println(" ");


            }
            readFile.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Function to find a customer profile
    private static void findUser() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        try {
            Scanner readProfiles = new Scanner(myObj);
            Scanner userInfo = new Scanner(System.in);
            System.out.println("Enter Account Number:");
            String AcNo = userInfo.next();
            String line = "";
            boolean user = false;

            while (readProfiles.hasNextLine()) {
                line = readProfiles.nextLine();
                String[] arr = line.split(",");
                if (arr[0].equals(AcNo)) {
                    System.out.println(" ");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(" ");
                    System.out.println("Account Number - " + arr[0]);
                    System.out.println("Account Owners Name - " + arr[1]);
                    System.out.println("Address - " + arr[2]);
                    System.out.println("Contact Number - " + arr[3]);
                    System.out.println(" ");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(" ");
                    user = true;
                }
            }
            if (user == false) {
                System.out.println("Invalid User Account Number");
            }
            readProfiles.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Function to update a customer profile
    private static void updateProfile() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        try {
            Scanner user = new Scanner(myObj);
            Scanner userInfo = new Scanner(System.in);
            String Name, stringPno, Address, No;
            long Pno = 0;
            System.out.println("Enter Account Number:");
            No = userInfo.nextLine();

            //Check if the account exist
            boolean account = false;
            String line = "";
            ArrayList<String> lines = new ArrayList<String>();
            while (user.hasNextLine()) {
                line = user.nextLine();
                String[] arr = line.split(",");
                if (arr[0].equals(No)) {
                    account = true;
                } else {
                    lines.add(line);
                }
            }

            if (account == false) {
                System.out.println("The Customer Account Number is Incorrect Please Check Again!");
            } else {
                System.out.println("Enter Name: (Firstname & Lastname)");
                Name = userInfo.nextLine();
                boolean intCheckPno = false;
                while (intCheckPno != true) {
                    try {
                        System.out.println("Enter Contact Number:");
                        stringPno = userInfo.nextLine();
                        Pno = Long.parseLong(stringPno);
                        intCheckPno = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Contact Number Must be a Numeric Value");
                    }
                }
                System.out.println("Enter Address: ( NOTE - Don't use commas for the address ) ");
                Address = userInfo.nextLine();
                String line1 = No + "," + Name + "," + Address + "," + Pno;
                lines.add(line1);
                try {
                    FileWriter writer = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
                    for (String rec : lines) {
                        writer.write(rec + "\n");
                    }
                    System.out.println("Customer Information Updated Successfully!!!");
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("File Write denied");
                }

            }
            user.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Function to delete a customer
    private static void deleteUser() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        try {
            Scanner user = new Scanner(myObj);
            Scanner accNo = new Scanner(System.in);
            System.out.println("Enter The Account No:");
            String AcNo = accNo.next();

           //Check if the account exist
            boolean account = false;
            String line = "";
            ArrayList<String> lines = new ArrayList<String>();
            while (user.hasNextLine()) {
                line = user.nextLine();
                String[] arr = line.split(",");
                if (arr[0].equals(AcNo)) {
                    account = true;
                } else {
                    lines.add(line);
                }
            }

            if (account == false) {
                System.out.println("The Customer Account Number is Incorrect Please Check Again!");
            } else {
                try {
                    FileWriter writer = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
                    for (String rec : lines) {
                        writer.write(rec + "\n");

                    }
                    System.out.println("Customer Removed!!!");
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("File Write denied");
                }

            }
            user.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //function to add records for a month
    private static void addUnits() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        try {

            Scanner user = new Scanner(myObj);
            Scanner billData = new Scanner(System.in);
            String Month, No, stringUnits;
            int Units = 0;
            System.out.println("Enter Account Number:");
            No = billData.nextLine();
            boolean monthCheck = false;
            boolean accCheck = false;
            String line = "";

           //Check if the account exist
            while (user.hasNextLine()) {
                line = user.nextLine();
                String[] arr = line.split(",");
                if (arr[0].equals(No)) {
                    accCheck = true;

                }
            }
            user.close();
            if (accCheck == false) {
                System.out.println("The Customer Account Number is Incorrect Please Check Again!");
            } else {

                File checkData = new File("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");

                System.out.println("Enter The Month & Year  (ex- January/2022)");
                Month = billData.nextLine();
                try {
                    Scanner billInfo = new Scanner(checkData);

                    //Check if there is an existing record for this month
                    while (billInfo.hasNextLine()) {
                        line = billInfo.nextLine();
                        String[] arrCheck = line.split(",");
                        if (arrCheck[1].equals(No) && arrCheck[0].equals(Month)) {
                            System.out.println("There is an existing record for this month. Please Check Again! ");
                            monthCheck = false;
                            break;

                        } else {
                            monthCheck = true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (monthCheck != false) {
                    boolean intCheck = false;

                    while (intCheck != true && monthCheck != false) {
                        try {

                            System.out.println("Enter Burned Units:");
                            stringUnits = billData.nextLine();
                            Units = Integer.parseInt(stringUnits);
                            intCheck = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Enter A Correct Numeric Value");
                        }
                    }
                    try {
                        DateTimeFormatter date = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                        LocalDate localDate = LocalDate.now();
                        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime localTime = LocalTime.now();

                        FileWriter writer = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt", true);
                        int Price = Units * 100;
                        writer.write(Month + ",");
                        writer.write(No + ",");
                        writer.write(Units + ",");
                        writer.write(Price + ",");
                        writer.write(date.format(localDate) + ",");
                        writer.write(time.format(localTime) + "\n");
                        writer.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.err.println("File Write denied");

                    }
                    System.out.println("Record added successfully!!!");
                }


            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //function to Print the bill
    private static void printBill() {

        File checkData = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        Scanner userInfo = new Scanner(System.in);
        System.out.println("Enter The Account Number");
        String AcNo = userInfo.nextLine();
        boolean accCheck = false;
        String name = "";

        try {
            String lineF;
            Scanner checkAcc = new Scanner(checkData);

            //Check if the account exist
            while (checkAcc.hasNextLine()) {
                lineF = checkAcc.nextLine();
                String[] arrCheck = lineF.split(",");
                if (arrCheck[0].equals(AcNo)) {
                    name = arrCheck[1];
                    accCheck = true;
                    break;


                } else {
                    accCheck = false;


                }
            }
            checkAcc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (accCheck == true) {
            File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");
            try {

                Scanner sc = new Scanner(myObj);
                userInfo = new Scanner(System.in);
                String line = "";


                System.out.println("Enter The Month & Year  (ex- January/2022)");
                String Month = userInfo.next();
                boolean noData = false;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] arr = line.split(",");

                    if (arr[0].equals(Month)) {
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("Bill For " + arr[0]);
                        System.out.println("Account Number - " + arr[1]);
                        System.out.println("Customer Name - " + name);
                        System.out.println("No of Units Consumed " + arr[2] + " (1 Unit = Rs.100.00/-)");
                        System.out.println("Total Amount Due Rs. " + arr[3] + ".00/-");
                        System.out.println("Date - " + arr[4]);
                        System.out.println("Time - " + arr[5]);
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        noData = true;
                    }

                }
                if (noData == false) {
                    System.out.println("Haven't created the bill for " + " " + Month);
                    System.out.println("First create the bill!");

                }
                sc.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (accCheck == false) {
            System.out.println("Invalid account number! ");
        }
    }

    //function to Print the bill
    private static void updateBill() {

        File checkData = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        Scanner userInfo = new Scanner(System.in);
        System.out.println("Enter The Account Number");
        String AcNo = userInfo.nextLine();
        boolean accCheck = false;

        try {
            String lineF;
            Scanner checkAcc = new Scanner(checkData);

            //Check if the account exist
            while (checkAcc.hasNextLine()) {
                lineF = checkAcc.nextLine();
                String[] arrCheck = lineF.split(",");
                if (arrCheck[0].equals(AcNo)) {
                    accCheck = true;
                    break;


                } else {
                    accCheck = false;


                }
            }
            checkAcc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (accCheck == true) {
            File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");
            try {

                Scanner billInfo = new Scanner(myObj);
                userInfo = new Scanner(System.in);
                String line = "";


                System.out.println("Enter The Month & Year  (ex- January/2022)");
                String Month = userInfo.next();
                boolean record = false;
                ArrayList<String> lines = new ArrayList<String>();

                //Check if there is a record for this month
                while (billInfo.hasNextLine()) {
                    line = billInfo.nextLine();
                    String[] arr = line.split(",");

                    if (arr[0].equals(Month)) {

                        record = true;
                    } else {
                        lines.add(line);
                    }

                }
                if (record == false) {
                    System.out.println("No bill record for" + " " + Month);
                    System.out.println("Please check again!");

                } else if (record == true) {
                    boolean intCheck = false;
                    String stringUnits;
                    int Units = 0;

                    //Check if the entered units are in numeric format
                    while (intCheck != true) {
                        try {
                            Scanner scUnit = new Scanner(System.in);
                            System.out.println("Enter the new Units:");
                            stringUnits = scUnit.nextLine();
                            Units = Integer.parseInt(stringUnits);
                            intCheck = true;
                        } catch (NumberFormatException e) {

                            System.out.println("Enter A Correct Numeric Value");

                        }

                    }
                    int Price = Units * 100;
                    DateTimeFormatter date = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                    LocalDate localDate = LocalDate.now();
                    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime localTime = LocalTime.now();

                    String line1 = Month + "," + AcNo + "," + Units + "," + Price + "," + date.format(localDate) + "," + time.format(localTime);
                    lines.add(line1);
                    try {
                        FileWriter writer = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");
                        for (String rec : lines) {
                            writer.write(rec + "\n");
                        }
                        System.out.println("Bill Record Updated Successfully!!!");
                        writer.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.err.println("File Write denied");
                    }

                }
                billInfo.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (accCheck == false) {
            System.out.println("Invalid account number! ");
        }

    }

    //function to Print the bill
    private static void deleteBill() {

        File checkData = new File("/home/gaji/projects/Degree/java-project/src/Project/profiles.txt");
        Scanner accInfo = new Scanner(System.in);
        System.out.println("Enter The Account Number");
        String AcNo = accInfo.nextLine();
        boolean accCheck = false;

        try {
            String lineF;
            Scanner checkAcc = new Scanner(checkData);

            //Check if the account exist
            while (checkAcc.hasNextLine()) {
                lineF = checkAcc.nextLine();
                String[] arrCheck = lineF.split(",");
                if (arrCheck[0].equals(AcNo)) {
                    accCheck = true;
                    break;


                } else {
                    accCheck = false;


                }
            }
            checkAcc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (accCheck == true) {
            File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");
            try {

                Scanner checkBill = new Scanner(myObj);
                accInfo = new Scanner(System.in);
                String line = "";
                System.out.println("Enter The Month & Year  (ex- January/2022)");
                String Month = accInfo.next();
                boolean record = false;
                ArrayList<String> lines = new ArrayList<String>();

                //Check if there is a record for this month
                while (checkBill.hasNextLine()) {
                    line = checkBill.nextLine();
                    String[] arr = line.split(",");

                    if (arr[0].equals(Month)) {

                        record = true;
                    } else {
                        lines.add(line);
                    }

                }
                if (record == false) {
                    System.out.println("No bill record for" + " " + Month);
                    System.out.println("Please check again!");

                } else if (record == true) {
                    try {
                        FileWriter writer = new FileWriter("/home/gaji/projects/Degree/java-project/src/Project/bill_info.txt");
                        for (String rec : lines) {
                            writer.write(rec + "\n");

                        }
                        System.out.println("Record Removed!!!");
                        writer.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.err.println("File Write denied");
                    }
                }
                checkBill.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (accCheck == false) {
            System.out.println("Invalid account number! ");
        }

    }

    //Function to view the help page
    private static void help() {
        File myObj = new File("/home/gaji/projects/Degree/java-project/src/Project/help.txt");
        try {
            Scanner sc = new Scanner(myObj);
            //Read data and display
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
