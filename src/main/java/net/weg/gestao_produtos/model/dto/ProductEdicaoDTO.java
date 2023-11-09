package net.weg.gestao_produtos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEdicaoDTO {

    private String name;
    private String expirationDate;
    private String description;
    private String manufacturer;
    private String category;
    private Integer price;
    private Integer stockQuantity;

}
