package com.example.gateway.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JwtValidator {
    private Payload payload;

    public boolean isVerified(String jwt) {
        try{
            final var keyFactory = KeyFactory.getInstance("RSA");
            final var publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getMimeDecoder().decode(Files.readString(Path.of("public.key"))
                    .replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "")
            )));

            final var deserialized = SignedJWT.parse(jwt);
            this.payload = deserialized.getPayload();
            final var verifier = new RSASSAVerifier(publicKey);
            return deserialized.verify(verifier);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | ParseException | JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Payload getPayload(){
        return payload;
    }
}
