package com.nestnfly.event.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestnfly.event.R;
import com.nestnfly.event.adapter.ScheduleAdapter;
import com.nestnfly.event.model.Schedule;
import com.nestnfly.event.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class ScheduleFragment extends BaseFragment {

    private RecyclerView rvSchedule;

    public ScheduleFragment(BaseActivity activity) {
        setBaseActivity(activity);
    }

    public static BaseFragment getFragment(BaseActivity activity) {
        BaseFragment fragment = new ScheduleFragment(activity);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);

        rvSchedule = (RecyclerView) view.findViewById(R.id.rvSchedule);
        LinearLayoutManager llManager = new LinearLayoutManager(getBaseActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvSchedule.setLayoutManager(llManager);
        List<Schedule> list = new ArrayList<>();
        list.add(new Schedule("9:30 - 10:00 AM","Registration" ,"Speaker - Steven Agee","Lobby"));
        list.add(new Schedule("10:00 - 10:30 AM","Introduction" ,"Speaker - Nabil Mensah","Main Area"));
        list.add(new Schedule("10:30 - 11:00 AM","Breackout Session" ,"Speaker - Aures Tohana","Main Area"));
        list.add(new Schedule("11:00 - 11:10 AM","Break" ,"Speaker - Marlon Stephen","Zone 1"));
        list.add(new Schedule("11:10 - 11:55 AM","Zone Summary" ,"Speaker - Fabian Stephen","Main Area"));
        list.add(new Schedule("11:55 - 12:00 AM","Closure End" ,"Speaker - Nabil Mensah","Main Area"));
        rvSchedule.setAdapter(new ScheduleAdapter(getBaseActivity(), list));
        return view;
    }
}
