package com.unbox.Keyboardy.dto;

import java.time.LocalDateTime;

import com.unbox.Keyboardy.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long idx;

    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String userId;

    private String password;

    @NotEmpty(message = "닉네임은 필수 항목입니다.")
    private String nickname;

    @Email(message = "이메일 형식이 아닙니다")
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    private String phone;

    private MemberRole role;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
