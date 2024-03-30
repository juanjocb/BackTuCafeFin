package com.backtucafe.controller;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Business;
import com.backtucafe.model.request.*;
import com.backtucafe.service.BusinessService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tuCafe/v1/business")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class BusinessController {

    private final BusinessService businessService;

    //Controlador finalizado para DESPLEGAR Y PRESENTAR
    @PostMapping(value = "register")
    public ResponseEntity<String> registerBusiness(@RequestBody RegisterBusinessRequest request) throws MessagingException {
        return businessService.registerBusiness(request);
    }

    //Controlador finalizado para DESPLEGAR Y PRESENTAR
    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginBusiness(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(businessService.loginBusiness(request));
    }

    @PutMapping(value = "{idBusiness}")
    public Business updateBusinessProfile(@PathVariable Long idBusiness, @RequestBody UpdateBusinessRequest request) {
        return businessService.updateProfBusiness(idBusiness, request);
    }

    @GetMapping(value = "getBusinessActive")
    public List<Business> getBusinessActive(){
        return businessService.filterBusinessByActiveStatus();
    }

    @GetMapping(value = "getBusinessNotActive")
    public List<Business> getBusinessNotActive(){
        return businessService.filterBusinessByInactiveStatus();
    }

    @GetMapping("/{idBusiness}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long idBusiness) {
        Business business = businessService.findBusinessById(idBusiness);
        if (business != null) {
            return new ResponseEntity<>(business, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
