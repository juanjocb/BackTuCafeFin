package com.backtucafe.service;

import com.backtucafe.model.Business;
import com.backtucafe.model.Image;
import com.backtucafe.repository.BusinessRepository;
import com.backtucafe.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final BusinessRepository businessRepository;

    public void uploadImage(Long businessId, String url) {
        System.out.println("Entro al servicio");
        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        System.out.println("Establecimiento: " + optionalBusiness);
        if (optionalBusiness.isEmpty()) {
            throw new IllegalArgumentException("Establecimientio " + businessId + " no encontrado");
        }
        Business business = optionalBusiness.get();
        System.out.println("Establecimiento despues del get: " + business);

        Image image = Image.builder()
                .url(url)
                .business(business)
                .build();

        imageRepository.save(image);
        System.out.println("Imagen: " + image);
    }

    public List<String> getImagesUrlsByBusinessId(Long businessId) {
        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        if (optionalBusiness.isEmpty()) {
            throw new IllegalArgumentException("Establecimiento " + businessId + " no encontrado");
        }
        Business business = optionalBusiness.get();
        List<String> imageUrls = imageRepository.findByBusiness(business)
                .stream()
                .map(Image::getUrl) // Mapea cada Image a su URL
                .collect(Collectors.toList()); // Colecta las URLs en una lista
        return imageUrls;
    }
}
