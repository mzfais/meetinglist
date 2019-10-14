package id.ac.itn.mymeeting.api;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallback<T> implements Callback<T> {
    @SuppressWarnings("unused")
    private static final String TAG = "RetrofitCallback";
    private Context mContext;
    private final Callback<T> mCallback;

    public RetrofitCallback(Context context, Callback<T> callback) {
        mContext = context;
        this.mCallback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
// Do application relavent custom operation like manupulating reponse etc.
        //AlertDialogHelper.dismiss(mContext);
        mCallback.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
// Handle error etc.
        //NetworkHelper.onFailure(t, (Activity) mContext);
        mCallback.onFailure(call, t);
    }
}
