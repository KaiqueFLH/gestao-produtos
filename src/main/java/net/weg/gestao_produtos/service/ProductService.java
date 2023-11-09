package net.weg.gestao_produtos.service;

import lombok.AllArgsConstructor;
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

    public void deleteProduct(Integer id) throws Exception {
        if (!productRepository.existsById(id)){
            throw new Exception("Não existe um produto com o ID "+id+"! Tente novamente com outro ID.");
        }
        productRepository.deleteById(id);
    }

    public Product editProduct(ProductEdicaoDTO dto) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(dto,product);

        if (!productRepository.existsById(product.getId())){
            throw new Exception("Não existe um produto com o ID "+product.getId()+"! Tente novamente com outro ID.");
        }else if (product.getPrice()<=0 && product.getStockQuantity()<0){
            throw new Exception("O preço e a quantidade em estoque não podem ser negativos");
        }
        return productRepository.save(product);
    }

    public Product createProduct(ProductCadastroDTO dto) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(dto,product);

        if (product.getPrice()<=0 && product.getStockQuantity()<0){
            throw new RuntimeException("O preço e a quantidade em estoque não podem ser negativos");
        }else if (productRepository.existsById(product.getId())){
            throw new Exception("Já existe um produto com o ID "+product.getId()+"! Tente novamente com outro ID.");
        }
        return productRepository.save(product);
    }

    public Product findOne(Integer id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

}
