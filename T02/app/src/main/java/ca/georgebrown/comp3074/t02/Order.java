package ca.georgebrown.comp3074.t02;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Order implements Serializable {

    private int orderId;
    private int subscriptionPlanPageId;
    private int customerId;
    private String orderDate;
    private float orderPrice;

    public Order(int orderId, int subscriptionPlanPageId, int customerId, String orderDate, float orderPrice) {
        this.orderId = orderId;
        this.subscriptionPlanPageId = subscriptionPlanPageId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSubscriptionPlanPageId() {
        return subscriptionPlanPageId;
    }

    public void setSubscriptionPlanPageId(int subscriptionPlanPageId) {
        this.subscriptionPlanPageId = subscriptionPlanPageId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return "Order " + orderId;
    }
}
