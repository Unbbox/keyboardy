package com.unbox.Keyboardy.controller.member;

import org.springframework.stereotype.Controller;

import com.unbox.Keyboardy.dto.AddressDto;
import com.unbox.Keyboardy.dto.MemberDto;
import com.unbox.Keyboardy.service.AddressService;
import com.unbox.Keyboardy.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Log4j2
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final AddressService addressService;

     // 로그인
     @GetMapping("/login")
     public String getLogin() {
         log.info("로그인 페이지 요청");
         
         return "member/login";
     }
 
    // 회원가입
    @GetMapping("/signup")
    public String getSignup() {
        log.info("회원가입 페이지 요청");

        return "member/signup";
    }

    @PostMapping("/signup")
    public String postSignup(@Valid MemberDto userDto) {
        log.info("회원가입 요청 {}", userDto);

        service.createUser(userDto);
        
        return "redirect:/member/login";
    }
    
}
