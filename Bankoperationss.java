package BankManagementSystem;
import java.sql.*;
import java.util.*;

public class Bankoperationss
{
    static Scanner sc=new Scanner(System.in);

    public static void Accountcreation(Connection con) throws Exception
    {
    	System.out.println("Enter a account number: ");
		long account_number=sc.nextLong();
		System.out.println("Enter a customer name: ");
		String name=sc.next();
		System.out.println("enter a pin number");
		int pin=sc.nextInt();
		String query="insert into Bank values(?,?,?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setLong(1,account_number);
		st.setString(2, name);
		st.setInt(3, pin);
		st.setDouble(4,0.0);
		int rowseffected=st.executeUpdate();
		if(rowseffected>0)
		{
			System.out.println("Sucēssfully account is created");
		}
		else
		{
			System.out.println("Iam sorry Unable to create a account");
		}
    }

    public static void checkbalance(Connection con) throws Exception
    {
    	System.out.println("enter a account number");
		 long account_number=sc.nextLong();
		 String query="select balance from bank where account_number=?";
		 PreparedStatement st=con.prepareStatement(query);
		 st.setLong(1,account_number);
		 ResultSet rs=st.executeQuery();
		 while(rs.next())
		 {
			 System.out.println("Balance "+rs.getDouble("balance"));
		 }
    }

    public static void depositamount(Connection con) throws Exception
    {
    	System.out.println("Enter a account number");
		long account_number=sc.nextLong();
		System.out.println("Enter a amount to be deposited: ");
		double amount=sc.nextDouble();
		String query="update bank set balance=balance+? where account_number=?";
		PreparedStatement st=con.prepareStatement(query);
		st.setDouble(1,amount);
		st.setLong(2,account_number);
		int count=st.executeUpdate();
		if(count>0)
		{
			System.out.println("amount to deposited sucessfully");
		}
		else
		{
			System.out.println("Your deposit is failed");
		}
    }

    public static void withdrawamount(Connection con) throws Exception
    {
    	System.out.println("enter a account number");
		long account_number=sc.nextLong();
		System.out.println("enter a amount to be with draw");
		double amount=sc.nextDouble();
		String amountquery="select balance from bank where account_number=?";
		PreparedStatement stmnt=con.prepareStatement(amountquery);
		stmnt.setDouble(1,account_number);
		ResultSet set=stmnt.executeQuery();
		if(set.next())
		{
			double currentbalance=set.getDouble("balance");
			if(currentbalance<amount)
			{
				System.out.println("insufficient funds");
			}
			else
			{
				String query="update bank set balance=balance-? where account_number=?";
				PreparedStatement st=con.prepareStatement(query);
				st.setDouble(1, amount);
				st.setLong(2, account_number);
				int count=st.executeUpdate();
				if(count>0)
				{
					System.out.println("Amount withdraw is sucessfull");
				}
				else
				{
					System.out.println("Account is not found");
				}
			}
		}	

    }

    public static void MoneyTransfer(Connection con) throws Exception
    {
    	  System.out.println("Enter your account number: ");
  	    long account_number = sc.nextLong();

  	    System.out.println("Enter your pin: ");
  	    int pin = sc.nextInt();

  	    System.out.println("Enter amount to transfer: ");
  	    double amount = sc.nextDouble();

  	    System.out.println("Enter receiver account number: ");
  	    long account_number_receiver = sc.nextLong();

  	    String query = "SELECT balance FROM Bank WHERE account_number=? AND pin=?";

  	    PreparedStatement st = con.prepareStatement(query);
  	    st.setLong(1, account_number);
  	    st.setInt(2, pin);

  	    ResultSet set = st.executeQuery();

  	    if(set.next())
  	    {
  	        double current_balance = set.getDouble("balance");

  	        if(current_balance >= amount)
  	        {
  	            String withdraw =
  	            "UPDATE Bank SET balance=balance-? WHERE account_number=? AND pin=?";

  	            PreparedStatement st1 = con.prepareStatement(withdraw);
  	            st1.setDouble(1, amount);
  	            st1.setLong(2, account_number);
  	            st1.setInt(3, pin);

  	            int count1 = st1.executeUpdate();

  	            String deposit =
  	            "UPDATE Bank SET balance=balance+? WHERE account_number=?";

  	            PreparedStatement st2 = con.prepareStatement(deposit);
  	            st2.setDouble(1, amount);
  	            st2.setLong(2, account_number_receiver);

  	            int count2 = st2.executeUpdate();

  	            if(count1 > 0 && count2 > 0)
  	            {
  	                System.out.println("Amount transferred successfully");
  	            }
  	            else
  	            {
  	                System.out.println("Transaction failed");
  	            }
  	        }
  	        else
  	        {
  	            System.out.println("Insufficient balance");
  	        }
  	    }
  	    else
  	    {
  	        System.out.println("Invalid account number or pin");
  	    }
  	}
 }