package Model;

public class Customer {
    private String orderId;
    private String customerId;
    private String customerName;
    private int orderStatus;
    private int orderQTY;
    private double orderValue;

    public Customer() {
    }

    public Customer(String customerId, String customerName, double orderValue) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderValue = orderValue;
    }

    public Customer(String orderId, String customerId, String customerName, int orderStatus, int orderQTY, double orderValue) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.orderQTY = orderQTY;
        this.orderValue = orderValue;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public String toString() {
        return STR."{ \{getOrderId()} - \{getCustomerId()} - \{getCustomerName()} - \{getOrderStatus()} - \{getOrderQTY()} - \{getOrderValue()} }";
    }
}