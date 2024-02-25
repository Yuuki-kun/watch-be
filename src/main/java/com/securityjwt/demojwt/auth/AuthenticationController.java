package com.securityjwt.demojwt.auth;

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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest,
            HttpServletResponse response
    ){
        return  authenticationService.register(registerRequest, response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return  authenticationService.authenticate(authenticationRequest);

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


}
