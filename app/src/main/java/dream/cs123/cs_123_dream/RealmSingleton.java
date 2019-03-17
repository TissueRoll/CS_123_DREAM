package dream.cs123.cs_123_dream;

import android.util.Log;

import java.util.UUID;

import dream.cs123.cs_123_dream.models.Contact;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmSingleton {

    private static Realm realm;
    public static RealmSingleton sharedInst;

    RealmSingleton() {
        realm = Realm.getDefaultInstance();
    }


    public static RealmSingleton getInstance() {
        if (sharedInst == null) {
            sharedInst = new RealmSingleton();
            //realm = Realm.getDefaultInstance();
        }
        return sharedInst;
    }


//    public Realm get_realm_instance() {
//        if (realm == null) {
//            realm = Realm.getDefaultInstance();
//        }
//        return realm;
//    }

    public void save_contact(final String first_name, final String last_name, final String number) {
        final String name = first_name + " " + last_name;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact c = realm.createObject(Contact.class, UUID.randomUUID().toString());
                c.set_name(name);
                c.set_first_name(first_name);
                c.set_last_name(last_name);
                c.set_contact_number(number);
            }
        });
    }

    public void edit_contact(final String contact_id, final String first_name, final String last_name, final String number) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact contact = realm.where(Contact.class).equalTo("id", contact_id).findFirst();
                contact.set_name(first_name + " " + last_name);
                contact.set_first_name(first_name);
                contact.set_last_name(last_name);
                contact.set_contact_number(number);
            }
        });
    }

    public void delete_contact(final String contact_id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Contact.class).equalTo("id", contact_id)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }

    public Contact fetch_individual_contact(final String contact_id) {
        return realm.where(Contact.class).equalTo("id",contact_id).findFirst();
    }

    public RealmResults<Contact> fetch_contacts() {
        return realm.where(Contact.class).findAll();
    }

    public void close_db() {
        realm.close();
        realm = null;
        sharedInst = null;
    }

}
