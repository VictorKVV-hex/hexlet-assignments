package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.mapper.JsonNullableMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    List<ProductDTO> index() {
        var products = productRepository.findAll();
        return products.stream()
//                .map(productMapper::map)
                .map(p -> productMapper.map(p))
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found: " + id));
        var productDto = productMapper.map(product);
        return productDto;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@Valid @RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        productRepository.save(product);
        var productDto = productMapper.map(product);
        return productDto;
    }

    @PutMapping(path = "/{id}")   // что б выдавал ответ "400 Bad request", если нет такого "id".
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductUpdateDTO productData, @PathVariable Long id) {
        if (categoryRepository.findById(productData.getCategoryId().get()).isPresent()) {
            var product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
            var category = categoryRepository.findById(productData.getCategoryId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
            product.setCategory(category);

            if (JsonNullableMapper.isPresent(productData.getPrice())) {
                product.setPrice(JsonNullableMapper.unwrap(productData.getPrice()));
            }
            if (JsonNullableMapper.isPresent(productData.getTitle())) {
                product.setTitle(JsonNullableMapper.unwrap(productData.getTitle()));
            }
            productRepository.save(product);
            return ResponseEntity.ok(productMapper.map(product));
        } else {
            return ResponseEntity.badRequest().body(new ProductDTO());
        }
    }

/*    ProductDTO update(@RequestBody @Valid ProductUpdateDTO productData, @PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found: " + id));
        var category =  categoryRepository.findById(productData.getCategoryId().get())
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        *//*        productMapper.update(productData, product);*//*
        product.setCategory(category);
        product.setPrice(productData.getPrice().get());
        product.setTitle(productData.getTitle().get());
        productRepository.save(product);
        var produktDTO = productMapper.map(product);
        return produktDTO;
    }*/

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

/*    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody @Valid ProductUpdateDTO productData, @PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found: " + id));
        productMapper.update(productData, product);
        productRepository.save(product);
        var productDto = productMapper.map(product);
        return productDto;
    }*/



    // END
}
