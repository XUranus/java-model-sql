package lib;

public class Attribute {
    /**
     For Example :
            ----------------------
            |   username  |  id  |
            ----------------------
            |   XUranus   | 1000 |
            ----------------------
     In this row of table {"username","XUranus"} is an Attribute
     A record is composed of several Attribute
     we mark it by key -- value

     T value: value only allow to be String or int or double
     **/
    private String key;
    private Object value;

    public Attribute(String key,Object value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(Object newValue) {
        this.value = newValue;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        if(this.value.getClass().toString().endsWith("String")) {
            return "'"+this.value+"'";
        }
        return this.value.toString();
    }
}
