package dream.cs123.cs_123_dream;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import dream.cs123.cs_123_dream.models.Contact;
import io.realm.Realm;

public class AddContact extends AppCompatActivity {

    //private Realm realm;
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
        setContentView(R.layout.activity_add_contact);
//        --- initialization of activity

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void add_contact_back_button (View view) {
        finish();
    }

    // have to add preventive check here
    public void add_contact_to_db (View view) {
        final String first_name = ((EditText) findViewById(R.id.add_contact_first_name)).getText().toString();
        final String last_name = ((EditText) findViewById(R.id.add_contact_last_name)).getText().toString();
        final String contact_number = ((EditText) findViewById(R.id.add_contact_number)).getText().toString();

        boolean fnok = acceptable_first_name(first_name);
        boolean lnok = acceptable_last_name(last_name);
        boolean nok = acceptable_number(contact_number);

        if (fnok && lnok && nok) {
            rs.save_contact(first_name, last_name, contact_number);
            finish();
        } else {
            String errors = "";
            if (!fnok) {
                errors += "Please start first name with a letter";
            }
            if (!lnok) {
                if (errors.length() > 0) errors += "\n";
                errors += "Please start last name with a letter";
            }
            if (!nok) {
                if (errors.length() > 0) errors += "\n";
                errors += "Please enter a number in this format: +639171234567";
            }
            Toast toast = Toast.makeText(getApplicationContext(),
                    errors,
                    Toast.LENGTH_SHORT);

            toast.show();
        }

    }

    private boolean acceptable_number (String number) {
        if (number.charAt(0) == '+' && number.length() == 13 && number.substring(1).matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean acceptable_first_name (String first_name) {
        if (first_name.length() > 1 && first_name.matches("[ a-zA-Z]+") && Character.isLetter(first_name.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean acceptable_last_name (String last_name) {
        if (last_name.length() > 1 && last_name.matches("[ a-zA-Z]+") && Character.isLetter(last_name.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }

}
