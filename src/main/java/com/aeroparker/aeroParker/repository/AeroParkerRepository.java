package com.aeroparker.aeroParker.repository;

import com.aeroparker.aeroParker.model.AeroParker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeroParkerRepository extends JpaRepository<AeroParker, Integer> {

    AeroParker findByEmailAddress(String email);

}

