package model;

import model.enums.OrderStatus;

public class Orders {
    private String id;
    private String customerId;
    private OrderStatus status;
    private int quantity;
    private double value;

    public Orders() {
    }

    public Orders(String id, String customerId, OrderStatus status, int quantity, double value) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.quantity = quantity;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return STR."Orders{id='\{id}\{'\''}, customerId='\{customerId}\{'\''}, status=\{status}, quantity=\{quantity}, value=\{value}\{'}'}";
    }
}
