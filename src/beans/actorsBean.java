package beans;

import helpers.jsonHelper;
import helpers.keyvaluepair;

import java.util.ArrayList;

public class actorsBean {

    private String fname;
    private String lname;
    private int age;
    private int id;

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String toJson() {
        ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
        datalist.add(new keyvaluepair("Name", this.fname));
        datalist.add(new keyvaluepair("Name", this.lname));
        datalist.add(new keyvaluepair("Age", Integer.toString(this.age)));
        return jsonHelper.toJsonObject(datalist);
    }

    @Override
    public String toString() {
        return "actorsBean{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
