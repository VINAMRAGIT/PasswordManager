package DataManipulate;

import DataAccess.PassEntry;

import javax.crypto.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

/**
 * <hr>
 * <h2>
 * {@code DecryptData}
 * </h2>
 * <p>
 * This class provides the {@code PassManager} class with a decrypted view of all
 * username-password entries in the file storing the data. We use the {@code AES} form of
 * encryption, running in the {@code ECB} mode, with further {@code PKCS5} padding.
 * </p>
 * <p>
 * The main premise of the {@code DecryptData} class is to be able to retrieve encrypted data and decrypt for the
 * user to view. It does this through the use of the {@code InputStream} and {@code Cipher} classes.
 * </p>
 * <hr>
 */
public class DecryptData extends EncryptDecrypt
{
    public DecryptData()
    throws IOException
    {
        super.init();
    }

    /**
     * This method provides the client with a {@code List} of {@code PassEntry} objects that have
     * been pulled from the storage and decrypted for viewing and access.
     *
     * @param cipher Cipher providing the algorithm of decryption
     * @return A {@code List} containing all {@code PassEntry} elements from the database.
     */
    public List<PassEntry> extract(Cipher cipher)
    throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        List<PassEntry> goal = new ArrayList<>();
        if (DATA_FILE.length() != 0) {
            byte[] temp, encoded;
            FileInputStream reader = new FileInputStream(DATA_FILE);
            int bytesRead;
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            temp = new byte[(int) DATA_FILE.length()];
            bytesRead = reader.read(temp);

            encoded = new byte[bytesRead];
            System.arraycopy(temp, 0, encoded, 0, bytesRead);

            String decrypted = new String(cipher.doFinal(encoded));

            //Parse data by turning it into
            goal = new ArrayList<>(decrypted.lines()
                                            .parallel()
                                            .map(s -> {
                                                String parse = s.substring(1, s.indexOf('>'));
                                                return new PassEntry(parse.substring(0, parse.indexOf(':')),
                                                                     parse.substring(parse.indexOf(':') + 1,
                                                                                     parse.lastIndexOf(':')),
                                                                     parse.substring(
                                                                             parse.lastIndexOf(':') + 1));
                                            })
                                            .toList());
        }
        return goal;

    }

}