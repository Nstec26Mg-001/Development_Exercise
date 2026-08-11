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

    @NotNull(message = "社員名を選択してください。", groups = Required.class)
    private Integer employeeId;

    private String employeeName;

    @NotBlank(message = "アカウント名を入力してください。", groups = Required.class)
    @Size(min = 5, max = 20, message = "アカウント名は5〜20文字で入力してください。", groups = Length.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "アカウント名は半角英数字で入力してください。", groups = CharType.class)
    private String accountName;

    @NotBlank(message = "パスワードを入力してください。", groups = Required.class)
    @Size(min = 5, max = 20, message = "パスワードは5〜20文字で入力してください。", groups = Length.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "パスワードは半角英数字で入力してください。", groups = CharType.class)
    private String password;
}
