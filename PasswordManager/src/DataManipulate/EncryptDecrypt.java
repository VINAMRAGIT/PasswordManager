package DataManipulate;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;


public class EncryptDecrypt
{
    protected static final File KEY_FILE = new File(
            "C:\\Users\\vinam\\IdeaProjects\\PasswordManager\\data\\KEY_FILE.enc");
    protected static final File DATA_FILE = new File(
            "C:\\Users\\vinam\\IdeaProjects\\PasswordManager\\data\\DATA_FILE.enc");
    protected SecretKeySpec keySpec;
    protected IvParameterSpec iv;

    /**
     * Initializes the {@code keySpec} and {@code ivParameterSpec} fields for further
     * encryption/decryption. We read from the files using {@code FileInputStream} to be able to
     * assign the proper byte array back to the fields respectively.
     */
    void init() throws
                IOException

    {
        byte[] temp;
        int bytesRead;
        byte[] encodedSpec;
        FileInputStream reader;

        //Read the cipher settings
        temp = new byte[(int) KEY_FILE.length()];
        reader = new FileInputStream(KEY_FILE);
        bytesRead = reader.read(temp);
        encodedSpec = new byte[bytesRead];
        System.arraycopy(temp, 0, encodedSpec, 0, bytesRead);

        //Recreate the secret/symmetric key
        keySpec = new SecretKeySpec(encodedSpec, "AES");
    }
}