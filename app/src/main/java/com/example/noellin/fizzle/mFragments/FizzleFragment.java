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

    private static final String[] paths = { "select item", "Rum","Vodka","Kahlua","Gin", "Tequila", "Bourbon","Triple Sec"};
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
                TextView fizzleTitle = (TextView)settingsDialog.findViewById(R.id.drink_title);
                selectView(fizzledPicture, fizzleText, fizzleTitle);


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

        String a = "Manhattan";
        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("Vermouth");
        a1.add("Bourbon");

        hmap.put(a, a1);

        String b = "Long Island";
        ArrayList<String> b2 = new ArrayList<String>();
        b2.add("Vodka");
        b2.add("Gin");
        b2.add("Rum");
        b2.add("Tequila");
        b2.add("Triple Sec");

        hmap.put(b, b2);

        String c = "Berita";
        ArrayList<String> c3 = new ArrayList<String>();
        c3.add("Beer");
        c3.add("Tequila");

        hmap.put(c, c3);

        String d = "JD Coke";
        ArrayList<String> d4 = new ArrayList<String>();
        d4.add("Bourbon");
        d4.add("Jack Daniels");

        hmap.put(d, d4);

        String e = "Mojito";
        ArrayList<String> e5 = new ArrayList<String>();
        e5.add("Rum");
        e5.add("Jack Daniels");

        hmap.put(e, e5);




    }


    public void selectView(ImageView image, TextView view, TextView title){

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

        if(results.size()>0){
            int randomNum = rand.nextInt(results.size());
            finalResult = results.get(randomNum);
        }

        if(finalResult.equals("Manhattan")){
            title.setText("Manhattan");
            image.setImageResource(R.drawable.manhattan);
            view.setText("Ingredients :bitters, vermouth, and bourbon. Add cherry\n" +
                    "Put 2 dashes angostura bitters\n" +
                    "Put 1 ounce sweet vermouth\n" +
                    "Put 2 1/2 ounces bourbon, such as Buffalo Trace\n" +
                    "Put 1 maraschino cherry\n");
        }
        else if(finalResult.equals("Long Island")){
            title.setText("Long Island");
            image.setImageResource(R.drawable.long_island);
            view.setText("Dating back to the 70s, LIIT is one of the top 5 most popular cocktails in the world.\n" +
                    "1 part vodka\n" +
                    "1 part tequila\n" +
                    "1 part rum\n" +
                    "1 part gin\n" +
                    "1 part triple sec\n" +
                    "1 1/2 parts sweet and sour mix\n" +
                    "1 splash Coca-Cola®");
        }
        else if(finalResult.equals("Berita")){
            title.setText("Berita");
            image.setImageResource(R.drawable.baritta);
            view.setText("Refreshing drink from the border.\n" +
                    "Ingredients : four bottles or cans of beer," +
                    " 1 one cup of tequila, and " +
                    "one can of frozen limeade concentrate");
        }
        else if(finalResult.equals("JD Coke")){
            title.setText("JD Coke");
            image.setImageResource(R.drawable.jd_coke);
            view.setText("The first mention of the drink, in the report of Chemistry and Soils. \n" +
                    "Jack Daniels and Coke\n" +
                    "Recipe : \n" +
                    "Fill a fourth of your glass \n" +
                    "Fill the rest with coke\n"
            );
        }
        else if (finalResult.equals("Mojito")){
            title.setText("Mojito");
            image.setImageResource(R.drawable.mojito);
            view.setText("2 Parts Light Rum\n" +
                    "¾ Part Simple Syrup\n" +
                    "¾ Part Lime Juice\n" +
                    "8 Whole Mint Leaf\n" +
                    "Soda Water\n" +
                    "Muddle mint leaf and simple syrup in a highball glass. Fill with crushed ice. Add light rum and lime juice. Stir. Top up with soda water.");
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