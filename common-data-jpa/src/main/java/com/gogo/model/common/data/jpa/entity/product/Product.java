package com.gogo.model.common.data.jpa.entity.product;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "product")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
@Setter
public class Product extends AbstractEntity {

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String alias;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "full_description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name = "main_image", nullable = false)
    private String mainImage;

    private boolean enabled;

    @Column(name = "in_stock")
    private boolean inStock;

    private BigDecimal cost;

    private BigDecimal price;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> images = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Nullable
    private List<ProductDetail> details = new ArrayList<>();

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal weight;

    @Column(name = "average_rating")
    private BigDecimal averageRating;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Transient
    public BigDecimal getDiscountPrice() {
        if (discountPercent != null && discountPercent.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discount = price.multiply(discountPercent).divide(BigDecimal.valueOf(100));
            return price.subtract(discount);
        }
        return this.price;
    }
}
