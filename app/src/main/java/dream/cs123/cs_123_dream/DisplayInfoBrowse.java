package dream.cs123.cs_123_dream;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// missing the sources for hardcode

public class DisplayInfoBrowse extends AppCompatActivity {
    private static final String TAG = "DisplayInfoBrowse";

    private static final String EMERGENCY_CODE = "EMERGENCY_CODE";
    private static final String CURRENT_MODE = "CURRENT_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_more_information);

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

        Intent intent = getIntent();
        String message = intent.getStringExtra("EMERGENCY_CODE");
        //String cur_mode = intent.getStringExtra("CURRENT_MODE");
        TextView tv1 = findViewById(R.id.text_display2);
        TextView title = findViewById(R.id.textView2);
        title.setText(message);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        if (message == null) {
            Log.d(TAG, "onCreate: holy shit why is it null");
        }
//        --- police
        else if (message.equals("Police Checkpoint")) {
            tv1.setText(getString(R.string.police_emergency_B_00));
        }
//        --- police

//        --- health
        else if (message.equals("Bleeding")) {
            tv1.setText(getString(R.string.health_emergency_B_00));
        } else if (message.equals("Breathing Difficulties")) {
            tv1.setText(getString(R.string.health_emergency_B_01));
        } else if (message.equals("CPR")) {
            tv1.setText(getString(R.string.health_emergency_B_02));
        } else if (message.equals("Seizures / Epileptic Fit")) {
            tv1.setText(getString(R.string.health_emergency_B_03));
        } else if (message.equals("Heart Attack")) {
            tv1.setText(getString(R.string.health_emergency_B_04));
        } else if (message.equals("Stroke")) {
            tv1.setText(getString(R.string.health_emergency_B_05));
        }
//        --- health

//        --- vehicular
        else if (message.equals("Minor Collision")) {
            tv1.setText(getString(R.string.vehicle_emergency_B_00));
        } else if (message.equals("Major Collision")) {
            tv1.setText(getString(R.string.vehicle_emergency_B_01));
        }

//        --- unknown
        else {
            tv1.setText(getString(R.string.lorem_long));
        }
    }

    public void display_info_browse_back_button (View view) {
        finish();
    }

}
