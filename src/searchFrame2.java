import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class searchFrame2 {
    public static void main(String[] args) {
        //create window with title
        JFrame frame = new JFrame("the search frame2");

        //window is 500 wide and 520 hight
        frame.setSize(500, 520);

        //had to use it to manually control every position and size(setBound)
        frame.setLayout(null);
        // when I click the X to close the window it gonna exit the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //center the window on screen
        frame.setLocationRelativeTo(null);
        //prevent resizing
        frame.setResizable(false);
        //set background color
        frame.getContentPane().setBackground(new Color(0xD4,0xD8,0xDD));


        // ****** the top bar ******

        //the dark blue bar at the top
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBackground(new Color(0x1A,0x2D,0x42));
        topBar.setBounds(0,0,500,55);
        frame.add(topBar);

        //-button- back button on the top left
        JButton backButton =new JButton("Back");
        backButton.setBounds(10,12,90,30);
        backButton.setFont(new Font("SansSerif",Font.BOLD,14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0x2E,0x41,0x56));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        topBar.add(backButton);

        //-label- title in the center of the top bar
        JLabel tLabel = new JLabel("Searched Flight",SwingConstants.CENTER);
        tLabel.setBounds(0,12,500,30);
        tLabel.setFont(new Font("SansSerif",Font.BOLD,22));
        tLabel.setForeground(Color.WHITE);
        topBar.add(tLabel);


        //-label- to show the selected departure date
        JLabel dateInfo=new JLabel("Selected Date: 4/3/2026");
        dateInfo.setBounds(50,70,170,25);
        dateInfo.setFont(new Font("SansSerif",Font.BOLD,15));
        dateInfo.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(dateInfo);

        //-label- to show the selected travel class
        JLabel classInfo =new JLabel("Class: Business");
        classInfo.setBounds(290,70,170,25);
        classInfo.setFont(new Font("SansSerif",Font.BOLD,15));
        classInfo.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(classInfo);



        //****here are the headers of the table:****

        //-label- bold headers for the table columns
        JLabel fcodeHead=new JLabel("Flight Code");
        fcodeHead.setBounds(30,130,100,25);
        fcodeHead.setFont(new Font("SansSerif",Font.BOLD,14));
        fcodeHead.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(fcodeHead);

        JLabel fromHead =new JLabel("From");
        fromHead.setBounds(130,130,60,25);
        fromHead.setFont(new Font("SansSerif",Font.BOLD,14));
        fromHead.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(fromHead);

        JLabel toHead =new JLabel("To");
        toHead.setBounds(240,130,60,25);
        toHead.setFont(new Font("SansSerif",Font.BOLD,14));
        toHead.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(toHead);

        JLabel capHead = new JLabel("Capacity");
        capHead.setBounds(340,130, 80,25);
        capHead.setFont(new Font("SansSerif",Font.BOLD,14));
        capHead.setForeground(new Color(0x1A,0x2D,0x42));
        frame.add(capHead);


        //for the table it accepts two array strings(later I hid the table header)
        String[] headers ={"Flight Code","From","To","Capacity"};
        //the rows of data (3 random flights for now)
        String[][] data = {
                {"B1033","JED","MED","200"},
                {"A2471","JED","RUH","150"},
                {"C5589","JED","DMM","180"}
        };

        //-table- to display the flight data in rows
        JTable flightTable =new JTable(data,headers);
        flightTable.setFont(new Font("SansSerif",Font.PLAIN,13));
        flightTable.setForeground(new Color(0x1A,0x2D,0x42));
        flightTable.setBackground(Color.WHITE);
        //make each row taller so its easier to click
        flightTable.setRowHeight(35);
        //only allow selecting one row at a time
        flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //highlight color when a row is clicked
        flightTable.setSelectionForeground(new Color(0x1A,0x2D,0x42));
        //remove the grid lines in between
        flightTable.setShowGrid(false);
        //prevent editing the cells (its just for display)
        flightTable.setEnabled(true);
        flightTable.setDefaultEditor(Object.class,null);

        //scroll pane to hold the table (essential for table or it will hide)
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(30,170,420,290);
        //hide the default table header because I made labels above for better looking
        flightTable.setTableHeader(null);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}