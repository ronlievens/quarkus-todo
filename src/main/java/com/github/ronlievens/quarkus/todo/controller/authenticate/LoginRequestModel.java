package com.github.ronlievens.quarkus.todo.controller.authenticate;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record LoginRequestModel(
    @Email
    String email,
    @NotBlank
    String password) {
}
