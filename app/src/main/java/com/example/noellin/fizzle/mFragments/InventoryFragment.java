package com.example.noellin.fizzle.mFragments;

/**
 * Created by Joseph Widjaja on 8/27/16.
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
import android.widget.AdapterView.OnItemClickListener;
import android.view.MotionEvent;
import android.text.Editable;
import android.widget.AdapterView;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

import com.example.noellin.fizzle.R;

public class InventoryFragment extends Fragment {

    String[] alcohols = { "Rum","Vodka","Kahlua","Gin", "Tequila" };
    ArrayList<String> ingredients = new ArrayList<String>();
    private MyListAdapter mAdapter;
    String textString; //keep track!

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.inventory_fragment,container,false);

        getActivity().setTitle("My Collection");

        //Create Array Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_singlechoice, alcohols);
        //Find TextView control
        final AutoCompleteTextView acTextView = (AutoCompleteTextView)rootView.findViewById(R.id.alcohols);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(0);
        //Set the adapter
        acTextView.setAdapter(adapter);

        acTextView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                acTextView.showDropDown();
                return false;
            }
        });

        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textString = adapter.getItem(position).toString();
            }
        });

        /**
        * Unset the var whenever the user types. Validation will
        * then fail. This is how we enforce selecting from the list.
        */
        acTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textString = null;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        ListView lv = (ListView)rootView.findViewById(R.id.listView);
        generateListContent();
        mAdapter = new MyListAdapter(getActivity(), R.layout.inventory_row, ingredients);
        lv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        ImageView plusSign =(ImageView)rootView.findViewById(R.id.add_item_button);
        plusSign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if(textString == null){
                    Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                }
                else {
                    ingredients.add(textString);
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), textString + " Added", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    //load database from Firebase
    public void generateListContent(){
        for(int i=0; i<5; i++){
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
            ViewHolder viewHolder = null;

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.minus_item_button);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                        ingredients.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(getItem(position));
            viewHolder.thumbnail.setTag(getItem(position));

            return convertView;
        }
    }

    public class ViewHolder{
        ImageView thumbnail;
        TextView title;

    }


}