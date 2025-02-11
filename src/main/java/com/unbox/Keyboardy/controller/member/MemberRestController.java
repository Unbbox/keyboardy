package com.unbox.Keyboardy.controller.member;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unbox.Keyboardy.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MemberRestController {
    
    private final MemberService memberService;
    
    // 아이디 중복확인
    @GetMapping("/member/checkId/{userId}")
    public ResponseEntity<Boolean> getValidId(@PathVariable("userId") String userId) {
        log.info("아이디 중복확인 요청: {}", userId);

        Boolean memId = memberService.checkUserId(userId);

        if(memId) {
            return ResponseEntity.ok(memId);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
}
