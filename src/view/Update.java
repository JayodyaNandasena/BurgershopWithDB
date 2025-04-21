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

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Jayodya Chathumini
 */
public class Update extends JFrame {
    private JLabel lblTitle, lblOrderId, lblCustId, lblCustName, lblQty, lblOrdervalue, lblStatus, lblMessage;
    private JTextField txtCustName, txtQty, txtCustId, txtValue, txtStatus, txtOrderID;
    private JButton btnSearch, btnUQty, btnUStatus, btnExit, btnHome, btnUpdate, btnOk;
    private JPanel panel1;
    private JSpinner spnQty;
    private JComboBox cmbStatus;

    public Update() {
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
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBackground(new Color(185, 82, 77));
        lblTitle.setOpaque(true);
        add(lblTitle);

        lblOrderId = new JLabel("Please enter the order ID : ");
        lblOrderId.setFont(new Font("", Font.BOLD, 17));
        lblOrderId.setBounds(30, 60, 270, 30);
        lblOrderId.setVerticalAlignment(SwingConstants.CENTER);
        lblOrderId.setHorizontalAlignment(SwingConstants.LEFT);

        txtOrderID = new JTextField();
        txtOrderID.setFont(new Font("", Font.PLAIN, 16));
        txtOrderID.setBounds(300, 60, 215, 30);

        panel1 = new JPanel();
        panel1.setBounds(0, 100, 700, 300);
        panel1.setLayout(null);
        panel1.setVisible(false);

        btnSearch = createStyledButton("Search", 555, 60, 100, 0, evt -> {
            String orderId = txtOrderID.getText().trim();

            if (orderId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an order ID");
                txtOrderID.setBackground(new Color(250, 205, 212));
                return;
            }

            try {
                Orders order = OrderController.byId(orderId);

                if (order == null) {
                    JOptionPane.showMessageDialog(null, "Invalid order ID");
                    txtOrderID.setBackground(new Color(250, 205, 212));
                    return;
                }

                txtOrderID.setBackground(new Color(238, 238, 238));
                panel1.setVisible(true);
                loadData(order);

                boolean isDelivered = order.getStatus() == OrderStatus.DELIVERED;
                boolean isCancelled = order.getStatus() == OrderStatus.CANCELLED;

                if (isDelivered || isCancelled) {
                    String statusText = isDelivered ? "delivered" : "cancelled";
                    lblMessage.setText(String.format(
                            "<html>* This order is already %s,<br>&nbsp You cannot update this order</html>",
                            statusText
                    ));
                    disableEditing();
                    btnOk.setVisible(true);
                } else {
                    lblMessage.setText("");
                    enableEditing();
                    btnOk.setVisible(false);
                }

            } catch (SQLException e) {
                throw new QueryFailException("Failed to retrieve order by ID", e);
            }
        });

        btnUQty = createStyledButton("Update Order Quantity", 50, 0, 280, 0, evt -> {
            btnUpdate.setEnabled(true);
            spnQty.setEnabled(true);
            cmbStatus.setEnabled(false);
        });
        btnUQty.setEnabled(false);
        panel1.add(btnUQty);

        btnUStatus = createStyledButton("Update Order Status", 370, 0, 280, 0, evt -> {
            btnUpdate.setEnabled(true);
            cmbStatus.setEnabled(true);
            spnQty.setEnabled(false);
        });
        btnUStatus.setEnabled(false);
        panel1.add(btnUStatus);

        lblCustId = new JLabel("Customer ID : ");
        lblCustId.setFont(new Font("", Font.BOLD, 16));
        lblCustId.setBounds(50, 60, 150, 30);
        lblCustId.setVerticalAlignment(SwingConstants.CENTER);
        lblCustId.setHorizontalAlignment(SwingConstants.LEFT);

        txtCustId = new JTextField("");
        txtCustId.setFont(new Font("", Font.PLAIN, 16));
        txtCustId.setBounds(200, 60, 100, 30);
        txtCustId.setHorizontalAlignment(SwingConstants.LEFT);
        txtCustId.setEditable(false);

        lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("", Font.BOLD, 16));
        lblCustName.setBounds(50, 100, 150, 30);
        lblCustName.setVerticalAlignment(SwingConstants.CENTER);
        lblCustName.setHorizontalAlignment(SwingConstants.LEFT);

        txtCustName = new JTextField("");
        txtCustName.setFont(new Font("", Font.PLAIN, 16));
        txtCustName.setBounds(200, 100, 100, 30);
        txtCustName.setHorizontalAlignment(SwingConstants.LEFT);
        txtCustName.setEditable(false);

