import java.util.ArrayList;

/**
 * Class Customer relays the information and formats the output strings.
 * 
 * @TJ Zimmerman
 * @1.01
 */
public class Customer
{
    /** Makes a boolean to check the gender for each of the people */
    private boolean isFem = true;

    /** Creates Strings that will hold the individual personal information after splitting the input data */
    private String firstN = "";
    private String lastN = "";
    private String addr = "";
    private String city = "";
    private String state = "";
    private String ccMake = "";
    private String endStr = "";
    private String result = "0 people found under the given search query!";

    /** Creates a double to hold the balance of a person including cents. */
    private double bal;

    /** Creates multiple ints to hold more info post splitting. */
    private int day, month, year;
    private int zip;

    /**
     * Constructor takes the input data and splits it up into the various variables that were created.
     * @param info 
     */
    public Customer(String info){
        String [] tokens = info.split(",|/");

        if(tokens[0].trim().equalsIgnoreCase("female"))
        {
            isFem = true;
        }
        else
        {
            isFem = false;
        }

        firstN = tokens[1].trim();
        lastN = tokens[2].trim(); 
        month = Integer.parseInt(tokens[3].trim());
        day = Integer.parseInt(tokens[4].trim());
        year = Integer.parseInt(tokens[5].trim());
        addr = (tokens[6].trim());
        city = tokens[7].trim();
        state = tokens[8].trim();
        zip = Integer.parseInt(tokens[9].trim());
        ccMake = tokens[10].trim();
        bal = Double.parseDouble(tokens[11].trim());
    }

    /**
     * Modifies the boolean based upon the gender of the inputted person.
     * @return isFem 
     */
    public boolean isFemale() 
    {
        if (isFem) {
            isFem = true;
        }

        else {
            isFem = false;
        }

        return isFem;
    }

    /**
     * Returns the first name
     * @return firstN
     */
    public String getFirst()
    {
        return firstN;
    }

    /**
     * Returns the last name
     * @return lastN
     */
    public String getLast()
    {
        return lastN;
    }

    /**
     * Returns the address
     * @addr
     */
    public String getAddress()
    {
        return addr;
    }

    /**
     * Returns the city
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Returns the state
     * @return state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Returns the  zipcode
     * @return zip
     */
    public int getZip()
    {
        return zip;
    }

    /**
     * Returns the credit card manufacturer
     * @return ccMake
     */
    public String getCreditCard()
    {
        return ccMake;
    }

    /**
     * Returns the day of birth
     * @return day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Returns the month of birth
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Returns the year of birth
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the credit card debt balance
     * @return bal
     */
    public double getBalance()
    {
        return bal;
    }

    /**
     * Formats the strings before output.
     * @return endStr
     */
    public String toString ()
    {
        endStr = getFirst() + " " + getLast() + "\n" + " " + getAddress()
        + "\n" + " " + getCity() + ", " + getState() + " " + getZip() + "\n \n";

        return endStr;
    }

    public static void main (String args[])
    {
        String info = "male, Bob, Bobberson, 2/12/1933, 2348 East St., Allendale, MI, 49401, VISA, 1567.89";
        Customer c = new Customer (info);
        System.out.println(c);
    }

}
