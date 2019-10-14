package id.ac.itn.mymeeting.model;

import android.content.Context;

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

    public MeetingViewModel() {
        MeetingDataSourceFactory meetingDataSourceFactory = new MeetingDataSourceFactory();
        livedataSource = meetingDataSourceFactory.getMeetingLiveDataSource();

        PagedList.Config config =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(MeetingDataSource.PAGE_SIZE)
                        .build();
        meetingPagedList = new LivePagedListBuilder(meetingDataSourceFactory, config).build();

    }

    public void Refresh() {
        if (livedataSource.getValue() != null) {
            livedataSource.getValue().invalidate();
        }
    }
}
