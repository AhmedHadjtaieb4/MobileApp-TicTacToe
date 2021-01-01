package com.example.moura.devmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class VendeurHomePage extends AppCompatActivity {
    public FrameLayout frameLayoutVendeur;
    public BottomNavigationView bottomNavigationViewVendeur;
    public VendeurAccountFragment vendeurAccountFragment ;
    public VendeurHomeFragment vendeurHomeFragment;
    public VendeurNotificationFragment vendeurNotificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendeur_home_page);

        frameLayoutVendeur=findViewById(R.id.frameLayoutVendeur);
        bottomNavigationViewVendeur=findViewById(R.id.bottomNavigationViewVendeur);
        vendeurAccountFragment=new VendeurAccountFragment() ;
        vendeurHomeFragment=new VendeurHomeFragment();
        vendeurNotificationFragment=new VendeurNotificationFragment();
        setFragment(vendeurHomeFragment);
        bottomNavigationViewVendeur.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {

                    case R.id.homeVendeur : {setFragment(vendeurHomeFragment);
                        return true;}
                    case R.id.notificationVendeur: {setFragment(vendeurNotificationFragment); return true;}
                    case R.id.AccountVendeur : {setFragment(vendeurAccountFragment); return true ;}
                    default:return false ;


                }








            }
        });




    }
    public void setFragment(Fragment fragment)
    {

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutVendeur,fragment);
        ft.commit();


    }
}
