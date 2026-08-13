package com.example.fullness.stationary.controller.form;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence({Required.class, Length.class, ProductCategoryRegisterForm.class})
public class ProductCategoryRegisterForm {

    @NotBlank(message = "カテゴリ名を入力してください。", groups = Required.class)
    @Size(min = 1, max = 30, message = "カテゴリ名は1〜30文字で入力してください。", groups = Length.class)
    private String name;
}
