package com.example.noellin.fizzle.mFragments;

/**
 * Created by noellin on 8/27/16.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.noellin.fizzle.R;
import com.example.noellin.fizzle.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Oclemmy on 5/10/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView profile;
    private TextView name;
    private TextView email;
    private TextView moodMessage;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
// ...

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView=inflater.inflate(R.layout.profile_fragment,container,false);

        String userName = getArguments().getString("DisplayName");
        final String userEmail = getArguments().getString("DisplayEmail");
        String imageUri = getArguments().getString("ImageURL");

        profile = (CircleImageView)rootView.findViewById(R.id.profile_image);
        name = (TextView)rootView.findViewById(R.id.username);
        email = (TextView)rootView.findViewById(R.id.email);
        moodMessage = (TextView)rootView.findViewById(R.id.moodmsg);
        getActivity().setTitle("Profile");
        Picasso.with(getActivity()).load(imageUri).into(profile);


        name.setText(userName);
        email.setText(userEmail + "@gmail.com");

        final DatabaseReference myRef = database.getReference("users");


        moodMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Mood Message");
                alert.setMessage("Before you are drunk, say something!");

                // Set an EditText view to get user input
                final EditText input = new EditText(getActivity());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(!(input.getText().toString().equals(""))){
                            myRef.child(userEmail).child("moodMsg").setValue(input.getText().toString());
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });



        myRef.child(userEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User storedUser = dataSnapshot.getValue(User.class);
                String currMoodMsg = storedUser.getMoodMsg();
                if(!currMoodMsg.equals("This person is already too drunk to say anything!")){
                    moodMessage.setText(currMoodMsg);
                }
                else{
                    moodMessage.setText("Enter your mood message here!");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });









        return rootView;
    }
}
