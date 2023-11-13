package net.weg.gestao_produtos.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Product;
import net.weg.gestao_produtos.model.dto.ProductCadastroDTO;
import net.weg.gestao_produtos.model.dto.ProductEdicaoDTO;
import net.weg.gestao_produtos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCadastroDTO product) {
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        } catch (AlreadyExistingBankException | EmptyNameOrNullException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productService.findOneProduct(id), HttpStatus.OK);
        }catch (NoExistsInBankException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductEdicaoDTO product) {
        try {
            return new ResponseEntity<>(productService.editProduct(product), HttpStatus.OK);
        } catch (NoExistsInBankException | EmptyNameOrNullException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExistsInBankException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
