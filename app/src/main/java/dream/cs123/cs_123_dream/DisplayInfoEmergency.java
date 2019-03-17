package dream.cs123.cs_123_dream;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// this is bugged; i cant go back from here

public class DisplayInfoEmergency extends AppCompatActivity {

    private static final String TAG = "DisplayInfoEmergency";
    
    private static final String EMERGENCY_CODE = "EMERGENCY_CODE";
    private static final String CURRENT_MODE = "CURRENT_MODE";
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

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

        Intent intent = getIntent();
        String message = intent.getStringExtra("EMERGENCY_CODE");
        //String cur_mode = intent.getStringExtra("CURRENT_MODE");
        TextView tv1 = findViewById(R.id.text_display);
        TextView title = findViewById(R.id.textView);
        title.setText(message);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        if (message == null) {
            Log.d(TAG, "onCreate: holy shit why is it null");
        }
//        --- police
        else if (message.equals("Police Checkpoint")) {
            tv1.setText(getString(R.string.police_emergency_E_00));
        }
//        --- police

//        --- health
        else if (message.equals("Bleeding")) {
            tv1.setText(getString(R.string.health_emergency_E_00));
        } else if (message.equals("Breathing Difficulties")) {
            tv1.setText(getString(R.string.health_emergency_E_01));
        } else if (message.equals("CPR")) {
            tv1.setText(getString(R.string.health_emergency_E_02));
        } else if (message.equals("Seizures / Epileptic Fit")) {
            tv1.setText(getString(R.string.health_emergency_E_03));
        } else if (message.equals("Heart Attack")) {
            tv1.setText(getString(R.string.health_emergency_E_04));
        } else if (message.equals("Stroke")) {
            tv1.setText(getString(R.string.health_emergency_E_05));
        }
//        --- health

//        --- vehicular
        else if (message.equals("Minor Collision")) {
            tv1.setText(getString(R.string.vehicle_emergency_E_00));
        } else if (message.equals("Major Collision")) {
            tv1.setText(getString(R.string.vehicle_emergency_E_01));
        }

//        --- unknown
        else {
            tv1.setText(getString(R.string.lorem_long));
        }
    }

    public void display_info_emergency_back_button (View view) {
        finish();
    }

    public void lookAtContacts(View view) {
        Intent intent = new Intent(this, ContactLookEmergency.class);
        startActivity(intent);
    }

    public void testCall2(View view) {
        Uri number = Uri.parse("tel:911");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void openCam(View view) {
        /*
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        */
        // https://stackoverflow.com/questions/18599421/launch-default-camera-app-no-return
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            PackageManager pm = this.getPackageManager();

            final ResolveInfo mInfo = pm.resolveActivity(i, 0);

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(mInfo.activityInfo.packageName, mInfo.activityInfo.name));
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        } catch (Exception e){
            Log.i(TAG, "Unable to launch camera: " + e);
        }
    }

}
