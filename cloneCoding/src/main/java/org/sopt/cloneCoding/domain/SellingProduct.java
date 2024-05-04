package org.sopt.cloneCoding.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class SellingProduct extends Product {
    private double price;
    private boolean negotiable;

    public SellingProduct(String title, Member seller, Double price, String description, String transactionPlace,
                          boolean negotiable) {
        super(title, seller, description, transactionPlace);
        this.price = price;
        this.negotiable = negotiable;
    }
}
