package net.weg.gestao_produtos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.gestao_produtos.model.Manufacturer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEdicaoDTO {

    private String expirationDate;
    private String name;
    private String description;
    private Manufacturer manufacturer;
    private String category;
    private Integer price;
    private Integer stockQuantity;

}
