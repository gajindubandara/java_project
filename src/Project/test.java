package Project;

import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Enter your name");
        Scanner scan = new Scanner(System.in);
        String name= scan.nextLine();
        name+=scan.nextLine();
        scan.close();

        System.out.println("Enter your name"+name);

    }
}
