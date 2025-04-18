package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

    private JButton btnPlaceOrder,btnSearch,btnViewOrder,btnUpdateOrder,btnExit;
    private JLabel lblMainMenu, lblImage;

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblMainMenu = new JLabel("iHungry Burgers");
        lblMainMenu.setFont(new Font("", Font.BOLD, 25));
        lblMainMenu.setBounds(0, 0, 700, 50);
        lblMainMenu.setForeground(Color.white);
        lblMainMenu.setVerticalAlignment(JLabel.CENTER);
        lblMainMenu.setHorizontalAlignment(JLabel.CENTER);
        lblMainMenu.setBackground(new Color(185, 82, 77));
        lblMainMenu.setOpaque(true);
        add(lblMainMenu);
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 50, 350, 450);
        imagePanel.setBackground(Color.white);
        
        try {
            BufferedImage shopImage=ImageIO.read(new File("src/images/ShopImage.png"));
            Image resizedImage=shopImage.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            ImageIcon imageIcon=new ImageIcon(resizedImage);
            JLabel imageLabel=new JLabel(imageIcon);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(350, 50, 350, 450);
        buttonPanel.setBackground(new Color(215, 215, 215));
        buttonPanel.setLayout(null);

        btnPlaceOrder = createStyledButton("Place order", 50, 70, 250, 30, evt -> {
            this.setVisible(false);
            new PlaceOrder().setVisible(true);
        });
        buttonPanel.add(btnPlaceOrder);

        btnViewOrder = createStyledButton("View Orders", 50, 130, 250, 30, evt -> {
            this.setVisible(false);
            new ViewOrders().setVisible(true);
        });
        buttonPanel.add(btnViewOrder);

        btnSearch = createStyledButton("Search", 50, 190, 250, 30, evt -> {
            this.setVisible(false);
            new Search().setVisible(true);
        });
        buttonPanel.add(btnSearch);

        btnUpdateOrder = createStyledButton("Update Order Details", 50, 250, 250, 30, evt -> {
            this.setVisible(false);
            new Update().setVisible(true);
        });
        buttonPanel.add(btnUpdateOrder);

        btnExit = createStyledButton("Exit", 220, 365, 100, 30, evt -> {
            System.exit(0);
        });
        buttonPanel.add(btnExit);

        add(buttonPanel);
        add(imagePanel);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/MainMenu.png")));

    }

    public JButton createStyledButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, height);
        button.setForeground(Color.white);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(new Color(185, 82, 77));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
