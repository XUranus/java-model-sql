import Model.User;
import lib.Attribute;
import lib.MysqlExcutor;

import java.util.ArrayList;

public class TestMain {
    public static void main(String [] args) {
        ArrayList<Attribute> attrList = new ArrayList<>();
        attrList.add(new Attribute("username","XUranus"));
        //attrList.add(new Attribute("age",20));

        User user = new User(attrList);
        //user.create();
        //user.delete();
        user.find(1);
        //user.find(new Attribute("username","XUranus"));
        //user.update(new Attribute("username","GODV"));

        /*MysqlExcutor excutor = new MysqlExcutor();
        String sql = "CREATE TABLE user( "+
        "id INT NOT NULL AUTO_INCREMENT, "+
        "username VARCHAR(100) NOT NULL, "+
        "PRIMARY KEY ( id ))";
        excutor.query(sql);*/

    }

}
