package Project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

//        System.out.println("Enter the User ID:");
//        String userID = sc.next();
//        System.out.println("Enter the Password:");
//        String inputPassword = sc.next();
//        String filePassword = readCredentials(userID);

//        if(inputPassword!=null && inputPassword.equals(filePassword)){
            System.out.println("logged In!!");
            String ans="yes";
            while(ans.equals("yes")) {
                System.out.println("Choose the Option");
                System.out.println("1-Record a new user");
                System.out.println("2-Display All Users");
                System.out.println("3-Add units");
                System.out.println("4-Get bill");
                System.out.println("5-Find A User");
                System.out.println("6-Delete A User");
                System.out.println("7-Update Bill");
                System.out.println("8-Update Profile");
                System.out.println("9-Delete Bill");
                System.out.println("10-Help");


                System.out.println("Enter your option");
                int option=sc.nextInt();
                switch(option){
                    case 1:
                        addProfile();
                        break;
                    case 2:
                        readFile();
                        break;
                    case 3:
                        addUnits();
                        break;
                    case 4:
                        getBill();
                        break;
                    case 5:
                        findUser();
                        break;
                    case 6:
                        deleteUser();
                        break;
                    case 7:
                        updateBill();
                        break;
//                    case 8:
//                        updateProfile();
//                        break;
                    case 9:
                        deleteBill();
                        break;
                    case 10:
                        help();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
                System.out.println("Do you want to continue?(yes/no)");
                ans=sc.next();
            }
//        }
//        else {
//            System.out.println("Invalid User ID or Password");
//        }
    }

    public static String readCredentials(String user){
        String password = "";
        try{
            File userFile = new File("/home/gaji/projects/Degree/pop/src/Project/login.txt");
            Scanner sc = new Scanner(userFile);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr= line.split(",");
                if(arr[0].equals(user)) {
                    password = arr[1];
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println(ex);
        }
        return password;
    }

    private static boolean addProfile() {
        Scanner sc=new Scanner(System.in);
        int No=0;
        String Name="";
        String Address="";
        String Address1="";
        String Address2="";
        int Pno=0;
        try {
            System.out.println("Enter Account Number:");
            No=sc.nextInt();

            System.out.println("Enter Name: (Firstname & Secondname)");
            Name=sc.next();
            Name+=sc.nextLine();

            System.out.println("Enter Phone Number:");
            Pno=sc.nextInt();


            System.out.println("Enter Address: ");
            System.out.println("No:");
            System.out.println("Street:");
            System.out.println("city:");
            Address=sc.next();
            Address1=sc.next();
            Address2=sc.next();


        }catch(InputMismatchException e) {
            System.err.println("Invlaid data entry");
            return false;
        }

        try {
            FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/profiles.txt",true);
            writer.write(No+",");
            writer.write(Name+",");
            writer.write(Address+" ");
            writer.write(Address1+" ");
            writer.write(Address2+",");
            writer.write(Pno+"\n");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("File Write denied");
            return false;
        }

        return true;
    }

    private static void readFile() {
        File myObj = new File("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
        try {
            Scanner sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                String line=sc.nextLine();
//                System.out.println(line);
//                line = sc.nextLine();
                String[] arr = line.split(",");
                System.out.println("Account Number - " + arr[0]);
                System.out.println("Account Owners Name - " + arr[1]);
                System.out.println("Address - " + arr[2]);
                System.out.println("Contact Number " + arr[3]);
                System.out.println(" ");
//                break;


            }
            sc.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean addUnits() {
        Scanner sc=new Scanner(System.in);
        String Month="";
        int No=0;
        int Units=0;
        try {
            System.out.println("Enter The Month (ex- January)");
            Month=sc.next();

            System.out.println("Enter Account Number:");
            No=sc.nextInt();

            System.out.println("Enter Burned Units:");
            Units=sc.nextInt();

        }catch(InputMismatchException e) {
            System.err.println("Invlaid data entry");
            return false;
        }

        try {
            FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt",true);
            int Price = Units * 100;
            writer.write(Month+",");
            writer.write(No+",");
            writer.write(Units+",");
            writer.write(Price+"\n");


            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("File Write denied");
            return false;
        }

        return true;
    }

    private static void getBill() {
        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
        try {
            Scanner sc=new Scanner(myObj);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Enter Account Number:");
            String AcNo=sc1.next();
            System.out.println("Enter The Month:");
            String Month=sc1.next();
            String line="";

            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] arr=line.split(",");
                if(arr[1].equals(AcNo) && arr[0].equals(Month) ) {
                    System.out.println(" ");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(" ");
                    System.out.println("Bill For The Month Of " + arr[0]);
                    System.out.println("Account Number - " + arr[1]);
                    System.out.println("No of Units Consumed " + arr[2]+ " (1 Unit = Rs.100.00/-)");
                    System.out.println("Total Amount Due Rs. " + arr[3]+".00/-");
                    System.out.println(" ");
                    System.out.println("----------------------------------------------------------------------");
                    break;
                }

            }
            sc.close();

        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void findUser() {
        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
        try {
            Scanner sc=new Scanner(myObj);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Enter Account Number:");
            String AcNo=sc1.next();
            String line="";
            boolean user=false;

            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] arr=line.split(",");
                if(arr[0].equals(AcNo)) {
                    System.out.println("Account Number - " + arr[0]);
                    System.out.println("Account Owners Name - " + arr[1]);
                    System.out.println("Address - " + arr[2]);
                    System.out.println("Contact Number " + arr[3]);
                    System.out.println(" ");
                    user=true;
                }
            }
            if (user==false){
                System.out.println("Invalid User Account Number");
            }
            sc.close();

        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void deleteUser() {
        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
        try {
            Scanner sc=new Scanner(myObj);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Enter The Account No:");
            String AcNo=sc1.next();
            boolean flag=false;
            String line="";
            ArrayList<String> lines=new ArrayList<String>();
            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] arr=line.split(",");
                if(arr[0].equals(AcNo)) {
                    flag=true;
                }else {
                    lines.add(line);
                }
            }

            if(flag==false) {
                System.out.println("The User is not found");
            }else {
                try {
                    FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
                    for(String rec:lines) {
                        writer.write(rec+"\n");

                    }
                    System.out.println("Record Removed!!!");
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("File Write denied");
                }

            }
            sc.close();

        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void deleteBill() {
        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
        try {
            Scanner sc=new Scanner(myObj);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Enter The Account No:");
            String AcNo=sc1.next();
            System.out.println("Enter Month:(ex-January)");
            String Month=sc1.next();
            boolean flag=false;
            String line="";
            ArrayList<String> lines=new ArrayList<String>();
            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] arr=line.split(",");
                if(arr[1].equals(AcNo) && arr[0].equals(Month)) {
                    flag=true;
                }else {
                    lines.add(line);
                }
            }

            if(flag==false) {
                System.out.println("Record not found");
            }else {
                try {
                    FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
                    for(String rec:lines) {
                        writer.write(rec+"\n");

                    }
                    System.out.println("Record Removed!!!");
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("File Write denied");
                }

            }
            sc.close();

        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void updateBill() {
        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
        try {
            Scanner sc=new Scanner(myObj);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Enter User Account No:");
//            int AcNo=sc1.nextInt();
            String AcNo=sc1.next();
            System.out.println("Enter the Month:");
            String Month=sc1.next();
            System.out.println("Enter the new Units:");
            int Units=sc1.nextInt();
            boolean flag=false;
            String line="";
            ArrayList<String> lines=new ArrayList<String>();
            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] arr=line.split(",");
                if(arr[0].equals(Month) && arr[1].equals(AcNo)) {
                    flag=true;
                }else {
                    lines.add(line);
                }
            }

            if(flag==false) {
                System.out.println("The Bill Information Not Found");
            }else {
                int Price= Units*100;
                String line1=Month+","+ AcNo +","+Units+","+Price;
                lines.add(line1);
                try {
                    FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
                    for(String rec:lines) {
                        writer.write(rec+"\n");
                    }
                    System.out.println("Bill Updated Successfully!!!");
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("File Write denied");
                }

            }
            sc.close();

        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    private static void updateProfile() {
//        File myObj=new File("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
//        try {
//            Scanner sc=new Scanner(myObj);
//            Scanner sc1=new Scanner(System.in);
//            System.out.println("Enter User Account No:");
//            String AcNo=sc1.next();
//
//            String line="";
//            boolean user=false;
//
//            while(sc.hasNextLine()) {
//                line=sc.nextLine();
//                String[] arr=line.split(",");
//                if(arr[0].equals(AcNo)) {
//                    System.out.println("Account Number - " + arr[0]);
//                    System.out.println("Account Owners Name - " + arr[1]);
//                    System.out.println("Address - " + arr[2]);
//                    System.out.println("Contact Number " + arr[3]);
//                    System.out.println(" ");
//                    user=true;
//                }
//            }
//            if (user==false){
//                System.out.println("Invalid User Account Number");
//            }
//
//            System.out.println("Do You Need To Update The Above Profile Information ?(yes/no)");
//            String ans=sc1.next();
//
//            if (ans.equals("yes")){
//                System.out.println("hello");
//                String Name="";
//                String Address="";
//                String Address1="";
//                String Address2="";
//                int Pno=0;
//
//
//                System.out.println("Enter Name: (Firstname & Secondname)");
//                Name=sc.next();
//                Name+=sc.nextLine();
//
//                System.out.println("Enter Phone Number:");
//                Pno=sc.nextInt();
//
//
//                System.out.println("Enter Address: ");
//                System.out.println("No:");
//                System.out.println("Street:");
//                System.out.println("city:");
//                Address=sc.next();
//                Address1=sc.next();
//                Address2=sc.next();
//
//                boolean flag=false;
//                ArrayList<String> lines=new ArrayList<String>();
//                while(sc.hasNextLine()) {
//                    line=sc.nextLine();
//                    String[] arr=line.split(",");
//                    if(arr[0].equals(AcNo)) {
//                        flag=true;
//                    }else {
//                        lines.add(line);
//                    }
//                }
//                if(flag==false) {
//                    System.out.println("The Bill Information Not Found");
//                }else {
//                    String line1=AcNo+","+ Name +","+ Address + Address1 + Address2 +","+Pno;
//                    lines.add(line1);
//                    try {
//                        FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
//                        for(String rec:lines) {
//                            writer.write(rec+"\n");
//                        }
//                        System.out.println("User Updated Successfully!!!");
//                        writer.close();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        System.err.println("File Write denied");
//                    }
//
//                }
//                sc.close();
//
////                System.out.println("Choose the Option");
////                System.out.println("1-Update User Name");
////                System.out.println("2-Update User Address");
////                System.out.println("3-Update User Contact No");
////                System.out.println("Enter your option");
////                int option=sc.nextInt();
////                switch(option){
////                    case 1:
////                        System.out.println("Enter the Name:");
////                        String Name=sc1.next();
////                        System.out.println(Name);
////                        break;
////                    case 2:
////                        System.out.println("Enter the Address:");
////                        String Address=sc1.next();
////                        System.out.println(Address);
////                        break;
////                    case 3:
////                        System.out.println("Enter the Contact No:");
////                        String Contact =sc1.next();
////                        System.out.println(Contact);
////                    default:
////                        System.out.println("Invalid option");
////                }
//            }
//
//
//
//
//
//
//
//
////            System.out.println("Enter the Month:");
////            String Month=sc1.next();
////            System.out.println("Enter the new Units:");
////            int Units=sc1.nextInt();
////            boolean flag=false;
////
////            ArrayList<String> lines=new ArrayList<String>();
////            while(sc.hasNextLine()) {
////                line=sc.nextLine();
////                String[] arr=line.split(",");
////                if(arr[0].equals(Month) && arr[1].equals(AcNo)) {
////                    flag=true;
////                }else {
////                    lines.add(line);
////                }
////            }
////
////            if(flag==false) {
////                System.out.println("The Bill Information Not Found");
////            }else {
////                int Price= Units*100;
////                String line1=Month+","+ AcNo +","+Units+","+Price;
////                lines.add(line1);
////                try {
////                    FileWriter writer=new FileWriter("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
////                    for(String rec:lines) {
////                        writer.write(rec+"\n");
////                    }
////                    System.out.println("Bill Updated Successfully!!!");
////                    writer.close();
////                } catch (IOException e) {
////                    // TODO Auto-generated catch block
////                    System.err.println("File Write denied");
////                }
////
////            }
////            sc.close();
//
//        } catch(FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }



    private static void help() {
        File myObj = new File("/home/gaji/projects/Degree/pop/src/Project/help.txt");
        try {
            Scanner sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                String line=sc.nextLine();
                System.out.println(line);



            }
            sc.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }















}
