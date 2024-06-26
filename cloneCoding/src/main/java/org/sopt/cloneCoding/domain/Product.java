package org.sopt.cloneCoding.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sopt.cloneCoding.domain.Member;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "type")
public class Product extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Member seller;
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionPlace transactionPlace;

    public Product(String title, Member seller, String description, TransactionPlace transactionPlace) {
        this.title = title;
        this.seller = seller;
        this.description = description;
        this.transactionPlace = transactionPlace;
    }
}
