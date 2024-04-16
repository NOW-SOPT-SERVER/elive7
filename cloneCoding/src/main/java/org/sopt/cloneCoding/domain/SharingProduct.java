package org.sopt.cloneCoding.domain;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@SuperBuilder
public class SharingProduct extends Product {
    private Boolean sharingEvent;

    public SharingProduct(String title, Member seller, Double price, String description, String transactionPlace,
                          Boolean sharingEvent) {
        super(title, seller, price, description, transactionPlace);
        this.sharingEvent = sharingEvent;
    }
}