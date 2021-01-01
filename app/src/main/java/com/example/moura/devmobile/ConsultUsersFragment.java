package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultUsersFragment extends Fragment implements View.OnLongClickListener{
    private View view ;
    public DatabaseReference ref;
    public FirebaseDatabase database;
    public List<Map<String,String>> l;
    private List<String> keysList;
    public DatabaseReference myRef;
    public DatabaseReference myRef2;
    public BottomNavigationView bottomNavigationViewAdmin;
     private RadioButton radioButton;
    private Map<String,Map<String,String>> users;
    private Map<String,List<String>> keys_email=new HashMap <>();
    private RecyclerView recyclerView;
    private List<User> usersList = new ArrayList<>();
    private List<User> usersList_selected = new ArrayList<>();
    private int counter=0;
    private UsersAdapter mAdapter;
    private Toolbar toolbar ;
    public Boolean is_in_action_mode = false;
    private TextView counter_text_view ;
    private Set keys;
    public ConsultUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_consult_users, container, false);

        toolbar=view.findViewById(R.id.toolbar_users);
        counter_text_view=view.findViewById(R.id.user_selected);

        counter_text_view.setVisibility(View.GONE);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users/magasiniers");
        myRef2 = database.getReference("Users/vendeurs");
        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_Users);
        mAdapter = new UsersAdapter(usersList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

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
                mAdapter.notifyDataSetChanged();
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

        users=(Map)dataSnapshot.getValue();
        l=new ArrayList <Map<String, String>>(users.values());
        keys =users.keySet();
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
                Toast.makeText(getContext(),keys_email.get(user.getEmail()).get(0).toString() + " "+keys_email.get(user.getEmail()).get(1).toString() +" is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));







        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_users,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode_users);
        counter_text_view.setVisibility(View.VISIBLE);
        is_in_action_mode=true;
        mAdapter.notifyDataSetChanged();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }
    public void prepareSelection(View v,int position)
    {
          if(((CheckBox)v).isChecked())
          {
             usersList_selected.add(usersList.get(position));
             counter=counter+1;
             updateCounter(counter);
          }else
          {
              usersList_selected.remove(usersList.get(position));
              counter=counter-1;
              updateCounter(counter);


          }

    }
    public void updateCounter(int counter)
    {
        if(counter==0)
        {
            counter_text_view.setText("0 Item selected");
        }
        else
        {
            counter_text_view.setText(counter+" Item selected ");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.delete_item)
        {
            mAdapter.updateAdapter(usersList_selected);
            clearActionMode();
         //Toast.makeText(getActivity(),usersList_selected.get(0).getEmail().toString(),Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }



    public void clearActionMode()
    {

        is_in_action_mode=false ;
         toolbar.getMenu().clear();
         toolbar.inflateMenu(R.menu.menu_users);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_text_view.setVisibility(View.GONE);
        counter_text_view.setText("0 Item selected");
        counter=0;
        //Toast.makeText(getActivity(),keys_email.get(usersList_selected.get(0).getEmail()).get(1),Toast.LENGTH_LONG).show();
        //myRef.child(keys_email.get(usersList_selected.get(0).getEmail()).get(0).toString()).setValue(null);
        for(int i=0 ;i<usersList_selected.size();i++)
        {
            if(keys_email.get(usersList_selected.get(i).getEmail()).get(1).toString().equals("Magasinier"))
            {
                myRef.child(keys_email.get(usersList_selected.get(i).getEmail()).get(0).toString()).setValue(null);
                Toast.makeText(getActivity(),"mag",Toast.LENGTH_LONG).show();
            }else
            {
                 myRef2.child(keys_email.get(usersList_selected.get(i).getEmail()).get(0).toString()).setValue(null);
               Toast.makeText(getActivity(),"vend",Toast.LENGTH_LONG).show();

            }

        }

        usersList_selected.clear();

    }
}
