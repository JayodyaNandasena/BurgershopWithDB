/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerController;
import controller.OrderController;
import exception.QueryFailException;
import model.Orders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jayodya Chathumini
 */
public class SearchCustomer extends JFrame {
    private JLabel lblTitle,lblCustId,lblCustName,lblGetName;
    private JTextField txtCustID;
    private JButton btnSearch,btnBack,btnExit,btnHome;
    private JTable tblCustomerDetails;
    private DefaultTableModel tblDefault;
    
    public SearchCustomer(){
        setTitle("Search Customers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("Search Customers");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        lblCustId = new JLabel("Please enter the customer ID : ");
        lblCustId.setFont(new Font("", Font.BOLD, 17));
        lblCustId.setBounds(30, 60, 270, 30);
        lblCustId.setVerticalAlignment(SwingConstants.CENTER);
        lblCustId.setHorizontalAlignment(SwingConstants.LEFT);
        
        txtCustID = new JTextField();
        txtCustID.setFont(new Font("", Font.PLAIN, 16));
        txtCustID.setBounds(300, 60, 215, 30);
        
        btnSearch = createStyledButton("Search", 555, 60, 100, 0, evt -> {
            String customerId=txtCustID.getText();
            if(customerId.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a customer ID");
                txtCustID.setBackground(new Color(250, 205, 212));
            }else{
                txtCustID.setBackground(new Color(238, 238, 238));
                loadTable(customerId);
            }
        });
        
        lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("", Font.BOLD, 16));
        lblCustName.setBounds(30, 100, 150, 30);
        lblCustName.setVerticalAlignment(SwingConstants.CENTER);
        lblCustName.setHorizontalAlignment(SwingConstants.LEFT);
        
        lblGetName = new JLabel("");
        lblGetName.setFont(new Font("", Font.PLAIN, 16));
        lblGetName.setBounds(190, 100, 100, 30);
        lblGetName.setVerticalAlignment(SwingConstants.CENTER);
        lblGetName.setHorizontalAlignment(SwingConstants.LEFT);
        
        String[] columnName={"Order ID", "Quantity", "Order value", "Order Status"};
        tblDefault=new DefaultTableModel(columnName,0);
		
        tblCustomerDetails=new JTable(tblDefault);
        tblCustomerDetails.setFont(new Font("",Font.PLAIN,14));
        
        JScrollPane tablePane=new JScrollPane(tblCustomerDetails);
        add(tablePane);
        tablePane.setBounds(30, 140, 630, 240);
        tablePane.setOpaque(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
        tblCustomerDetails.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        
        btnHome = createStyledButton("Main Menu", 310, 400, 130, 155, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        
        btnBack = createStyledButton("Back", 450, 400, 100, 155, evt -> {
            this.setVisible(false);
            new Search().setVisible(true);
        });
        
        btnExit = createStyledButton("Exit", 560, 400, 100, 155, evt -> {
            System.exit(0);
        });
        
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/searchCustomer.png")));

        add(lblCustId);
        add(lblCustName);
        add(lblGetName);
        add(txtCustID);
        add(btnSearch);
        add(btnHome);
        add(btnExit);
        add(btnBack);
    }
    
    private void loadTable(String customerId){
        tblDefault.setRowCount(0);
        String customerName = null;

        try {
            customerName = CustomerController.getName(customerId);
        } catch (SQLException e) {
            throw new QueryFailException("Failed to retrieve customer name",e);
        }

        if (customerName == null){
            JOptionPane.showMessageDialog(null, "Invalid customer ID");
            return;
        }

        lblGetName.setText(customerName);
        List<Orders> orderList = new ArrayList<>();

        try {
            orderList = OrderController.byCustomerId(customerId);
        } catch (SQLException e) {
            throw new QueryFailException("Failed to retrieve orders",e);
        }

        if (orderList.isEmpty()){
            JOptionPane.showMessageDialog(null, "No orders for this customer");
            return;
        }

        for(Orders order: orderList){
            Object[] rowData={
                    order.getId(),
                    order.getQuantity(),
                    order.getValue(),
                    order.getStatus().toString()
            };

            tblDefault.addRow(rowData);
        }
    }
    
    private JButton createStyledButton(String text, int x, int y, int width, int r, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, 30);
        button.setForeground(Color.white);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(new Color(r, 82, 77));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
    
}
