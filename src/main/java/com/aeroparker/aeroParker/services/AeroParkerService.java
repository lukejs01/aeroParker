package com.aeroparker.aeroParker.services;

import com.aeroparker.aeroParker.model.AeroParker;
import com.aeroparker.aeroParker.repository.AeroParkerRepository;
import com.aeroparker.aeroParker.utils.SaveUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AeroParkerService {

    @Autowired
    private AeroParkerRepository repository;

    public AeroParkerService(AeroParkerRepository repository) {
        this.repository = repository;
    }

    public void saveUser(AeroParker user) {
        SaveUtils utils = new SaveUtils(repository);
        user.setEmailAddress(user.getEmailAddress().toLowerCase(Locale.ROOT));

        if (utils.validateUser(user)) {
            repository.save(user);
        }
    }
}
