package com.backtucafe.controller;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Admin;
import com.backtucafe.model.Business;
import com.backtucafe.model.request.*;
import com.backtucafe.repository.AdminRepository;
import com.backtucafe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tuCafe/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    //Controlador finalizado para DESPLEGAR Y PRESENTAR
    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginBusiness(@RequestBody LoginRequest request) {
        System.out.println("Entro al controlador");
        return ResponseEntity.ok(adminService.loginAdmin(request));
    }

    @GetMapping("/getAdmin")
    public List<Admin>getAdmin(){
        return adminRepository.findAll();
    }


    @PutMapping(value = "changeStatus/{idBusiness}")
    public ResponseEntity<String> changeBusinessStatus(@PathVariable Long idBusiness, @RequestBody ChangeStatusRequest request) {
        boolean statusChanged = adminService.changeBusinessStatus(idBusiness, request.getStatus());
        if (statusChanged) {
            return ResponseEntity.ok("Estado del establecimiento cambiado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo cambiar el estado del establecimiento");
        }
    }
}
