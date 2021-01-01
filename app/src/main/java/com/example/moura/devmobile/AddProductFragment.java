package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {
    public FirebaseDatabase database;

    public DatabaseReference numberProducts;
    public EditText idEdit;
    public EditText nameEdit;
    public EditText colorEdit;
    public EditText modelEdit;
    private EditText prixEdit;
    public EditText quantityEdit ;
    public Button addProduct;
    public Produit produit;
    public Integer numberprod;
    public String ch;
    public  View view ;

    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_add_product, container, false);

        idEdit=view.findViewById(R.id.idProductToAdd);
        nameEdit=view.findViewById(R.id.nameProductToAdd);
        prixEdit=view.findViewById(R.id.prixProductToAdd);
        colorEdit=view.findViewById(R.id.colorProductToAdd);
        modelEdit=view.findViewById(R.id.modelProductToAdd);
        quantityEdit=view.findViewById(R.id.quantityProductToAdd);
        addProduct=view.findViewById(R.id.addproductButton);
        database = FirebaseDatabase.getInstance();

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=nameEdit.getText().toString();
                String color=colorEdit.getText().toString();
                String model=modelEdit.getText().toString();
                String id=idEdit.getText().toString();
                String quantity=quantityEdit.getText().toString();
                String prix=prixEdit.getText().toString();
                produit=new Produit(name,prix,id,color,model);

                //ch="prod"+ch;
                DatabaseReference myRef = database.getReference("products");
                myRef=myRef.push();
                myRef.child("id").setValue(name);
                myRef.child("name").setValue(id);
                myRef.child("color").setValue(color);
                myRef.child("model").setValue(model);
                myRef.child("quantity").setValue(quantity);
                myRef.child("prix").setValue(prix);








            }
        });



        return view;
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
