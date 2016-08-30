package com.example.noellin.fizzle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends FragmentActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.

    //backend object
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    SignInButton signInButton;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //start activity results
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_SIGN_OUT = 9002;

    private static final String TAG = "SignInActivity";

    //UI to make the app prettier
    private ImageView imageView;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = (ImageView) findViewById(R.id.yinyang);
        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        //Yin-yang ball animation
        imageView.startAnimation(animation);

        //start the animation and make log in button visible
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signInButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //make the yinyang sign to fade in and fade out
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, .3f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", .3f, 1f);
        fadeIn.setDuration(2000);
        fadeOut.setDuration(2000);
        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn).after(fadeOut);
        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });

        //start the animation
        mAnimationSet.start();



        // / Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        signInButton = (SignInButton) findViewById(R.id.google_signin);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1064593067645-ehe8rnmi5tjdhduunsqp61b4jr3jlgkc.apps.googleusercontent.com")
                .requestEmail()//.requestIdToken("1021736687932-f73c4tc0pdcdhlrt16b3h3pj7qi0aq1b.apps.googleusercontent.com")
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();



        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        signInButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            /*case R.id.register:
                startActivity(new Intent(LoginPage.this, CreateAccount.class));
                break;*/
            case R.id.google_signin:
                signIn();
                break;
        }

    }
    // sign in method
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        imageView.clearAnimation();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }

            //handleSignInResult(result);
        }

        if(requestCode == RC_SIGN_OUT){
            imageView.startAnimation(animation);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        // ...
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final DatabaseReference myRef = database.getReference("users");
                            // Name, email address, and profile photo Url
                            final String name = user.getDisplayName();
                            final String email = user.getEmail();
                            final String firebaseEmail = email.split("@")[0];
                            final Uri photoUrl = user.getPhotoUrl();
                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use
                            // FirebaseUser.getToken() instead.
                            final String uid = user.getUid();
                            final String partnerEmail = "";
                            final String partnerName = "";


                            myRef.child(firebaseEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);//----<<//
                                    String defaultMsg = "This person is already too drunk to say anything!";
                                    if (!dataSnapshot.exists()) {
                                        User nwUser = new User(firebaseEmail, partnerEmail, photoUrl.toString(), uid, name, partnerName, defaultMsg);
                                        myRef.child(firebaseEmail).setValue(nwUser);
                                        i.putExtra("DisplayName", name);
                                        i.putExtra("DisplayEmail", firebaseEmail);
                                        i.putExtra("ImageURL", photoUrl.toString());
                                        i.putExtra("BackendUID", uid);
                                        i.putExtra("PartnerEmail", partnerEmail);
                                        i.putExtra("PartnerName", partnerName);
                                        i.putExtra("MoodMsg",defaultMsg);
                                        Toast.makeText(getBaseContext(), "Welcome to Fizzle! " + name, Toast.LENGTH_SHORT).show();
                                        Log.w(TAG, "Data did not already exist!");
                                        startActivityForResult(i, RC_SIGN_OUT);

                                    }
                                    else {
                                        User storedUser = dataSnapshot.getValue(User.class);
                                        i.putExtra("DisplayName", storedUser.getUserName());
                                        i.putExtra("DisplayEmail", storedUser.getEmail());
                                        i.putExtra("ImageURL", storedUser.getPhotoUri());
                                        i.putExtra("BackendUID", storedUser.getUid());
                                        i.putExtra("PartnerEmail", storedUser.getPartnerEmail());
                                        i.putExtra("PartnerName", storedUser.getPartnerName());
                                        i.putExtra("MoodMsg", storedUser.getMoodMsg());
                                        Toast.makeText(getBaseContext(), "Welcome back to Fizzle! " + storedUser.getUserName() , Toast.LENGTH_SHORT).show();
                                        Log.w(TAG, "Data already exists! and "+ storedUser.getUserName());
                                        startActivityForResult(i, RC_SIGN_OUT);

                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    System.out.println("The read failed: " + databaseError.getMessage());
                                }
                            });


                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();

    }
}
