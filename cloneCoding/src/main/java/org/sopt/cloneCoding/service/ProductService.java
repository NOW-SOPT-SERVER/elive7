package org.sopt.cloneCoding.service;

import jakarta.transaction.Transactional;
import java.io.IOException;
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
import org.sopt.cloneCoding.external.S3Service;
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
    private final S3Service s3Service;
    private static final String PRODUCT_S3_UPLOAD_FOLER = "product/";

    @Transactional
    public String createSellingProduct(SellingProductCreateDto sellingProductCreateDto) {
        Member member = memberService.findMemberById(sellingProductCreateDto.getSellerId());
        try {
            String imageUrl = null;
            if (sellingProductCreateDto.getImage() != null){
                imageUrl = s3Service.uploadImage(PRODUCT_S3_UPLOAD_FOLER, sellingProductCreateDto.getImage());
            }
            SellingProduct sellingProduct = SellingProduct.builder()
                    .title(sellingProductCreateDto.getTitle())
                    .seller(member)
                    .price(sellingProductCreateDto.getPrice())
                    .description(sellingProductCreateDto.getDescription())
                    .transactionPlace(sellingProductCreateDto.getTransactionPlace())
                    .negotiable(sellingProductCreateDto.getNegotiable())
                    .imageUrl(imageUrl)
                    .build();
            sellingProductRepository.save(sellingProduct);
            return sellingProduct.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException((e.getMessage()));
        }
    }

    @Transactional
    public String createSharingProduct(SharingProductCreateDto sharingProductCreateDto) {
        Member member = memberService.findMemberById(sharingProductCreateDto.getSellerId());
        try{
                String imageUrl = null;
                if (sharingProductCreateDto.getImage() != null){
                    imageUrl = s3Service.uploadImage(PRODUCT_S3_UPLOAD_FOLER, sharingProductCreateDto.getImage());
                }
                SharingProduct sharingProduct = SharingProduct.builder()
                        .title(sharingProductCreateDto.getTitle())
                        .seller(member)
                        .description(sharingProductCreateDto.getDescription())
                        .transactionPlace(sharingProductCreateDto.getTransactionPlace())
                        .sharingEvent(sharingProductCreateDto.getSharingEvent())
                        .imageUrl(imageUrl)
                        .build();
            sharingProductRepository.save(sharingProduct);
            return sharingProduct.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException((e.getMessage()));
        }
    }

    public Slice<ProductFindAllDto> findProductByPlace(TransactionPlace transactionPlace, Pageable pageable){
        return ProductFindAllDto.findAll(productRepository.findAllByTransactionPlace(transactionPlace, pageable));
    }

    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND)
        );
    }

    public void deleteProduct(Long productId){
        Product product = findProductById(productId);
        if (product instanceof SellingProduct){
            try {
                SellingProduct sellingProduct = (SellingProduct) product;
                if (sellingProduct.getImageUrl() != null){
                    s3Service.deleteImage(sellingProduct.getImageUrl());
                }
                sellingProductRepository.delete(sellingProduct);
            } catch (RuntimeException | IOException e){
                throw new RuntimeException((e.getMessage()));
            }
        }
        else if (product instanceof SharingProduct){
            try {
                SharingProduct sharingProduct = (SharingProduct) product;
                if (sharingProduct.getImageUrl() != null){
                    s3Service.deleteImage(sharingProduct.getImageUrl());
                }
                sharingProductRepository.delete(sharingProduct);
            } catch (RuntimeException | IOException e){
                throw new RuntimeException((e.getMessage()));
            }
        }
    }
}
