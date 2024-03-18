package com.backtucafe.service;

import com.backtucafe.controller.response.TokenResponse;
import com.backtucafe.model.Client;
import com.backtucafe.model.request.LoginRequest;
import com.backtucafe.model.request.RegisterRequest;
import com.backtucafe.model.request.UpdateClientRequest;
import com.backtucafe.repository.ClientRepository;
import com.backtucafe.security.TokenUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ClientService{

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final JavaMailSender javaMailSender;

    public String registerCliente(@Validated RegisterRequest request) throws MessagingException {
        Client client = Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("cliente")
                .build();

        clientRepository.save(client);

        sendRegistrationEmail(client.getEmail(), client.getName());
        return "Te has registrado con exito";
    }

    public TokenResponse loginCliente(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Client client = clientRepository.findByEmail(request.getEmail());
        String token = tokenUtils.getTokenClient(client);
        return TokenResponse.builder()
                .token(token)
                .role("cliente")
                .build();
    }

    public Client updateProfClient(Long id_client, UpdateClientRequest request){
        Client client = clientRepository.findById(id_client).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        if (client != null){
            if (request.getName() != null){
                client.setName(request.getName());
            }

            if (request.getPhone() != null){
                client.setPhone(request.getPhone());
            }

            if (request.getPassword() != null){
                String newPassowrd = passwordEncoder.encode(request.getPassword());
                client.setPassword(newPassowrd);
            }

            if (request.getCity() != null){
                client.setCity(request.getCity());
            }

            if (request.getCountry() != null){
                client.setCountry(request.getCountry());
            }

            if (request.getPhoto() != null){
                client.setPhoto(request.getPhoto());
            }

            clientRepository.save(client);

            return client;
        }else{
            return null;
        }

    }

    public void sendRegistrationEmail(String to, String name) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("!Registro Exitoso¡");

        // HTML del contenido del correo electrónico
        String htmlContent = "<div style='background-image: url(\"cid:background\"); background-size: cover; background-position: center; background-repeat: repeat; background-color: rgba(255, 255, 255, 0.3); height: 500px; display: flex; justify-content: center; align-items: center ;'>" +
                "<div style='text-align: center; color: black;'>" +
                "<p style='font-size: 24px; font-weight: bold; color: black;'>¡Hola " + name + ",!</p><br>" +
                "<p style='font-size: 16px; color: black;'>¡Bienvenido a TuCafe!</p>"+
                "<p style='font-size: 14px; color: black;'>Gracias por registrarte en TuCafe! Ahora podrás disfrutar de esta maravillosa experiencia.</p>" +
                "<img src='cid:logo' width='150' style='border: 3px solid #663300; border-radius: 50%;'>" +
                "<p style='font-size: 14px; color: white;'>Queremos agradecerte por registrarte con nosotros y confiar en nuestra plataforma para disfrutar de una experiencia única. Estamos emocionados de tenerte a bordo y estamos seguros de que te encantará lo que tenemos preparado para ti." +
                "<br>" +
                "¡Gracias por unirte a nuestra comunidad de amantes del café!" +
                "<br>" +
                "Atentamente," +
                "<b style='color: black;'>TuCafe</b></p>"+
                "</div>" +
                "</div>";

        String cssStyles = "<style>" +
                "@media screen and (max-width: 768px) {" +
                "   div { height: auto !important; }" +
                "   p { font-size: 14px !important; }" +
                "   .logo { max-width: 40px !important; }" +
                "</style>";


        htmlContent = cssStyles + htmlContent;


        helper.setText(htmlContent, true);

        ClassPathResource backgroundImg = new ClassPathResource("images/fondo.jpeg");
        helper.addInline("background", backgroundImg);

        ClassPathResource logoImg = new ClassPathResource("images/tucafe2.jpeg");
        helper.addInline("logo", logoImg);


        javaMailSender.send(message);

    }

}
