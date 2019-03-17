package dream.cs123.cs_123_dream.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Contact extends RealmObject {
    //@Required
    @PrimaryKey
    private String id;
    @Required
    private String name;
    @Required
    private String first_name;
    @Required
    private String last_name;
    //@Required
    private String contact_number;

    /*
    set functions
     */
    public void set_id(String id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }

    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }

    public void set_contact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    /*
    get functions
     */

    public String get_id() {
        return id;
    }

    public String get_name() {
        return name;
    }

    public String get_first_name() {
        return first_name;
    }

    public String get_last_name() { return last_name; }

    public String get_contact_number () {
        return contact_number;
    }

}
