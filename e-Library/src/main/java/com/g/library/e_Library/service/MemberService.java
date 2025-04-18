package com.g.library.e_Library.service;

 import com.g.library.e_Library.entity.Member;
import com.g.library.e_Library.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member addMember(Member member) {
        log.info("Saving a new member.");
        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        Member savedMember = this.memberRepository.save(member);
        log.info("Saved a new member with ID: {}", member.getId());
        return savedMember;
    }

    public Member getMemberById(UUID memberId) {
        Optional<Member> optionalMember = this.memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            log.info("Member with ID: {} doesn't exist.", memberId);
            return null;
        }
        log.info("Member with ID: {} was successfully found.", memberId);
        return optionalMember.get();
    }

    public List<Member> getAllMembers() {
        List<Member> memberList = this.memberRepository.findAll();
        log.info("Found {} members.", memberList.size());
        return memberList;
    }

    public Member updateMember(Member member) {
        Member existingMember = this.getMemberById(member.getId());
        if (existingMember == null) {
            log.info("Update Failed!");
            return null;
        }
        member = this.memberRepository.save(member);
        log.info("Update success!");
        return member;
    }
}