package dream.cs123.cs_123_dream.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Emergency extends RealmObject {
    //@Required
    @PrimaryKey
    private int id;
    //@Required
    private String type;
    //@Required
    private String name;
    //@Required
    private String ez_description;
    //@Required
    private String description;

    /*
    set functions
     */

    public void set_id(int id) {
        this.id = id;
    }

    public void set_type(String type) {
        this.type = type;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_ez_description(String ez_description) {
        this.ez_description = ez_description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    /*
    get functions
     */

    public int get_id() {
        return id;
    }

    public String get_type() {
        return type;
    }

    public String get_name() {
        return name;
    }

    public String get_ez_description() {
        return ez_description;
    }

    public String get_description() {
        return description;
    }

}
