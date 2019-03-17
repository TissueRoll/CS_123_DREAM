package dream.cs123.cs_123_dream;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import dream.cs123.cs_123_dream.models.Contact;

public class ViewContact extends AppCompatActivity {

    private static final String TAG = "ViewContact";

    private RealmSingleton rs = RealmSingleton.getInstance();
    private Contact contact;
    private String cid;

//    --- Location vars
    protected Location mLastLocation;
    private String mLatitude;
    private String mLongitude;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
//    --- Location vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_contact);

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

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1); // this may be needed for sms
        cid = getIncomingIntent();
        contact = rs.fetch_individual_contact(cid);

        TextView contact_name = (TextView) findViewById(R.id.contact_name);
        TextView contact_number = (TextView) findViewById(R.id.contact_number2);

        contact_name.setText(contact.get_name());
        contact_number.setText(contact.get_contact_number());

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    public void view_contact_back_button (View view) {
        finish();
    }

    public void callContact(View view) {
        Uri number = Uri.parse("tel:" + contact.get_contact_number());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void sendMessageContact(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        Uri number = Uri.parse("smsto:" + contact.get_contact_number());
        sendIntent.setData(number);
        if (mLatitude == null || mLongitude == null) {
            sendIntent.putExtra("sms_body", "This is an automated message from the sender. Do not worry, since I am able to reach you, that means I am safe! You are getting this message because I got into an accident. I should contact you in a bit! If not, please call me to be sure!");
        } else {
            sendIntent.putExtra("sms_body", "This is an automated message from the sender. Do not worry, since I am able to reach you, that means I am safe! You are getting this message because I got into an accident. My last known location according to my phone is http://maps.google.com/maps?q=" + mLatitude + "," + mLongitude + " . You can click this link to find out where I am on the map!");
        }

        startActivity(sendIntent);
    }

    private String getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking");
        if(getIntent().hasExtra("contact_id")) {
            Log.d(TAG, "getIncomingIntent: found");
            return getIntent().getStringExtra("contact_id");
        }
        Log.d(TAG, "getIncomingIntent: not found");
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // following code lifted from Sanjay007, https://github.com/Sanjay007/LocationExample/blob/master/app/src/main/java/com/example/sanju/locationexamplew/MainActivity.java
    // all credit goes to him

//    ---
    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.loc_perm, R.string.settings,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }
    }

    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.loc_perm, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    private void getLastLocation() {
        try {
            mFusedLocationClient.getLastLocation()
                    .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                mLastLocation = task.getResult();
                                mLatitude = String.valueOf(mLastLocation.getLatitude());
                                mLongitude = String.valueOf(mLastLocation.getLongitude());
                            } else {
                                Log.w(TAG, "getLastLocation:exception", task.getException());
                            }
                        }
                    });
        } catch (SecurityException e) {
            Log.e(TAG, "getLastLocation: permission denied");
        }

    }
//    ---

}
