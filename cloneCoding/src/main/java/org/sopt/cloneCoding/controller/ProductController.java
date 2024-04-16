package org.sopt.cloneCoding.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.domain.Product;
import org.sopt.cloneCoding.service.MemberService;
import org.sopt.cloneCoding.service.ProductService;
import org.sopt.cloneCoding.service.dto.ProductCreateDto;
import org.sopt.cloneCoding.service.dto.SellingProductCreateDto;
import org.sopt.cloneCoding.service.dto.SharingProductCreateDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/selling")
    public ResponseEntity createSellingProduct(
            @RequestBody SellingProductCreateDto sellingProductCreateDto
    ){
        return ResponseEntity.created(URI.create(productService.createSellingProduct(sellingProductCreateDto))).build();
    }

    @PostMapping("/sharing")
    public ResponseEntity createSharingProduct(
            @RequestBody SharingProductCreateDto sharingProductCreateDto
    ){
        return ResponseEntity.created(URI.create(productService.createSharingProduct(sharingProductCreateDto))).build();
    }
}
