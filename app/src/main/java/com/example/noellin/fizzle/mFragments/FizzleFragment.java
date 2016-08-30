package com.example.noellin.fizzle.mFragments;

/**
 * Created by Joseph Widjaja on 8/27/16.
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
import android.view.Window;
import android.app.Dialog;

import com.example.noellin.fizzle.R;

import java.util.ArrayList;

public class FizzleFragment extends Fragment {

    private Spinner spinner;
    private Spinner spinner2;
    private Spinner spinner3;
    private static final String[] paths = {"select item","item 1", "item 2", "item 3", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test"};
    final ArrayList<String> selected = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fizzle_fragment,container,false);
        getActivity().setTitle("Fizzle");


        for(int i = 0; i < 3; i++) {
            selected.add("");
        }

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

        Button button =(Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v){
                final String text = spinner.getSelectedItem().toString();
                final String text2 = spinner2.getSelectedItem().toString();
                final String text3 = spinner3.getSelectedItem().toString();

                selected.set(0, text);
                selected.set(1, text2);
                selected.set(2, text3);

                Dialog settingsDialog = new Dialog(getActivity());
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(getActivity().getLayoutInflater().inflate(R.layout.fizzle_popup
                        , null));
                settingsDialog.show();




                Toast.makeText(getContext(), "Fizzle with " + selected.get(0) + " " + selected.get(1) + " " + selected.get(2) , Toast.LENGTH_SHORT).show();
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