package com.ecommerceproject.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateCompanyDto {
    //@NotNull(message = "El nombre no puede ser null")
    //@NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    //@NotNull(message = "El RUC no puede ser null")
    //@NotBlank(message = "El RUC no puede estar en blanco")
    //@Size(min = 11, max = 11)
    private String ruc;

    //@NotNull(message = "La dirección no puede ser null")
    //@NotBlank(message = "La dirección no puede estar en blanco")
    private String address;

    //@NotNull(message = "El email no puede ser null")
    //@NotBlank(message = "El email no puede estar en blanco")
    //@Email
    private String email;

    //@NotNull(message = "El número de teléfono no puede ser null")
    //@NotBlank(message = "El número de teléfono no puede estar en blanco")
    //@Size(min = 9, max = 9)
    private String phoneNumber;

    private String website;

    private String logoUrl;

    private MultipartFile logo;
}
