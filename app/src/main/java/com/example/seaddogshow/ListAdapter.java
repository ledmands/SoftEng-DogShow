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
    private List<Trainer> trainerList;



    public ListAdapter(Activity mActivity, List<Trainer> trainerList){

        super(mActivity, R.layout.trainer_list_item,trainerList); // uses the list_item layout
        this.mActivity = mActivity;
        this.trainerList = trainerList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.trainer_list_item, null, true);

        TextView trainerName = listItemView.findViewById(R.id.listItemTrainerName);
        TextView trainerCountry = listItemView.findViewById(R.id.listItemTrainerCountry);
        TextView trainerClub = listItemView.findViewById(R.id.listItemTrainerClub);

        Trainer trainer = trainerList.get(position);

        trainerName.setText(trainer.getName());
        trainerCountry.setText(trainer.getCountry());
        trainerClub.setText(trainer.getClub());

        return listItemView;
    }

}
