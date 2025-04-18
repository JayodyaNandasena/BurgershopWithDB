package view;

import exception.LoadImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenu extends JFrame {

    private final JButton btnPlaceOrder,btnSearch,btnViewOrder,btnUpdateOrder,btnExit;
    private JLabel lblMainMenu;

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
        // edited Jlabel.CENTER -> SwingConstants.CENTER
        lblMainMenu.setVerticalAlignment(SwingConstants.CENTER);
        lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
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
            throw new LoadImageException("Failed to load image", e);
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
        // edited Jlabel.CENTER -> SwingConstants.CENTER
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(new Color(185, 82, 77));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
