package com.example.noellin.fizzle.mFragments;

/**
 * Created by noellin on 8/27/16.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.noellin.fizzle.R;

public class FizzleFragment extends Fragment {

    private Spinner spinner;
    private Spinner spinner2;
    private Spinner spinner3;
    private static final String[]paths = {"select item","item 1", "item 2", "item 3"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fizzle_fragment,container,false);

        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        spinner2 = (Spinner)rootView.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner)rootView.findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        final String text = spinner.getSelectedItem().toString();
        final String text2 = spinner.getSelectedItem().toString();
        final String texgt3 = spinner.getSelectedItem().toString();
    
        Button button =(Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "Fizzle is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

}