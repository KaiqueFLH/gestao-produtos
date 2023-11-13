package net.weg.gestao_produtos.service;

import lombok.AllArgsConstructor;
import net.weg.gestao_produtos.Exceptions.AlreadyExistingBankException;
import net.weg.gestao_produtos.Exceptions.NoExistsInBankException;
import net.weg.gestao_produtos.Exceptions.EmptyNameOrNullException;
import net.weg.gestao_produtos.model.Product;
import net.weg.gestao_produtos.model.dto.ProductCadastroDTO;
import net.weg.gestao_produtos.model.dto.ProductEdicaoDTO;
import net.weg.gestao_produtos.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void deleteProduct(Integer id) throws NoExistsInBankException {
        if (!productRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        productRepository.deleteById(id);
    }

    public Product editProduct(ProductEdicaoDTO dto) throws NoExistsInBankException {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        if (!productRepository.existsById(product.getId())) {
            throw new NoExistsInBankException(product.getName());
        } else if (product.getPrice() <= 0 && product.getStockQuantity() < 0) {
            throw new RuntimeException("O preço e a quantidade em estoque não podem ser negativos");
        }else if(product.getName().equals("")){
            throw new EmptyNameOrNullException();
        }
        return productRepository.save(product);
    }

    public Product createProduct(ProductCadastroDTO dto) throws AlreadyExistingBankException {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        if (product.getPrice() <= 0 && product.getStockQuantity() < 0) {
            throw new RuntimeException("O preço e a quantidade em estoque não podem ser negativos");
        } else if (product.getId() != null && productRepository.existsByBarCode(product.getBarCode())) {
            throw new AlreadyExistingBankException(product.getName());
        }else if(product.getName().equals("")){
            throw new EmptyNameOrNullException();
        }
        return productRepository.save(product);
    }

    public Product findOneProduct(Integer id) throws NoExistsInBankException {
        if (!productRepository.existsById(id)) {
            throw new NoExistsInBankException(id);
        }
        return productRepository.findById(id).get();
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

}
