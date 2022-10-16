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

    private final Activity mActivity;
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

        TextView trainerName = listItemView.findViewById(R.id.listItemTrainerName);
        TextView trainerCity = listItemView.findViewById(R.id.listItemTrainerCity);
        TextView trainerClub = listItemView.findViewById(R.id.listItemTrainerClub);
        //TextView trainerExp = listItemView.findViewById(R.id.listItemTrainerExp);

        Trainer trainer = trainerList.get(position);

        trainerName.setText(trainer.getName());
        trainerCity.setText(trainer.getCity());
        trainerClub.setText(trainer.getClub());
        //trainerExp.setText(trainer.getYearsExperience());
        //trainerExp.setText(trainer.getYearsExp());

        return listItemView;
    }

}
