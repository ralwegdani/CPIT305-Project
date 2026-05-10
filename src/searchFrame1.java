import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class searchFrame1 extends JFrame {
        public static void main(String[] args) {
            //create window with title
            JFrame frame = new JFrame("the search frame1");

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
            JLabel tLabel =new JLabel("Search Flight",SwingConstants.CENTER);
            tLabel.setBounds(0,12,500,30);
            tLabel.setFont(new Font("SansSerif",Font.BOLD,22));
            // white to make it visible
            tLabel.setForeground(Color.WHITE);
            topBar.add(tLabel);


            // ***** from and to fields*****

            //-label- on the left of the from input box
            JLabel fromLabel =new JLabel("From:");
            fromLabel.setBounds(30, 80,50,25);
            fromLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            fromLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(fromLabel);

            //-box- where we type the departure airport
            JTextField fromField =new JTextField();
            fromField.setBounds(80, 80,145,25);
            fromField.setFont(new Font("SansSerif",Font.PLAIN,13));
            frame.add(fromField);

            //-label- on the left of the to input box
            JLabel toLabel =new JLabel("To: ");
            toLabel.setBounds(260, 80, 30,25);
            toLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            toLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(toLabel);

            //-box- where we type the arrival airport
            JTextField toField = new JTextField();
            toField.setBounds(295, 80,145,25);
            toField.setFont(new Font("SansSerif",Font.PLAIN,13));
            frame.add(toField);


            // ****** the departure date *******

            //-label- for the date
            JLabel dateLabel = new JLabel("Departure Date:");
            dateLabel.setBounds(30,140,160,25);
            dateLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            dateLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(dateLabel);

            //-box- where the user types the travel date
            JTextField dateField =new JTextField("DD/MM/YYYY");
            dateField.setBounds(190,140,250,25);
            dateField.setFont(new Font("SansSerif",Font.PLAIN,13));
            frame.add(dateField);


            // ******** adults and children dropdown menus********

            //-label- for the adults count
            JLabel adultsLabel = new JLabel("Adults: ");
            adultsLabel.setBounds(50,200,80,25);
            adultsLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            adultsLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(adultsLabel);

            //the numbers to pick from 0 to 9
            String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};

            //-dropdown- to choose number of adults
            JComboBox <String> adultBox =new JComboBox<> (numbers);
            adultBox.setBounds(110,200,60,25);
            adultBox.setFont(new Font("SansSerif",Font.PLAIN,13));
            frame.add(adultBox);


            //-label- for the children count
            JLabel childLabel = new JLabel("Child:");
            childLabel.setBounds(300, 200,80, 25);
            childLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            childLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(childLabel);

            //-dropdown- to choose number of children
            JComboBox <String> childBox =new JComboBox<> (numbers);
            childBox.setBounds(345,200,60,25);
            childBox.setFont(new Font("SansSerif",Font.PLAIN,13));
            frame.add(childBox);


            // ******* travel class radio buttons *******

            //-label- for the class selection
            JLabel classLabel =new JLabel("Class of Travel:");
            classLabel.setBounds(30,260, 150,25);
            classLabel.setFont(new Font("SansSerif",Font.BOLD,14));
            classLabel.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(classLabel);

            //three radio buttons for the three classes
            JRadioButton ecoBut =new JRadioButton("Economy");
            ecoBut.setBounds(100,300,110,25);
            ecoBut.setFont(new Font("SansSerif",Font.PLAIN,13));
            ecoBut.setBackground(new Color(0xD4,0xD8,0xDD));
            ecoBut.setForeground(new Color(0x1A,0x2D,0x42));
            //economy is selected by default
            ecoBut.setSelected(true);
            frame.add(ecoBut);

            JRadioButton businessBut =new JRadioButton("Business");
            businessBut.setBounds(220,300,110,25);
            businessBut.setFont(new Font("SansSerif",Font.PLAIN,13));
            businessBut.setBackground(new Color(0xD4,0xD8,0xDD));
            businessBut.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(businessBut);



            JRadioButton firstBut=new JRadioButton("First");
            firstBut.setBounds(350,300,110, 25);
            firstBut.setFont(new Font("SansSerif",Font.PLAIN,13));
            firstBut.setBackground(new Color(0xD4,0xD8,0xDD));
            firstBut.setForeground(new Color(0x1A,0x2D,0x42));
            frame.add(firstBut);

            //group them so only one can be selected
            ButtonGroup classGroup=new ButtonGroup();
            classGroup.add(ecoBut);
            classGroup.add(businessBut);
            classGroup.add(firstBut);


            // ***** search button*****

            //-button- where we click to search for flights
            JButton searchBut=new JButton("Search");
            searchBut.setBounds(140,380,200, 30);
            searchBut.setFont(new Font("SansSerif",Font.BOLD,14));
            searchBut.setForeground(Color.WHITE);
            searchBut.setBackground(new Color(0x1A,0x2D,0x42));
            searchBut.setFocusPainted(false);
            searchBut.setBorderPainted(false);
            frame.add(searchBut);


            //this to make the window appear on screen or the program is shut the moment it runs
            //components must be added before we see the window
            frame.setVisible(true);
        }
    }