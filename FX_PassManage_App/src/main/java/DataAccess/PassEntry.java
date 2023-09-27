package DataAccess;

/**
 * <hr>
 * <h2>
 * {@code PassEntry}
 * </h2>
 * <p>This class represents a single entry in the {@code passMap} object, representing a single
 * username-password pair, belonging to a specific key in the passMap {@code HashMap}. The
 * strings store in this object will be decrypted, plain text that will be used to display the
 * values for access in the application</p>
 * <hr>
 */
public record PassEntry(String category, String user, String pass)
{

    public String toString()
    {
        return "<" + category + ":" + user + ":" + pass + ">";
    }

    public boolean equals(PassEntry passEntry)
    {
        return this.category.equals(passEntry.category()) && this.user.equals(passEntry.user()) && this.pass.equals(
                passEntry.pass());
    }

}