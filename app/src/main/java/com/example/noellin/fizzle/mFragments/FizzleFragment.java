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
import android.widget.ImageView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.Window;
import android.app.Dialog;
import java.io.File;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.noellin.fizzle.R;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

public class FizzleFragment extends Fragment {

    private Spinner spinner;
    private Spinner spinner2;
    private Spinner spinner3;

    private static final String[] paths = { "select item", "Rum","Vodka","Kahlua","Gin", "Tequila" };
    final ArrayList<String> selected = new ArrayList<String>();

    private HashMap<String, Boolean> fizzleResults = new HashMap<String, Boolean>();
    ArrayList<String> selection = new ArrayList<String>();

    private HashMap<String, ArrayList<String>> fizzleDatabase = new HashMap<String, ArrayList<String>>();
    ArrayList<String> results = new ArrayList<String>();
    String finalResult = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fizzle_fragment,container,false);
        //final View dView=inflater.inflate(R.layout.fizzle_popup,container,false);
        getActivity().setTitle("Fizzle");

        for(int i = 0; i < 3; i++) {
            selected.add("");
        }

        loadDatabase(fizzleDatabase);


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
                for(int i = 0; i < paths.length; i++){
                    fizzleResults.put(paths[i], false);
                }

                final String text = spinner.getSelectedItem().toString();
                final String text2 = spinner2.getSelectedItem().toString();
                final String text3 = spinner3.getSelectedItem().toString();

                selected.set(0, text);
                selected.set(1, text2);
                selected.set(2, text3);

                fizzleResults.put(selected.get(0), true);
                fizzleResults.put(selected.get(1), true);
                fizzleResults.put(selected.get(2), true);

                Set set = fizzleResults.entrySet();

                Iterator i = set.iterator();
                while(i.hasNext()){
                    Map.Entry me = (Map.Entry)i.next();
                    if(!me.getKey().toString().equals("select item")){
                        if ((Boolean) me.getValue())
                            selection.add(me.getKey().toString());
                        //selection = me.getKey().toString();
                    }
                }

                final Dialog settingsDialog = new Dialog(getActivity());
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(getActivity().getLayoutInflater().inflate(R.layout.fizzle_popup, null));

                ImageView fizzledPicture = (ImageView)settingsDialog.findViewById(R.id.fizzleImage);
                TextView fizzleText = (TextView)settingsDialog.findViewById(R.id.drink_description);
                selectView(fizzledPicture, fizzleText);


                settingsDialog.show();

                Button dialogButton = (Button) settingsDialog.findViewById(R.id.dButton);
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        settingsDialog.dismiss();
                    }
                });

                Toast.makeText(getContext(), results  + "Fizzle with " + selected.get(0) + " " + selected.get(1) + " " + selected.get(2) , Toast.LENGTH_SHORT).show();

                results.clear();
                selection.clear();
            }

        });

        return rootView;
    }

    public void loadDatabase(HashMap<String, ArrayList<String>> hmap){

        String a = "Test";
        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("Vodka");
        a1.add("Gin");

        hmap.put(a, a1);

        String b = "Test2";
        ArrayList<String> b2 = new ArrayList<String>();
        b2.add("Vodka");
        b2.add("Gin");

        hmap.put(b, b2);

        String c = "Test3";
        ArrayList<String> c3 = new ArrayList<String>();
        c3.add("Vodka");
        c3.add("Gin");

        hmap.put(c, c3);

        String d = "Test4";
        ArrayList<String> d4 = new ArrayList<String>();
        d4.add("Vodka");
        d4.add("Gin");

        hmap.put(d, d4);




    }


    public void selectView(ImageView image, TextView view){

        Set set = fizzleDatabase.entrySet();

        Random rand = new Random();

        Iterator i = set.iterator();
        while(i.hasNext()){
            Map.Entry me = (Map.Entry)i.next();
            ArrayList<String> ing = (ArrayList<String>)me.getValue();
            for(int j =0; j<selected.size(); j++){
                if(ing.contains(selected.get(j))){
                    results.add(me.getKey().toString());
                }
            }
        }

        int randomNum = rand.nextInt(fizzleDatabase.size());

        if(results.size()>0){
          finalResult = results.get(randomNum);
        }

        if(finalResult.equals("Test")){
            image.setImageResource(R.drawable.cats);
            view.setText("Some drink that contains Gin and Vodka");
        }
        else if(finalResult.equals("Test2")){
            image.setImageResource(R.drawable.heart);
            view.setText("Some drink that contains Gin and Vodka");
        }
        else if(finalResult.equals("Test3")){
            image.setImageResource(R.drawable.camera);
            view.setText("Some drink that contains Gin and Vodka");
        }
        else if(finalResult.equals("Test4")){
            image.setImageResource(R.drawable.fizzle);
            view.setText("Some drink that contains Gin and Vodka");
        }


        //number randomize selection

            /*
            if (selection.contains("Gin") && selection.contains("Vodka")) {
                image.setImageResource(R.drawable.cats);
                view.setText("Some drink that contains Gin and Vodka");
            }
            else if (selection.contains("Vodka")) {
                image.setImageResource(R.drawable.heart);
                view.setText("Some drink that contains Vodka");
            }
            else if (selection.contains("Rum")) {
                image.setImageResource(R.drawable.camera);
                view.setText("Some drink that contains Rum");
            }*/

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