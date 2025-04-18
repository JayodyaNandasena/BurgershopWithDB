/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CustomerController;
import Model.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Jayodya Chathumini
 */
public class SearchOrder extends JFrame {
    private JLabel lblTitle,lblCustId,lblCustName,lblGetName,lblQty,lblGetQty;
    private JLabel lblOrdervalue,lblGetValue,lblStatus,lblGetStatus;
    private JTextField txtOrderID;
    private JButton btnSearch,btnBack,btnExit,btnHome;
    private JPanel panel;
    
    public SearchOrder(){
        setTitle("Search Orders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("Search Orders");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        lblCustId = new JLabel("Please enter the order ID : ");
        lblCustId.setFont(new Font("", Font.BOLD, 17));
        lblCustId.setBounds(30, 60, 270, 30);
        lblCustId.setVerticalAlignment(JLabel.CENTER);
        lblCustId.setHorizontalAlignment(JLabel.LEFT);
        
        txtOrderID = new JTextField();
        txtOrderID.setFont(new Font("", Font.PLAIN, 16));
        //txtCustID.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        txtOrderID.setBounds(300, 60, 215, 30);
        
        btnSearch = createStyledButton("Search", 555, 60, 100, 30, 0, 82, 77, evt -> {
            String orderId=txtOrderID.getText();
            if(orderId.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter an order ID");
                txtOrderID.setBackground(new Color(250, 205, 212));
            }else{
                txtOrderID.setBackground(new Color(238, 238, 238));
                loadData(orderId);
            }
        });
        
        panel=new JPanel();
        panel.setBounds(50, 50, 500, 350);
        panel.setLayout(null);
        
        lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("", Font.BOLD, 16));
        lblCustName.setBounds(50, 100, 150, 30);
        lblCustName.setVerticalAlignment(JLabel.CENTER);
        lblCustName.setHorizontalAlignment(JLabel.LEFT);
        
        lblGetName = new JLabel("");
        lblGetName.setFont(new Font("", Font.PLAIN, 16));
        lblGetName.setBounds(190, 100, 100, 30);
        lblGetName.setVerticalAlignment(JLabel.CENTER);
        lblGetName.setHorizontalAlignment(JLabel.LEFT);
        
        lblQty = new JLabel("Order Quantity : ");
        lblQty.setFont(new Font("", Font.BOLD, 16));
        lblQty.setBounds(50, 150, 150, 30);
        lblQty.setVerticalAlignment(JLabel.CENTER);
        lblQty.setHorizontalAlignment(JLabel.LEFT);
        
        lblGetQty = new JLabel("");
        lblGetQty.setFont(new Font("", Font.PLAIN, 16));
        lblGetQty.setBounds(190, 150, 100, 30);
        lblGetQty.setVerticalAlignment(JLabel.CENTER);
        lblGetQty.setHorizontalAlignment(JLabel.LEFT);
        
        lblOrdervalue = new JLabel("Order value : ");
        lblOrdervalue.setFont(new Font("", Font.BOLD, 16));
        lblOrdervalue.setBounds(50, 200, 150, 30);
        lblOrdervalue.setVerticalAlignment(JLabel.CENTER);
        lblOrdervalue.setHorizontalAlignment(JLabel.LEFT);
        
        lblGetValue = new JLabel("");
        lblGetValue.setFont(new Font("", Font.PLAIN, 16));
        lblGetValue.setBounds(190, 200, 100, 30);
        lblGetValue.setVerticalAlignment(JLabel.CENTER);
        lblGetValue.setHorizontalAlignment(JLabel.LEFT);
        
        lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("", Font.BOLD, 16));
        lblStatus.setBounds(50, 250, 150, 30);
        lblStatus.setVerticalAlignment(JLabel.CENTER);
        lblStatus.setHorizontalAlignment(JLabel.LEFT);
        
        lblGetStatus = new JLabel("");
        lblGetStatus.setFont(new Font("", Font.PLAIN, 16));
        lblGetStatus.setBounds(190, 250, 100, 30);
        lblGetStatus.setVerticalAlignment(JLabel.CENTER);
        lblGetStatus.setHorizontalAlignment(JLabel.LEFT);
        
        
        btnHome = createStyledButton("Main Menu", 310, 400, 130, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        
        btnBack = createStyledButton("Back", 450, 400, 100, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new Search().setVisible(true);
        });
        
        btnExit = createStyledButton("Exit", 560, 400, 100, 30, 155, 82, 77, evt -> {
            System.exit(0);
        });
        
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/searchOrder.png")));
        
        panel.add(lblCustName);
        panel.add(lblGetName);
        panel.add(lblQty);
        panel.add(lblGetQty);
        panel.add(lblOrdervalue);
        panel.add(lblGetValue);
        panel.add(lblStatus);
        panel.add(lblGetStatus);
        
        add(lblCustId);
        add(panel);
        add(txtOrderID);
        add(btnSearch);
        add(btnHome);
        add(btnExit);
        add(btnBack);
    }
    
    private void loadData(String orderId){
        clear();
        
        boolean found=false;
        
        Customer [] orderArray=CustomerController.toArray();
        
        for(int i=0;i<orderArray.length;i++){
            Customer obj = orderArray[i];
            if(orderId.equals(obj.getOrderId())){
                lblGetName.setText(obj.getCustomerName());
                lblGetQty.setText(Integer.toString(obj.getOrderQTY()));
                lblGetValue.setText("Rs. "+ Double.toString(obj.getOrderValue()));
                lblGetStatus.setText(CustomerController.getStatusString(obj.getOrderStatus()));
                found=true;
            }
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(null, "Invalid order ID");
            txtOrderID.setBackground(new Color(250, 205, 212));
        }
    }
    
    private void clear(){
        lblGetName.setText("");
        lblGetQty.setText("");
        lblGetValue.setText("");
        lblGetStatus.setText("");
    }
    
    private JButton createStyledButton(String text,int x, int y, int width, int height,int r, int g, int b, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, height);
        button.setForeground(Color.white);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(new Color(r, g, b));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
    
}
