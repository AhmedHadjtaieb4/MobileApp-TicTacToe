package com.example.moura.devmobile;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class VendeurHomeFragment extends Fragment {

private Button consult ;
private View view ;
private Button start;



    private CardView startCard;
    private CardView consultCard;
    private CardView facebookCard;
    private CardView logoutCard;
    private CardView messagesCard;
    private CardView profileCard ;

    public VendeurHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_vendeur_home2, container, false);


        startCard=view.findViewById(R.id.startPurchaseCard);
        consultCard=view.findViewById(R.id.consultProductsCard2);
        facebookCard=view.findViewById(R.id.facebookCard2);
        logoutCard=view.findViewById(R.id.logoutCard2);
        profileCard=view.findViewById(R.id.profileCard2);
        messagesCard=view.findViewById(R.id.messagesCard2);





        startCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPayment startPayment=new StartPayment();
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.frameLayoutVendeur,startPayment);
                ft.commit();
            }
        });





        consultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultProductFragment consultProductFragment=new ConsultProductFragment();
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.frameLayoutVendeur,consultProductFragment);
                ft.commit();
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
                VendeurNotificationFragment vendeurNotificationFragment =new VendeurNotificationFragment();
                setFragment(vendeurNotificationFragment);
            }
        });



        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VendeurAccountFragment vendeurAccountFragment=new VendeurAccountFragment();
                setFragment(vendeurAccountFragment);
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
        ft.replace(R.id.frameLayoutVendeur,fragment);
        ft.commit();


    }
}
