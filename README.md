# Java Model-to-SQL famework
## About this
this is a basic java framework , simplify the insert,select,update,delete query of mysql  
just like Eloquent Model in php laravel  

## Usage & example code
when you want to create a model ,for example : `User`   
you just need to create a class like the following below:  
```
public class User extends Model {
    public User(ArrayList<Attribute> attributeList) {
        super("User",attributeList);
    }
}
```
making it inhert the class `lib.Model`, which provides various interface interact with mySQL  
when you want to edit the model data, just like the following:  
```
ArrayList<Attribute> attrList = new ArrayList<>();
attrList.add(new Attribute("username","XUranus"));
attrList.add(new Attribute("age",20));
 
User user = new User(attrList);
user.create();
user.find(1);
user.find(new Attribute("username","XUranus"));
user.update(new Attribute("username","GODV"));
user.delete();
```
## Many bugs ， Still developing...