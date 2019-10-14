package id.ac.itn.mymeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.itn.mymeeting.fragment.MeetingFragment;
import id.ac.itn.mymeeting.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    MeetingFragment meetingFragment = new MeetingFragment();
    SettingFragment settingFragment = new SettingFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = meetingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.frame, settingFragment, "2").hide(settingFragment).commit();
        fm.beginTransaction().add(R.id.frame, meetingFragment, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_nav_meeting:
                            fm.beginTransaction().hide(active).show(meetingFragment).commit();
                            active = meetingFragment;
                            return true;

                        case R.id.bottom_nav_settings:
                            fm.beginTransaction().hide(active).show(settingFragment).commit();
                            active = settingFragment;
                            return true;

                    }
                    return false;
                }
            };

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //parameter is the name of service we want in either string format or referenced through the context CLASS as seen.

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //this also needs permission in the Android Manifest

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

}
