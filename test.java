import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@UtilityClass
@Slf4j
public class CommonUtil {

    private static final int MAX_FILE_LENGTH = 100;

    public static PrivateKey loadPrivateKey(String privateKeyName) throws
            NoSuchAlgorithmException, InvalidKeySpecException,
            URISyntaxException, IOException {
        String privateKeyContent = privateKeyName;

        // Load plain file
        if (privateKeyName.contains(".pem")) {
            privateKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(privateKeyName).toURI())));
        }

        // Format private key
        privateKeyContent =
                privateKeyContent.replaceAll("\\R", "").replace("***REMOVED***", "").replace("***REMOVED***"
                        + "***REMOVED***", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(
                Base64.getDecoder().decode(privateKeyContent));
        return kf.generatePrivate(keySpecPKCS8);
    }

}