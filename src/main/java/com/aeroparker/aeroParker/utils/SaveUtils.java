package com.aeroparker.aeroParker.utils;

import com.aeroparker.aeroParker.model.AeroParker;
import com.aeroparker.aeroParker.repository.AeroParkerRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveUtils {

    @Autowired
    private AeroParkerRepository repository;

    public SaveUtils(AeroParkerRepository repository) {
        this.repository = repository;
    }


    public boolean validateUser(AeroParker user) {
        boolean names = validateNames(user.getFirstName(), user.getLastName());

        if (!user.getPhoneNumber().isEmpty()) {
            boolean number = validatePhoneNumber(user.getPhoneNumber());
            if (!number) {
                throw new IllegalArgumentException("You have tried to enter an invalid number");
            }
        }

        checkForUniqueEmail(user.getEmailAddress());

        if (!names) {
            throw new IllegalArgumentException("You have tried to enter an invalid name");
        }

        return true;
    }

    private boolean validateNames(String firstName, String lastName) {
        String onlyLetters = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(onlyLetters);

        Matcher matcher = pattern.matcher(firstName + lastName);
        return matcher.matches();
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        String validNumber = "^\\+?44\\d{10}$";
        Pattern pattern = Pattern.compile(validNumber);

        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    private void checkForUniqueEmail(String email) {
        AeroParker user = repository.findByEmailAddress(email);

        if (user != null) {
            // email already exists
            throw new EntityExistsException("This email address is already in use");
        }
    }

}
