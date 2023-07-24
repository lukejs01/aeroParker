package com.aeroparker.aeroParker;

import com.aeroparker.aeroParker.model.AeroParker;

public class TestConstants {

    public AeroParker createUser(String email,
                                 String title,
                                 String firstName,
                                 String lastName,
                                 String addressLine1,
                                 String addressLine2,
                                 String city,
                                 String postcode,
                                 String phoneNumber) {
        AeroParker aeroParker = new AeroParker();

        aeroParker.setEmailAddress(email);
        aeroParker.setTitle(title);
        aeroParker.setFirstName(firstName);
        aeroParker.setLastName(lastName);
        aeroParker.setAddressLine1(addressLine1);
        aeroParker.setAddressLine2(addressLine2);
        aeroParker.setCity(city);
        aeroParker.setPostcode(postcode);
        aeroParker.setPhoneNumber(phoneNumber);

        return aeroParker;
    }
}
