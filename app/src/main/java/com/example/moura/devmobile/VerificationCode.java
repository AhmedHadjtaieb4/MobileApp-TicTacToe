package com.example.moura.devmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

public class VerificationCode extends AppCompatActivity {

    private Integer id;
    private RadioButton radioButton;
    private EditText codeEdit;
    private Button verify ;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        Intent intent =getIntent();
        String nom=intent.getStringExtra("id");
        codeEdit=findViewById(R.id.code);
        verify=findViewById(R.id.verify);

       verify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               code=codeEdit.getText().toString();

               switch (code)
               {
                   case "admin" : startActivity(new Intent(VerificationCode.this,AdminHomePage.class)); break;
                   case "magasinier" :startActivity(new Intent(VerificationCode.this,MagasinierHomePage.class)); break;
                   case "vendeur" : startActivity(new Intent(VerificationCode.this,VendeurHomePage.class)); break;
                    default: Toast.makeText(VerificationCode.this,"code not valid !!",Toast.LENGTH_LONG).show(); break;
               }
           }
       });


    }
}
