package id.ac.itn.mymeeting.api;

import id.ac.itn.mymeeting.model.ListMeetingModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("meeting")
    Call<ListMeetingModel> getListMeeting(@Query("time") String time, @Query("idpeg") String idpeg, @Query("page") int page);
}
