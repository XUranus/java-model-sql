package lib;

import java.util.ArrayList;

public class Model implements ModelAPI{
    /**
     This is a abstarct class only used to be inhert by Models
     providing various mySQL interface
     Simplifying the use to the mySQL
    **/
    private String name;
    //name cannot be editable

    private ArrayList<Attribute> attributeList;

    public Model(String name,ArrayList<String> Keys) {
        this.name = name;
        this.attributeList = new ArrayList<>();
        for(String key:Keys) {
            attributeList.add(new Attribute(key,null));
        }
    }

    private Model(ArrayList<Attribute> attributeList,String Name) {
        //for inner construction
        this.name = Name;
        this.attributeList = attributeList;
    }

    public String getModelName() {
        return this.name;
    }

    public String toString() {
        String result = "Model: "+this.getModelName()+"\n";
        for(int i=0;i<attributeList.size();i++) {
            result+= attributeList.get(i).getKey()+"\t";
        }
        result += "\n";
        for(int i=0;i<attributeList.size();i++) {
            result += attributeList.get(i).getValue()+"\t";
        }
        result +="\n";
        return result;
    }

    public int getId() {
        //id must exist when you design the table!
        //if not exist or not created an instance -> return -1
        for(Attribute attr:attributeList) {
            if(attr.getKey().toUpperCase().equals("ID"))
                return Integer.parseInt((String)attr.getValue());
        }
        return -1;
    }

    public void setAttribute(String key,Object value) {
        for(Attribute attr:attributeList) {
            if(attr.getKey().equals(key)) {
                attr.setValue(value);
                return;
            }
        }
        System.out.println("attribute : '"+key+"' not found!");
    }

    public Object getAttribute(String key) {
        for(Attribute attr:attributeList) {
            if(attr.getKey().equals(key)) {
                return attr.getKey();
            }
        }
        System.out.println("attribute : '"+key+"' not found!");
        return null;
    }


    /************************** Interface declared below **********************************/


    public Model find(int index) {
        //find the record of the index in the table
        //index is the main key auto increasement
        String sql = "SELECT * FROM "+this.getModelName()+" WHERE ID = "+index;
        MysqlExcutor excuter = new MysqlExcutor();
        ArrayList resultArrays = excuter.excuteQuery(this.attributeList,sql);
        if(resultArrays.size()==1) {
            return new Model((ArrayList)resultArrays.get(0),this.getModelName());
        }
        else return null;
    }

    public ArrayList<Model> find(String key,Object value) {
        //find the record of the attribute in the table
        //maybe multiple results
        Attribute attribute = new Attribute(key,value);
        String sql = "SELECT * FROM "+this.getModelName()+" WHERE "+attribute.getKey()+" = "+attribute.getValue();
        MysqlExcutor excuter = new MysqlExcutor();
        ArrayList resultArrays = excuter.excuteQuery(this.attributeList,sql);
        ArrayList<Model> models = new ArrayList<>();
        if(resultArrays.size()>=1) {
            for(int i=0;i<resultArrays.size();i++) {
                models.add(new Model((ArrayList)resultArrays.get(i),this.getModelName()));
            }
            return models;
        }
        else return null;
    }

    public void create(ArrayList<Attribute> attributeList) {
        //create a new instance of the modelType
        //it must be first time to call!
        String attrs = "(";
        for(int i=0;i<attributeList.size();i++) {
            attrs += attributeList.get(i).getKey();
            if(i!=attributeList.size()-1) {
                attrs += ",";
            }
        }
        attrs += ") ";

        String values = "(";
        for(int i=0;i<attributeList.size();i++) {
            values += attributeList.get(i).getValue();
            if(i!=attributeList.size()-1) {
                values += ",";
            }
        }
        values += ") ";

        String sql = "INSERT INTO "+this.getModelName()+" "+attrs+"VALUES "+values;

        MysqlExcutor excuter = new MysqlExcutor();
        int rs = excuter.excuteUpdate(sql);
        if(rs==1) {
            System.out.println("create Success");
        }
        else {
            System.out.println("create Failed");
        }
    }


    public void update(Attribute attribute) {
        //update the instance after edit
        String sql = "UPDATE "+this.getModelName()+" "+
        "SET "+attribute.getKey()+" = "+attribute.getValue()+" "+
        "WHERE id = "+this.getId();
        MysqlExcutor excuter = new MysqlExcutor();
        int rs = excuter.excuteUpdate(sql);
        System.out.println("update "+rs+"items success");
    }

    public void update() {
        for(Attribute attr:attributeList) {
            update(attr);
        }
    }


    public void delete() {
        //delete the instance from the mysql table
        String sql = "DELETE FROM "+this.getModelName()+" WHERE  ID = "+this.getId();
        MysqlExcutor excuter = new MysqlExcutor();
        int rs = excuter.excuteUpdate(sql);
        System.out.println("delete "+rs+"items success");
    }

}
