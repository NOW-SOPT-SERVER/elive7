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

    public SharingProduct(String title, Member seller, String description, TransactionPlace transactionPlace,
                          Boolean sharingEvent, String imageUrl) {
        super(title, seller, description, transactionPlace, imageUrl);
        this.sharingEvent = sharingEvent;
    }
}