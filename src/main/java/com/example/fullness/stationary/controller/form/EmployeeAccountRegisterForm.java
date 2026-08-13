package com.example.fullness.stationary.controller.form;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence({Required.class, Length.class, CharType.class, EmployeeAccountRegisterForm.class})
public class EmployeeAccountRegisterForm {

    @NotNull(message = "{validation.required.employeeId}", groups = Required.class)
    private Integer employeeId;

    private String employeeName;

    @NotBlank(message = "{validation.required.accountName}", groups = Required.class)
    @Size(min = 5, max = 20, message = "{validation.length.accountName}", groups = Length.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validation.charType.accountName}",
            groups = CharType.class)
    private String accountName;

    @NotBlank(message = "{validation.required.password}", groups = Required.class)
    @Size(min = 5, max = 20, message = "{validation.length.password}", groups = Length.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validation.charType.password}",
            groups = CharType.class)
    private String password;
}
