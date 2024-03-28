package com.backtucafe.controller;

import com.backtucafe.model.request.ViewRequest;
import com.backtucafe.service.ViewService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tuCafe/v1/view")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://front-tu-cafe-v3h2.vercel.app/")
public class ViewController {

    private final ViewService viewService;

    @PostMapping(value = "newView")
    public String view(@RequestBody ViewRequest request) throws MessagingException {
        return viewService.view(request);
    }

}
