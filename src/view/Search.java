/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import exception.LoadImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author Jayodya Chathumini
 */
public class Search extends JFrame {
    private JLabel lblTitle;
    private JButton btnOrders,btnCustomers,btnBestCustomer,btnExit,btnHome;

    public Search() {
        setTitle("Search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("Search");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
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
            throw new LoadImageException("Failed to load shop image",e);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(350, 50, 350, 450);
        buttonPanel.setBackground(new Color(215, 215, 215));
        buttonPanel.setLayout(null);

        btnOrders = createStyledButton("Search Orders", 50, 100, 250, 30, 185, 82, 77, evt -> {
            new SearchOrder().setVisible(true);
            this.setVisible(false);
        });
        buttonPanel.add(btnOrders);

        btnCustomers = createStyledButton("Search Customers", 50, 150, 250, 30, 185, 82, 77, evt -> {
            new SearchCustomer().setVisible(true);
            this.setVisible(false);
        });
        buttonPanel.add(btnCustomers);

        btnBestCustomer = createStyledButton("Search Best Customer", 50, 200, 250, 30, 185, 82, 77, evt -> {
            this.setVisible(false);
            new SearchBestCustomer().setVisible(true);
        });
        buttonPanel.add(btnBestCustomer);

        btnHome = createStyledButton("Back to home page", 130, 270, 170, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        buttonPanel.add(btnHome);

        btnExit = createStyledButton("Exit", 130, 320, 170, 30, 155, 82, 77, evt -> {
            System.exit(0);
        });
        buttonPanel.add(btnExit);

        add(buttonPanel);
        add(imagePanel);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/search.png")));

    }

    public JButton createStyledButton(String text, int x, int y, int width, int height, int r, int g, int b,
            ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, height);
        button.setForeground(Color.white);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(new Color(r, g, b));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
