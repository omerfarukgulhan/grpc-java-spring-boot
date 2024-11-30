package com.ofg.discount.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private BigDecimal discountPrice;
    @ManyToOne
    @JoinColumn(name = "categoriy_id", referencedColumnName = "id")
    private Category category;
}
