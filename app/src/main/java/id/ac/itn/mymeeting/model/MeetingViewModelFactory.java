package id.ac.itn.mymeeting.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MeetingViewModelFactory implements ViewModelProvider.Factory {
    private String time;
    private String idPeg;

    public MeetingViewModelFactory(String time, String idPeg) {
        this.time = time;
        this.idPeg = idPeg;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MeetingViewModel(this.time, this.idPeg);
    }
}
