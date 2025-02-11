package com.unbox.Keyboardy.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private Long idx;
    
    private Long postCode;

    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String roadAddress;

    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String detailAddress;

    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String extraAddress;
}
