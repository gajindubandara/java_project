package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        deleteBill();

    }

    //function to Print the bill
    private static void deleteBill() {

        File checkData = new File("/home/gaji/projects/Degree/pop/src/Project/profiles.txt");
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter The Account Number");
        String AcNo = sc1.nextLine();
        boolean accCheck = false;

        try {
            String lineF;
            Scanner sc2 = new Scanner(checkData);
            while (sc2.hasNextLine()) {
                lineF = sc2.nextLine();
                String[] arrCheck = lineF.split(",");
                if (arrCheck[0].equals(AcNo)) {
                    accCheck = true;
                    break;


                } else {
                    accCheck = false;


                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (accCheck == true) {
            File myObj = new File("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
            try {

                Scanner sc = new Scanner(myObj);
                sc1 = new Scanner(System.in);
                String line = "";


                System.out.println("Enter The Month:");
                String Month = sc1.next();
                boolean Data = false;
                ArrayList<String> lines = new ArrayList<String>();
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] arr = line.split(",");

                    if (arr[0].equals(Month)) {

                        Data = true;
                    } else {
                        lines.add(line);
                    }

                }
                if (Data == false) {
                    System.out.println("No bill record for the month of" + " " + Month);
                    System.out.println("Please check again!");

                } else if (Data == true) {
                    try {
                        FileWriter writer = new FileWriter("/home/gaji/projects/Degree/pop/src/Project/bill_info.txt");
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
                sc.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (accCheck == false) {
            System.out.println("Invalid account number! ");
        }

    }





}
