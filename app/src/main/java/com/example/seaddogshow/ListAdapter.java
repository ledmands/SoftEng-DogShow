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

public class ListAdapter extends ArrayAdapter {

    private Activity mActivity;
    List<Trainer> trainerList;


    public ListAdapter(Activity mActivity, List<Trainer> trainerList){

        super(mActivity, R.layout.list_item,trainerList); // uses the list_item layout
        this.mActivity = mActivity;
        this.trainerList = trainerList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);

        TextView lvName = listItemView.findViewById(R.id.lvName);
        TextView lvTown = listItemView.findViewById(R.id.lvTown);
        TextView lvOrg = listItemView.findViewById(R.id.lvOrg);
        TextView lvAbout = listItemView.findViewById(R.id.lvAbout);

        Trainer trainer = trainerList.get(position);

        lvName.setText(trainer.getName());
        lvTown.setText(trainer.getTown());
        lvOrg.setText(trainer.getOrganization());
        lvAbout.setText(trainer.getAbout());

        return listItemView;
    }

}
