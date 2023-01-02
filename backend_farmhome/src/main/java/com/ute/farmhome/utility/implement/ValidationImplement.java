package com.ute.farmhome.utility.implement;

import com.ute.farmhome.utility.Validation;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationImplement implements Validation {
    private String PASSWORD_PATTERN ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
    private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    @Override
    public boolean validatePassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
