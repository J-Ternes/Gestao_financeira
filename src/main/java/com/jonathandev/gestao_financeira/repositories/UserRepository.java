package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
     UserModel findByEmail(String email);


}
