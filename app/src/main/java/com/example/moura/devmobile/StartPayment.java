package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class StartPayment extends Fragment {
     private TextView cost ;
    public DatabaseReference numberProducts;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    public DatabaseReference myRef2;
    public DatabaseReference myRef3;


    private Map<String,Map<String,String>> products;
    private RecyclerView recyclerView;
    private ProduitAdapter mAdapter;
    private List<Produit> produitList = new ArrayList<>();
    private Set keys;
    private List<String> keysList;
    private Map<String,String> keys_name=new HashMap<>();
   private View view ;
   private TextView textTotal;
    public List<Map<String,String>> l;
private Button total ;
private Button nextItem;
private Button restart;
private EditText idEdit;
private EditText clientNameEdit;
private String clientName ;
private EditText clientEmailEdit ;
private  String clientEmail ;
private EditText clientTelEdit ;
private String clientTel;
private Button newPurshase ;
private int somme=0;

public StartPayment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_start_payment, container, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("products");
        myRef2 = database.getReference("Clients & Facturation");




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products = (Map) dataSnapshot.getValue();
                try
                {



                    l = new ArrayList <Map <String, String>>(products.values());

                    for (int i = 0; i < products.size(); i++) {
                        Produit produit = new Produit(l.get(i).get("name"), l.get(i).get("prix"), l.get(i).get("color"),l.get(i).get("id"),l.get(i).get("model"));

                        produitList.add(produit);


                        keys_name.put(produit.getid(), produit.getPrix());

                    }




                }catch ( Exception e)
                {
                    e.printStackTrace();

                }

            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        clientNameEdit=view.findViewById(R.id.client_name_edit);
        clientEmailEdit=view.findViewById(R.id.client_mail_edit);
        clientTelEdit=view.findViewById(R.id.client_tel_edit);
        newPurshase=view.findViewById(R.id.newPurshase);
        textTotal=view.findViewById(R.id.text_total);
        restart=view.findViewById(R.id.restart);
        idEdit=view.findViewById(R.id.id_purshase);
        nextItem=view.findViewById(R.id.next_item);
        total=view.findViewById(R.id.total);
          cost=view.findViewById(R.id.total_cost);
        nextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String ch1 =idEdit.getText().toString();
               String ch=keys_name.get(ch1);
                int s=Integer.parseInt(ch);
                somme=somme+s;

                Toast.makeText(getActivity(),Integer.toString(somme),Toast.LENGTH_LONG).show();
                idEdit.setText(null);
                idEdit.setHint("id of the item");
            }
        });




        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String costTotal=Integer.toString(somme);
              cost.setText(costTotal);
              idEdit.setVisibility(View.GONE);
              total.setVisibility(View.GONE);
              restart.setVisibility(View.GONE);
              nextItem.setVisibility(View.GONE);
              cost.setVisibility(View.VISIBLE);
               textTotal.setText("Total Price ");
               clientEmail=clientEmailEdit.getText().toString();
               clientName=clientNameEdit.getText().toString();
               clientTel=clientTelEdit.getText().toString();
               myRef3=myRef2.push();
               myRef3.child("name").setValue(clientName);
               myRef3.child("email").setValue(clientEmail);
               myRef3.child("tel").setValue(clientTel);
               myRef3.child("Total").setValue(costTotal);
              newPurshase.setVisibility(View.VISIBLE);




                                           }
        });
newPurshase.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cost.setText("Item to Purshase");
        idEdit.setVisibility(View.VISIBLE);
        total.setVisibility(View.VISIBLE);
        restart.setVisibility(View.VISIBLE);
        nextItem.setVisibility(View.VISIBLE);
        cost.setVisibility(View.GONE);
        clientTelEdit.setText(null);
        clientEmailEdit.setText(null);
        clientNameEdit.setText(null);
        somme=0;
        newPurshase.setVisibility(View.GONE);
    }
});


restart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        somme=0;
        idEdit.setText(null);
    }
});


        return view;
    }

}
