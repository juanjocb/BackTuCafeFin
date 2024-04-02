package com.backtucafe.controller;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Business;
import com.backtucafe.model.Client;
import com.backtucafe.model.request.ChangePasswordRequest;
import com.backtucafe.model.request.LoginRequest;
import com.backtucafe.model.request.RegisterRequest;
import com.backtucafe.model.request.UpdateClientRequest;
import com.backtucafe.repository.BusinessRepository;
import com.backtucafe.service.ClientService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tuCafe/v1/client")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class ClientController {

    private final ClientService clientService;
    private final BusinessRepository businessRepository;

    //Controlador finalizado para DESPLEGAR Y PRESENTAR
    @PostMapping(value = "register")
    public ResponseEntity<String> registerClient(@RequestBody RegisterRequest request) throws MessagingException {
        return clientService.registerCliente(request);
    }

    //Controlador finalizado para DESPLEGAR Y PRESENTAR
    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(clientService.loginCliente(request));
    }

    @PutMapping("put/{id_client}")
    public Client updateClientProfile(@PathVariable Long id_client, @RequestBody UpdateClientRequest request) {
        return clientService.updateProfClient(id_client, request);
    }

    @GetMapping("{idClient}")
    public ResponseEntity<Client> getBusinessById(@PathVariable Long idClient) {
        Client client = clientService.findClientById(idClient);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{idClient}/changePassword")
    public ResponseEntity<String> changeBusinessPassword(@PathVariable Long idClient, @RequestBody ChangePasswordRequest request) {
        return clientService.changeClientPassword(idClient, request);
    }

    @GetMapping("/listBusiness")
    public List<Business>getBusiness(){
        return businessRepository.findAll();
    }
}