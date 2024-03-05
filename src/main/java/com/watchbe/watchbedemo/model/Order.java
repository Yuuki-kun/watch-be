package com.watchbe.watchbedemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order__")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Date orderDate;

    private double amount;
    private float tax;
    private float shipping;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddress address;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
        for (OrderDetails od: orderDetails) {
            od.setOrder(this);
        }
    }
    public void addOrderDetails(OrderDetails od){
        this.orderDetails.add(od);
        od.setOrder(this);
    }

    public void removeOrderDetails(OrderDetails od){
        this.orderDetails.remove(od);
        od.setOrder(null);
    }

    public  void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    orderStatus.getOrders().add(this);}

}
