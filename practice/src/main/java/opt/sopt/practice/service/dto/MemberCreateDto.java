package opt.sopt.practice.service.dto;

import opt.sopt.practice.domain.Part;

public record MemberCreateDto(String name, Part part, int age) {
}
