//package com.watchbe.watchbedemo.codetest;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.PaymentIntentCreateParams;
//import com.stripe.param.checkout.SessionCreateParams;
//import com.watchbe.watchbedemo.dto.CartItem;
//import com.watchbe.watchbedemo.dto.CreateCheckoutRequest;
//import com.watchbe.watchbedemo.model.*;
//import com.watchbe.watchbedemo.repository.CustomerRepository;
//import com.watchbe.watchbedemo.repository.OrderDetailsRepository;
//import com.watchbe.watchbedemo.repository.OrderRepository;
//import com.watchbe.watchbedemo.repository.OrderStatusRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("api/v1/checkout")
//@RequiredArgsConstructor
//public class CheckOutController {
//    private final OrderDetailsRepository orderDetailsRepository;
//    private final CustomerRepository customerRepository;
//    private final OrderRepository orderRepository;
//    private final OrderStatusRepository orderStatusRepository;
//
////    @PostMapping("/create-payment-session")
////    public String createPaymentIntent(@RequestBody CreateCheckOutRequest checkOutRequest) throws StripeException {
////
////        //check validation
////        if (checkOutRequest.getCartItems() == null || checkOutRequest.getCartItems().isEmpty()) {
////            throw new IllegalArgumentException("Cart items cannot be empty");
////        }
////        if (checkOutRequest.getCustomerId() == 0) {
////            throw new IllegalArgumentException("Customer id cannot be empty");
////        }
////        if (checkOutRequest.getCartItems().stream().anyMatch(cartItem -> cartItem.getQuantity() == 0)) {
////            throw new IllegalArgumentException("Quantity cannot be empty");
////        }
////        if (checkOutRequest.getCartItems().stream().anyMatch(cartItem -> cartItem.getPrice() == 0)) {
////            throw new IllegalArgumentException("Price cannot be empty");
////        }
////
////        //find all OrderDetail by cartItem's id
////        List<OrderDetails> orderDetailsList =
////                orderDetailsRepository.findAllById(checkOutRequest.getCartItems().stream().map(CartItem::getCartId).collect(Collectors.toList()));
////
////
////        Order order = Order.builder().customer().build();
////
////        SessionCreateParams params = new SessionCreateParams.Builder()
////                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
////                .setMode(SessionCreateParams.Mode.PAYMENT)
////                .setSuccessUrl("https://your_domain/checkout/success")
////                .setCancelUrl("https://your_domain/checkout/cancel")
////
////                .setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder().addAllAllowedCountry(
////                        new ArrayList<>(
////                                Arrays.asList(SessionCreateParams.ShippingAddressCollection.AllowedCountry.US,
////                                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VN)
////                        )).build())
////                .setPaymentIntentData(SessionCreateParams.PaymentIntentData.builder()
////                        .setCaptureMethod(SessionCreateParams.PaymentIntentData.CaptureMethod.MANUAL)
////                        .build())
////                .addLineItem(
////                        SessionCreateParams.LineItem.builder()
////                                .setQuantity(1L)
////                                .setPriceData(
////                                        SessionCreateParams.LineItem.PriceData.builder()
////                                                .setCurrency("vnd")
////                                                .setUnitAmount(checkOutRequest.getAmount() * 1000) // assuming the
////                                                // amount is
////                                                // in cents
////                                                .setProductData(
////                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
////                                                                .setName(checkOutRequest.getProductName())
////                                                                .build()
////                                                )
////                                                .build()
////                                )
////                                .build()
////                )
////                .build();
////
////        Session session = Session.create(params);
////
////        return session.getUrl();
////
////    }
//
//    @PostMapping("/create-payment-intent")
//    public ResponseEntity<Long> createPaymentIntent(@RequestBody CreateCheckoutRequest checkOutRequest) throws StripeException {
//                if (checkOutRequest.getOrderDetailsId() == null || checkOutRequest.getOrderDetailsId().isEmpty()) {
//            throw new IllegalArgumentException("Cart items cannot be empty");
//        }
//        if (checkOutRequest.getCustomerId() == 0) {
//            throw new IllegalArgumentException("Customer id cannot be empty");
//        }
//        if (checkOutRequest.getOrderDetailsId().stream().anyMatch(orderDetailId -> orderDetailId == 0)) {
//            throw new IllegalArgumentException("Quantity cannot be empty");
//        }
//
//
//        List<OrderDetails> orderDetailsList =
//                orderDetailsRepository.findAllById(checkOutRequest.getOrderDetailsId());
//
//        double totalAmount =  orderDetailsList.stream()
//                .mapToDouble(orderDetails -> orderDetails.getPrice() * orderDetails.getQuantity())
//                .sum();
//
//        Customer customer =
//                customerRepository.findById(checkOutRequest.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
//        OrderStatus orderStatus = OrderStatus.builder().status(Status.PENDING).orders(new ArrayList<>()).build();
//        orderStatusRepository.save(orderStatus);
//
//        Order order =
//                Order.builder().customer(customer).orderStatus(orderStatus).orderDate(new Date()).tax(100f).amount(totalAmount).shipping(100f).build();
//        order.setOrderDetails(orderDetailsList);
//        orderRepository.save(order);
//        return ResponseEntity.ok(order.getId());
//    }
//
//
//
//
//}
