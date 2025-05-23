/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.OrderController;
import exception.QueryFailException;
import model.Orders;
import model.enums.OrderStatus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jayodya Chathumini
 */
public class ViewOrders extends JFrame {
    private JLabel lblTitle,lblMessage;
    private ButtonGroup group;
    private JRadioButton rdoPreparing,rdoDelivered,rdoCancelled,rdoAll;
    private JButton btnExit,btnHome;
    private JTable tblCustomerDetails;
    private DefaultTableModel tblDefault;
    
    public ViewOrders(){
        setTitle("View Order");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("View Orders");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        lblMessage = new JLabel("Please select the type of orders to view :");
        lblMessage.setFont(new Font("", Font.BOLD, 17));
        lblMessage.setBounds(30, 50, 700, 50);
        lblMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblMessage);
        
        group = new ButtonGroup();
        
        rdoPreparing=new JRadioButton("Preparing orders");
        rdoPreparing.setBounds(30, 100, 150, 30);
        group.add(rdoPreparing);
        rdoPreparing.addActionListener(evt -> {
            loadTable("PREPARING");
        });
        
        
        rdoDelivered=new JRadioButton("Delivered orders");
        rdoDelivered.setBounds(200, 100, 150, 30);
        group.add(rdoDelivered);
        rdoDelivered.addActionListener(evt -> {
            loadTable("DELIVERED");
        });
        
        rdoCancelled=new JRadioButton("Cancelled orders");
        rdoCancelled.setBounds(380, 100, 150, 30);
        group.add(rdoCancelled);
        rdoCancelled.addActionListener(evt -> {
            loadTable("CANCELLED");
        });
        
        rdoAll=new JRadioButton("All orders");
        rdoAll.setBounds(560, 100, 150, 30);
        group.add(rdoAll);
        rdoAll.addActionListener(evt -> {
            loadTable("ALL");
        });
        
        String[] columnName={"Order ID", "Customer ID", "Name", "Quantity", "Order value", "Order Status"};
        tblDefault=new DefaultTableModel(columnName,0);
		
        tblCustomerDetails=new JTable(tblDefault);
        tblCustomerDetails.setFont(new Font("",Font.PLAIN,14));
        
        JScrollPane tablePane=new JScrollPane(tblCustomerDetails);
        add(tablePane);
        tablePane.setBounds(30, 150, 630, 230);
        tablePane.setOpaque(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
        tblCustomerDetails.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );

        btnHome = createStyledButton("Main Menu", 420, 400, 130, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        
        btnExit = createStyledButton("Exit", 560, 400, 100, evt -> {
            System.exit(0);
        });
        
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/viewOrders.png")));

        add(rdoPreparing);
        add(rdoDelivered);
        add(rdoCancelled);
        add(rdoAll);
        add(btnHome);
        add(btnExit);
    }
    
    private void loadTable(String status){
        tblDefault.setRowCount(0);
        List<Orders> orders = new ArrayList<>();

        if (Objects.equals(status, "ALL")){
            try {
                orders = OrderController.all();
            } catch (SQLException e) {
                throw new QueryFailException("Failed to retrieve orders",e);
            }
        }else {
            try {
                orders = OrderController.byStatus(
                        OrderStatus.valueOf(status)
                );
            } catch (SQLException e) {
                throw new QueryFailException("Failed to retrieve orders",e);
            }
        }

        for (Orders order: orders){
            Object[] rowData={
                    order.getId(),
                    order.getCustomerId(),
                    order.getCustomer().getName(),
                    order.getQuantity(),
                    order.getValue(),
                    order.getStatus().toString()
            };
            tblDefault.addRow(rowData);
        }
    }
    
    private JButton createStyledButton(String text, int x, int y, int width, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, 30);
        button.setForeground(Color.white);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(new Color(155, 82, 77));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
