package id.ac.itn.mymeeting.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.itn.mymeeting.MainActivity;
import id.ac.itn.mymeeting.R;
import id.ac.itn.mymeeting.adapter.MeetingPagedAdapter;
import id.ac.itn.mymeeting.model.MeetingModel;
import id.ac.itn.mymeeting.model.MeetingViewModel;
import id.ac.itn.mymeeting.model.MeetingViewModelFactory;

public class MeetingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MeetingFragment";
    private static final String DATA_LIST = "null";
    public static final String FIRST_LOAD = "false";

    private RecyclerView recyclerView;
    MeetingViewModel meetingViewModel;
    SwipeRefreshLayout swipe;
    MeetingPagedAdapter adapter;

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meeting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe = view.findViewById(R.id.swipeLayout);
        swipe.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.rvMeetingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new MeetingPagedAdapter(getActivity());
        Boolean firstLoad;
        try {
            firstLoad = getArguments().getBoolean(FIRST_LOAD);
        } catch (Exception ex) {
            firstLoad = false;
        }
        load_data(firstLoad);
        recyclerView.setAdapter(adapter);
        //}
        Log.d(TAG, "onViewCreated: setAdapter");
    }

    private void load_data(Boolean firstLoad) {
        if (firstLoad) {
            swipe.setRefreshing(true);
            Log.d(TAG, "load_data: firstload: true");
        }
        meetingViewModel = ViewModelProviders.of(getActivity(),new MeetingViewModelFactory("all","79")).get(MeetingViewModel.class);
        meetingViewModel.meetingPagedList.observe(getActivity(), new Observer<PagedList<MeetingModel>>() {
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
        meetingViewModel.Refresh();
       /* if(meetingViewModel.meetingPagedList.getValue().size()<1){
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }*/
        swipe.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATA_LIST, "available");
    }
}
