package net.weg.gestao_produtos.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCadastroDTO {

    private String name;
    private String expirationDate;
    private String description;
    private Long barCode;
    private Double weight;
    private Double measure;
    private String manufacturer;
    private String category;
    private Integer price;
    private Integer stockQuantity;
}
