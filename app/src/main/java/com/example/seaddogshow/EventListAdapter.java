package com.example.seaddogshow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventListAdapter extends ArrayAdapter {

    private final Activity mActivity;
    private List<Events> eventList;


    public EventListAdapter(Activity mActivity, List<Events> eventList) {

        super(mActivity, R.layout.event_list_item, eventList); // uses the event_list_item layout
        this.mActivity = mActivity;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.event_list_item, null, true);

        TextView event = listItemView.findViewById(R.id.listItemEventEvent);
        TextView day = listItemView.findViewById(R.id.listItemEventDay);

        Events events = eventList.get(position);

        event.setText(events.getEvent());
        day.setText(events.getDay());

        return listItemView;
    }
}