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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jayodya Chathumini
 */
public class Update extends JFrame {
    private JLabel lblTitle, lblOrderId, lblCustId, lblCustName, lblQty, lblOrdervalue,lblStatus,lblMessage;
    private JTextField txtCustName,txtQty,txtCustId,txtValue,txtStatus,txtOrderID;
    private JButton btnSearch,btnUQty,btnUStatus,btnExit,btnHome,btnUpdate,btnOk;
    private JPanel panel1;
    private JSpinner spnQty;
    private JComboBox cmbStatus;
    
    public Update(){
        setTitle("Update Orders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("Update Orders");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);
        
        lblOrderId = new JLabel("Please enter the order ID : ");
        lblOrderId.setFont(new Font("", Font.BOLD, 17));
        lblOrderId.setBounds(30, 60, 270, 30);
        lblOrderId.setVerticalAlignment(JLabel.CENTER);
        lblOrderId.setHorizontalAlignment(JLabel.LEFT);
        
        txtOrderID = new JTextField();
        txtOrderID.setFont(new Font("", Font.PLAIN, 16));
        txtOrderID.setBounds(300, 60, 215, 30);
        
        panel1=new JPanel();
        panel1.setBounds(0, 100, 700, 300);
        panel1.setLayout(null);
        panel1.setVisible(false);
        
        btnSearch = createStyledButton("Search", 555, 60, 100, 30, 0, 82, 77, evt -> {
            String orderId=txtOrderID.getText();
            if(orderId.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter an order ID");
                txtOrderID.setBackground(new Color(250, 205, 212));
            }else{
                if(CustomerController.isExistOrder(orderId)){
                    txtOrderID.setBackground(new Color(238, 238, 238));
                    panel1.setVisible(true);
                    int status=loadData(orderId);
                    if(status==2){
                        lblMessage.setText("<html>* This order is already delivered,<br>&nbsp You cannot update this order</html>");
                        btnUQty.setEnabled(false);
                        btnUStatus.setEnabled(false);
                        btnOk.setVisible(true);
                    }else if(status==3){
                        lblMessage.setText("<html>* This order is already cancelled,<br>&nbsp You cannot update this order</html>");
                        btnUQty.setEnabled(false);
                        btnUStatus.setEnabled(false);
                        btnOk.setVisible(true);
                    }else{
                        btnUQty.setEnabled(true);
                        btnUStatus.setEnabled(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid order ID");
                    txtOrderID.setBackground(new Color(250, 205, 212));
                }
                
            }
        });
        
        btnUQty = createStyledButton("Update Order Quantity", 50, 0, 280, 30, 0, 82, 77, evt -> {
            btnUpdate.setEnabled(true);
            spnQty.setEnabled(true);
            cmbStatus.setEnabled(false);
        });
        btnUQty.setEnabled(false);
        panel1.add(btnUQty);
        
        btnUStatus = createStyledButton("Update Order Status", 370, 0, 280, 30, 0, 82, 77, evt -> {
            btnUpdate.setEnabled(true);
            cmbStatus.setEnabled(true);
            spnQty.setEnabled(false);
        });
        btnUStatus.setEnabled(false);
        panel1.add(btnUStatus);
        
        lblCustId = new JLabel("Customer ID : ");
        lblCustId.setFont(new Font("", Font.BOLD, 16));
        lblCustId.setBounds(50, 60, 150, 30);
        lblCustId.setVerticalAlignment(JLabel.CENTER);
        lblCustId.setHorizontalAlignment(JLabel.LEFT);
        
        txtCustId = new JTextField("");
        txtCustId.setFont(new Font("", Font.PLAIN, 16));
        txtCustId.setBounds(200, 60, 100, 30);
        //txtCustId.setVerticalAlignment(JLabel.CENTER);
        txtCustId.setHorizontalAlignment(JLabel.LEFT);
        txtCustId.setEditable(false);
        
        lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("", Font.BOLD, 16));
        lblCustName.setBounds(50, 100, 150, 30);
        lblCustName.setVerticalAlignment(JLabel.CENTER);
        lblCustName.setHorizontalAlignment(JLabel.LEFT);
        
        txtCustName = new JTextField("");
        txtCustName.setFont(new Font("", Font.PLAIN, 16));
        txtCustName.setBounds(200, 100, 100, 30);
        //txtCustName.setVerticalAlignment(JLabel.CENTER);
        txtCustName.setHorizontalAlignment(JLabel.LEFT);
        txtCustName.setEditable(false);
        
        lblQty = new JLabel("Order Quantity : ");
        lblQty.setFont(new Font("", Font.BOLD, 16));
        lblQty.setBounds(50, 140, 150, 30);
        lblQty.setVerticalAlignment(JLabel.CENTER);
        lblQty.setHorizontalAlignment(JLabel.LEFT);
        
        SpinnerNumberModel model=new SpinnerNumberModel(1, 1, null, 1);
        spnQty= new JSpinner(model);
        spnQty.setBounds(200, 140, 100, 30);
        ((DefaultEditor) spnQty.getEditor()).getTextField().setEditable(false);
        spnQty.setEnabled(false);
        spnQty.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                txtValue.setText(Double.toString(CustomerController.generateValue((Integer)spnQty.getValue())));
            }
          }
        );
        
