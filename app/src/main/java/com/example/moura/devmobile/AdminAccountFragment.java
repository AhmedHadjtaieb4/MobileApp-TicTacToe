package com.example.moura.devmobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class AdminAccountFragment extends Fragment {


    private View view;
    private FirebaseAuth mAuth;
    public DatabaseReference ref;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    private Map<String,Map<String,Map<String,String>>> users;
    private TextView nameText;
    private TextView emailText;
    private TextView telText;
    private TextView functionText;
    private TextView title;
    private TextView title2;
    private Button logOut ;
    public AdminAccountFragment()
    {


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_admin_account, container, false);
   logOut=view.findViewById(R.id.logoutBtnAccount__);

        database = FirebaseDatabase.getInstance();


        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("Users");
        FirebaseUser user = mAuth.getCurrentUser();
        nameText=view.findViewById(R.id.nameAccount__);
        emailText=view.findViewById(R.id.emailAccount__);
        telText=view.findViewById(R.id.telAccount__);
        functionText=view.findViewById(R.id.functionAccount__);
        title=view.findViewById(R.id.titleAccount__);
        title2=view.findViewById(R.id.title2__);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users=(Map)dataSnapshot.getValue();
                FirebaseUser user = mAuth.getCurrentUser();

                String email ;
                String name;
                String tel ;
                String function;
                String id =user.getUid().toString();
                if(users.get("admin").containsKey(id)) {

                    name = users.get("admin").get(id).get("name");
                    email = users.get("admin").get(id).get("email");
                    tel = users.get("admin").get(id).get("tel");
                    function = users.get("admin").get(id).get("function");


                    String txt=name+" "+"is working as "+function ;
                    emailText.setText(email);
                    nameText.setText(name);
                    telText.setText(tel);
                    functionText.setText(function);
                    title.setText(name);
                    title2.setText(function);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


logOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),LogInModeSelection.class));
    }
});

        return view;
    }


}
