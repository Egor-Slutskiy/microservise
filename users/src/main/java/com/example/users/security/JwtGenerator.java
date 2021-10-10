package com.example.users.security;

import com.example.users.entity.UserEntity;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtGenerator {

    public String generate(UserEntity user){
        try{
            final var keyFactory = KeyFactory.getInstance("RSA");
            final var privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(Files.readString(Path.of("private.key"))
                    .replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "")
            )));

            final var claims = new JWTClaimsSet.Builder()
                    .claim("id", user.getId())
                    .build();

            final var signer = new RSASSASigner(privateKey);
            final var signed = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.RS256),
                    claims
            );
            signed.sign(signer);

            return signed.serialize();
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException | JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
