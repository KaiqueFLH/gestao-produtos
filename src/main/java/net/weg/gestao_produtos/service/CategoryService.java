package net.weg.gestao_produtos.service;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Category;
import net.weg.gestao_produtos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) throws AlreadyExistingBankException {
        if (category.getId() != null && categoryRepository.existsById(category.getId())) {
            throw new AlreadyExistingBankException(category.getName());
        } else if (category.getName().equals("")) {
            throw new EmptyNameOrNullException();
        }

        return categoryRepository.save(category);
    }

    public Category editCategory(Category category) throws NoExistsInBankException {
        if (!categoryRepository.existsById(category.getId())) {
            throw new NoExistsInBankException(category.getName());
        } else if (category.getName().equals("")) {
            throw new EmptyNameOrNullException();
        }
        return categoryRepository.save(category);
    }

    public Category findOneCategory(Integer id) throws NoExistsInBankException {
        if (!categoryRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Integer id) throws NoExistsInBankException {
        if (!categoryRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        categoryRepository.deleteById(id);
    }
}
