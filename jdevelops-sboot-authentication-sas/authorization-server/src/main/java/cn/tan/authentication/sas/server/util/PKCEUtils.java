package cn.tan.authentication.sas.server.util;

import com.nimbusds.jose.util.Base64URL;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * PKCE - S256算法
 * @author tan
 */
public class PKCEUtils {


    /**
     * 生成 code_verifier
     * @return Base64URL.encode
     */
    public static String codeVerifierGenerator(){
        return  Base64URL.encode(UUID.randomUUID().toString()).toString();
    }

    /**
     * 生成 code_challenge
     * @param codeVerifier codeVerifierGenerator()
     * @return codeChallengeGenerator
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public static String codeChallengeGenerator(String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digestCodeVerifier = messageDigest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64URL.encode(digestCodeVerifier).toString();
    }

}
