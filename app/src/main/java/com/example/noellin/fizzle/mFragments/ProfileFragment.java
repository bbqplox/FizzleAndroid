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
import android.widget.TextView;

import com.example.noellin.fizzle.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Oclemmy on 5/10/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView profile;
    private TextView name;
    private TextView email;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView=inflater.inflate(R.layout.profile_fragment,container,false);
        profile = (CircleImageView)rootView.findViewById(R.id.profile_image);
        name = (TextView)rootView.findViewById(R.id.username);
        email = (TextView)rootView.findViewById(R.id.email);





        String userName = getArguments().getString("DisplayName");
        String userEmail = getArguments().getString("DisplayEmail");
        String imageUri = getArguments().getString("ImageURL");

        name.setText(userName);
        email.setText(userEmail + "@gmail.com");

        getActivity().setTitle("Profile");

        Picasso.with(getActivity()).load(imageUri).into(profile);

        return rootView;
    }
}
