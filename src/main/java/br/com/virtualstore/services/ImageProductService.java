package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.BadRequestException;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.Image;
import br.com.virtualstore.models.responses.BrandResponse;
import br.com.virtualstore.repositories.ImageRepository;
import br.com.virtualstore.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageProductService {
    @Autowired
    private ImageRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;
    private Image image = new Image();

    @Transactional
    public Image save(Long id_product, MultipartFile file) {
        return  productRepository.findById(id_product)
                .map(result -> {
                    image.setProduct(result);
                    try {
                        image.setImage(saveImageWithinLocalDisc(id_product, file));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return repository.save(image);
                })
                .orElseThrow(() -> new EntityNotFoundException("Product" + CP.NOT_FOUND + "id: " + id_product));
    }

    @Transactional
    private String saveImageWithinLocalDisc(Long id, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BadRequestException("There is not file, verify and try again");
        }
        byte[] bytes = file.getBytes();
        String imageName = String.valueOf(id) + "_" + file.getOriginalFilename();
        Path path = Paths.get("/images/products" + imageName);
        Files.write(path, bytes);
        return imageName;
    }
}
