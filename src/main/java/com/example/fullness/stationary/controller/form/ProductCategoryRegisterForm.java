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

    @NotBlank(message = "{validation.required.categoryName}", groups = Required.class)
    @Size(min = 1, max = 30, message = "{validation.length.categoryName}", groups = Length.class)
    private String name;
}
