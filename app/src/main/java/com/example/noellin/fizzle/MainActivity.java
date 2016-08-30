package com.example.noellin.fizzle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.noellin.fizzle.mFragments.BrowseFragment;
import com.example.noellin.fizzle.mFragments.BuddiesFragment;
import com.example.noellin.fizzle.mFragments.FizzleFragment;
import com.example.noellin.fizzle.mFragments.InventoryFragment;
import com.example.noellin.fizzle.mFragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;
    private CircleImageView profile;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String userEmail;
    private String backendUID;
    private String userName;
    private DatabaseReference mDatabase;
    private String imageUri;
    private String moodMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get content from login page
        userName = getIntent().getStringExtra("DisplayName");
        userEmail = getIntent().getStringExtra("DisplayEmail");
        backendUID = getIntent().getStringExtra("BackendUID");
        imageUri = getIntent().getStringExtra("ImageURL");
        moodMsg = getIntent().getStringExtra("MoodMsg");


        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_center, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut();
            finishActivity(1);
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem fizzleItem=new AHBottomNavigationItem("Fizzle",R.drawable.fizzlenavbar);
        AHBottomNavigationItem browseItem=new AHBottomNavigationItem("Browse",R.drawable.browsenavbar);
        AHBottomNavigationItem inventoryItem=new AHBottomNavigationItem("Inventory",R.drawable.inventorynavbar);
        AHBottomNavigationItem buddiesItem=new AHBottomNavigationItem("Buddies",R.drawable.findbuddiesnavbar);
        AHBottomNavigationItem profileItem=new AHBottomNavigationItem("Profile",R.drawable.profilenavbar);


        //ADD THEM to bar
        bottomNavigation.addItem(fizzleItem);
        bottomNavigation.addItem(browseItem);
        bottomNavigation.addItem(inventoryItem);
        bottomNavigation.addItem(buddiesItem);
        bottomNavigation.addItem(profileItem);


        //set properties
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#000000"));

        //set current item
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position==0)
        {
            FizzleFragment fizzleFragment=new FizzleFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,fizzleFragment).commit();
        }else  if (position==1)
        {
            BrowseFragment browseFragment=new BrowseFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,browseFragment).commit();
        }else  if (position==2)
        {
            InventoryFragment inventoryFragment=new InventoryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,inventoryFragment).commit();
        }
        else  if (position==3)
        {
            BuddiesFragment buddiesFragment=new BuddiesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,buddiesFragment).commit();
        }
        else  if (position==4)
        {
            Bundle bundle = new Bundle();
            bundle.putString("DisplayEmail", userEmail);
            bundle.putString("ImageURL", imageUri);
            bundle.putString("DisplayName", userName);
            bundle.putString("MoodMsg", moodMsg);
            ProfileFragment profileFragment=new ProfileFragment();
            profileFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,profileFragment).commit();
        }
    }
}
