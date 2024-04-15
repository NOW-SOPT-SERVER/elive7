package opt.sopt.practice.service.dto;

import java.util.List;
import java.util.stream.Collectors;
import opt.sopt.practice.domain.Member;
import opt.sopt.practice.domain.Part;

public record MemberFindAllDto(long id, String name, Part part, int age) {
    public static List<MemberFindAllDto> findAll(List<Member> members){
        List<MemberFindAllDto> list = members.stream().map(MemberFindAllDto::of).collect(Collectors.toList());
        return list;
    }
    public static MemberFindAllDto of (Member member){
        return new MemberFindAllDto(member.getId(), member.getName(), member.getPart(), member.getAge());
    }
}
