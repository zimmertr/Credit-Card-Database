import java.util.*; //Import Scanner and ArrayList
import java.io.*;
import java.text.NumberFormat;
import java.awt.*;

/**
 * Class CustomerDatabase handles the logic of the program
 * 
 * @TJ Zimmerman
 * @1.01
 */
public class CustomerDatabase 
{
    private NumberFormat fmt;    
    private ArrayList<Customer> db;

    /**
     * Constructor initializes the array list and the number format.
     */
    public CustomerDatabase ()
    {
        db = new ArrayList<Customer>();
        fmt = NumberFormat.getCurrencyInstance();
    }

    /**
     * This method reads the customer information from the provided document. 
     */
    public void readCustomerData(String filename)
    {

        try {
            File f = new File("CustomerNames.txt");
            Scanner sc = new Scanner(f);
            String logline;

            logline = sc.nextLine();
            while (sc.hasNext()) {
                logline = sc.nextLine();

                // remove this print statement after method works
                System.out.println(logline);

                Customer c = new Customer(logline);
                addCustomer(c);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }
    }

    /**
     * This method adds the customer to the arraylist.
     */
    public void addCustomer (Customer c)
    {
        db.add(c);
    }

    /**
     * This method counts the number of people in the arraylist.
     */
    public int countCustomers ()
    {
        return db.size();
    }

    /**
     * This method counts the number of people in the array list by given zipcode.
     */
    public int countCustomers(int zip)
    {
        int count = 0;
        for (Customer c: db)
        {
            if (c.getZip()==zip)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * This method counts the number of people in the arraylist who are debt free.
     */
    public int countDebtFree () 
    {
        int count = 0;
        for (Customer c: db)
        {
            if (c.getBalance()==0.0)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * This method finds the person in the arraylist with the highest amount of debt.
     */
    public Customer getHighestDebt ()
    {
        double highest = 0.0;
        Customer a = null;
        for (Customer c: db)
        {
            if (c.getBalance() > highest)
            {highest = c.getBalance();
                a = c;
            }
        }
        return a;
    }    

    /**
     * This method finds the youngest person who has a card.
     */
    public Customer getYoungestCardholder ()
    {
        Customer youngest = null;
        int day = 0;
        int month = 0;
        int year = 0;
        for (Customer c: db)
        {
            if (c.getYear() > year)
            {
                year = c.getYear();
                youngest = c;
                if (c.getMonth() >= youngest.getMonth())
                {
                    month = c.getMonth();
                    youngest = c;
                    if (c.getDay() >= youngest.getDay())
                    {
                        day = c.getDay();
                        youngest = c;
                    }
                }
            }
        }
        return youngest;
    }

    /**
     * This method searches by card and gets information
     * regarding how many people have hte card and such.
     */
    public String getCardSummary(String card)
    {

        int count = 0;
        double sum = 0;
        double avg;
        String result = "";
        if(!card.equalsIgnoreCase("Visa") && !card.equalsIgnoreCase
        ("Discover") && !card.equalsIgnoreCase("MasterCard"))
        {
            return "Credit Card Company not valid";
        }
        else
        {
            for (Customer c: db)
            {
                if (card.equalsIgnoreCase(c.getCreditCard()))
                {
                    count++;
                    sum+=c.getBalance();
                }
            }

            if (count == 0) 
            {
                result = "Customers were not found!";
                return result;
            }
            else
            {

                avg = sum/count;
                return card +": " + count + " cards with average balance of " +
                fmt.format(avg);
            }
        }

    }

    /**
     * This method relays teh amount of people between
     * two given balances.
     */
    public ArrayList <Customer> getMailingList(double low, double high)
    {

        ArrayList<Customer> temp = new ArrayList<Customer>();

        for (Customer c : db) {
            double balance = c.getBalance();
            if (low <= balance && balance <= high) {
                temp.add(c);
            }
        }

        return temp;

    }

    /**
     * This method searches the arraylist for people who are located
     * in the provided area.
     */
    public ArrayList <Customer> getMailingList(String keyword)
    {
        String result = "";
        ArrayList<Customer> temp = new ArrayList<Customer>();

        for (Customer c : db) {
            if (c.getCity().toLowerCase().contains(keyword.toLowerCase()) || (c.getState().toLowerCase().contains(keyword.toLowerCase()))) {
                temp.add(c);
            }

        }

        return temp;

    }

    /**
     * The purpose of the main method is to test this class.
     */
    public static void main(String args[]){
        String info = "male, Joe, Smith, 4/20/1963, addr, San Francisco, CA, 49401, Discover, 12345.67";
        Customer cust1 = new Customer(info);
        info = "female, Jo Anne, Henderson, 2/19/1972, addr2, Detroit, MI, 49401, Mastercard, 12000";
        Customer cust2 = new Customer(info);
        info = "male, bob, bobberson, 1/2/1234, add3, Allendale, MI, 49401, Visa, 233.30";
        Customer cust3 = new Customer(info);
        info = "female, bobette, bobberson, 12/22/1236, add4, Grand Rapids, MI, 49401, Visa, 0.00";
        Customer cust4 = new Customer(info);

        CustomerDatabase db = new CustomerDatabase();
        db.addCustomer(cust1);
        db.addCustomer(cust2);
        db.addCustomer(cust3);
        db.addCustomer(cust4);
        System.out.println("Youngest Cardholder: " + db.getYoungestCardholder() + " \n\n");
        System.out.println("Highest Debt: " + db.getHighestDebt() + " \n\n"); 
        System.out.println("Count Customers: " + db.countCustomers() + " \n\n");
        System.out.println("Debt Free: " + db.countDebtFree() + " \n\n");

    }

}