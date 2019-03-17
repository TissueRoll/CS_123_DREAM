package dream.cs123.cs_123_dream;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import dream.cs123.cs_123_dream.models.Contact;
import dream.cs123.cs_123_dream.models.ContactFullAdapter;
import io.realm.RealmResults;


public class ContactFullView extends AppCompatActivity {

    //private Realm realm;
    private ContactFullAdapter adapter;
    private RecyclerView rv;
    private RealmSingleton rs = RealmSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // just get the fuckin shit look decent
        TextView tvtemp = new TextView(this);
        tvtemp.setText("BROWSE");
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

//        --- initialization of activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_main_list);
//        --- initialization of activity

//        --- RecyclerView initialization
        rv = (RecyclerView) findViewById(R.id.list_v2);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        --- RecyclerView initialization

//        --- initialization of Realm/adapter
        RealmResults<Contact> contacts = rs.fetch_contacts();
        contacts.sort("last_name");
        adapter = new ContactFullAdapter(this, contacts);
        rv.setAdapter(adapter);
//        --- initialization of Realm/adapter

//        --- Floating Action Button stuff
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_v2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactFullView.this, AddContact.class);
                startActivity(intent);
            }
        });
//        --- Floating Action Button stuff
    }

    public void contact_full_view_back_button (View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rs.close_db();
    }


}
