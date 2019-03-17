package dream.cs123.cs_123_dream;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.UUID;
import io.realm.Realm;
import io.realm.RealmResults;
import dream.cs123.cs_123_dream.models.Contact;
//import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;

/*
    KNOWN BUGS:

 */

public class ContactView extends AppCompatActivity {

    /*
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // just get the fuckin shit look decent
        TextView tvtemp = new TextView(this);
        tvtemp.setText("CONTACTS");
        tvtemp.setTextSize(20);
        tvtemp.setTypeface(null, Typeface.BOLD);
        tvtemp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tvtemp.setGravity(Gravity.CENTER);
        tvtemp.setTextColor(getResources().getColor(R.color.white));
        Typeface pnbold = getResources().getFont(R.font.proxima_nova_bold);
        tvtemp.setTypeface(pnbold);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tvtemp);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.steelteal)));

        realm = Realm.getDefaultInstance();

        RealmResults<Contact> contacts = realm.where(Contact.class).findAll();
        contacts = contacts.sort("name");
        final ContactAdapter adapter = new ContactAdapter(this, contacts);

        ListView listView = (ListView) findViewById(R.id.contact_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Contact contact = (Contact) parent.getAdapter().getItem(position);
                LayoutInflater inflater = (ContactView.this).getLayoutInflater();
                View temp = inflater.inflate(R.layout.contact_action_screen, null);
                final EditText editContactName = (EditText) temp.findViewById(R.id.actionContactName);
                final EditText editContactNumber = (EditText) temp.findViewById(R.id.actionContactNumber);
                editContactName.setText(contact.get_name());
                editContactNumber.setText(contact.get_contact_number());
                AlertDialog dialog = new AlertDialog.Builder(ContactView.this)
                        .setTitle("Edit Contact Information") // sets title of the dialog box
                        .setView(temp) // the xml file of the alert dialog
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() { // save button
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeContactName(contact.get_id(), String.valueOf(editContactName.getText()));
                                changeContactNumber(contact.get_id(), String.valueOf(editContactNumber.getText()));
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() { // delete button
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteContact(contact.get_id());
                            }
                        })
                        .create();
                dialog.show(); // shows dialog box
            }
        });

        Button mBTNadd = (Button) findViewById(R.id.addConfirm);
        mBTNadd.setOnClickListener(new View.OnClickListener(){ // what happens when you click this
            @Override
            public void onClick(View view){
                LayoutInflater inflater = (ContactView.this).getLayoutInflater();
                View temp = inflater.inflate(R.layout.contact_action_screen, null);
                final EditText addContactName = (EditText) temp.findViewById(R.id.actionContactName);
                final EditText addContactNumber = (EditText) temp.findViewById(R.id.actionContactNumber);
                AlertDialog dialog = new AlertDialog.Builder(ContactView.this) // dialog box; research how to put more shit in the dialog box
                        .setTitle("Add Contact")
                        .setView(temp)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        Contact contact = realm.createObject(Contact.class, UUID.randomUUID().toString()); // creates a contact entry with a UUID, defaults with NULL
                                        contact.set_name(String.valueOf(addContactName.getText()));
                                        contact.set_contact_number(String.valueOf(addContactNumber.getText())); // consider converting contact no. to string
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        Button mBTNcancel = (Button) findViewById(R.id.addCancel);
        mBTNcancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
    */

    /*
        closes realm instance to deallocate memory
     */
    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void changeContactName(final int contactId, final String name) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact contact = realm.where(Contact.class).equalTo("id", contactId).findFirst();
                contact.set_name(name);
            }
        });
    }

    private void changeContactNumber(final int contactId, final String number) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact contact = realm.where(Contact.class).equalTo("id", contactId).findFirst();
                contact.set_contact_number(number);
            }
        });
    }

    private void deleteContact(final int contactId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Contact.class).equalTo("id", contactId)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }
    */

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }
    */


}
