package net.weg.gestao_produtos.controller;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Category;
import net.weg.gestao_produtos.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        try{
            return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
        }catch (AlreadyExistingBankException | EmptyNameOrNullException e ){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<Category> editCategory(@RequestBody Category category){
        try {
            return new ResponseEntity<>(categoryService.editCategory(category),HttpStatus.OK);
        }catch (NoExistsInBankException | EmptyNameOrNullException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExistsInBankException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findOneCategory(@PathVariable Integer id){
        try {
            categoryService.findOneCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoExistsInBankException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategory(){
        return new ResponseEntity<>(categoryService.findAllCategory(),HttpStatus.OK);
    }
}
