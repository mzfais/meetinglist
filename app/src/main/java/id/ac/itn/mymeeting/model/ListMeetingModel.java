package id.ac.itn.mymeeting.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//Model untuk menampilkan list meeting
public class ListMeetingModel {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("totalpage")
    private Integer totalpage;
    @SerializedName("EntriAccess")
    private String EntriAccess;
    @SerializedName("lastpage")
    private Boolean lastpage;
    @SerializedName("data")
    private List<MeetingModel> meeting;
    @SerializedName("singledata")
    private MeetingModel singledata;

    public ListMeetingModel(Boolean status, ArrayList<MeetingModel> meeting) {
        this.status = status;
        this.meeting = meeting;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getEntriAccess() {
        return EntriAccess;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public Boolean getLastpage() {
        return lastpage;
    }

    public List<MeetingModel> getMeeting() {
        return meeting;
    }

    public MeetingModel getSingledata() {
        return singledata;
    }

}
