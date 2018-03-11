package lib;

import java.util.ArrayList;

public interface ModelAPI {
    public void create(ArrayList<Attribute> attributeList);
    public void delete();
    public void update(Attribute attribute);
    public Model find(int index);
    public ArrayList<Model> find(String key,Object value);

}
