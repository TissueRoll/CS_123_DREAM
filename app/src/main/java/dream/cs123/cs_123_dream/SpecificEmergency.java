package dream.cs123.cs_123_dream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class SpecificEmergency extends AppCompatActivity {

    private static final String EMERGENCY_CODE = "EMERGENCY_CODE";
    private static final String CURRENT_MODE = "CURRENT_MODE";
    private ArrayList<String> police, health, vehicle, error_list, fList;
    private SpecEmerAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_emergency);

        // just get the fuckin shit look decent
        TextView tvtemp = new TextView(this);
        tvtemp.setText("EMERGENCY");
        tvtemp.setTextSize(20);
        tvtemp.setTypeface(null, Typeface.BOLD);
        tvtemp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tvtemp.setGravity(Gravity.CENTER);
        tvtemp.setTextColor(getResources().getColor(R.color.white));
        Typeface pnbold = getResources().getFont(R.font.proxima_nova_bold);
        tvtemp.setTypeface(pnbold);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tvtemp);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.claret)));

        context = this;
        fList = new ArrayList<>();
        police = new ArrayList<>();
        health = new ArrayList<>();
        vehicle = new ArrayList<>();
        error_list = new ArrayList<>();
        initializeLists();
        Intent intent = getIntent();
        String message = intent.getStringExtra("EMERGENCY_TYPE");
        String cur_mode = intent.getStringExtra("CURRENT_MODE");

        final RecyclerView rV = (RecyclerView) findViewById(R.id.see1);
        rV.setLayoutManager(new LinearLayoutManager(this));
        if (message.equals("Police Emergency")) {
            //adapter = new SpecEmerAdapter(this, police);
            fList = police;
        } else if (message.equals("Health Emergency")) {
            //adapter = new SpecEmerAdapter(this, health);
            fList = health;
        } else if (message.equals("Vehicular Emergency")) {
            //adapter = new SpecEmerAdapter(this, vehicle);
            fList = vehicle;
        } else {
            fList = error_list;
            //adapter = new SpecEmerAdapter(this, error_list);
        }
        adapter = new SpecEmerAdapter(this, fList);
        rV.setAdapter(adapter);

    }

    private void initializeLists() {
//        --- police
        if (police.isEmpty()) {
            // version 1
            police.add("Police Checkpoint");
        }
//        --- police

//        --- health
        if (health.isEmpty()) {
            // version 1
            health.add("Bleeding");
            health.add("Breathing Difficulties");
            health.add("CPR");
            health.add("Seizures / Epileptic Fit");
            health.add("Heart Attack");
            health.add("Stroke");
        }
//        --- health

//        --- vehicular
        if (vehicle.isEmpty()) {
            // version 1
            vehicle.add("Minor Collision");
            vehicle.add("Major Collision");
        }
//        --- vehicular

//        --- standard
        if (error_list.isEmpty()) {
            error_list.add("Lorem Ipsum");
            error_list.add("Lorem Ipsum");
        }
    }

    public void specific_emergency1_back_button (View view) {
        finish();
    }


}
