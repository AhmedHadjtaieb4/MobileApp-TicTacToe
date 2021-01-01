package com.example.moura.devmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class AdminHomePage extends AppCompatActivity {
      public FrameLayout frameLayoutAdmin;
      public BottomNavigationView bottomNavigationViewAdmin;
      public AdminAccountFragment adminAccountFragment ;
      public AdminHomeFragment adminHomeFragment;
      public AdminNotificationFragment adminNotificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        frameLayoutAdmin=findViewById(R.id.frameLayoutAdmin);
        bottomNavigationViewAdmin=findViewById(R.id.bottomNavigationViewAdmin);
        adminAccountFragment=new AdminAccountFragment() ;
        adminHomeFragment=new AdminHomeFragment();
        adminNotificationFragment=new AdminNotificationFragment();
           setFragment(adminHomeFragment);
        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {

                    case R.id.homeAdmin : {setFragment(adminHomeFragment);
                                                        return true;}
                    case R.id.notificationAdmin: {setFragment(adminNotificationFragment); return true;}
                    case R.id.AccountAdmin : {setFragment(adminAccountFragment); return true ;}
                    default:return false ;


                }








            }
        });



    }

    public void setFragment(Fragment fragment)
    {

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutAdmin,fragment);
        ft.commit();


    }





}
