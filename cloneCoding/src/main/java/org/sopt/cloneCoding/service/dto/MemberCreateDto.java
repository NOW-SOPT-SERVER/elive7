package org.sopt.cloneCoding.service.dto;

import org.springframework.web.multipart.MultipartFile;

public record MemberCreateDto(String userId, String name, int age) {
}
