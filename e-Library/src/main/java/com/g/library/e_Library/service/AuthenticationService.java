package com.g.library.e_Library.service;

import com.g.library.e_Library.dto.AuthDto;
import com.g.library.e_Library.entity.Member;
import com.g.library.e_Library.exceptions.IncorrectCredentialsException;
 import com.g.library.e_Library.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member login(AuthDto authDto) {
        Optional<Member> memberOptional = this.memberRepository.findByUsername(authDto.getUsername());
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format(
                    "Member with username: %s was not found", authDto.getUsername()));
        }
        Member member = memberOptional.get();
        if (!this.passwordEncoder.matches(authDto.getPassword(), member.getPassword())) {
            log.info("Password is incorrect");
            throw new IncorrectCredentialsException("Incorrect password provided");
        }
        return member;
    }


}