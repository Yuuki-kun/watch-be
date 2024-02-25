package com.securityjwt.demojwt.controller;

import com.securityjwt.demojwt.dto.CartItem;
import com.securityjwt.demojwt.dto.OrderDetailsDto;
import com.securityjwt.demojwt.exception.NotFoundException;
import com.securityjwt.demojwt.mapper.OrderDetailsMapperImpl;
import com.securityjwt.demojwt.model.Cart;
import com.securityjwt.demojwt.model.OrderDetails;
import com.securityjwt.demojwt.model.Watch;
import com.securityjwt.demojwt.repository.CartRepository;
import com.securityjwt.demojwt.repository.CustomerRepository;
import com.securityjwt.demojwt.repository.OrderDetailsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user-cart")
@RequiredArgsConstructor
public class CartController {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapperImpl orderDetailsMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<OrderDetailsDto>> getAllCart(@RequestParam("cart") Long cartId){
        List<OrderDetailsDto> orderDetailsDto =
                cartRepository.findById(cartId).orElseThrow().getOrderDetails().stream().map(
                        orderDetailsMapper::mapTo
                ).collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailsDto);
    }


    /**@apiNote
     *
     * @param cartItem product wanna add to cart
     * @return OrderDetailsDto
     */
    @PostMapping("/add-to-cart")
    public ResponseEntity<OrderDetailsDto> addToCart(@RequestBody CartItem cartItem){

        Cart cart = cartRepository.findById((long) cartItem.getCartId()).orElseThrow(()->new NotFoundException("cart " +
                "not found, " +
                "id="+cartItem.getCartId()));
        Watch watch = entityManager.getReference(Watch.class, cartItem.getWatchId());
        OrderDetails orderDetails = OrderDetails.builder()
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .watch(watch)
                .build();
        cart.addOrderDetails(orderDetails);
        cartRepository.save(cart);
        return ResponseEntity.ok(orderDetailsMapper.mapTo(orderDetails));
    }

    /**
     *
     * @param
     *
     * @return
     */
    @DeleteMapping("/delete-items")
    public ResponseEntity<List<OrderDetailsDto>> removeFromCart(@RequestParam("cart") String cartId,
                                                                @RequestParam("item") String orderDetailsId){
        Cart cart = cartRepository.findById(Long.valueOf(cartId)).orElseThrow(()-> new NotFoundException("cart " +
                "not found, " +
                "id="+cartId));
        List<OrderDetails> orderDetails = cart.getOrderDetails();
//        cart.setOrderDetails(new ArrayList<>());
        for (OrderDetails o:
             orderDetails) {
            if(o.getId().equals(Long.valueOf(orderDetailsId))){
                System.out.println("o="+o.getId()+"id="+orderDetailsId);
                cart.removeOrderDetails(o);
                orderDetailsRepository.delete(o);
                break;
            }
        }
        List<OrderDetailsDto> orderDetailsDto =
                cart.getOrderDetails().stream().map(
                        orderDetailsMapper::mapTo
                ).collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailsDto);
    }


}
