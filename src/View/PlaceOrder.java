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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jayodya Chathumini
 */
public class PlaceOrder extends JFrame {
    private JButton btnPlaceOrder,btnCancel,btnHome,btnExit,btnAdd,btnOk;
    private JRadioButton rdoYes, rdoNo;
    private JSeparator seperator;
    private JLabel lblTitle,lblOrderID,lblCustID,lblcustName,lblQty,lblStatus;
    private JLabel lblOID,lblNetTotal,lblTot,lblStts,lblMessage;
    private JTextField txtCustID,txtCustName,txtQty;
    private JSpinner spnQty;
    private ButtonGroup group;

    public PlaceOrder() {
        setTitle("Place Order");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lblTitle = new JLabel("Place Order");
        lblTitle.setFont(new Font("", Font.BOLD, 25));
        lblTitle.setBounds(0, 0, 700, 50);
        lblTitle.setForeground(Color.white);
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);

        seperator = new JSeparator();

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 25, 400, 450);
        panel1.setLayout(null);

        lblOrderID = new JLabel("Order ID : ");
        lblOrderID.setFont(new Font("", Font.BOLD, 16));
        lblOrderID.setBounds(30, 50, 100, 40);
        panel1.add(lblOrderID);

        lblCustID = new JLabel("Customer ID : ");
        lblCustID.setFont(new Font("", Font.BOLD, 16));
        lblCustID.setBounds(30, 200, 150, 40);
        panel1.add(lblCustID);

        lblcustName = new JLabel("Customer Name : ");
        lblcustName.setFont(new Font("", Font.BOLD, 16));
        lblcustName.setBounds(30, 250, 150, 40);
        panel1.add(lblcustName);

        lblQty = new JLabel("Burger Quantity : ");
        lblQty.setFont(new Font("", Font.BOLD, 16));
        lblQty.setBounds(30, 300, 150, 40);
        panel1.add(lblQty);

        lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("", Font.BOLD, 16));
        lblStatus.setBounds(30, 350, 150, 40);
        panel1.add(lblStatus);

        lblOID = new JLabel(CustomerController.generateOrderId());
        lblOID.setFont(new Font("", Font.BOLD, 16));
        lblOID.setBounds(180, 55, 200, 30);
        panel1.add(lblOID);

        txtCustID = new JTextField();
        txtCustID.setFont(new Font("", Font.PLAIN, 16));
        txtCustID.setBounds(180, 205, 200, 30);
        txtCustID.setEditable(false);
        panel1.add(txtCustID);

        txtCustName = new JTextField();
        txtCustName.setFont(new Font("", Font.PLAIN, 16));
        txtCustName.setBounds(180, 255, 200, 30);
        txtCustName.setEditable(false);
        panel1.add(txtCustName);
        
        SpinnerNumberModel model=new SpinnerNumberModel(0,0, null, 1);
        spnQty= new JSpinner(model);
        spnQty.setBounds(180, 305, 200, 30);
        spnQty.setEnabled(false);
        panel1.add(spnQty);

        lblStts = new JLabel("Pending Order...");
        lblStts.setFont(new Font("", Font.ITALIC, 16));
        lblStts.setBounds(180, 355, 200, 30);
        panel1.add(lblStts);
        
        lblMessage = new JLabel("Have you ordered with us before?");
        lblMessage.setFont(new Font("", Font.BOLD, 19));
        lblMessage.setBounds(30, 100, 340, 40);
        panel1.add(lblMessage);

        group = new ButtonGroup();

        rdoYes = new JRadioButton("Yes");
        rdoYes.setFont(new Font("", Font.PLAIN, 15));
        rdoYes.setBounds(40, 140, 100, 30);
        rdoYes.setVerticalAlignment(JLabel.CENTER);
        rdoYes.setHorizontalAlignment(JLabel.LEFT);
        rdoYes.setOpaque(true);
        rdoYes.setFocusable(false);
        group.add(rdoYes);

        rdoYes.addActionListener(evt -> {
            changeSpinner(1,1,true);
            txtCustID.setText("");
            txtCustID.setEditable(true);
            txtCustID.setBackground(Color.white);
            txtCustName.setEditable(false);
            spnQty.setEnabled(true);
            btnOk.setEnabled(true);
            btnCancel.setEnabled(true);
            btnHome.setEnabled(false);
        });
        panel1.add(rdoYes);

        rdoNo = new JRadioButton("No");
        rdoNo.setFont(new Font("", Font.PLAIN, 15));
        rdoNo.setBounds(140, 140, 100, 30);
        rdoNo.setVerticalAlignment(JLabel.CENTER);
        rdoNo.setHorizontalAlignment(JLabel.LEFT);
        rdoNo.setOpaque(true);
        rdoNo.setFocusable(false);
        group.add(rdoNo);

        rdoNo.addActionListener(evt -> {
            
            txtCustID.setText(CustomerController.generateCustomerId());
            txtCustID.setEditable(false);
            txtCustName.setEditable(true);
            btnOk.setEnabled(true);
            btnCancel.setEnabled(true);
            btnHome.setEnabled(false);
            changeSpinner(1,1,true);
        });
        panel1.add(rdoNo);
        add(panel1);

        btnOk = createStyledButton("OK", 300, 390, 60, 30, 0, 82, 77, false, evt -> {
            if (validateCustomer()) {
                    txtCustID.setBackground(new Color(238, 238, 238));
                    txtCustName.setBackground(new Color(238, 238, 238));
                    lblStts.setText("PREPARING...");
                    rdoYes.setEnabled(false);
                    rdoNo.setEnabled(false);
                    txtCustID.setEditable(false);
                    txtCustName.setEditable(false);
                    spnQty.setEnabled(false);
                    btnOk.setEnabled(false);
                    btnPlaceOrder.setEnabled(true);
            }
        });
        panel1.add(btnOk);

        JPanel panel2 = new JPanel();
        panel2.setBounds(400, 50, 300, 450);
        panel2.setBackground(new Color(215, 215, 215));
        panel2.setLayout(null);

        btnPlaceOrder = createStyledButton("Place order", 65, 70, 170, 40, 0, 82, 77, false, evt -> {
            JOptionPane.showMessageDialog(null, "Order added successfully!");
            Customer orderObj = new Customer(
                    lblOID.getText(),
                    txtCustID.getText(),
                    txtCustName.getText(),
                    1,
                    (Integer)spnQty.getValue(),
                    Double.parseDouble(lblTot.getText()) 
            );
            CustomerController.add(orderObj);
            reset(true);
        });
        panel2.add(btnPlaceOrder);

        btnHome = createStyledButton("Back to home page", 65, 190, 170, 40, 185, 82, 77, true, evt -> {
            this.setVisible(false);
            new MainMenu().setVisible(true);
        });
        panel2.add(btnHome);

        btnCancel = createStyledButton("Cancel Order", 65, 130, 170, 40, 185, 82, 77, false, evt -> {
            int option = JOptionPane.showOptionDialog(
                    null,
                    "Re you sure you want to cancel order?",
                    "Confirm cancel",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[] { "No, back to order", "Yes, cancel my order" },
                    "No, back to order");
            if (option == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Order cancelled!");
                reset(false);
            } else if (option == JOptionPane.YES_OPTION) {

            }
        });
        panel2.add(btnCancel);

        lblNetTotal = new JLabel("NET Total : Rs. ");
        lblNetTotal.setFont(new Font("", Font.BOLD, 16));
        lblNetTotal.setBounds(30, 255, 150, 30);
        panel2.add(lblNetTotal);

        lblTot = new JLabel("");
        lblTot.setFont(new Font("", Font.BOLD, 16));
        lblTot.setBounds(130, 255, 140, 30);
        lblTot.setForeground(new Color(185, 82, 77));
        lblTot.setVerticalAlignment(JLabel.CENTER);
        lblTot.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(lblTot);

        add(panel2);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/placeOrders.png")));

    }

    public JButton createStyledButton(String text, int x, int y, int width, int height, int r, int g, int b,
            boolean isEnabled, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setBounds(x, y, width, height);
        button.setForeground(Color.white);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(new Color(r, g, b));
        button.setOpaque(true);
        button.setFocusable(false);
        button.setEnabled(isEnabled);
        button.addActionListener(actionListener);
        return button;
    }

    public void reset(boolean nextID) {
        txtCustID.setText("");
        txtCustName.setText("");
        changeSpinner(0, 0, false);
        lblStts.setText("Pending Order...");
        lblTot.setOpaque(false);
        btnOk.setEnabled(false);
        btnPlaceOrder.setEnabled(false);
        btnCancel.setEnabled(false);
        group.clearSelection();
        rdoYes.setEnabled(true);
        rdoNo.setEnabled(true);
        btnHome.setEnabled(true);

        if (nextID) {
            lblOID.setText(CustomerController.generateOrderId());
        }

    }
    
    public void changeSpinner(int value, int min, boolean isEnabled){
        SpinnerNumberModel modelNew=new SpinnerNumberModel(value,min, null, 1);
        spnQty.setModel(modelNew);
        ((JSpinner.DefaultEditor) spnQty.getEditor()).getTextField().setEditable(false);
        lblTot.setText(Double.toString(CustomerController.generateValue(
                (Integer)spnQty.getValue())));
        spnQty.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lblTot.setText(Double.toString(CustomerController.generateValue(
                        (Integer)spnQty.getValue())));
            }
          }
        );
        spnQty.setEnabled(isEnabled);
    }

    public boolean validateCustomer() {
        boolean validCustId = false;
        if (rdoYes.isSelected()) {
            String id = txtCustID.getText();
            if (!id.isEmpty()) {
                String custName = CustomerController.getCustomerName(id);
                if (custName != null) {
                    txtCustName.setText(custName);
                    validCustId = true;
                } else {
                    int option = JOptionPane.showOptionDialog(
                            null,
                            "Incorrect Customer ID. Would you like to try again or generate a new ID?",
                            "Incorrect Customer ID",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[] { "Try Again", "Generate new ID" },
                            "Try Again");
                    if (option == JOptionPane.YES_OPTION) {
                        txtCustID.setBackground(new Color(250, 205, 212));
                    } else {
                        txtCustID.setText(CustomerController.generateCustomerId());
                        txtCustID.setBackground(new Color(238, 238, 238));
                        txtCustID.setEditable(false);
                        txtCustName.setEditable(true);
                        rdoNo.setSelected(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a customer ID");
                txtCustID.setBackground(new Color(250, 205, 212));
                txtCustName.setBackground(new Color(238, 238, 238));
            }
        } else {
            String name = txtCustName.getText();
            if (!name.isEmpty()) {
                if (CustomerController.validateCustomerName(name)) {
                    validCustId = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid customer name (ex: Kamal)");
                    txtCustName.setBackground(new Color(250, 205, 212));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a customer name (ex: Kamal)");
                txtCustName.setBackground(new Color(250, 205, 212));
            }
        }
        return validCustId;
    }
}
