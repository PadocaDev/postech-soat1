package com.padocadev.external.configuracao.seguranca;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secret;

    public String generateToken(String cpf) {
        return Jwts.builder()
                .setSubject(cpf)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
