package net.weg.gestao_produtos.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.gestao_produtos.model.Manufacturer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCadastroDTO {

    private String expirationDate;
    private String name;
    private String description;
    private Long barCode;
    private Double weight;
    private Double measure;
    private Manufacturer manufacturer;
    private String category;
    private Integer price;
    private Integer stockQuantity;
}
