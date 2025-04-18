package model;

public class CustomerInOrder {
    private String customerId;
    private String customerName;
    private Double totalOrderValue;

    public CustomerInOrder(String customerId, String customerName, Double totalOrderValue) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalOrderValue = totalOrderValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(Double totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }
}
