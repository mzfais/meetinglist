package id.ac.itn.mymeeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.itn.mymeeting.fragment.MeetingFragment;
import id.ac.itn.mymeeting.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String APP_STATUS = "null";
    private static final String FIRST_LOAD = "true";
    private String fActive = "meeting";
    private Boolean firstLoad = true;
    /*
        MeetingFragment meetingFragment = new MeetingFragment();
        SettingFragment settingFragment = new SettingFragment();
    */
    final FragmentManager fm = getSupportFragmentManager();
//    Fragment active = new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            loadFragment(new MeetingFragment(),fActive);
        } else {
            String status = savedInstanceState.getString(FIRST_LOAD);
            firstLoad = Boolean.parseBoolean(status);
            Log.d(TAG, "onCreate: meeting firstload: " + status);
            if (savedInstanceState.getString(APP_STATUS).equals("meeting")) {
                loadFragment(new MeetingFragment(),"meeting");
                Log.d(TAG, "onCreate: meeting aktif");
            } else {
                loadFragment(new SettingFragment(),"setting");
                Log.d(TAG, "onCreate: setting aktif");
            }
        }

/*
        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.frame, settingFragment, "2").hide(settingFragment).commit();
            fm.beginTransaction().add(R.id.frame, meetingFragment, "1").commit();
        } else {
            if (savedInstanceState.getString(APP_STATUS).equals("meeting")) {
                fm.beginTransaction().replace(R.id.frame, meetingFragment, "1").commit();
                fm.beginTransaction().add(R.id.frame, settingFragment, "2").hide(settingFragment).commit();
                active = meetingFragment;
                Log.d(TAG, "onCreate: meeting aktif");
            } else {
                fm.beginTransaction().replace(R.id.frame, settingFragment, "2").commit();
                fm.beginTransaction().add(R.id.frame, meetingFragment, "1").hide(meetingFragment).commit();
                active = settingFragment;
                Log.d(TAG, "onCreate: setting aktif");
            }


        }*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    String tag = "meeting";
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_nav_meeting:
                            fragment = new MeetingFragment();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(MeetingFragment.FIRST_LOAD, firstLoad);
                            fragment.setArguments(bundle);
                            tag = "meeting";
                            firstLoad = false;
                            break;
//                            fm.beginTransaction().hide(active).show(meetingFragment).commit();
                        //active = meetingFragment;
                        //return true;

                        case R.id.bottom_nav_settings:
                            fragment = new SettingFragment();
                            tag = "setting";
                            break;
                        //                          fm.beginTransaction().hide(active).show(settingFragment).commit();
                        //active = settingFragment;
                        //return true;

                    }
                    //active = fragment;
                    return loadFragment(fragment, tag);
                    //return false;
                }
            };

    private boolean loadFragment(Fragment fragment, String tag) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
            fActive = tag;
            return true;
        }
        return false;
    }

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FIRST_LOAD, String.valueOf(firstLoad));
        if (fActive.equals("meeting")) {
            outState.putString(APP_STATUS, "meeting");
            Log.d(TAG, "onSaveInstanceState: meeting aktif");
        } else {
            outState.putString(APP_STATUS, "setting");
            Log.d(TAG, "onSaveInstanceState: setting aktif");
        }
    }

}