        lblQty = new JLabel("Order Quantity : ");
        lblQty.setFont(new Font("", Font.BOLD, 16));
        lblQty.setBounds(50, 140, 150, 30);
        lblQty.setVerticalAlignment(SwingConstants.CENTER);
        lblQty.setHorizontalAlignment(SwingConstants.LEFT);

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, null, 1);
        spnQty = new JSpinner(model);
        spnQty.setBounds(200, 140, 100, 30);
        ((DefaultEditor) spnQty.getEditor()).getTextField().setEditable(false);
        spnQty.setEnabled(false);
        spnQty.addChangeListener(new ChangeListener() {
                                     public void stateChanged(ChangeEvent e) {
                                         txtValue.setText(Double.toString(OrderController.generateValue((Integer) spnQty.getValue())));
                                     }
                                 }
        );

        lblOrdervalue = new JLabel("Order value :   Rs. ");
        lblOrdervalue.setFont(new Font("", Font.BOLD, 16));
        lblOrdervalue.setBounds(50, 180, 150, 30);
        lblOrdervalue.setVerticalAlignment(SwingConstants.CENTER);
        lblOrdervalue.setHorizontalAlignment(SwingConstants.LEFT);

        txtValue = new JTextField("");
        txtValue.setFont(new Font("", Font.PLAIN, 16));
        txtValue.setBounds(200, 180, 150, 30);
        txtValue.setHorizontalAlignment(SwingConstants.LEFT);
        txtValue.setEditable(false);

        lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("", Font.BOLD, 16));
        lblStatus.setBounds(50, 220, 150, 30);
        lblStatus.setVerticalAlignment(SwingConstants.CENTER);
        lblStatus.setHorizontalAlignment(SwingConstants.LEFT);

        txtStatus = new JTextField("");
        txtStatus.setFont(new Font("", Font.PLAIN, 16));
        txtStatus.setBounds(200, 220, 100, 30);
        txtStatus.setHorizontalAlignment(SwingConstants.LEFT);

        lblMessage = new JLabel("");
        lblMessage.setFont(new Font("", Font.PLAIN, 12));
        lblMessage.setForeground(Color.red);
        lblMessage.setBounds(380, 220, 300, 30);
        lblMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);

        btnOk = createStyledButton("Ok", 580, 220, 70, 0, evt -> {
            reset();
        });
        btnOk.setVisible(false);


        cmbStatus = new JComboBox();
        cmbStatus.setModel(new DefaultComboBoxModel<>(
                new String[]{"PREPARING", "DELIVERED", "CANCELLED"})
        );
        cmbStatus.setBounds(200, 220, 150, 30);
        cmbStatus.setEnabled(false);

        btnUpdate = createStyledButton("Save Changes", 50, 400, 150, 155, evt -> {
            OrderStatus status = OrderStatus.valueOf(Objects.requireNonNull(cmbStatus.getSelectedItem()).toString());

            try {
                OrderController.update(
                        txtOrderID.getText(),
                        status,
                        (Integer) spnQty.getValue(),
                        Double.parseDouble(txtValue.getText())
                );
            } catch (SQLException e) {
                throw new QueryFailException("Error updating order", e);
            }
            JOptionPane.showMessageDialog(null, "Order updated successfully!");
            cmbStatus.setEnabled(false);
            spnQty.setEnabled(false);
            btnUpdate.setEnabled(false);
            reset();
        });
        btnUpdate.setEnabled(false);

        btnHome = createStyledButton("Main Menu", 420, 400, 130, 155, evt -> {
            if (btnUpdate.isEnabled()) {
                int option = JOptionPane.showOptionDialog(
                        null,
                        "Are you sure you want to go back to Main Menu? Changes you made might not be saved!",
                        "Confirm main menu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"No, back to update order", "Yes, back to Main menu"},
                        "No, back to update order");
                if (option == JOptionPane.NO_OPTION) {
                    this.setVisible(false);
                    new MainMenu().setVisible(true);
                }
            } else {
                this.setVisible(false);
                new MainMenu().setVisible(true);
            }
        });

        btnExit = createStyledButton("Exit", 560, 400, 100, 155, evt -> {
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

    private void loadData(Orders order) {
        txtCustId.setText(order.getCustomerId());
        txtCustName.setText(order.getCustomer().getName());
        spnQty.setValue(order.getQuantity());
        txtValue.setText(Double.toString(order.getValue()));

        switch (order.getStatus()) {
            case OrderStatus.PREPARING:
                cmbStatus.setSelectedItem("PREPARING");
                break;
            case OrderStatus.DELIVERED:
                cmbStatus.setSelectedItem("DELIVERED");
                break;
            case OrderStatus.CANCELLED:
                cmbStatus.setSelectedItem("CANCELLED");
                break;
        }
    }

    // Helper methods
    private void disableEditing() {
        btnUQty.setEnabled(false);
        btnUStatus.setEnabled(false);
        spnQty.setEnabled(false);
        cmbStatus.setEnabled(false);
    }

    private void enableEditing() {
        btnUQty.setEnabled(true);
        btnUStatus.setEnabled(true);
        spnQty.setEnabled(false);
        cmbStatus.setEnabled(false);
    }

    public void reset() {
        txtOrderID.setText("");
        panel1.setVisible(false);
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
