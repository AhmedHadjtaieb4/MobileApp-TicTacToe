package com.example.moura.devmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagasinierAccountFragment extends Fragment {

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
    public MagasinierAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_magasinier_account2, container, false);
        database = FirebaseDatabase.getInstance();


        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("Users");
        FirebaseUser user = mAuth.getCurrentUser();
        logOut=view.findViewById(R.id.logoutBtnAccount);
        nameText=view.findViewById(R.id.nameAccount);
        emailText=view.findViewById(R.id.emailAccount);
        telText=view.findViewById(R.id.telAccount);
        functionText=view.findViewById(R.id.functionAccount);
        title=view.findViewById(R.id.titleAccount);
      title2=view.findViewById(R.id.title2);
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
                if(users.get("magasiniers").containsKey(id))
                {

                    name=users.get("magasiniers").get(id).get("name");
                    email=users.get("magasiniers").get(id).get("email");
                    tel=users.get("magasiniers").get(id).get("tel");
                    function=users.get("magasiniers").get(id).get("function");

                }
                else
                {
                    name=users.get("vendeurs").get(id).get("name");
                    email=users.get("vendeurs").get(id).get("email");
                    tel=users.get("vendeurs").get(id).get("tel");
                    function=users.get("vendeurs").get(id).get("function");


                }
                String txt=name+" "+"is working as "+function ;
                emailText.setText(email);
                nameText.setText(name);
                telText.setText(tel);
                functionText.setText(function);
                title.setText(name);
                title2.setText(function);

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
