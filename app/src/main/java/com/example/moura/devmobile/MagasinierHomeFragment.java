package com.example.moura.devmobile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagasinierHomeFragment extends Fragment {



    private CardView addCard;
    private CardView consultCard;
    private CardView facebookCard;
    private CardView logoutCard;
    private CardView messagesCard;
    private CardView profileCard ;



   public Button addProduct;
   public Button removeProduct;
   public Button consultProduct;
    public View view ;
    public MagasinierHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view= inflater.inflate(R.layout.fragment_magasinier_home2, container, false);

        addCard=view.findViewById(R.id.addProductCard1);
        consultCard=view.findViewById(R.id.consultProductsCard1);
        facebookCard=view.findViewById(R.id.facebookCard1);
        logoutCard=view.findViewById(R.id.logoutCard1);
        profileCard=view.findViewById(R.id.profileCard1);
        messagesCard=view.findViewById(R.id.messagesCard1);








        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductFragment addProductFragment=new AddProductFragment();
                setFragment(addProductFragment);
            }
        });





        consultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultProductFragment consultProductFragment =new ConsultProductFragment();
                setFragment(consultProductFragment);
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
                MagasinierNotificationFragment magasinierNotificationFragment=new MagasinierNotificationFragment();
                setFragment(magasinierNotificationFragment);
            }
        });



        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MagasinierAccountFragment magasinierAccountFragment=new MagasinierAccountFragment();
                setFragment(magasinierAccountFragment);
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
        ft.replace(R.id.frameLayoutMagasinier,fragment);
        ft.commit();


    }

}
