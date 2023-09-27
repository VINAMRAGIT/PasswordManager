package DataManipulate;

import DataAccess.PassEntry;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.stream.Collectors;

public class EncryptData extends EncryptDecrypt
{
    public EncryptData()
    throws
    IOException
    {
        super.init();
    }

    public void install(Cipher cipher, PassEntry passEntry, List<PassEntry> list)
    throws
    InvalidKeyException,
    IOException,
    IllegalBlockSizeException,
    BadPaddingException
    {
        //Setting up cipher and OutputStream
        cipher.init(
                Cipher.ENCRYPT_MODE,
                keySpec
                   );
        FileOutputStream writer = new FileOutputStream(DATA_FILE);
        list.add(passEntry);

        //Adding element to list, then overwriting data with new data containing new element
        byte[] result = cipher.doFinal(list.stream()
                                           .map(PassEntry::toString)
                                           .collect(Collectors.joining("\n"))
                                           .getBytes());
        writer.write(result);
    }

    public void remove(Cipher cipher, PassEntry p, List<PassEntry> list)
    throws
    InvalidKeyException,
    IOException, IllegalBlockSizeException, BadPaddingException
    {
        //Setting up cipher and OutputStream
        cipher.init(Cipher.ENCRYPT_MODE,
                    keySpec);
        FileOutputStream writer = new FileOutputStream(DATA_FILE);

        list.remove(p);
        //Check to see if list is now empty, and remove all data from file if it is
        if (list.size() == 0) {
            writer.flush();
            writer.close();
            new PrintWriter(DATA_FILE).close();
        }
        else {
            //overwriting data without previous element
            writer.write(cipher.doFinal(list.stream()
                                            .map(PassEntry::toString)
                                            .collect(Collectors.joining("\n"))
                                            .getBytes()));
        }
    }
}