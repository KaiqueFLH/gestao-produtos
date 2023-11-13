package net.weg.gestao_produtos.repository;

import lombok.Data;
import net.weg.gestao_produtos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByBarCode(Long barCode);

}
