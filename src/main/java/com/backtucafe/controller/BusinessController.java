package com.backtucafe.controller;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Business;
import com.backtucafe.model.Client;
import com.backtucafe.model.request.LoginRequest;
import com.backtucafe.model.request.RegisterRequest;
import com.backtucafe.model.request.UpdateBusinessRequest;
import com.backtucafe.model.request.UpdateClientRequest;
import com.backtucafe.service.BusinessService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tuCafe/v1/business")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://front-tu-cafe-v3h2.vercel.app/")
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping(value = "register")
    public ResponseEntity<String> registerBusiness(@RequestBody RegisterRequest request) throws MessagingException {
        return ResponseEntity.ok(businessService.registerBusiness(request));
    }

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginBusiness(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(businessService.loginBusiness(request));
    }

    @PutMapping("/{id_business}")
    public Business updateBusinessProfile(@PathVariable Long id_business, @RequestBody UpdateBusinessRequest request) {
        return businessService.updateProfBusiness(id_business, request);
    }



}
