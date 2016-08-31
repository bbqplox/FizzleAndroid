package com.example.noellin.fizzle.mFragments;

/**
 * Created by noellin on 8/27/16.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import android.app.Dialog;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.noellin.fizzle.R;

import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.browse_fragment,container,false);


        getActivity().setTitle("Browse Drink");


        SwipeMenuListView listView = (SwipeMenuListView) rootView.findViewById(R.id.swipelist);
        final List<String> list = new ArrayList<>();
        list.add("Manhattan");
        list.add("Long Island");
        list.add("JD Coke");
        list.add("Mojito");
        list.add("Beritta");
        final CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), R.layout.swipelistrow, list);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                //create an action that will be showed on swiping an item in the list
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity());
                item1.setBackground(new ColorDrawable(Color.DKGRAY));
                // set width of an option (px)
                item1.setWidth(200);
                item1.setTitle("View Drink");
                item1.setTitleSize(18);
                item1.setTitleColor(Color.WHITE);
                menu.addMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity());
                // set item background
                item2.setBackground(new ColorDrawable(Color.RED));
                item2.setWidth(200);
                item2.setTitle("Action 2");
                item2.setTitleSize(18);
                item2.setTitleColor(Color.WHITE);
                menu.addMenuItem(item2);
            }
        };
        //set MenuCreator
        listView.setMenuCreator(creator);
        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String value = adapter.getItem(position);
                switch (index) {
                    case 0:

                        final Dialog settingsDialog = new Dialog(getActivity());
                        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        settingsDialog.setContentView(getActivity().getLayoutInflater().inflate(R.layout.fizzle_popup, null));
                        settingsDialog.show();

                        Button dialogButton = (Button) settingsDialog.findViewById(R.id.dButton);
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                settingsDialog.dismiss();
                            }
                        });

                        ImageView image = (ImageView)settingsDialog.findViewById(R.id.fizzleImage);
                        TextView title = (TextView)settingsDialog.findViewById(R.id.drink_title);
                        TextView view = (TextView)settingsDialog.findViewById(R.id.drink_description);

                        if(position == 0){
                            title.setText("Manhattan");
                            image.setImageResource(R.drawable.manhattan);
                            view.setText("Ingredients :bitters, vermouth, and bourbon. Add cherry\n" +
                                    "Put 2 dashes angostura bitters\n" +
                                    "Put 1 ounce sweet vermouth\n" +
                                    "Put 2 1/2 ounces bourbon, such as Buffalo Trace\n" +
                                    "Put 1 maraschino cherry\n");

                        }

                        if(position == 1){
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

                        if(position == 2){
                            title.setText("JD Coke");
                            image.setImageResource(R.drawable.jd_coke);
                            view.setText("The first mention of the drink, in the report of Chemistry and Soils. \n" +
                                    "Jack Daniels and Coke\n" +
                                    "Recipe : \n" +
                                    "Fill a fourth of your glass \n");

                        }

                        if(position == 3){
                            title.setText("Mojito");
                            image.setImageResource(R.drawable.mojito);
                            view.setText("2 Parts Light Rum\n" +
                                    "¾ Part Simple Syrup\n" +
                                    "¾ Part Lime Juice\n" +
                                    "8 Whole Mint Leaf\n" +
                                    "Soda Water\n" +
                                    "Muddle mint leaf and simple syrup in a highball glass. Fill with crushed ice. Add light rum and lime juice. Stir. Top up with soda water.");


                        }

                        if(position == 4){
                            title.setText("Berita");
                            image.setImageResource(R.drawable.baritta);
                            view.setText("Refreshing drink from the border.\n" +
                                    "Ingredients : four bottles or cans of beer," +
                                    " 1 one cup of tequila, and " +
                                    "one can of frozen limeade concentrate");

                        }


                        Toast.makeText(getActivity(), "Viewing Drink" , Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Action 2 for "+ value , Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;}});







        return rootView;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}