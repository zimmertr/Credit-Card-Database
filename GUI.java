import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.JButton;
// import javax.swing.JFrame;

/**
 * Class GUI creates a graphical interface for the program.
 * 
 * @TJ Zimmerman
 * @1.01
 */
public class GUI extends JPanel{

    /** text fields */
    private JPanel textAreas;
    private JLabel max, min, cityState;
    private JTextField max1, min1, cityState1;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu testMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem;
    private JMenuItem countItem;
    private JMenuItem summaryItem;

    /**JButtons*/
    private JButton searchCityState, searchDebt, youngestClient, highest;

    CustomerDatabase database;
    /**
     * This method is meant to test the gui.
     */
    public static void main(String arg[])
    {
        JFrame frame = new JFrame ("Credit Card Database");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new GUI());

        frame.pack();
        frame.setVisible(true);

        new GUI();

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GUI(){
        database = new CustomerDatabase();
        database.readCustomerData("CustomerNames.txt");

        theGUI = new JFrame("Credit Card Database");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates a JPanel for Right Side Menu buttons

        JPanel options = new JPanel();
        GridLayout layout = new GridLayout(4,1);
        options.setLayout(layout);
        theGUI.add(options, BorderLayout.EAST);
        GridLayout optionsGrid = new GridLayout(4,1);

        searchCityState = new JButton("Search City/ST");
        options.add(searchCityState);

        searchDebt = new JButton("Search Debt");
        options.add(searchDebt);

        youngestClient = new JButton("youngest");
        options.add(youngestClient);

        highest = new JButton("highest");
        options.add(highest);

        //my buttons & button listener
        ButtonListener listener = new ButtonListener();

        searchCityState.addActionListener(listener);
        searchDebt.addActionListener(listener);
        highest.addActionListener(listener);
        youngestClient.addActionListener(listener);  

        // create the Results Area for the Center area
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        theGUI.add(BorderLayout.CENTER, scrollPane);

        //creates a JPanel for text areas
        textAreas = new JPanel();
        theGUI.add(textAreas, BorderLayout.SOUTH);


        min= new JLabel("Min $");
        textAreas.add(min);

        min1 = new JTextField(8);
        textAreas.add(min1);

        max = new JLabel("Max $");
        textAreas.add(max);
        max1 = new JTextField(8);
        textAreas.add(max1);

        cityState = new JLabel("City/ST");
        textAreas.add(cityState);
        cityState1 = new JTextField(8);
        textAreas.add(cityState1);
        // set up File menus
        setupMenus();
        theGUI.pack();

    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        testMenu = new JMenu("Test");
        countItem = new JMenuItem("Counts");
        summaryItem = new JMenuItem("Summaries");

        // assign action listeners
        ButtonListener ml = new ButtonListener();
        quitItem.addActionListener(ml);
        openItem.addActionListener(ml);
        countItem.addActionListener(ml);
        summaryItem.addActionListener(ml);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        testMenu.add(countItem);
        testMenu.add(summaryItem);
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(testMenu);
        theGUI.setJMenuBar(menus);

    }

    /*********************************************************************
    Respond to menu selections and button clicks

    @param e the button or menu item that was selected
     *********************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e)
        {
            // menu item - quit

            if (e.getSource() == quitItem)
            {
                System.exit(1);
            }

            if(e.getSource() == youngestClient)
            {
                results.setText("");
                Customer c = database.getYoungestCardholder();
                results.append(c.toString());
            }

            if(e.getSource() == searchCityState)
            {
                results.setText("");
                ArrayList<Customer> c = database.getMailingList(cityState1.getText());
                displayCustomers(c);
            }

            if(e.getSource() == searchDebt)
            {
                results.setText("");
                ArrayList<Customer> c = database.getMailingList
                    (Double.parseDouble(min1.getText()),Double.parseDouble(max1.getText()));
                displayCustomers(c);
            }

            if(e.getSource() == highest)
            {
                results.setText("");
                Customer c = database.getHighestDebt();
                results.append(c.toString());
            }

        }

        public void displayCustomers (ArrayList <Customer> list)
        {
            results.setText("");
            for (Customer c: list){
                results.append(c + "\n");

            }
        }

        public void showSummaries()

        {
            String clearScreen = "\f";

        }

    }
}