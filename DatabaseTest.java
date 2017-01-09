

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * The test class CrapsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DatabaseTest
{
    /**
     * Default constructor for test class BankTest
     */
    private CustomerDatabase db;
    private final int COUNT = 3;

    
    public DatabaseTest()
    {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        db = new CustomerDatabase(); 
        String info = "male, Joe, Smith, 4/20/1963, addr, San Francisco, CA, 49401, Discover, 12400";
        Customer cust = new Customer(info);
        db.addCustomer(cust);
        
        info = "female, Jo Anne, Henderson, 2/19/1972, addr2, Detroit, MI, 49777, MasterCard, 12000";
        cust = new Customer(info);
        db.addCustomer(cust);

        info = "female, Jessica, Anderson, 2/19/1982, addr2, Detroit, MI, 49777, Discover, 0";
        cust = new Customer(info);
        db.addCustomer(cust);
                
    }
    

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testCustomerConstructor()
    {
        String info = "male, Joe, Smith, 4/20/1963, addr, San Francisco, CA, 49401, Discover, 12345.67";
        Customer cust = new Customer(info);        
        Assert.assertEquals("Customer() first name does not match", 
                "Joe", cust.getFirst());                
        Assert.assertEquals("Customer() first name does not match", 
                "Smith", cust.getLast());  
        Assert.assertEquals("Customer() credit card does not match", 
                "Discover", cust.getCreditCard());
        Assert.assertFalse("Customer() first name does not match", 
                cust.isFemale());                  
        Assert.assertEquals("Customer() birth day does not match", 
                20, cust.getDay());  
        Assert.assertEquals("Customer() birth month does not match", 
                4, cust.getMonth());  
        Assert.assertEquals("Customer() birth year does not match", 
                1963, cust.getYear());             
            } 
    
    @Test
    public void testDebtFree()
    {
        Assert.assertEquals("debtFree() not correct", 
                1, db.countDebtFree());                
    } 

    @Test
    public void testDebtMailingList()
    {
        ArrayList <Customer> c = db.getMailingList(11000, 13000);
        Assert.assertEquals("getMailingList(int, int) not correct", 
                2, c.size());                
        c = db.getMailingList(13000, 15000);
        Assert.assertEquals("getMailingList(int, int) not correct", 
                0, c.size());         
    } 

    @Test
    public void testCityMailingList()
    {
        ArrayList <Customer> c = db.getMailingList("Det");
        Assert.assertEquals("getMailingList(String) not correct", 
                2, c.size());                
        c = db.getMailingList("fred");
        Assert.assertEquals("getMailingList(String) not correct", 
                0, c.size());         
    }     
    @Test
    public void testYoungest()
    {
        Customer c = db.getYoungestCardholder();
        Assert.assertEquals("getYoungestCardholder() not correct", 
                "Jessica", c.getFirst());                
    }   

    @Test
    public void testHighestDebt()
    {
        Customer c = db.getHighestDebt();
        Assert.assertEquals("getHighestDebt() not correct", 
                "Joe", c.getFirst());                
    } 
 
    @Test
    public void testCardSummary()
    {
        String str = db.getCardSummary("Discover");
        Assert.assertTrue("getCardSummary() average not correct", 
                str.contains("6,200"));                
        str = db.getCardSummary("MasterCard");
        Assert.assertTrue("getCardSummary() not correct", 
                str.contains("12,000"));   
        str = db.getCardSummary("Visa");
        Assert.assertTrue("getCardSummary() average debt should be zero", 
                str.contains("not found"));                  
    } 
    
    @Test
    public void testCount()
    {
        Assert.assertEquals("countCustomers() not correct", 
                COUNT, db.countCustomers());                
    } 
    
    @Test
    public void testCountZipcodes()
    {
        Assert.assertEquals("countCustomers(int zip) not correct", 
                1, db.countCustomers(49401));                
        Assert.assertEquals("countCustomers(int zip) not correct", 
                2, db.countCustomers(49777));  
        Assert.assertEquals("countCustomers(int zip) not correct", 
                0, db.countCustomers(49999));                   
    }     
    
}
