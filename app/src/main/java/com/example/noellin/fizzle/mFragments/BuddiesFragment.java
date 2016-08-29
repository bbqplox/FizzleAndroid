package com.example.noellin.fizzle.mFragments;

/**
 * Created by noellin on 8/27/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.example.noellin.fizzle.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuddiesFragment extends ListFragment {


    // Array of strings storing country names
    String[] countries = new String[] {
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text"
    };

    // Array of integers points to images stored in /res/drawable/
    int[] flags = new int[]{
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar,
            R.drawable.profilenavbar

    };

    // Array of strings to store currencies
    String[] currency = new String[]{
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text",
            "Placeholder text"
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.buddies_fragment,container,false);
        getActivity().setTitle("Buddies");

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "Drink Name : " + countries[i]);
            hm.put("cur","Description : " + currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_layout, from, to);

        setListAdapter(adapter);

        return rootView;
    }
}
