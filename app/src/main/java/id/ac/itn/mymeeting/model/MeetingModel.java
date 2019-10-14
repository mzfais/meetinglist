package id.ac.itn.mymeeting.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MeetingModel implements Serializable {
    @SerializedName("IdMeeting")
    private String idMeeting;
    @SerializedName("JdlMeeting")
    private String jdlMeeting;
    @SerializedName("TgMeeting")
    private String tgMeeting;
    @SerializedName("TsStart")
    private String tsStart;
    @SerializedName("TsEnd")
    private String tsEnd;
    @SerializedName("TmpMeeting")
    private String tmpMeeting;
    @SerializedName("AcaraMeeting")
    private String AcaraMeeting;
    @SerializedName("RoleId")
    private String roleId;
    @SerializedName("UserEntri")
    private String userEntri;
    @SerializedName("IpEntri")
    private String ipEntri;
    @SerializedName("TsEntri")
    private String tsEntri;
    @SerializedName("lokasi")
    private String lokasi;

    public MeetingModel() {}

    public MeetingModel(String jdlMeeting, String tgMeeting, String tsStart, String tsEnd, String acaraMeeting, String lokasi) {
        this.jdlMeeting = jdlMeeting;
        this.tgMeeting = tgMeeting;
        this.tsStart = tsStart;
        this.tsEnd = tsEnd;
        AcaraMeeting = acaraMeeting;
        this.lokasi = lokasi;
    }

    public String getIdMeeting() {
        return idMeeting;
    }

    public String getJdlMeeting() {
        return jdlMeeting;
    }

    public String getTgMeeting() {
        return tgMeeting;
    }

    public String getTmpMeeting() {
        return tmpMeeting;
    }

    public String getTsStart() {
        return tsStart;
    }

    public String getTsEnd() {
        return tsEnd;
    }

    public String getAcaraMeeting() {
        return AcaraMeeting;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getUserEntri() {
        return userEntri;
    }

    public String getIpEntri() {
        return ipEntri;
    }

    public String getTsEntri() {
        return tsEntri;
    }

    public String getLokasi() {
        return lokasi;
    }
}
