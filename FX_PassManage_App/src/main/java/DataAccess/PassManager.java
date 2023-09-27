package DataAccess;


import DataManipulate.*;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <hr>
 * <h2>
 * {@code PassManager database}
 * </h2>
 * <p>
 * This database instantiates upon starting of the program, reading encrypted
 * username-password pairs from the file, decoding and storing them in {@code PassEntry}
 * objects, and displaying them based on the user's commands.
 * </p>
 * <p>
 * The file only has to store encrypted username-password pairs, and this class can procure the
 * value from the file and decode them and present them. Additionally, this class will also do
 * the opposite, encrypting newly added values and storing them in the file for further reading.
 * </p>
 * <p>
 * The algorithm we will be using to encrypt the values is the {@code AES} encryption algorithm,
 * with a {@code key} size of 128 bits, and a consequent {@code Initialization Vector} size of 16
 * bytes. We will use the {@code Cipher Block Chaining(CBC)} method of encryption, meaning the
 * {@code Initialization Vector} will xor with the plain values, which will then be encrypted for
 * storage, or decrypted for display. The method used to pad the plain values before the xor and
 * encryption will be {@code PKCS5}.
 * </p>
 * <p>
 * This database implements other classes as well, such as the {@code Authenticator} class,
 * which helps ensure that the user is able to login with only one proper password, which is
 * stored in hash form. Other classes include the {@code EncryptData} and {@code DecryptData}
 * classes, which include the extraction of encrypted data from the file and its assignment to
 * the {@code passbook} instance, as well as encryption of data for storage.
 * </p>
 * <hr>
 */

public class PassManager
{
    private static LocalDateTime loginInstance;
    private static EncryptData encrypt;
    public List<PassEntry> passbook;
    private Cipher cipher;

    /**
     * Initializes the password manager by providing new objects to the {@code EncryptData,
     * DecryptData, Scanner, and Authenticator} objects respectively. Additionally, the cipher is
     * also initialized, and the passbook {@code ArrayList} is also provided the decrypted data from
     * storage.
     */
    public boolean init(String answer)
    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException, InvalidKeyException,
           IllegalBlockSizeException, BadPaddingException
    {
        Authenticator login = new Authenticator();
        if (login.checkHash(answer)) {
            loginInstance = LocalDateTime.now();
            encrypt = new EncryptData();
            DecryptData decrypt = new DecryptData();
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            passbook = decrypt.extract(cipher);
            return true;
        }
        else return false;
    }


    public List<PassEntry> getPassbook()
    {
        return passbook;
    }

    /**
     * Inserts the given {@code PassEntry} record into the {@code passbook} list.
     *
     * @param passEntry input category-username-password group to be inserted
     */
    public void insertData(PassEntry passEntry)
    throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        encrypt.install(cipher, passEntry, passbook);
    }

    public void remove(String passEntry)
    throws IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException
    {
        for (PassEntry p : passbook) {
            if (p.category()
                 .equals(passEntry))
            {
                encrypt.remove(cipher, p, passbook);
                break;
            }
        }
    }
}