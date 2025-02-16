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

public class gui {
    static Random rand = new Random();
    public static void main(String[] args) {
        JFrame jframe = createFrame();
        JMenuBar menuBar = createMenuBar(jframe);
        jframe.setJMenuBar(menuBar);
        jframe.getContentPane().setBackground(new java.awt.Color(50, 51, 51));

        JButton pressButton = createButton("First searchbot", "https://github.com/bran7230/Firstsearchpython");
        JButton htmlButton = createButton("HTML Project Final", "https://github.com/bran7230/HTML-python");
        JButton todoList = createButton("To do List", "https://github.com/bran7230/to-do-list");
        JButton searchBot = createButton("Grouped up projects", "https://github.com/bran7230/My-Projects");
        JButton searchBot2 = createButton("Final Search Bot Attempt", "https://github.com/bran7230/finalsearchbot");
        JButton personalHtml = createButton("Personal HTML Project", "https://github.com/bran7230/personalweb");

        jframe.getContentPane().setLayout(new GridLayout(4, 2));

        jframe.getContentPane().add(pressButton);
        jframe.getContentPane().add(htmlButton);
        jframe.getContentPane().add(todoList);
        jframe.getContentPane().add(searchBot);
        jframe.getContentPane().add(searchBot2);
        jframe.getContentPane().add(personalHtml);

        jframe.setVisible(true);
    }

    private static JFrame createFrame() {
        JFrame jframe = new JFrame("Test");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        URL icon = gui.class.getResource("/imgs/test.jpg.png");
        assert icon != null;
        ImageIcon iconImage = new ImageIcon(icon);
        jframe.setIconImage(iconImage.getImage());

        return jframe;
    }

    public static JFrame newFrame() {

        JFrame newFrame = new JFrame("New Frame, Use W for Up, A for Left, D for Right, S for Down (Size Adjusts Brightness)");
        newFrame.setSize(600, 400);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setLocationRelativeTo(null);

        JButton addButton = new JButton("+");
        JButton minusButton = new JButton("-");

        newFrame.getContentPane().setLayout(new GridLayout(2, 1));

        newFrame.add(addButton);
        newFrame.add(minusButton);

        addButton.setFont(new Font("Arial", Font.PLAIN, 50));
        minusButton.setFont(new Font("Arial", Font.PLAIN, 50));

        AtomicInteger count = new AtomicInteger();

        Timer timer = getTimer(newFrame, addButton, minusButton);
        timer.start();

        addButton.addActionListener(_-> {

            count.addAndGet(100);
            newFrame.setSize(600 + count.get(), 400 + count.get());

        });

        minusButton.addActionListener(_-> {

            count.addAndGet(-100);
            newFrame.setSize(600 + count.get(), 400 + count.get());

        });



        // Add key listener to move frame
        newFrame.addKeyListener(new KeyAdapter() {

            @Override

            public void keyPressed(KeyEvent e) {

                int x = newFrame.getX();
                int y = newFrame.getY();
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_W:
                        newFrame.setLocation(x, y - 10);
                        System.out.println("W");
                        break;

                    case KeyEvent.VK_S:
                        newFrame.setLocation(x, y + 10);
                        break;

                    case KeyEvent.VK_A:
                        newFrame.setLocation(x - 10, y);
                        break;

                    case KeyEvent.VK_D:
                        newFrame.setLocation(x + 10, y);
                        break;
                }
            }
        });

        newFrame.setFocusable(true);
        addButton.setFocusable(false);
        minusButton.setFocusable(false);

        return newFrame;
    }

    private static Timer getTimer(JFrame newFrame, JButton addButton, JButton minusButton) {
        ActionListener updateListener = e->{
            
            Color newColor = new Color(
                    
                    Math.min(rand.nextInt(255), newFrame.getWidth() / 10),
                    Math.min(rand.nextInt(255), newFrame.getHeight() / 10),
                    Math.min(rand.nextInt(255), newFrame.getHeight() / 10)
            );
            
            addButton.setBackground(newColor);
            minusButton.setBackground(newColor);
            
        };

        return new Timer(1000, updateListener);
    }

    private static JMenuBar createMenuBar(JFrame jframe) {

        JMenuBar menuBar = new JMenuBar();
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem fullScreenItem = new JMenuItem("Full Screen");
        JMenuItem iconItem = new JMenuItem("Small Screen");
        JMenuItem minItem = new JMenuItem("Minimal Screen");
        JMenuItem gameItem = new JMenuItem("Game Screen");

        menuBar.add(exitItem);
        menuBar.add(fullScreenItem);
        menuBar.add(iconItem);
        menuBar.add(minItem);
        menuBar.add(gameItem);

        menuBar.setBackground(Color.GRAY);
        exitItem.setBackground(Color.GRAY);
        fullScreenItem.setBackground(Color.GRAY);
        iconItem.setBackground(Color.GRAY);
        minItem.setBackground(Color.GRAY);
        gameItem.setBackground(Color.GRAY);

        menuBar.setForeground(Color.WHITE);
        exitItem.setForeground(Color.WHITE);
        fullScreenItem.setForeground(Color.WHITE);
        iconItem.setForeground(Color.WHITE);
        minItem.setForeground(Color.WHITE);
        gameItem.setForeground(Color.WHITE);

        menuBar.setLayout(new FlowLayout());

        fullScreenItem.addActionListener(_ -> jframe.setExtendedState(JFrame.MAXIMIZED_BOTH));
        exitItem.addActionListener(_ -> System.exit(0));
        iconItem.addActionListener(_ -> jframe.setSize(600, 600));
        minItem.addActionListener(_ -> jframe.setState(JFrame.ICONIFIED));
        gameItem.addActionListener(_ -> newFrame().setVisible(true));

        return menuBar;
    }

    private static JButton createButton(String title, String url) {

        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        JButton button = new JButton(title);
        button.setBackground(new java.awt.Color(50, 51, 51));
        button.setForeground(new java.awt.Color(255, 255, 255));
        button.setFocusable(false);

        button.addActionListener(_ -> {

            try {
                assert desktop != null;
                desktop.browse(new URI(url));
            }
            catch (URISyntaxException | IOException _) {
            }

        });

        return button;
    }
}
