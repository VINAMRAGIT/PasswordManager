package DataManipulate;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * <hr>
 * <h2>{@code Authenticator}</h2>
 * <p>The {@code Authenticator} class provides an interface with which the client can choose to
 * access the database. Using a randomly-generated salt, the password was initially hashed with
 * the salt to generate a password hash, which is what every login attempt is compared to.</p>
 * <hr>
 */
public class Authenticator
{
    private static final File SALT_FILE = new File(
            "C:\\Users\\vinam\\IdeaProjects\\PasswordManager\\data\\SALT_FILE.enc");
    private static final File HASH_FILE = new File(
            "C:\\Users\\vinam\\IdeaProjects\\PasswordManager\\data\\HASH_FILE.enc");
    private static final String algorithm = "PBKDF2WithHmacSHA1";
    private final byte[] passHash = new byte[16];
    private final byte[] salt = new byte[16];

    public Authenticator() throws
                           IOException
    {
        byte[] temp = new byte[16];
        //Reading in the login password in hash form
        FileInputStream passIn = new FileInputStream(HASH_FILE);
        int bytesRead = passIn.read(temp);
        System.arraycopy(temp, 0, passHash, 0, bytesRead);
        System.out.println("PASS HASH CREATED>>> " + Arrays.toString(passHash));

        //Reading in the salt
        passIn = new FileInputStream(SALT_FILE);
        passIn.read(temp);
        System.arraycopy(temp, 0, salt, 0, bytesRead);
        System.out.println("SALT CREATED>>> " + Arrays.toString(salt));
    }

    /**
     * Using the {@code SecretKeyFactory} class, this method provides the client with
     * boolean-based feedback over whether the provided password entry successfully matches the
     * entry required to log into the database.
     *
     * @param message password passe as input by the client
     * @return boolean showing whether the message's hash matches the password's hash
     */
    public boolean checkHash(String message) throws
                                             NoSuchAlgorithmException,
                                             InvalidKeySpecException
    {
        return Arrays.equals(SecretKeyFactory.getInstance(algorithm)
                                             .generateSecret(
                                                     new PBEKeySpec(message.toCharArray(), salt,
                                                                    65536, 128))
                                             .getEncoded(), passHash);
    }
}