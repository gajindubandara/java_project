package Project;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class login {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the User ID:");
        String userID = sc.next();
        System.out.println("Enter the Password:");
        String inputPassword = sc.next();
        String filePassword = readCredentials(userID);

        if(inputPassword!=null && inputPassword.equals(filePassword)){
            System.out.println("logged In!!");
        }else {
            System.out.println("Invalid User ID or Password");
        }
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

//    private static boolean writeFile() {
//        Scanner sc=new Scanner(System.in);
//        String stdName="";
//        int marks=0;
//        try {
//            System.out.println("Enter Student Name:");
//            stdName=sc.next();
//
//            System.out.println("Enter Student Marks:");
//            marks=sc.nextInt();
//            if((marks<0) || (marks>100)) {
//                System.err.println("The marks must be between 0-100");
//                return false;
//            }
//        }catch(InputMismatchException e) {
//            System.err.println("Invlaid data entry");
//            return false;
//        }
//
//        try {
//            FileWriter writer=new FileWriter("C:/bsc02/stdFile.txt",true);
//            writer.write(stdName+"\t");
//            writer.write(marks+"\n");
//            writer.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            System.err.println("File Write denied");
//            return false;
//        }
//
//        return true;
//    }

//    private static void readFile() {
//        File myObj=new File("C:/bsc02/stdFile.txt");
//        try {
//            Scanner sc=new Scanner(myObj);
//
//            while(sc.hasNextLine()) {
//                String line=sc.nextLine();
//                System.out.println(line);
//            }
//            sc.close();
//
//        } catch(FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    private static void getMarks() {
//        File myObj=new File("C:/bsc02/stdFile.txt");
//        try {
//            Scanner sc=new Scanner(myObj);
//            Scanner sc1=new Scanner(System.in);
//            System.out.println("Enter the student name:");
//            String name=sc1.next();
//            boolean flag=false;
//            String line="";
//            while(sc.hasNextLine()) {
//                line=sc.nextLine();
//                String[] arr=line.split("\t");
//                if(arr[0].equals(name)) {
//                    flag=true;
//                    break;
//                }
//
//            }
//
//            if(flag==false) {
//                System.out.println("The Student is not found");
//            }else {
//                System.out.println(line);
//            }
//            sc.close();
//
//        } catch(FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }



//    private static void delete() {
//        File myObj=new File("C:/bsc02/stdFile.txt");
//        try {
//            Scanner sc=new Scanner(myObj);
//            Scanner sc1=new Scanner(System.in);
//            System.out.println("Enter the student name:");
//            String name=sc1.next();
//            boolean flag=false;
//            String line="";
//            ArrayList<String> lines=new ArrayList<String>();
//            while(sc.hasNextLine()) {
//                line=sc.nextLine();
//                String[] arr=line.split("\t");
//                if(arr[0].equals(name)) {
//                    flag=true;
//                }else {
//                    lines.add(line);
//                }
//            }
//
//            if(flag==false) {
//                System.out.println("The Student is not found");
//            }else {
//                try {
//                    FileWriter writer=new FileWriter("C:/bsc02/stdFile.txt");
//                    for(String rec:lines) {
//                        writer.write(rec+"\n");
//                    }
//                    writer.close();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    System.err.println("File Write denied");
//                }
//
//            }
//            sc.close();
//
//        } catch(FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }



}
