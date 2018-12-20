package decryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Decryption {

    private final static String phrase_X = "ce6ci est ma p4hrase secret\u00e9e pour encry\u00e0\u00a4pter l!es chaines#";
/*    String phrase_Y = "0123456789abcdef";
    String phrase_Z = "fedcba9876543210";

    String phrase_1 = "0123456789abcdef";
    String phrase_2 = "0123456789abcdef";*/

    static String Decryption(String str){
        String string;
        try {
            Cipher cipher = Cipher.getInstance("DES");

            Object secret_key = new DESKeySpec(phrase_X.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory secKeyFactory = SecretKeyFactory.getInstance("DES");
            secret_key = secKeyFactory.generateSecret((KeySpec)secret_key);


            byte[] bytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));

            System.out.println("Bytes before de-ciphered (Byte String) => "+ new String(bytes, StandardCharsets.UTF_8));

            String encoded_str = Base64.encodeBase64String(bytes);
            System.out.println("Bytes before de-ciphered (Encoded String) => "+ encoded_str);


            cipher.init(Cipher.DECRYPT_MODE, (Key)secret_key);

            byte[] o = cipher.doFinal(bytes);

            encoded_str= Base64.encodeBase64String(o);
            System.out.println("Bytes after de-ciphered (DES) => "+ encoded_str);

            string = Arrays.toString(o);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return string;
    }
}
