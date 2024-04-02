package com.backtucafe.controller;

import com.backtucafe.model.Business;
import com.backtucafe.model.View;
import com.backtucafe.model.request.ViewRequest;
import com.backtucafe.repository.ViewRepository;
import com.backtucafe.service.ViewService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tuCafe/v1/view")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ViewController {

    private final ViewService viewService;
    private final ViewRepository viewRepository;

    @PostMapping(value = "newView")
    public String view(@RequestBody ViewRequest request) throws MessagingException {
        return viewService.view(request);
    }


    @GetMapping("views")
    public List<View>getViews(){
        return viewRepository.findAll();
    }

}
