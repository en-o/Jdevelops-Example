package cn.tan.authentication.sas.server.util;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PKCEUtilsTest {

    @Test
    void codeVerifierGenerator() {
        //生成code_verifier
        System.out.println(PKCEUtils.codeVerifierGenerator());
    }

    @Test
    void codeChallengeGenerator() throws NoSuchAlgorithmException {

        //生成code_verifier
        String codeVerifier = PKCEUtils.codeVerifierGenerator();
        assertNotNull(codeVerifier);
        System.out.println(codeVerifier);
        //生成code_challenge
        String codeChallenge = PKCEUtils.codeChallengeGenerator(codeVerifier);
        assertNotNull(codeChallenge);
        System.out.println(codeChallenge);

    }
}
