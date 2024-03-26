package com.backtucafe.service;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Admin;
import com.backtucafe.model.Business;
import com.backtucafe.model.request.LoginRequest;
import com.backtucafe.repository.AdminRepository;
import com.backtucafe.repository.BusinessRepository;
import com.backtucafe.repository.CategoryRepository;
import com.backtucafe.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final BusinessRepository businessRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public TokenResponse loginAdmin(LoginRequest request) {
        System.out.println("Entro al servicio");
        System.out.println(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        System.out.println("Paso el autenticador");
        Admin admin = adminRepository.findByEmail(request.getEmail());
        System.out.println(admin);
        System.out.println(request);
        String token = tokenUtils.getTokenAdmin(admin);
        System.out.println(token);
        return TokenResponse.builder()
                .token(token)
                .role(admin.getRole())
                .build();
    }

    public boolean changeBusinessStatus(Long idBusiness, boolean newStatus) {
        Business business = businessRepository.findById(idBusiness)
                .orElseThrow(() -> new RuntimeException("Business no encontrado"));

        if (business != null) {
            business.setStatus(newStatus);
            businessRepository.save(business);
            return true;
        } else {
            return false;
        }
    }
}
