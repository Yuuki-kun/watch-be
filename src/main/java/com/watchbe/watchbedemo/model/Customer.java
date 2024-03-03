package com.watchbe.watchbedemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watchbe.watchbedemo.model.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private LocalDate dob;

    private String gender;
    private String avatarLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        for (Review r: reviews) {
            r.setCustomer(this);
        }
    }
    public void addReview(Review r){
        this.reviews.add(r);
        r.setCustomer(this);
    }

    public void removeReview(Review r){
        this.reviews.remove(r);
        r.setCustomer(null);
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();
    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
        for (ShippingAddress s: shippingAddresses) {
            s.setCustomer(this);
        }
    }
    public void addShippingAddress(ShippingAddress s){
        this.shippingAddresses.add(s);
        s.setCustomer(this);
    }
    public void removeShippingAddress(ShippingAddress s){
        this.shippingAddresses.remove(s);
        s.setCustomer(null);
    }
}
