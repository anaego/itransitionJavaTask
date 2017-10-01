package by.itransition;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Администратор on 24.06.2017.
 */
public class Encoder {

    private static char[] VALID_CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

    public static String encode(String salt, String computersMove) throws NoSuchAlgorithmException {
        String algorithm = "HmacSHA256";

        Charset utfCs = Charset.forName("US-ASCII");
        Mac sha256HMAC = Mac.getInstance(algorithm);
        SecretKeySpec secretKey = new SecretKeySpec(utfCs.encode(salt).array(), algorithm);
        try {
            sha256HMAC.init(secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] macData = sha256HMAC.doFinal(utfCs.encode(computersMove).array());
        String result = DatatypeConverter.printHexBinary(macData);
        return result;
    }

    // cs = cryptographically secure
    public static String csRandomAlphaNumericString(int numChars) {
        SecureRandom srand = new SecureRandom();
        Random rand = new Random();
        char[] buff = new char[numChars];

        for (int i = 0; i < numChars; ++i) {
            // reseed rand once you've used up all available entropy bits
            if ((i % 10) == 0) {
                rand.setSeed(srand.nextLong()); // 64 bits of random!
            }
            buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
        }
        return new String(buff);
    }
}
