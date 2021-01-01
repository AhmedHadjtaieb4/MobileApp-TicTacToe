package com.example.moura.devmobile;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInModeSelection extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEdit;
    private EditText passwordEdit;
    private Button LogIn ;
    private RadioButton buttonCheked;
    private RadioGroup radioGroup;
    private TextView t;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    public DatabaseReference myRef2;
    private String function="" ;
    private String function1="";
    private String function2="";

    private ProgressBar mProgressBar;
    private TextView mLoadingText;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_mode_selection);
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Users/magasiniers");
        //myRef2 = database.getReference("Users/vendeurs");
        mAuth = FirebaseAuth.getInstance();

        emailEdit=findViewById(R.id.email);
        passwordEdit=findViewById(R.id.password);
        LogIn=findViewById(R.id.login);
         radioGroup=findViewById(R.id.radiogroupe);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });




    }

    public void startLogin()
    {
        final String email=emailEdit.getText().toString();
        final String password=passwordEdit.getText().toString();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {

            Toast.makeText(LogInModeSelection.this,"Field empty !!",Toast.LENGTH_LONG).show();

        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener <AuthResult>() {
                @Override
                public void onComplete(@NonNull Task <AuthResult> task) {


                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                               String ch=user.getUid();
                               String ch2=user.getUid();
                               ch="Users/vendeurs"+"/"+ch+"/"+"function";
                               ch2="Users/magasiniers"+"/"+ch2+"/"+"function";
                        myRef2 = database.getReference(ch);
                        myRef = database.getReference(ch2);
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                     function2=dataSnapshot.getValue(String.class);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                        myRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               function1=dataSnapshot.getValue(String.class);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        try {
                            if (!function1.isEmpty()) {
                                function = function1;
                            }
                        }catch (Exception e){


                            try{
                                if(!function2.isEmpty())
                                {
                                    function=function2;
                                }

                            }catch (Exception e1)
                            {

                                function="admin";
                            }

                        }

                        //Integer id=radioGroup.getCheckedRadioButtonId();
                              //buttonCheked=findViewById(id);
                              //String Btn=buttonCheked.getText().toString();




                        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
                        mLoadingText = (TextView) findViewById(R.id.LoadingCompleteTextView);
                          mProgressBar.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (mProgressStatus < 100){
                                    mProgressStatus++;
                                    android.os.SystemClock.sleep(50);
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mProgressBar.setProgress(mProgressStatus);
                                        }
                                    });
                                }
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mLoadingText.setVisibility(View.VISIBLE);
                                        LogIn.setText("Start");
                                    }
                                });
                            }
                        }).start();





                        if(!function.isEmpty())
                        {
                            //Toast.makeText(LogInModeSelection.this,function,Toast.LENGTH_LONG).show();
                            switch (function)
                            {
                                case "admin" : startActivity(new Intent(LogInModeSelection.this,AdminHomePage.class)); break;
                                case "Magasinier" :startActivity(new Intent(LogInModeSelection.this,MagasinierHomePage.class)); break;
                                case "Vendeur" : startActivity(new Intent(LogInModeSelection.this,VendeurHomePage.class)); break;
                                default: Toast.makeText(LogInModeSelection.this,"code not valid !!",Toast.LENGTH_LONG).show(); break;
                            }


                        }





                      //Intent intent= new Intent(LogInModeSelection.this, VerificationCode.class);
                        // intent.putExtra("id",Btn);
                         //startActivity(intent);
                    } else {
                        Toast.makeText(LogInModeSelection.this, "Login problem !!", Toast.LENGTH_LONG).show();


                    }

                }
            });
        }

    }
}
