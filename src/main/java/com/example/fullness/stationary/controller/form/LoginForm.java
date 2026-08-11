package com.example.fullness.stationary.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotBlank(message = "アカウント名を入力してください。")
    @Size(min = 5, max = 20, message = "アカウント名は5〜20文字で入力してください。")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "アカウント名は半角英数字で入力してください。")
    private String accountName;

    @NotBlank(message = "パスワードを入力してください。")
    @Size(min = 5, max = 20, message = "パスワードは5〜20文字で入力してください。")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "パスワードは半角英数字で入力してください。")
    private String password;
}
