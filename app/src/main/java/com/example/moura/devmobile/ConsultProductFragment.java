package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
public class ConsultProductFragment extends Fragment implements View.OnLongClickListener{

    private View view ;
    public TextView textView;
    public Button test;
    //public   Map<String,String[][]>products;
    public DatabaseReference ref;
    public DatabaseReference numberProducts;
    public FirebaseDatabase database;
    public Integer numberprod;
    public List<Map<String,String>> l;
    //private List<String> keys;
    public DatabaseReference myRef;
    private Map<String,Map<String,String>> products;
    private RecyclerView recyclerView;
    private ProduitAdapter mAdapter;
    private List<Produit> produitList = new ArrayList<>();

    private int counter=0;
    private Toolbar toolbar ;
    public Boolean is_in_action_mode = false;
    private TextView counter_text_view ;
    private List<Produit> ProductsList_selected = new ArrayList<>();
    private Set keys;
    private List<String> keysList;
    private Map<String,List<String>> keys_name=new HashMap<>();


    public ConsultProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_consult_product, container, false);

        toolbar=view.findViewById(R.id.toolbar_users);
        counter_text_view=view.findViewById(R.id.user_selected);

        counter_text_view.setVisibility(View.GONE);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("products");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new ProduitAdapter(produitList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        numberProducts = database.getReference("productsNumber");
        numberProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                numberprod=Integer.valueOf(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;





        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products = (Map) dataSnapshot.getValue();
                try
                {
                    keys = products.keySet();
                keysList = new ArrayList <>(keys);


                l = new ArrayList <Map <String, String>>(products.values());
                // keys=new ArrayList <String>(products.keySet());
                //Toast.makeText(ConsultProduct.this,keys.get(0),Toast.LENGTH_LONG).show();
                for (int i = 0; i < products.size(); i++) {
                    Produit produit = new Produit(l.get(i).get("name"), l.get(i).get("prix"), l.get(i).get("color"),l.get(i).get("id"),l.get(i).get("model"));
                    produitList.add(produit);

                    List <String> name_key_list = new ArrayList <>();
                    name_key_list.add(keysList.get(i));
                    keys_name.put(produit.getName(), name_key_list);

                }
                mAdapter.notifyDataSetChanged();

                //Toast.makeText(ConsultProduct.this, array[0], Toast.LENGTH_LONG).show();

            }catch ( Exception e)
                {
                    e.printStackTrace();

                }

            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Produit produit = produitList.get(position);
                //Toast.makeText(getContext(),keys_name.get(produit.getName()).get(0).toString() +" is selected!", Toast.LENGTH_SHORT).show();
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
            ProductsList_selected.add(produitList.get(position));
            counter=counter+1;
            updateCounter(counter);
        }else
        {
            ProductsList_selected.remove(produitList.get(position));
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
            mAdapter.updateAdapter(ProductsList_selected);
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
        //Toast.makeText(getActivity(),keys_name.get(ProductsList_selected.get(2).getName().toString()).get(0),Toast.LENGTH_LONG).show();
        //myRef.child(keys_email.get(usersList_selected.get(0).getEmail()).get(0).toString()).setValue(null);
         for(int i=0;i<ProductsList_selected.size();i++)
         {

             myRef.child(keys_name.get(ProductsList_selected.get(i).getName().toString()).get(0)).setValue(null);


         }

        ProductsList_selected.clear();

    }





    @Override
    public void onStart() {
        super.onStart();

        numberProducts = database.getReference("productsNumber");
        numberProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                numberprod=Integer.valueOf(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;





    }
}
