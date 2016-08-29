package com.example.noellin.fizzle.mFragments;

/**
 * Created by noellin on 8/27/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.noellin.fizzle.R;

public class InventoryFragment extends Fragment {

    String[] alcohols = { "Rum","Vodaka","Kalua","Gin" };
    ArrayList<String> ingredients = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.inventory_fragment,container,false);

        //super.onCreate(savedInstanceState);

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_singlechoice, alcohols);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView)rootView.findViewById(R.id.alcohols);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

        ListView lv = (ListView)rootView.findViewById(R.id.listView);
        generateListContent();
        lv.setAdapter(new MyListAdapter(getActivity(), R.layout.inventory_fragment, ingredients));

        return rootView;
    }

    public void generateListContent(){
        for(int i=0; i<55; i++){
            ingredients.add("This is row number " + i);
        }
    }

    private class MyListAdapter extends ArrayAdapter<String>{
        private int layout;
        private MyListAdapter(Context context, int resource, List<String> objects){
            super(context, resource, objects);
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            ViewHolder mainViewholder = null;

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.minus_item_button);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "minus button clicked for item " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                convertView.setTag(viewHolder);
            }
            else{
                mainViewholder = (ViewHolder) convertView.getTag();
                mainViewholder.title.setText(getItem(position));
            }

            return convertView;
        }
    }

    public class ViewHolder{
        ImageView thumbnail;
        TextView title;

    }


}