package com.watchbe.watchbedemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    @OrderBy("id ASC") // Sắp xếp danh sách orderDetails theo id
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public void setOrderDetails (List<OrderDetails> orderDetails){
        this.orderDetails = orderDetails;
        for(OrderDetails od : orderDetails){
            od.setCart(this);
        }
    }
    public void addOrderDetails (OrderDetails orderDetails){
        this.orderDetails.add(orderDetails);
        orderDetails.setCart(this);
    }
    public void removeOrderDetails(OrderDetails orderDetails){
        this.orderDetails.remove(orderDetails);
        orderDetails.setCart(null);
    }

}
