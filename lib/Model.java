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

    private int id;
    //id cannot be editable

    private ArrayList<Attribute> attributeList;

    public Model() {
        // junk Consructor
        this.name = "null";
        this.id = -1;
        this.attributeList = null;
    }

    public Model(String name,ArrayList<Attribute> attributes) {
        //construct for return
        this.name = name;
        this.id = -1;
        this.attributeList = attributes;
    }

    public Model(String name,int id,ArrayList<Attribute> attributes) {
        //construct for return
        this.name = name;
        this.id = id;
        this.attributeList = attributes;
    }

    public String getModelName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }


    /************************** Interface declared below **********************************/


    public Model find(int index) {
        //find the record of the index in the table
        String sql = "SELECT * FROM "+this.getModelName()+" WHERE ID = "+index;
        return excuteQuery(sql);
    }

    public Model find(Attribute attribute) {
        //find the record of the attribute in the table
        String sql = "SELECT * FROM "+this.getModelName()+" WHERE "+attribute.getKey()+" = "+attribute.getValue();
        return excuteQuery(sql);
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
        excuteQuery(sql);
    }

    public void create() {
        //create a new instance of the modelType
        //it must be first time to call
        create(attributeList);
    }

    public void update(Attribute attribute) {
        //update the instance after edit
        String sql = "UPDATE "+this.getModelName()+" "+
        "SET "+attribute.getKey()+" = "+attribute.getValue()+" "+
        "WHERE id = "+this.getId();
        excuteQuery(sql);
    }

    public void delete() {
        //delete the instance from the mysql table
        String sql = "DELETE FROM "+this.getModelName()+" WHRER  ID = "+this.getId();
        excuteQuery(sql);
    }

    public Model excuteQuery(String sql) {
        //excute Query
        //connect -> query -> close
        MysqlExcutor excuter = new MysqlExcutor();
        excuter.query(sql);
        return new Model();
    }




}
