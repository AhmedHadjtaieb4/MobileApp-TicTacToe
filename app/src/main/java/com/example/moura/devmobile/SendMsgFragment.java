package com.example.moura.devmobile;

import android.content.Context;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SendMsgFragment extends Fragment {
    private View view;
    public DatabaseReference ref;
    public FirebaseDatabase database;
    public List<Map<String,String>> l;
    private List<String> keysList;
    public DatabaseReference myRef;
    public DatabaseReference myRef2;
    public DatabaseReference myRef3;
    public DatabaseReference myRef4;
    private Map<String,Map<String,Map<String,String>>> users3;

    private FirebaseAuth mAuth;
    private Map<String,Map<String,String>> users1;
private String email2;
    private Map<String,Map<String,String>> users;
    private Map<String,List<String>> keys_email=new HashMap<>();
    private List<User> usersList = new ArrayList<>();
    private Set keys;
    private TextView msgEdit ;
    public SendMsgFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_send_msg, container, false);

        final String value = getArguments().getString("email");
        mAuth = FirebaseAuth.getInstance();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users/magasiniers");
        myRef2 = database.getReference("Users/vendeurs");
        myRef3 = database.getReference("Messages");
        myRef4 = database.getReference("Users");



        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users3=(Map)dataSnapshot.getValue();
                FirebaseUser user = mAuth.getCurrentUser();



                String id =user.getUid().toString();
                if(users3.get("magasiniers").containsKey(id))
                {

                    email2=users3.get("magasiniers").get(id).get("email");


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users=(Map)dataSnapshot.getValue();
                l=new ArrayList <Map<String, String>>(users.values());
                keys =users.keySet();





                keysList=new ArrayList <>(keys);

                // Toast.makeText(getActivity(),keysList.get(0),Toast.LENGTH_LONG).show();





                for(int i=0;i<l.size();i++)
                {

                    User user=new User(l.get(i).get("email"),l.get(i).get("name"),l.get(i).get("address"),l.get(i).get("lastName"),l.get(i).get("tel"),l.get(i).get("function"),l.get(i).get("password"));
                    usersList.add(user);
                    List <String> email_function_list = new ArrayList <>();
                    email_function_list.add(keysList.get(i));
                    email_function_list.add(l.get(i).get("function"));
                    keys_email.put(user.getEmail(), email_function_list);


                }
                keysList.clear();
                keys.clear();

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        usersList.clear();

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                users1=(Map)dataSnapshot.getValue();
                l=new ArrayList <Map<String, String>>(users1.values());
                keys =users1.keySet();
                keysList=new ArrayList <>(keys);

                for(int i=0;i<l.size();i++)
                {
                    User user=new User(l.get(i).get("email"),l.get(i).get("name"),l.get(i).get("address"),l.get(i).get("lastName"),l.get(i).get("tel"),l.get(i).get("function"),l.get(i).get("password"));
                    usersList.add(user);
                    List <String> email_function_list = new ArrayList <>();
                    email_function_list.add(keysList.get(i));
                    email_function_list.add(l.get(i).get("function"));
                    keys_email.put(user.getEmail(), email_function_list);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button send =view.findViewById(R.id.send_btn_from_magasinier);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),keys_email.get(value).get(0),Toast.LENGTH_LONG).show();
                msgEdit=view.findViewById(R.id.text_of_magasinier_msg);
                String email;
                String msg;
                try {
                    msg = msgEdit.getText().toString();
                    email = users1.get(mAuth.getUid().toString()).get("email");

                }catch (Exception e)
                {
                    e.printStackTrace();
                    msg = msgEdit.getText().toString();
                    //email = users.get(mAuth.getUid().toString()).get("email");
                    email=email2;
                }

                myRef3=myRef3.child(keys_email.get(value).get(0)).push();

                myRef3.child("msg").setValue(msg);
                myRef3.child("from").setValue(email);
            }
        });


        return view;
    }

}
