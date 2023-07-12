package org.yinyinwu.findaplace.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.yinyinwu.findaplace.validation.FieldMatch;
import org.yinyinwu.findaplace.validation.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @ValidPassword
    @NotEmpty
    private String password;
    private boolean loggedin;

}
