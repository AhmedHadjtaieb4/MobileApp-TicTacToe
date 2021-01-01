package com.example.moura.devmobile;


import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
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
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagasinierNotificationFragment extends Fragment {
    private View view ;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private Map<String,Map<String,Map<String,String>>>msgs;
    private List<Map<String,String>>listMsg;
    FirebaseUser user ;
    private TextView msgText ;

    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    private Button send_button;
    private List<com.example.moura.devmobile.Message>list_of_msgs=new ArrayList <>();
    private List<com.example.moura.devmobile.Message>list_of_msgs_2;

    public MagasinierNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_magasinier_notification, container, false);
        send_button=view.findViewById(R.id.to_send_btn_magasinier);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_msg_magasinier);
        mAdapter=new MessageAdapter(list_of_msgs);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Messages");
        mAuth = FirebaseAuth.getInstance();
        msgText=view.findViewById(R.id.msg);
        user = mAuth.getCurrentUser();
        final String id=user.getUid().toString();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msgs=(Map)dataSnapshot.getValue();
                //Toast.makeText(getActivity(),msgs.get(mAuth.getUid().toString()),Toast.LENGTH_LONG).show();
                Map<String,Map<String,String>> map=msgs.get(id);
                listMsg=new ArrayList<Map<String,String>>(map.values());
                list_of_msgs.clear();

                for(int i=0;i<listMsg.size();i++)
                {
                    String msg=listMsg.get(i).get("msg");
                    String from=listMsg.get(i).get("from");
                    com.example.moura.devmobile.Message message=new com.example.moura.devmobile.Message(msg,from);
                    list_of_msgs.add(message);
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
                com.example.moura.devmobile.Message msg = list_of_msgs.get(position);


                //Toast.makeText(getContext(),user.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                SendMsgFragment sendMsgFragment=new SendMsgFragment();
                Bundle args = new Bundle();
                args.putString("email",msg.getFrom());
                sendMsgFragment.setArguments(args);

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMagasinier,sendMsgFragment);
                ft.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_recive_msg_magasinier srMsg=new send_recive_msg_magasinier();
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                    ft.replace(R.id.frameLayoutMagasinier,srMsg);
                    ft.commit();


            }
        });





        return view;
    }

}
