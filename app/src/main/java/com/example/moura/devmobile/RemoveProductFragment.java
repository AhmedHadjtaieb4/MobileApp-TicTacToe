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
public class RemoveProductFragment extends Fragment {

    private FirebaseDatabase database;
    private Integer numberprod;
    private DatabaseReference numberProducts;
    private Button remove;
    private EditText removeEdit;
    private String ch;
    private View view ;
    public RemoveProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_remove_product, container, false);



        remove=view.findViewById(R.id.removebtn);
        removeEdit=view.findViewById(R.id.removeEditText);
        database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("products");

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToRemove =removeEdit.getText().toString();
                myRef.child(itemToRemove).setValue(null);
                numberprod=numberprod-1;
                ch=Integer.toString(numberprod);
                numberProducts.setValue(ch);

            }
        });





        return view ;
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
