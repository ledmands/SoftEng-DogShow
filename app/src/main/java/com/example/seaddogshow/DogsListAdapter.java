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

public class DogsListAdapter extends ArrayAdapter {
    private final Activity mActivity;
    List<Dogs> dogsList;



    public DogsListAdapter(Activity mActivity, List<Dogs> dogsList){

        super(mActivity, R.layout.dog_list_item,dogsList); // uses the list_item layout
        this.mActivity = mActivity;
        this.dogsList = dogsList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.dog_list_item, null, true);

        TextView dogName = listItemView.findViewById(R.id.dogListItemName);
        TextView dogBreed = listItemView.findViewById(R.id.dogListItemBreed);
        TextView dogFavoriteToy = listItemView.findViewById(R.id.dogListItemToy);
        TextView dogEvents = listItemView.findViewById(R.id.dogListItemEvents);

        Dogs dogs = dogsList.get(position);

        dogName.setText(dogs.getName());
        dogBreed.setText(dogs.getBreed());
        dogFavoriteToy.setText(dogs.getFavorite_Toy());
        dogEvents.setText(dogs.getEvents());

        return listItemView;
    }
}
