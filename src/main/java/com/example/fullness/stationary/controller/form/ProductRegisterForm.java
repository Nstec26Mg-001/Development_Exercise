package com.example.fullness.stationary.controller.form;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence({Required.class, Length.class, ProductRegisterForm.class})
public class ProductRegisterForm {

    @NotBlank(message = "{validation.required.productName}", groups = Required.class)
    @Size(min = 1, max = 20, message = "{validation.length.productName}", groups = Length.class)
    private String name;

    @NotNull(message = "{validation.required.price}", groups = Required.class)
    @Min(value = 0, message = "{validation.length.price}", groups = Length.class)
    @Max(value = 999999, message = "{validation.length.price}", groups = Length.class)
    private Integer price;

    @NotNull(message = "{validation.required.stock}", groups = Required.class)
    @Min(value = 0, message = "{validation.length.stock}", groups = Length.class)
    @Max(value = 9999, message = "{validation.length.stock}", groups = Length.class)
    private Integer stock;

    @NotNull(message = "{validation.required.categoryId}", groups = Required.class)
    private Integer categoryId;

    private String categoryName;

    private String imagePath;
}
