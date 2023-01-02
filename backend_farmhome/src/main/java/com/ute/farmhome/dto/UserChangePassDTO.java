package com.ute.farmhome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserChangePassDTO {
    private String oldPassword;
    private String confirmNewPassword;
    private String newPassword;
}
