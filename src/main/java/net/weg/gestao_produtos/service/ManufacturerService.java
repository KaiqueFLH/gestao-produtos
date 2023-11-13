package net.weg.gestao_produtos.service;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Manufacturer;
import net.weg.gestao_produtos.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    public Manufacturer createManufacturer(Manufacturer manufacturer) throws AlreadyExistingBankException {
        if (manufacturer.getId()!=null && manufacturerRepository.existsById(manufacturer.getId())) {
            throw new AlreadyExistingBankException(manufacturer.getName());
        } else if (manufacturer.getName().equals("")) {
            throw new EmptyNameOrNullException();
        }

        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer editManufacturer(Manufacturer manufacturer) throws NoExistsInBankException {
        if (!manufacturerRepository.existsById(manufacturer.getId())){
            throw new NoExistsInBankException(manufacturer.getName());
        } else if (manufacturer.getName().equals("")){
            throw new EmptyNameOrNullException();
        }
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer findOneManufacturer(Integer id) throws NoExistsInBankException {
        if (!manufacturerRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        return manufacturerRepository.findById(id).get();
    }

    public List<Manufacturer> findAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    public void deleteManufacturer(Integer id) throws NoExistsInBankException {
        if (!manufacturerRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        manufacturerRepository.deleteById(id);
    }
}
