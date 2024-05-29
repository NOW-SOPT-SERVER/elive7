package org.sopt.cloneCoding.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.common.dto.ErrorMessage;
import org.sopt.cloneCoding.domain.Heart;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.domain.Product;
import org.sopt.cloneCoding.exception.DuplicateLikeException;
import org.sopt.cloneCoding.repository.HeartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberService memberService;
    private final ProductService productService;

    @Transactional
    public String createLike(Long memberId, Long productId){
        if (heartRepository.findByMember_IdAndProduct_Id(memberId, productId).isPresent()){ //중복 좋아요 방지
            throw new DuplicateLikeException(ErrorMessage.DUPLICATE_LIKE);
        }
        Member member = memberService.findMemberById(memberId);
        Product product = productService.findProductById(productId);
        Heart heart = Heart.builder().member(member).product(product).build();
        heartRepository.save(heart);
        return heart.getId().toString();
    }
}
