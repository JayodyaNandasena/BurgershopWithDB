package controller;

import db.CustomerList;
import model.Customer;

public class CustomerController {

    // orders array
    public static CustomerList list = new CustomerList();

    // generate order Id
    public static String generateOrderId() {
        if (list.size() == 0) {
            return "B0001";
        }
        String lastOrderId = list.get(list.size() - 1).getOrderId();
        int number = Integer.parseInt(lastOrderId.split("B")[1]); //1
        number++;//2
        return String.format("B%04d", number); //printf("",) //B0002
    }

    // generate customer Id
    public static String generateCustomerId() {
        if (list.size() == 0) {
            return "C0001";
        }
        String lastCustomerId = list.get(list.size() - 1).getCustomerId();
        int number = Integer.parseInt(lastCustomerId.split("C")[1]);
        number++;
        return String.format("C%04d", number);
    }

    // validation Customer Name
    public static boolean validateCustomerName(String custName) {
        return !Character.isDigit(custName.charAt(0));
    }

    // generate order value
    public static double generateValue(int quantity) {
        return quantity * 500.00;
    }

    // view orders details
    public static String viewOrderDetails(int i) {
        String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", list.get(i).getOrderId(), list.get(i).getCustomerId(),
                list.get(i).getCustomerName(), list.get(i).getOrderQTY(), list.get(i).getOrderValue());
        return line;
    }

    //searching for customer name
    public static String getCustomerName(String customerId) {
        String name = null;
        for (int i = 0; i < list.size(); i++) {
            if (customerId.equalsIgnoreCase(list.get(i).getCustomerId())) {
                name = list.get(i).getCustomerName();
                break;
            }
        }
        return name;
    }

    //searching for order details
    public static Customer getOrderDetails(String orderID) {
        for (int i = 0; i < list.size(); i++) {
            Customer customer = list.get(i);
            if (orderID.equalsIgnoreCase(customer.getOrderId())) {
                return customer;
            }
        }
        return null;
    }

    //searching for an order
    public static Boolean isExistOrder(String orderId) {
        for (int i = 0; i < list.size(); i++) {
            if (orderId.equalsIgnoreCase(list.get(i).getOrderId())) {
                return true;
            }
        }
        return false;
    }

    //add order
    public static void add(Customer data) {
        list.add(data);
    }

    //get orders as an array
    public static Customer[] toArray() {
        return list.toArray();
    }

    public static Customer[] sortCustomers() {
        Customer[] customer = list.toArray();
        Customer[] sortCustomer = new Customer[0];

        for (int i = 0; i < customer.length; i++) {
            boolean isExist = false;
            for (int j = 0; j < sortCustomer.length; j++) {
                if (sortCustomer[j].getCustomerId().equals(customer[i].getCustomerId())) {
                    if (customer[i].getOrderStatus() != 3) {
                        double tot = sortCustomer[j].getOrderValue();
                        sortCustomer[j].setOrderValue(tot += customer[i].getOrderValue());
                    }
                    isExist = true;
                }
            }
            if (!isExist) {
                Customer[] tempSortCustomer = new Customer[sortCustomer.length + 1];
                for (int j = 0; j < sortCustomer.length; j++) {
                    tempSortCustomer[j] = sortCustomer[j];
                }
                tempSortCustomer[tempSortCustomer.length - 1] = new Customer(customer[i].getCustomerId(), customer[i].getCustomerName(), customer[i].getOrderValue());
                sortCustomer = tempSortCustomer;
            }

//        if (!isExist) {
//            Customer[] tempSortCustomer = new Customer[sortCustomer.length + 1];
//            for (int j = 0; j < sortCustomer.length; j++) {
//                tempSortCustomer[j] = sortCustomer[j];
//            }
//            tempSortCustomer[tempSortCustomer.length - 1] = customer[i];
//            sortCustomer = tempSortCustomer;
//        }
        }
        // Bubble sort
        for (int i = 0; i < sortCustomer.length - 1; i++) {
            for (int j = 0; j < sortCustomer.length - i - 1; j++) {
                if (sortCustomer[j].getOrderValue() < sortCustomer[j + 1].getOrderValue()) {
                    Customer temp = sortCustomer[j];
                    sortCustomer[j] = sortCustomer[j + 1];
                    sortCustomer[j + 1] = temp;
                }
            }
        }
        return sortCustomer;
    }


    //size of linked list
    public static int size() {
        return list.size();
    }

    //get String value of order status
    public static String getStatusString(int code) {
        switch (code) {
            case 1:
                return "PREPARING";
            case 2:
                return "DELIVERED";
            case 3:
                return "CANCEL";
            default:
                return "INVALID";
        }
    }

}