package com.dev.cinema.dto.request;

import com.dev.cinema.annotation.EmailConstraint;
import com.dev.cinema.annotation.FieldsValueMatch;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FieldsValueMatch(firstFieldName = "password",
        secondFieldName = "confirmPassword")
public class UserRequestDto {
    @EmailConstraint
    private String email;
    @Size(min = 6, max = 12)
    private String password;
    private String confirmPassword;
}
