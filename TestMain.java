import Model.User;
import lib.Attribute;
import lib.Model;
import lib.MysqlExcutor;

import java.util.ArrayList;

public class TestMain {
    public static void main(String [] args) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("username");
        keys.add("id");
        User user = new User(keys);
        /*
        ArrayList<Model> users = user.find("username","XUranus");
        for(Model u: users) {
            System.out.println(u);
        }*/

        //System.out.println(user.find(2));

        /*ArrayList<Attribute> attrList = new ArrayList<>();
        attrList.add(new Attribute("username","WXX"));
        user.create(attrList);*/

        //user.find(1).delete();


        Model newUser = user.find(2);
        newUser.setAttribute("username","XYX");
        System.out.println(newUser);
        newUser.update();

        /*MysqlExcutor excutor = new MysqlExcutor();
        String sql = "CREATE TABLE user( "+
        "id INT NOT NULL AUTO_INCREMENT, "+
        "username VARCHAR(100) NOT NULL, "+
        "PRIMARY KEY ( id ))";
        excutor.query(sql);*/

    }

}
