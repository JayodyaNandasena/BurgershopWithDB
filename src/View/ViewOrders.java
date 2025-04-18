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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
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
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        lblMessage = new JLabel("Please select the type of orders to view :");
        lblMessage.setFont(new Font("", Font.BOLD, 17));
        lblMessage.setBounds(30, 50, 700, 50);
        lblMessage.setVerticalAlignment(JLabel.CENTER);
        lblMessage.setHorizontalAlignment(JLabel.LEFT);
        add(lblMessage);
        
        group = new ButtonGroup();
        
        rdoPreparing=new JRadioButton("Preparing orders");
        rdoPreparing.setBounds(30, 100, 150, 30);
        group.add(rdoPreparing);
        rdoPreparing.addActionListener(evt -> {
            loadTable(1);
        });
        
        
        rdoDelivered=new JRadioButton("Delivered orders");
        rdoDelivered.setBounds(200, 100, 150, 30);
        group.add(rdoDelivered);
        rdoDelivered.addActionListener(evt -> {
            loadTable(2);
        });
        
        rdoCancelled=new JRadioButton("Cancelled orders");
        rdoCancelled.setBounds(380, 100, 150, 30);
        group.add(rdoCancelled);
        rdoCancelled.addActionListener(evt -> {
            loadTable(3);
        });
        
        rdoAll=new JRadioButton("All orders");
        rdoAll.setBounds(560, 100, 150, 30);
        group.add(rdoAll);
        rdoAll.addActionListener(evt -> {
            loadTable(-1);
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
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tblCustomerDetails.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        tblCustomerDetails.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );

        btnHome = createStyledButton("Main Menu", 420, 400, 130, 30, 155, 82, 77, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        
        btnExit = createStyledButton("Exit", 560, 400, 100, 30, 155, 82, 77, evt -> {
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
    
    private void loadTable(int status){
        tblDefault.setRowCount(0);
        
        Customer [] orderArray=CustomerController.toArray();
        
        for(int i=0;i<orderArray.length;i++){
            Customer obj = orderArray[i];
            int stts=obj.getOrderStatus();
            String sttsString=CustomerController.getStatusString(stts);
            if(stts==status){
                Object[] rowdata={
                    obj.getOrderId(),
                    obj.getCustomerId(),
                    obj.getCustomerName(),
                    obj.getOrderQTY(),
                    obj.getOrderValue(),
                    sttsString
                };
                tblDefault.addRow(rowdata);
            }else if (status==-1){
                Object[] rowdata={
                    obj.getOrderId(),
                    obj.getCustomerId(),
                    obj.getCustomerName(),
                    obj.getOrderQTY(),
                    obj.getOrderValue(),
                    sttsString
                };
                tblDefault.addRow(rowdata);
            }
        }
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
