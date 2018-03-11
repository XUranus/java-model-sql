# Java Model-to-SQL famework
## About this
this is a basic java framework , simplify the insert,select,update,delete query of mysql  
just like Eloquent Model in php laravel  

## Usage & example code
when you want to create a model ,for example : `User`   
you just need to create a class like the following below:  
```
public class User extends Model {
    private static ArrayList<String> Keys;
    public User(ArrayList<String> Keys) {
        super("User",Keys);
    }
}
```
making it inhert the class `lib.Model`, which provides various interface interact with mySQL  
when you want to edit the model data, just like the following:

### SQL prepartion
you must create databases and schemas first  
excute in command line:`CREATE DATABASE XXX`,then create `CREATE TABLE xxxx`  
you must make sure that table has `ID`, which be **auto increasement**

### Model init
```
ArrayList<String> keys = new ArrayList<>();
keys.add("username");
keys.add("id");
User user = new User(keys);
```

### Model Method 
#### find(index)
return an unique model instance which id = index 
```
user.find(2);
```
#### find(String key,Object value)  
return a ArrayList may contains several model instance  
```
ArrayList<Model> users = user.find("username","XUranus");
```
#### delete() 
delete an instance from the table
```
user.find(1).delete();
```
#### create(ArrayList <Attribute> attrList)
```
ArrayList<Attribute> attrList = new ArrayList<>();
attrList.add(new Attribute("username","WXX"));
user.create(attrList);
```
#### update()
```
Model newUser = user.find(2);
newUser.setAttribute("username","XYX");
newUser.update();
```
## Many bugs ， Still developing...
