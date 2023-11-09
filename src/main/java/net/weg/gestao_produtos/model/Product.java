package net.weg.gestao_produtos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Around;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String expirationDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long barCode;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double measure;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private String category;
    private Integer price;
    private Integer stockQuantity;



}
