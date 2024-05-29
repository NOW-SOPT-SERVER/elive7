package org.sopt.cloneCoding.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.common.dto.ErrorMessage;
import org.sopt.cloneCoding.common.dto.ErrorResponse;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.domain.Product;
import org.sopt.cloneCoding.domain.SellingProduct;
import org.sopt.cloneCoding.domain.SharingProduct;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.sopt.cloneCoding.exception.NotFoundException;
import org.sopt.cloneCoding.repository.ProductRepository;
import org.sopt.cloneCoding.repository.SellingProcuctRepository;
import org.sopt.cloneCoding.repository.SharingProductRepository;
import org.sopt.cloneCoding.service.dto.ProductFindAllDto;
import org.sopt.cloneCoding.service.dto.SellingProductCreateDto;
import org.sopt.cloneCoding.service.dto.SharingProductCreateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SellingProcuctRepository sellingProductRepository;
    private final SharingProductRepository sharingProductRepository;
    private final MemberService memberService;

    @Transactional
    public String createSellingProduct(SellingProductCreateDto sellingProductCreateDto) {
        Member member = memberService.findMemberById(sellingProductCreateDto.getSellerId());
        SellingProduct sellingProduct = SellingProduct.builder()
                .title(sellingProductCreateDto.getTitle())
                .seller(member)
                .price(sellingProductCreateDto.getPrice())
                .description(sellingProductCreateDto.getDescription())
                .transactionPlace(sellingProductCreateDto.getTransactionPlace())
                .negotiable(sellingProductCreateDto.getNegotiable())
                .build();
        sellingProductRepository.save(sellingProduct);
        return sellingProduct.getId().toString();
    }

    @Transactional
    public String createSharingProduct(SharingProductCreateDto sharingProductCreateDto) {
        Member member = memberService.findMemberById(sharingProductCreateDto.getSellerId());
        SharingProduct sharingProduct = SharingProduct.builder()
                .title(sharingProductCreateDto.getTitle())
                .seller(member)
                .description(sharingProductCreateDto.getDescription())
                .transactionPlace(sharingProductCreateDto.getTransactionPlace())
                .sharingEvent(sharingProductCreateDto.getSharingEvent())
                .build();
        sharingProductRepository.save(sharingProduct);
        return sharingProduct.getId().toString();
    }

    public Slice<ProductFindAllDto> findProductByPlace(TransactionPlace transactionPlace, Pageable pageable){
        return ProductFindAllDto.findAll(productRepository.findAllByTransactionPlace(transactionPlace, pageable));
    }

    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND)
        );
    }
}
