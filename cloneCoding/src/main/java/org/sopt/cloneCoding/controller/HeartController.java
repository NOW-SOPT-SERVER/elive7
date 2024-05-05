package org.sopt.cloneCoding.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.common.dto.SuccessMessage;
import org.sopt.cloneCoding.common.dto.SuccessStatusResponse;
import org.sopt.cloneCoding.service.HeartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/like/{productId}")
    public ResponseEntity<SuccessStatusResponse> createProductLike(
            @RequestHeader Long memberId,
            @PathVariable Long productId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", heartService.createLike(memberId, productId))
                .body(SuccessStatusResponse.of(SuccessMessage.LIKE_CREATE_SUCCESS));
    }
}
