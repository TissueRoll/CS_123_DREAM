package dream.cs123.cs_123_dream.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Hotline extends RealmObject {
    //@Required
    @PrimaryKey
    private int id;
    //@Required
    private String name;
    //@Required
    private int contact_number;

    /*
    set functions
     */

    public void set_id(int id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_contact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    /*
    get functions
     */

    public int get_id() {
        return id;
    }

    public String get_name() {
        return name;
    }

    public int get_contact_number(){
        return contact_number;
    }


}
