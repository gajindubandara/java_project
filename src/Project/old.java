package FileExam;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class old {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the User ID:"); 
		String userID=sc.next();
		System.out.println("Enter the Password:");
		String password=sc.next();
		
		String pwd=checkLogin(userID); 
		if((pwd!=null) && pwd.equals(password)) {
			String ans="yes";
		  while(ans.equals("yes")) {
			System.out.println("Choose the Option");
			System.out.println("1-Record Student Marks");
			System.out.println("2-Display all Students Marks");
			System.out.println("3-Search Student Record");
			System.out.println("4-Delete Student Record");
			
			
			System.out.println("Enter your option");
			int option=sc.nextInt();
			switch(option){
			case 1:
				writeFile();
				break;
			case 2:
				readFile();
				break;
			case 3:
				getMarks();
				break;
			case 4:
				delete();
				break;
		     default:
		    	 System.out.println("Invalid option");
			}
			System.out.println("Do you want to continue?(yes/no)");
			ans=sc.next();
		  }
		}else {
			System.out.println("Invalid User ID or Password");
		}	
		
	}
	
	
	private static boolean writeFile() {
		Scanner sc=new Scanner(System.in);
		String stdName="";
		int marks=0;
		try {
		   System.out.println("Enter Student Name:");
		   stdName=sc.next();
		
		   System.out.println("Enter Student Marks:");
		   marks=sc.nextInt();
		   if((marks<0) || (marks>100)) {
			   System.err.println("The marks must be between 0-100");
			   return false;
		   }
		}catch(InputMismatchException e) {
			System.err.println("Invlaid data entry");
			return false;
		}
		
		try {
			FileWriter writer=new FileWriter("C:/bsc02/stdFile.txt",true);
			writer.write(stdName+"\t");	
			writer.write(marks+"\n");
			writer.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("File Write denied");
			return false;
		}
		
		return true;
	}
	
	private static void readFile() {
		File myObj=new File("C:/bsc02/stdFile.txt");
		try {
			Scanner sc=new Scanner(myObj);
			
			while(sc.hasNextLine()) {
				String line=sc.nextLine();
				System.out.println(line);
			}
			sc.close();
			
		} catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static void getMarks() {
		File myObj=new File("C:/bsc02/stdFile.txt");
		try {
			Scanner sc=new Scanner(myObj);
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter the student name:");
			String name=sc1.next();
			boolean flag=false;
			String line="";
			while(sc.hasNextLine()) {
				line=sc.nextLine();
				String[] arr=line.split("\t");
				if(arr[0].equals(name)) {					
					flag=true;
					break;
				}
				
			}
			
			if(flag==false) {
				System.out.println("The Student is not found");
			}else {
				System.out.println(line);
			}
			sc.close();
			
		} catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	private static void delete() {
		File myObj=new File("C:/bsc02/stdFile.txt");
		try {
			Scanner sc=new Scanner(myObj);
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter the student name:");
			String name=sc1.next();
			boolean flag=false;
			String line="";
			ArrayList<String> lines=new ArrayList<String>();
			while(sc.hasNextLine()) {
				line=sc.nextLine();
				String[] arr=line.split("\t");
				if(arr[0].equals(name)) {					
					flag=true;					
				}else {
					lines.add(line);
				}				
			}
			
			if(flag==false) {
				System.out.println("The Student is not found");
			}else {
				try {
					FileWriter writer=new FileWriter("C:/bsc02/stdFile.txt");
					for(String rec:lines) {
						writer.write(rec+"\n");							
					}					
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
	
	private static String checkLogin(String userID) {
		File myObj=new File("home/gaji/projects/Degree/pop/src/Project/login.txt");
		try {
			Scanner sc=new Scanner(myObj);
			
			while(sc.hasNextLine()) {
				String line=sc.nextLine();				
				String[] arr=line.split("\t");
				if(arr[0].equals(userID)) {
					return arr[1];
				}
			}
			sc.close();
			return null;
		} catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		
	}

}
