package dream.cs123.cs_123_dream;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BrowseMode extends AppCompatActivity {

    private static final String EMERGENCY_TYPE = "EMERGENCY_TYPE";
    private static final String CURRENT_MODE = "CURRENT_MODE";

    private Button mBBTNswitch;
    private Button mBBTNaddcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_mode);

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
        ((Button)findViewById(R.id.BPolEmer)).setTypeface(pnbold);
        ((Button)findViewById(R.id.BVehEmer)).setTypeface(pnbold);
        ((Button)findViewById(R.id.BHealEmer)).setTypeface(pnbold);
        ((Button)findViewById(R.id.browseSwitch)).setTypeface(pnbold);

        mBBTNswitch = (Button) findViewById(R.id.browseSwitch);
        mBBTNswitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        mBBTNaddcontact = (Button) findViewById(R.id.addContact);
        mBBTNaddcontact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendAddContactPrompt();
            }
        });
    }

    private void sendAddContactPrompt() {
        Intent intent = new Intent(this, ContactFullView.class);
        startActivity(intent);
    }

    public void sendSpecEmerPolB(View view) {
        Intent intent = new Intent (this, VerySpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.BPolEmer);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Browse");
        startActivity(intent);
    }

    public void sendSpecEmerHealB(View view) {
        Intent intent = new Intent (this, VerySpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.BHealEmer);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Browse");
        startActivity(intent);
    }

    public void sendSpecEmerVehB(View view) {
        Intent intent = new Intent (this, VerySpecificEmergency.class);
        Button btn = (Button) findViewById(R.id.BVehEmer);
        String msg = btn.getText().toString();
        intent.putExtra(EMERGENCY_TYPE, msg);
        intent.putExtra(CURRENT_MODE, "Browse");
        startActivity(intent);
    }

}
