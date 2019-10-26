package id.ac.itn.mymeeting.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import id.ac.itn.mymeeting.datasource.MeetingDataSource;
import id.ac.itn.mymeeting.datasource.MeetingDataSourceFactory;

public class MeetingViewModel extends ViewModel {
    public LiveData<PagedList<MeetingModel>> meetingPagedList;
    public LiveData<PageKeyedDataSource<Integer, MeetingModel>> livedataSource;
    private static final String TAG = "MeetingViewModel";
    private String filter, idPeg;

    public MeetingViewModel(String filter, String idPeg) {
        this.filter = filter;
        this.idPeg = idPeg;

        MeetingDataSourceFactory meetingDataSourceFactory = new MeetingDataSourceFactory("all", "79");
        livedataSource = meetingDataSourceFactory.getMeetingLiveDataSource();

        PagedList.Config config =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(MeetingDataSource.PAGE_SIZE)
                        .build();
        meetingPagedList = new LivePagedListBuilder(meetingDataSourceFactory, config).build();
        Log.d(TAG, "MeetingViewModel: time: " + this.filter + ", idpeg: " + this.idPeg);

    }

    public void Refresh() {
        if (livedataSource.getValue() != null) {
            livedataSource.getValue().invalidate();
        }
    }
}
