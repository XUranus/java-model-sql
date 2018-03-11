package Model;
import lib.Model;
import java.util.ArrayList;

public class User extends Model {
    private static ArrayList<String> Keys;

    public User(ArrayList<String> Keys) {
        super("User",Keys);
    }
}
