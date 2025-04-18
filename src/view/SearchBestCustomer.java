/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerController;
import model.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jayodya Chathumini
 */
public class SearchBestCustomer extends JFrame {
    
    private JLabel lblTitle;
    private JButton btnBack, btnExit, btnHome;
    private JTable tblCustomerDetails;
    private DefaultTableModel tblDefault;
    
    public SearchBestCustomer(){
        setTitle("Search Best Customer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        lblTitle = new JLabel("Search Best Customer");
        lblTitle.setFont(new Font("",Font.BOLD,25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(new Color(185,82,77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        String[] columnName={"Customer ID", "Name", "Total Order value"};
        tblDefault=new DefaultTableModel(columnName,0);
		
        tblCustomerDetails=new JTable(tblDefault);
        tblCustomerDetails.setFont(new Font("",Font.PLAIN,14));
        
        JScrollPane tablePane=new JScrollPane(tblCustomerDetails);
        add(tablePane);
        tablePane.setBounds(50, 75, 600, 300);
        tablePane.setOpaque(true);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tblCustomerDetails.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        tblDefault.setRowCount(0);
        
        Customer [] orderArray=CustomerController.sortCustomers();
        
        for(int i=0;i<orderArray.length;i++){
            Customer obj = orderArray[i];
            Object[] rowdata={
                obj.getCustomerId(),
                obj.getCustomerName(),
                obj.getOrderValue()
            };
            tblDefault.addRow(rowdata);
        }
        
        btnHome = createStyledButton("Main Menu", 300, 400, 130, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        add(btnHome);
        
        btnBack = createStyledButton("Back", 440, 400, 100, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new Search().setVisible(true);
        });
        add(btnBack);
        
        btnExit = createStyledButton("Exit", 550, 400, 100, 30, 155, 82, 77, evt -> {
            System.exit(0);
        });
        add(btnExit);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/searchCustomer.png")));

    }
    
    public JButton createStyledButton(String text,int x, int y, int width, int height,int r, int g, int b, ActionListener actionListener) {
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
