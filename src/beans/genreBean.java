package beans;

import helpers.jsonHelper;
import helpers.keyvaluepair;

import java.util.ArrayList;

public class genreBean {
    private int id;
    private String titel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public String toString() {
        return "genreBean{" +
                "id=" + id +
                ", titel='" + titel + '\'' +
                '}';
    }

    public String toJson() {
        ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
        datalist.add(new keyvaluepair("Title", (this.titel)));
        return jsonHelper.toJsonObject(datalist);
    }


}
