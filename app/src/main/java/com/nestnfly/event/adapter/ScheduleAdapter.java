package com.nestnfly.event.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestnfly.event.R;
import com.nestnfly.event.custom.TfTextView;
import com.nestnfly.event.model.Schedule;
import com.nestnfly.event.ui.BaseActivity;

import java.util.List;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleVH>{
    private List<Schedule> list;
    private BaseActivity baseActivity;

    public ScheduleAdapter(BaseActivity baseActivity, List<Schedule> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ScheduleVH(LayoutInflater.from(baseActivity).inflate(R.layout.item_schedule, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleVH scheduleVH, int position) {
        scheduleVH.txtEvent.setText(list.get(position).getEvent());
        scheduleVH.txtSchedule.setText(list.get(position).getSchedule());
        scheduleVH.txtTime.setText(list.get(position).getTime());
        scheduleVH.txtLocation.setText(list.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleVH extends RecyclerView.ViewHolder {
        private TfTextView txtTime;
        private TfTextView txtSchedule;
        private TfTextView txtEvent;
        private TfTextView txtLocation;

        public ScheduleVH(@NonNull View itemView) {
            super(itemView);
            txtLocation = (TfTextView) itemView.findViewById(R.id.txtLocation);
            txtEvent = (TfTextView) itemView.findViewById(R.id.txtEvent);
            txtSchedule = (TfTextView) itemView.findViewById(R.id.txtSchedule);
            txtTime = (TfTextView) itemView.findViewById(R.id.txtTime);
        }
    }
}
