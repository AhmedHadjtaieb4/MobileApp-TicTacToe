package com.example.moura.devmobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AdminHomeFragment extends Fragment {
public View view ;
public Button add;
public Button remove;
private Button consult;
private CardView addCard;
private CardView consultCard;
private CardView facebookCard;
private CardView logoutCard;
private CardView messagesCard;
private CardView profileCard ;
    public AdminHomeFragment ()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_admin_home, container, false);

           addCard=view.findViewById(R.id.addUserCard);
        consultCard=view.findViewById(R.id.consultUsersCard);
        facebookCard=view.findViewById(R.id.facebookCard);
        logoutCard=view.findViewById(R.id.logoutCard);
        profileCard=view.findViewById(R.id.profileCard);
        messagesCard=view.findViewById(R.id.messagesCard);


        addCard.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AddUser addUserFragment=new AddUser();
                   setFragment(addUserFragment);
               }
           });





        consultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultUsersFragment consultUsersFragment=new ConsultUsersFragment();
                setFragment(consultUsersFragment);
            }
        });




        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LogInModeSelection.class));
            }
        });


        messagesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminNotificationFragment adminNotificationFragment=new AdminNotificationFragment();
                setFragment(adminNotificationFragment);
            }
        });



        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminAccountFragment adminAccountFragment=new AdminAccountFragment();
                setFragment(adminAccountFragment);
            }
        });
         facebookCard.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
                 Intent viewIntent =
                         new Intent("android.intent.action.VIEW",
                                 Uri.parse("http://www.facebook.com/"));
                 startActivity(viewIntent);
             }
         });








        return view;
    }
    public void setFragment(Fragment fragment)
    {

        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutAdmin,fragment);
        ft.commit();


    }
}
