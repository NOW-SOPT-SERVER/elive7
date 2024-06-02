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

    public SellingProduct(String title, Member seller, Double price, String description, TransactionPlace transactionPlace,
                          boolean negotiable, String imageUrl) {
        super(title, seller, description, transactionPlace, imageUrl);
        this.price = price;
        this.negotiable = negotiable;
    }
}
