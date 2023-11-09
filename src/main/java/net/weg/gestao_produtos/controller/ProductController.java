package net.weg.gestao_produtos.controller;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.model.Product;
import net.weg.gestao_produtos.model.dto.ProductCadastroDTO;
import net.weg.gestao_produtos.model.dto.ProductEdicaoDTO;
import net.weg.gestao_produtos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCadastroDTO product) {
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.findOne(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductEdicaoDTO product) {
        try {
            return new ResponseEntity<>(productService.editProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
