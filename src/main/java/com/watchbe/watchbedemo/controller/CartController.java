package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.CartItem;
import com.watchbe.watchbedemo.dto.OrderDetailsDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.mapper.OrderDetailsMapperImpl;
import com.watchbe.watchbedemo.model.Cart;
import com.watchbe.watchbedemo.model.OrderDetails;
import com.watchbe.watchbedemo.model.Watch;
import com.watchbe.watchbedemo.repository.CartRepository;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import com.watchbe.watchbedemo.repository.OrderDetailsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @PutMapping("/update-quantity")
    public ResponseEntity<List<OrderDetailsDto>> updateItemQuantity(@RequestParam("cart") String cartId,
                                                                    @RequestParam("item") String itemId,
                                                                    @RequestParam("method") String method){
        return ResponseEntity.ok(new ArrayList<>());
    }

}
