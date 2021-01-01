package com.example.moura.devmobile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUser extends Fragment {


       private View view ;
       private Button add;
       private EditText nameEdit;
       private EditText emailEdit;
       private EditText passEdit;
       private EditText lastNameEdit;
       private EditText telEdit;
       private EditText addressEdit;
       private RadioGroup radioGroup ;
       private RadioButton radioButton;
       private String name;
    private String password;
    private String email;
    private String tel;

    private String lastname;
    private String address;

    private String function;





    public FirebaseDatabase database;
    private FirebaseAuth mAuth;
  private   DatabaseReference reference;

    public AddUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_add_user2, container, false);

        nameEdit=view.findViewById(R.id.nameRegister);
        passEdit=view.findViewById(R.id.passwordRegister);
        lastNameEdit=view.findViewById(R.id.lastName);
        emailEdit=view.findViewById(R.id.emailRegister);
        telEdit=view.findViewById(R.id.TelRegister);
        add=view.findViewById(R.id.addregister);
        addressEdit=view.findViewById(R.id.address);
        radioGroup=view.findViewById(R.id.radioGroupeRegister);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  name = nameEdit.getText().toString();
                  password = passEdit.getText().toString();
                  email = emailEdit.getText().toString();
                  tel = telEdit.getText().toString();
                  lastname = lastNameEdit.getText().toString();
                  address = addressEdit.getText().toString();
                 Integer id = radioGroup.getCheckedRadioButtonId();
                 radioButton = view.findViewById(id);
                  function = radioButton.getText().toString();
                // DatabaseReference myRef = database.getReference("Users" + email);
                 Boolean test = true;
                 reference=database.getReference("Users");
                 switch (function) {
                     case "Magasinier": {
                          reference = reference.child("magasiniers");
                         break;
                     }
                     case "Vendeur": {
                          reference = reference.child("vendeurs");
                         break;
                     }
                     default: {
                         Toast.makeText(getActivity(), "wrong function", Toast.LENGTH_LONG).show();
                         test = false;
                         break;
                     }
                 }

             if(test){



                 mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener <AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task <AuthResult> task) {
                         if (task.isSuccessful()) {

                             FirebaseUser user = mAuth.getCurrentUser();
                               String ch=user.getUid().toString();
                             Toast.makeText(getActivity(), ch, Toast.LENGTH_LONG).show();
                             reference=reference.child(ch);
                             reference.child("name").setValue(name);
                             reference.child("email").setValue(email);
                             reference.child("password").setValue(password);
                             reference.child("tel").setValue(tel);
                             reference.child("address").setValue(address);
                             reference.child("last name").setValue(lastname);
                             reference.child("function").setValue(function);


                         } else {
                             Toast.makeText(getActivity(), "Registraition failed", Toast.LENGTH_LONG).show();


                         }
                     }
                 });




             }else (Toast.makeText(getActivity(),"verify function",Toast.LENGTH_LONG)).show();

             }
         });



        return view ;
    }

}