        lblOrdervalue = new JLabel("Order value :   Rs. ");
        lblOrdervalue.setFont(new Font("", Font.BOLD, 16));
        lblOrdervalue.setBounds(50, 180, 150, 30);
        lblOrdervalue.setVerticalAlignment(JLabel.CENTER);
        lblOrdervalue.setHorizontalAlignment(JLabel.LEFT);
        
        txtValue = new JTextField("");
        txtValue.setFont(new Font("", Font.PLAIN, 16));
        txtValue.setBounds(200, 180, 150, 30);
        txtValue.setHorizontalAlignment(JLabel.LEFT);
        txtValue.setEditable(false);
        
        lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("", Font.BOLD, 16));
        lblStatus.setBounds(50, 220, 150, 30);
        lblStatus.setVerticalAlignment(JLabel.CENTER);
        lblStatus.setHorizontalAlignment(JLabel.LEFT);
        
        txtStatus = new JTextField("");
        txtStatus.setFont(new Font("", Font.PLAIN, 16));
        txtStatus.setBounds(200, 220, 100, 30);
        txtStatus.setHorizontalAlignment(JLabel.LEFT);
        
        lblMessage = new JLabel("");
        lblMessage.setFont(new Font("", Font.PLAIN, 12));
        lblMessage.setForeground(Color.red);
        lblMessage.setBounds(380, 220, 300, 30);
        lblMessage.setVerticalAlignment(JLabel.CENTER);
        lblMessage.setHorizontalAlignment(JLabel.LEFT);
        
        btnOk = createStyledButton("Ok", 580, 220, 70, 30, 0, 82, 77, evt -> {
            reset();
        });
        btnOk.setVisible(false);
        
        
        cmbStatus=new JComboBox();
        cmbStatus.setModel(new DefaultComboBoxModel<> (new String[]{"PREPARING","DELIVERED","CANCELLED"}));
        cmbStatus.setBounds(200, 220, 150, 30);
        cmbStatus.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String newStatus=cmbStatus.getSelectedItem().toString();
                
            }
        });
        cmbStatus.setEnabled(false);
        
        btnUpdate = createStyledButton("Save Changes", 50, 400, 150, 30, 155, 82, 77, evt -> {
            String newStatus=cmbStatus.getSelectedItem().toString();
            int status;
            if(newStatus.equals("PREPARING")){
                status=1;
            }else if(newStatus.equals("DELIVERED")){
                status=2;
            }else{
                status=3;
            }
            updateData(txtOrderID.getText(), (Integer)spnQty.getValue(), Double.parseDouble(txtValue.getText()), status);
            JOptionPane.showMessageDialog(null, "Order updated successfully!");
            cmbStatus.setEnabled(false);
            spnQty.setEnabled(false);
            btnUpdate.setEnabled(false);
            reset();
        });
        btnUpdate.setEnabled(false);
        
        btnHome = createStyledButton("Main Menu", 420, 400, 130, 30, 155, 82, 77, evt -> {
            if(btnUpdate.isEnabled()){
                int option = JOptionPane.showOptionDialog(
                        null,
                        "Are you sure you want to go back to Main Menu? Changes you made might not be saved!",
                        "Confirm main menu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[] { "No, back to update order", "Yes, back to Main menu" },
                        "No, back to update order");
                if (option == JOptionPane.NO_OPTION) {
                    this.setVisible(false);
                    new MainMenu().setVisible(true);  
                }
            }else{
                this.setVisible(false);
                new MainMenu().setVisible(true);
            }
            
            
        });
        
        btnExit = createStyledButton("Exit", 560, 400, 100, 30, 155, 82, 77, evt -> {
            System.exit(0);
        });
        
        panel1.add(lblCustId);
        panel1.add(lblCustName);
        panel1.add(lblQty);
        panel1.add(lblOrdervalue);
        panel1.add(lblStatus);
        panel1.add(lblMessage);
        panel1.add(btnOk);
        panel1.add(txtCustId);
        panel1.add(txtCustName);
        panel1.add(spnQty);
        panel1.add(txtValue);
        panel1.add(cmbStatus);
        
        add(btnSearch);
        add(lblOrderId);
        add(txtOrderID);
        add(panel1);
        add(btnHome);
        add(btnExit);
        add(btnUpdate);
        
        
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/updateOrder.png")));
    }
    
    private int loadData(String orderId){
        Customer customer=CustomerController.getOrderDetails(orderId);
        txtCustId.setText(customer.getCustomerId());
        txtCustName.setText(customer.getCustomerName());
        spnQty.setValue(customer.getOrderQTY());
        txtValue.setText(Double.toString(customer.getOrderValue()));
        int status=customer.getOrderStatus();
        switch(status){
            case 1:cmbStatus.setSelectedItem("PREPARING");break;
            case 2:cmbStatus.setSelectedItem("DELIVERED");break;
            case 3:cmbStatus.setSelectedItem("CANCELLED");break;
        }
        return status;
    }
    
    private void updateData(String orderId, int qty, double value, int status){
        Customer customer=CustomerController.getOrderDetails(orderId);
        customer.setOrderQTY(qty);
        customer.setOrderValue(value);
        customer.setOrderStatus(status);
    }
    
    public void reset() {
        txtOrderID.setText("");
        panel1.setVisible(false);
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
