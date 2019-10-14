package id.ac.itn.mymeeting.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.itn.mymeeting.R;
import id.ac.itn.mymeeting.model.MeetingModel;


public class MeetingPagedAdapter extends PagedListAdapter<MeetingModel, MeetingPagedAdapter.MeetingViewHolder> {
    private static final String TAG = "MhsPagedAdapter";
    private Context mCtx;

    public MeetingPagedAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx)
                .inflate(R.layout.item_list_meeting, parent, false);
        Log.d(TAG, "onCreateViewHolder: seharusnya tampak");
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        MeetingModel meetingModel = getItem(position);
        Log.d(TAG, "onBindViewHolder: tampilkan " + meetingModel.getJdlMeeting());
        if (meetingModel != null) {
            holder.tvjudul.setText(meetingModel.getJdlMeeting());
            holder.tvagenda.setText(meetingModel.getAcaraMeeting());
            holder.tvwaktu.setText(meetingModel.getTgMeeting());
        } else {
            Toast.makeText(mCtx, "MeetingModel MeetingModel tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<MeetingModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<MeetingModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MeetingModel oldItem, @NonNull MeetingModel newItem) {
            return oldItem.getIdMeeting() == newItem.getIdMeeting();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MeetingModel oldItem, @NonNull MeetingModel newItem) {
            return oldItem == newItem;
        }
    };

    public void clear() {
        if (getItemCount() > 0) {
            getCurrentList().clear();
            notifyDataSetChanged();
        }
    }

    class MeetingViewHolder extends RecyclerView.ViewHolder {
        TextView tvjudul, tvagenda, tvwaktu;

        MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvjudul = itemView.findViewById(R.id.tv_item_judul);
            tvagenda = itemView.findViewById(R.id.tv_item_agenda);
            tvwaktu = itemView.findViewById(R.id.tv_item_waktu);
        }
    }
}
