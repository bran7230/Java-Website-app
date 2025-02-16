
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class gui {//Main graphic class
    static Random rand = new Random();
    public static void main(String[] args) {

        JFrame jframe = createFrame();//Creating the window

        JMenuBar menuBar = createMenuBar(jframe);//Making the menu

        jframe.setJMenuBar(menuBar);//Loading Menu

        jframe.getContentPane().setBackground(new java.awt.Color(50, 51, 51));//Making main Background Color

        //Making buttons for the links
        JButton pressButton = createButton("First searchbot", "https://github.com/bran7230/Firstsearchpython");
        JButton htmlButton = createButton("HTML Project Final", "https://github.com/bran7230/HTML-python");
        JButton todoList = createButton("To do List", "https://github.com/bran7230/to-do-list");
        JButton searchBot = createButton("Grouped up projects", "https://github.com/bran7230/My-Projects");
        JButton searchBot2 = createButton("Final Search Bot Attempt", "https://github.com/bran7230/finalsearchbot");
        JButton personalHtml = createButton("Personal HTML Project", "https://github.com/bran7230/personalweb");

        jframe.getContentPane().setLayout(new GridLayout(4, 2));//Alinging content on grid for readablillity

        //Adding Buttons to main frame
        jframe.getContentPane().add(pressButton);
        jframe.getContentPane().add(htmlButton);
        jframe.getContentPane().add(todoList);
        jframe.getContentPane().add(searchBot);
        jframe.getContentPane().add(searchBot2);
        jframe.getContentPane().add(personalHtml);

        jframe.setVisible(true);//so you can actually see it
    }

    private static JFrame createFrame() {//Main frame
        JFrame jframe = new JFrame("Brandons App");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//So you can end app
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        URL icon = gui.class.getResource("/imgs/test.jpg.png");//image for the icon
        assert icon != null;
        ImageIcon iconImage = new ImageIcon(icon);//making icon
        jframe.setIconImage(iconImage.getImage());//getting data

        return jframe;//returning the main frame to create window
    }

    public static JFrame newFrame() {//Game frame window

        JFrame newFrame = new JFrame("New Frame, Use W for Up, A for Left, D for Right, S for Down (Size Adjusts Brightness)");//Title for descriptions
        newFrame.setSize(600, 400);//setting base size
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//default "Get rid off" opperation
        newFrame.setLocationRelativeTo(null);//so its not top left, top right, etc only in middle

        //Size adjust buttons
        JButton addButton = new JButton("+");
        JButton minusButton = new JButton("-");

        newFrame.getContentPane().setLayout(new GridLayout(2, 1));//layout

        //adding them
        newFrame.add(addButton);
        newFrame.add(minusButton);

        //base fonts for buttons and size
        addButton.setFont(new Font("Arial", Font.PLAIN, 50));
        minusButton.setFont(new Font("Arial", Font.PLAIN, 50));

        AtomicInteger count = new AtomicInteger();//so it can be accessed during the listeners to adjust frame size per click

        Timer timer = getTimer(newFrame, addButton, minusButton);//timer for color
        timer.start();

        //when you click + button
        addButton.addActionListener(_-> {

            count.addAndGet(100);//adding  count for Frame width
            newFrame.setSize(600 + count.get(), 400 + count.get());//size based off of current count and default size

        });

        //when you click - button
        minusButton.addActionListener(_-> {

            count.addAndGet(-100);//minus the current count
            newFrame.setSize(600 + count.get(), 400 + count.get());//same thing as add button

        });



        // Add key listener to move frame
        newFrame.addKeyListener(new KeyAdapter() {//to move the frame

            @Override

            public void keyPressed(KeyEvent e) {

                int x = newFrame.getX();//positions X to move
                int y = newFrame.getY();//position y to move
                switch (e.getKeyCode()) {//gets keys input

                    case KeyEvent.VK_W://if they press W
                        newFrame.setLocation(x, y - 10);//Move up
                        break;

                    case KeyEvent.VK_S://if they press S
                        newFrame.setLocation(x, y + 10);//Move down
                        break;

                    case KeyEvent.VK_A://if they press A
                        newFrame.setLocation(x - 10, y);//Move left
                        break;

                    case KeyEvent.VK_D://if they press D
                        newFrame.setLocation(x + 10, y);//move right
                        break;
                }
            }
        });

        newFrame.setFocusable(true);//so it can move

        //so they listeners dont break when you adjust size
        addButton.setFocusable(false);
        minusButton.setFocusable(false);

        return newFrame;
    }

    private static Timer getTimer(JFrame newFrame, JButton addButton, JButton minusButton) {//color function
        ActionListener updateListener = e->{//the updating function
            
            Color newColor = new Color(//creating color based off of size
                    
                    Math.min(rand.nextInt(255), newFrame.getWidth() / 10),
                    Math.min(rand.nextInt(255), newFrame.getHeight() / 10),
                    Math.min(rand.nextInt(255), newFrame.getHeight() / 10)
            );
            //adjusting the color of button
            addButton.setBackground(newColor);
            minusButton.setBackground(newColor);
            
        };

        return new Timer(1000, updateListener);//returning the time
    }

    //menu function
    private static JMenuBar createMenuBar(JFrame jframe) {
        //adding new menu
        JMenuBar menuBar = new JMenuBar();
        //creating the items
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem fullScreenItem = new JMenuItem("Full Screen");
        JMenuItem iconItem = new JMenuItem("Small Screen");
        JMenuItem minItem = new JMenuItem("Minimal Screen");
        JMenuItem gameItem = new JMenuItem("Game Screen");

        //adding the items
        menuBar.add(exitItem);
        menuBar.add(fullScreenItem);
        menuBar.add(iconItem);
        menuBar.add(minItem);
        menuBar.add(gameItem);

        //all default colors
        menuBar.setBackground(Color.GRAY);
        exitItem.setBackground(Color.GRAY);
        fullScreenItem.setBackground(Color.GRAY);
        iconItem.setBackground(Color.GRAY);
        minItem.setBackground(Color.GRAY);
        gameItem.setBackground(Color.GRAY);

        //all default fonts
        menuBar.setForeground(Color.WHITE);
        exitItem.setForeground(Color.WHITE);
        fullScreenItem.setForeground(Color.WHITE);
        iconItem.setForeground(Color.WHITE);
        minItem.setForeground(Color.WHITE);
        gameItem.setForeground(Color.WHITE);

        //so it looks good
        menuBar.setLayout(new FlowLayout());

        //if they press make fullscreen
        fullScreenItem.addActionListener(_ -> jframe.setExtendedState(JFrame.MAXIMIZED_BOTH));

        //if they press stop app
        exitItem.addActionListener(_ -> System.exit(0));

        //if they press make small
        iconItem.addActionListener(_ -> jframe.setSize(600, 600));

        //if  they press minamize app
        minItem.addActionListener(_ -> jframe.setState(JFrame.ICONIFIED));

        //if they press load game
        gameItem.addActionListener(_ -> newFrame().setVisible(true));

        return menuBar;
    }

    //making the click buttons for website
    private static JButton createButton(String title, String url) {

        //if they can support the web
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        JButton button = new JButton(title);
        button.setBackground(new java.awt.Color(50, 51, 51));
        button.setForeground(new java.awt.Color(255, 255, 255));
        button.setFocusable(false);

        //if they press, load web
        button.addActionListener(_ -> {

            try {
                assert desktop != null;//so they can run
                desktop.browse(new URI(url));//load web based on provided button url
            }
            catch (URISyntaxException | IOException _) {
            }

        });

        return button;
    }
}
