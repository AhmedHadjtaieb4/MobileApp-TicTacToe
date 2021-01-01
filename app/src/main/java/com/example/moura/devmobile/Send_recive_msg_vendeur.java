package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class Send_recive_msg_vendeur extends Fragment {

    private View view;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    private FirebaseAuth mAuth;
    public DatabaseReference myRef3;
    public DatabaseReference myRef2;
    private Map<String,Map<String,String>> users;
    private List<String> keys;
    private List<String> emails;
    public List<Map<String,String>> lista;
    private Button send;
    private Adapter mAdapter;
    private List<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Map<String,Map<String,String>> users_map;
    private RadioButton radioButton;
    public BottomNavigationView bottomNavigationViewAdmin;
    public List<Map<String,String>> l;


    private Map<String,String> keyEmail;



    FirebaseUser user ;





    public Send_recive_msg_vendeur() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_send_recive_msg_vendeur, container, false);
        database = FirebaseDatabase.getInstance();
        send=view.findViewById(R.id.send3);
        myRef = database.getReference("Messages");
        myRef3 = database.getReference("Users/magasiniers");
        myRef2 = database.getReference("Users/vendeurs");
        mAuth = FirebaseAuth.getInstance();
        emails=new ArrayList <String>();
        keyEmail=new HashMap<String, String>() ;
        user = mAuth.getCurrentUser();


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_3);
        mAdapter = new Adapter(usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);









        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users=(Map)dataSnapshot.getValue();
                l=new ArrayList <Map<String, String>>(users.values());
                for(int i=0;i<l.size();i++)
                {
                    User user=new User(l.get(i).get("email"),l.get(i).get("name"),l.get(i).get("address"),l.get(i).get("lastName"),l.get(i).get("tel"),l.get(i).get("function"),l.get(i).get("password"));
                    usersList.add(user);


                }
                mAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        usersList.clear();

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                users=(Map)dataSnapshot.getValue();
                l=new ArrayList <Map<String, String>>(users.values());
                for(int i=0;i<l.size();i++)
                {
                    User user=new User(l.get(i).get("email"),l.get(i).get("name"),l.get(i).get("address"),l.get(i).get("lastName"),l.get(i).get("tel"),l.get(i).get("function"),l.get(i).get("password"));
                    usersList.add(user);


                }
                mAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User user = usersList.get(position);


                //Toast.makeText(getContext(),user.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                SendMsgFragment sendMsgFragment=new SendMsgFragment();
                Bundle args = new Bundle();
                args.putString("email",user.getEmail());
                sendMsgFragment.setArguments(args);

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutVendeur,sendMsgFragment);
                ft.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));













        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

    }
}
