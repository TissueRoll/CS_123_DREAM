package dream.cs123.cs_123_dream;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dream.cs123.cs_123_dream.models.PermissionsUtils;


public class EmergencyModeMain extends AppCompatActivity {

    private static final String EMERGENCY_TYPE = "EMERGENCY_TYPE";
    private static final String CURRENT_MODE = "CURRENT_MODE";
    private Button mEBTNswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_mode_main);

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
        ((Button)findViewById(R.id.EmerVehicle)).setTypeface(pnbold);
        ((Button)findViewById(R.id.EmerHealth)).setTypeface(pnbold);
        ((Button)findViewById(R.id.EmerPolice)).setTypeface(pnbold);
        ((Button)findViewById(R.id.SwitchMode)).setTypeface(pnbold);

        PermissionsUtils.checkAndRequestPermissions(this);

        mEBTNswitch = (Button) findViewById(R.id.SwitchMode);
        mEBTNswitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendSwitchToggle();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EmergencyModeMain.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

    public void sendSpecEmerPol(View view) {
        Intent intent = new Intent (this, SpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.EmerPolice);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Emergency");
        startActivity(intent);
    }

    public void sendSpecEmerHeal(View view) {
        Intent intent = new Intent (this, SpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.EmerHealth);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Emergency");
        startActivity(intent);
    }

    public void sendSpecEmerVeh(View view) {
        Intent intent = new Intent (this, SpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.EmerVehicle);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Emergency");
        startActivity(intent);
    }

    private void sendSwitchToggle() {
        Intent intent = new Intent(this, BrowseMode.class);
        startActivity(intent);
    }

}
