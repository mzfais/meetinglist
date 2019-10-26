package id.ac.itn.mymeeting.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import id.ac.itn.mymeeting.model.MeetingModel;

public class MeetingDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MeetingModel>> meetingLiveDataSource = new MutableLiveData<>();

    String filter = "all";
    String idPeg = "";

    public MeetingDataSourceFactory(String filter, String idPeg) {
        this.filter = filter;
        this.idPeg = idPeg;
    }

    @Override
    public DataSource create() {
        MeetingDataSource meetingDataSource = new MeetingDataSource(this.filter,this.idPeg);
        meetingLiveDataSource.postValue(meetingDataSource);
        return meetingDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MeetingModel>> getMeetingLiveDataSource() {
        return meetingLiveDataSource;
    }
}
