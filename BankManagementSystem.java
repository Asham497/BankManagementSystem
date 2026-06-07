package BankManagementSystem;

import java.sql.*;
import java.util.*;

public class BankManagementSystem
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        String url="jdbc:mysql://localhost:3306/BankManagementSystem";
        String username="root";
        String password="system123";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,username,password);

            while(true)
            {
                System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
                System.out.println("1. Create Account");
                System.out.println("2. Check Balance");
                System.out.println("3. Deposit Amount");
                System.out.println("4. Withdraw Amount");
                System.out.println("5. Money Transfer");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice=sc.nextInt();

                switch(choice)
                {
                    case 1:
                        Bankoperationss.Accountcreation(con);
                        break;

                    case 2:
                    	 Bankoperationss.checkbalance(con);
                        break;

                    case 3:
                    	 Bankoperationss.depositamount(con);
                        break;

                    case 4:
                    	 Bankoperationss.withdrawamount(con);
                        break;

                    case 5:
                    	 Bankoperationss.MoneyTransfer(con);
                        break;

                    case 6:
                        System.out.println("Thank You");
                        con.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}