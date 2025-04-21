package com.realworld.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
