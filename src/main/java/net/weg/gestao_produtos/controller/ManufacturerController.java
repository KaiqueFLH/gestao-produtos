package net.weg.gestao_produtos.controller;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Manufacturer;
import net.weg.gestao_produtos.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer){
        try{
            return new ResponseEntity<>(manufacturerService.createManufacturer(manufacturer), HttpStatus.CREATED);
        }catch (AlreadyExistingBankException | EmptyNameOrNullException e ){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<Manufacturer> editManufacturer(@RequestBody Manufacturer manufacturer){
        try {
            return new ResponseEntity<>(manufacturerService.editManufacturer(manufacturer),HttpStatus.OK);
        }catch (NoExistsInBankException | EmptyNameOrNullException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manufacturer> deleteManufacturer(@PathVariable Integer id){
        try {
            manufacturerService.deleteManufacturer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExistsInBankException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findOneManufacturer(@PathVariable Integer id){
        try {
            manufacturerService.findOneManufacturer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoExistsInBankException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> findAllManufacturer(){
        return new ResponseEntity<>(manufacturerService.findAllManufacturer(),HttpStatus.OK);
    }
}
