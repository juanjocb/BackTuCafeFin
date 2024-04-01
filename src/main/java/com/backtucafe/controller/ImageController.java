package com.backtucafe.controller;

import com.backtucafe.model.Image;
import com.backtucafe.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tuCafe/v1/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "upload/{idBusiness}")
    public ResponseEntity<String> uploadImage(@PathVariable Long idBusiness, @RequestBody String url) {
        System.out.println("Entro al controlador");
        try {
            System.out.println("Entro al try");
            imageService.uploadImage(idBusiness, url);
            System.out.println("Salio del service");
            return new ResponseEntity<>("Imagen subida con exito", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Entro al catch");
            return new ResponseEntity<>("Error al subir la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{idBusiness}")
    public ResponseEntity<List<String>> getImagesUrlsByBusinessId(@PathVariable Long idBusiness) {
        List<String> imageUrls = imageService.getImagesUrlsByBusinessId(idBusiness);
        return new ResponseEntity<>(imageUrls, HttpStatus.OK);
    }


}
