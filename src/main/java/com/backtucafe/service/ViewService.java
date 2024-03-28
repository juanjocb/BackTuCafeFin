package com.backtucafe.service;

import com.backtucafe.model.View;
import com.backtucafe.model.request.ViewRequest;
import com.backtucafe.repository.ClientRepository;
import com.backtucafe.repository.ViewRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ClientRepository clientRepository;
    private final ViewRepository viewRepository;

    public String view(ViewRequest request) throws MessagingException {

        View view = View.builder()
                .stars(request.getStars())
                .comment(request.getComment())
                .client(clientRepository.findById(request.getClient()))
                .build();

        viewRepository.save(view);
        return "Tu comentario ha sido enviado con exito";
    }
}
