package com.watchbe.watchbedemo.auth;

import com.watchbe.watchbedemo.service.PaypalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PaypalService paypalService;
    @PostMapping("/register/{method}/{tempoCartId}")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable(value = "method", required = false) String method,
            @PathVariable(value = "tempoCartId", required = false) Long tempoCartId,
            @RequestBody RegisterRequest registerRequest,
            HttpServletResponse response
    ){

        return  authenticationService.register(method, tempoCartId, registerRequest, response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return  authenticationService.authenticate(authenticationRequest);

    }
    @PostMapping("/authenticate-checkout/{tempoCartId}")
    public ResponseEntity<AuthenticationResponse> authenticateCheckout(

            @RequestBody AuthenticationRequest authenticationRequest,
            @PathVariable("tempoCartId") Long tempoCartId){

        return  authenticationService.authenticateCheckout(tempoCartId,authenticationRequest);
    }


    @GetMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {

                authenticationService.refreshToken(request, response);
    }

    @GetMapping("/confirm")
    public ResponseEntity<AuthenticationResponse> confirm(@RequestParam("token") String token){

        return authenticationService.confirm(token);
    }

    @PostMapping("/test-payment")
    public ResponseEntity<String> testPayment(
            @RequestBody String paymentMethodId
    ) throws IOException {
        //get order info
        //create order
        paypalService.checkOrder("2");
//        checkoutService.processPaymentIntent();
        return ResponseEntity.ok("Payment successful");
    }
}
