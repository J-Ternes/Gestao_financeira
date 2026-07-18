package com.jonathandev.gestao_financeira.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jonathandev.gestao_financeira.dtos.JWTEmailERoleDto;
import com.jonathandev.gestao_financeira.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UserModel usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().name())
                    .withExpiresAt(Instant.now().plusSeconds(86400))
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto estava criando o token");
        }
    }

    public JWTEmailERoleDto validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            String emailJwt = decode.getSubject();
            String roleJwt = decode.getClaim("role").asString();

            JWTEmailERoleDto emailERole = new JWTEmailERoleDto(emailJwt,roleJwt);


            return emailERole;

        }catch (JWTVerificationException e){
            throw new RuntimeException("Token JWT inválido ou expirado", e);
        }
    }
}
