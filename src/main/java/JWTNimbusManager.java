import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

import java.util.Base64;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWEProvider;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.AESEncrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.Base64URL;

//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Hex;

import sun.security.rsa.RSAPublicKeyImpl;

public class JWTNimbusManager {

    public static final String PUBLIC_KEY = "30820122300D06092A864886F70D01010105000382010F003082010A0282010100BAA70D36813D82B7A07DC24FE1D5E92F739FE5F21FB429E7CBE97FD365C27539D4B5D8B0BF3B2CC22DE653D89A92464DEECC7A0A59BE0058A8D66E6E84F3ACBC507C4C8709FB07329D7108F02F6F5009969F022FEE8C2D8515A643EDE1CEC2B80694BD8F46B58D613F3858E25DD9ED77BBEE8395B6086371B1B7F1B89D3F8B8592A1CEE664C68606E4F5E8D37C51B6DDB8AA606CCA1A030FD29A6A7B23E5E9DA7D31AC3ECFEAF576016625898626DAB851D3445389621C42EADF5A6B84DC15B6B49101A7B717AA146995B7EAE90EA92B8DDDC9BF3CD86FF8422A48544C8590F2B37BDA8C4D78C5B4E0834C1B4733052502BD4F42D39384F14B3035127D8728A50203010001";
//    public static final String PUBLIC_KEY = "abcdefghijklmnopqrstuvwxyz012345"; // 32 chars (256 bits)
//    public static final String PUBLIC_KEY = "abcdefghijklmnop";  // 16 chars (128 bits)

    public static void verify(String token) {

        try {
            JWEObject jweObject = JWEObject.parse(token);

            Base64URL authTag64 = jweObject.getAuthTag();
            String authTag = authTag64.decodeToString();
            System.out.println("authTag: " + authTag);

            System.out.println("cipherText: " + jweObject.getCipherText().decodeToString());

            JWEHeader jweHeader = jweObject.getHeader();
            JWEAlgorithm jweAlgorithm = jweHeader.getAlgorithm();
            System.out.println("jweAlgorithm: " + jweAlgorithm.getName());

            System.out.println("encrypted key: " + jweObject.getEncryptedKey().decodeToString());

            System.out.println("IV: " + jweObject.getIV());

//            Payload payload = jweObject.getPayload();
//            System.out.println("payload: " + payload.toString());

//            System.out.println("parsedString: " + jweObject.getParsedString());

            JWEObject.State state = jweObject.getState();
            System.out.println("state: " + state.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static String createToken() throws Exception {
//        JWEAlgorithm jweAlgorithm = JWEAlgorithm.parse("A256KW");
//        JWEAlgorithm jweAlgorithm = JWEAlgorithm.parse("A256GCMKW");

//        JWEAlgorithm jweAlgorithm = JWEAlgorithm.parse("A128KW");
//        JWEAlgorithm jweAlgorithm = JWEAlgorithm.parse("RSA-OAEP-256");  // com.nimbusds.jose.JOSEException: The RSA-OAEP-256 algorithm is not supported by the JWE encrypter: Supported algorithms: [A256GCMKW, A256KW]
        JWEAlgorithm jweAlgorithm = JWEAlgorithm.RSA_OAEP_256;

        JWEHeader jweHeader = new JWEHeader(jweAlgorithm, EncryptionMethod.A256GCM);

        // Request JWT encrypted with RSA-OAEP-256 and 128-bit AES/GCM
//        JWEHeader jweHeader = new JWEHeader(
//                JWEAlgorithm.RSA_OAEP_256,
//                EncryptionMethod.A128GCM
//        );

        Payload payload = new Payload("testo da cifrare");

        JWEObject jweObject = new JWEObject(jweHeader, payload);

//        Base64URL key = new Base64URL(PUBLIC_KEY);
//        JWEEncrypter jweEncrypter = new AESEncrypter(key.decode());
//        JWEEncrypter jweEncrypter = new AESEncrypter(PUBLIC_KEY.getBytes());

//        RSAPublicKey rsaPbKey = new RSAPublicKeyImpl(PublicKeyConstants.RSA_PUBLIC_KEY.getBytes());
        RSAPublicKey rsaPbKey = getPublicKey(PublicKeyConstants.RSA_PUBLIC_KEY);
        RSAEncrypter jweEncrypter = new RSAEncrypter(rsaPbKey);

        jweObject.encrypt(jweEncrypter);

        String token = jweObject.serialize();

        return token;
    }

    private static RSAPublicKey getPublicKey(String publicKeyContent) throws Exception
    {
        KeyFactory factory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey pubKey = (RSAPublicKey) factory.generatePublic(keySpecX509);
        return pubKey;

//        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);
//        return factory.generatePublic(encodedKeySpec);
    }

    public static void aaa() throws DecoderException {
        String hex = "aaaa";
        byte[] decodedHex = Hex.decodeHex(hex);
//        byte[] encodedHexB64 = org.apache.commons.codec.binary.Base64.encodeBase64(decodedHex);
        String str = org.apache.commons.codec.binary.Base64.encodeBase64String(decodedHex);


    }
}
