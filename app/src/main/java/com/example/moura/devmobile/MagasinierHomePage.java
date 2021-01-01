package com.example.moura.devmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MagasinierHomePage extends AppCompatActivity {
    public FrameLayout frameLayoutMagasinier;
    public BottomNavigationView bottomNavigationViewMagasinier;
    public MagasinierAccountFragment magasinierAccountFragment ;
    public MagasinierHomeFragment magasinierHomeFragment;
    public MagasinierNotificationFragment magasinierNotificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasinier_home_page);



        frameLayoutMagasinier=findViewById(R.id.frameLayoutMagasinier);
        bottomNavigationViewMagasinier=findViewById(R.id.bottomNavigationViewMagasinier);
        magasinierAccountFragment=new MagasinierAccountFragment() ;
        magasinierHomeFragment=new MagasinierHomeFragment();
        final VendeurNotificationFragment  vendeurNotificationFragment=new VendeurNotificationFragment();
        final send_recive_msg_magasinier srmsg=new send_recive_msg_magasinier();
        magasinierNotificationFragment=new MagasinierNotificationFragment();
          setFragment(magasinierHomeFragment);
        bottomNavigationViewMagasinier.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {

                    case R.id.homeMagasinier : {setFragment(magasinierHomeFragment);
                        return true;}
                    case R.id.notificationMagasinier: {setFragment(magasinierNotificationFragment); return true;}
                    case R.id.AccountMagasinier : {setFragment(magasinierAccountFragment); return true ;}
                    default:return false ;


                }

            }
        });





    }

    public void setFragment(Fragment fragment)
    {

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMagasinier,fragment);
        ft.commit();


    }


}
