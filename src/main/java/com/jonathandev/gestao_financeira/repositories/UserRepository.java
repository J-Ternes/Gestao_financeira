package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
     UserDetails findByEmail(String email);


}
