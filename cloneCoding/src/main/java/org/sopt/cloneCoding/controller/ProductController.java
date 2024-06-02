package org.sopt.cloneCoding.controller;


import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.common.dto.SuccessMessage;
import org.sopt.cloneCoding.common.dto.SuccessStatusResponse;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.sopt.cloneCoding.service.MemberService;
import org.sopt.cloneCoding.service.ProductService;
import org.sopt.cloneCoding.service.dto.ProductFindAllDto;
import org.sopt.cloneCoding.service.dto.SellingProductCreateDto;
import org.sopt.cloneCoding.service.dto.SharingProductCreateDto;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/selling")
    public ResponseEntity<SuccessStatusResponse> createSellingProduct(
            @ModelAttribute SellingProductCreateDto sellingProductCreateDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", productService.createSellingProduct(sellingProductCreateDto))
                .body(SuccessStatusResponse.of(SuccessMessage.PRODUCT_CREATE_SUCCESS));
    }

    @PostMapping("/sharing")
    public ResponseEntity<SuccessStatusResponse> createSharingProduct(
            @ModelAttribute SharingProductCreateDto sharingProductCreateDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", productService.createSharingProduct(sharingProductCreateDto))
                .body(SuccessStatusResponse.of(SuccessMessage.PRODUCT_CREATE_SUCCESS));
    }

    @GetMapping("/place/{transactionPlace}")
    public ResponseEntity<SuccessStatusResponse<Slice<ProductFindAllDto>>> getProductByPlace(
            @PathVariable TransactionPlace transactionPlace,
            @PageableDefault(page = 0, size = 3) Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.RRODUCT_GET_SUCCESS,
                        productService.findProductByPlace(transactionPlace, pageable)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
