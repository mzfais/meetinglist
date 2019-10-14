package id.ac.itn.mymeeting.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import id.ac.itn.mymeeting.api.ApiClient;
import id.ac.itn.mymeeting.model.ListMeetingModel;
import id.ac.itn.mymeeting.model.MeetingModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingDataSource extends PageKeyedDataSource<Integer, MeetingModel> {

    private static final String TAG = "MeetingDataSource";
    public static final int PAGE_SIZE = 5;
    public static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MeetingModel> callback) {
        ApiClient.getInstance().getApi().getListMeeting("all", "79", FIRST_PAGE)
                .enqueue(new Callback<ListMeetingModel>() {
                    @Override
                    public void onResponse(Call<ListMeetingModel> call, Response<ListMeetingModel> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getMeeting(), null, FIRST_PAGE + 1);
                            Log.d(TAG, "onResponse: LoadInitial " + response.body().getMeeting().size() + " data");
                        }
                    }

                    @Override
                    public void onFailure(Call<ListMeetingModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: loadInitial " + t.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MeetingModel> callback) {
        ApiClient.getInstance().getApi()
                .getListMeeting("all", "79", params.key)
                .enqueue(new Callback<ListMeetingModel>() {
                    @Override
                    public void onResponse(Call<ListMeetingModel> call, Response<ListMeetingModel> response) {
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getMeeting(), key);
                            Log.d(TAG, "onResponse: loadBefore " + key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ListMeetingModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: LoadBefore " + t.getMessage());
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MeetingModel> callback) {
        ApiClient.getInstance().getApi()
                .getListMeeting("all", "79", params.key)
                .enqueue(new Callback<ListMeetingModel>() {
                    @Override
                    public void onResponse(Call<ListMeetingModel> call, Response<ListMeetingModel> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getMeeting(), params.key + 1);
                            Log.d(TAG, "onResponse: loadAfter " + params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ListMeetingModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: LoadAfter " + t.getMessage());
                    }
                });
    }
}
