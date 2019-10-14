package id.ac.itn.mymeeting.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import id.ac.itn.mymeeting.model.MeetingModel;

public class MeetingDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MeetingModel>> meetingLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        MeetingDataSource meetingDataSource = new MeetingDataSource();
        meetingLiveDataSource.postValue(meetingDataSource);
        return meetingDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MeetingModel>> getMeetingLiveDataSource() {
        return meetingLiveDataSource;
    }
}
