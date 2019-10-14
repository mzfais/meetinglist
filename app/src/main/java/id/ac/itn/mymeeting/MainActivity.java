package id.ac.itn.mymeeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import id.ac.itn.mymeeting.adapter.MeetingPagedAdapter;
import id.ac.itn.mymeeting.model.MeetingModel;
import id.ac.itn.mymeeting.model.MeetingViewModel;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    MeetingViewModel meetingViewModel;
    SwipeRefreshLayout swipe;
    MeetingPagedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = findViewById(R.id.swipeLayout);
        swipe.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.rvMeetingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new MeetingPagedAdapter(this);
        load_data();
        recyclerView.setAdapter(adapter);
    }

    void load_data() {
        swipe.setRefreshing(true);
        meetingViewModel = ViewModelProviders.of(this).get(MeetingViewModel.class);
        meetingViewModel.meetingPagedList.observe(this, new Observer<PagedList<MeetingModel>>() {
            @Override
            public void onChanged(PagedList<MeetingModel> data) {
                adapter.submitList(data);
                Log.d(TAG, "onChanged: submit meetingmodels ke adapter");
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        if(isNetworkAvailable()) {
            meetingViewModel.Refresh();
        }
       /* if(meetingViewModel.meetingPagedList.getValue().size()<1){
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }*/
        swipe.setRefreshing(false);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //parameter is the name of service we want in either string format or referenced through the context CLASS as seen.

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //this also needs permission in the Android Manifest

        boolean isAvailable = false;
        if (networkInfo !=null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }
}
