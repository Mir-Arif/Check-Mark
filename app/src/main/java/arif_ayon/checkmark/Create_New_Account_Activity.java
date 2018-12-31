package arif_ayon.checkmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arif_ayon.checkmark.Database.Database_Handler;
import arif_ayon.checkmark.Model.Contact;

public class Create_New_Account_Activity extends AppCompatActivity {

    Button add_account;
    EditText userName, passWord, semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);


        add_account =(Button)findViewById(R.id.add_account_btn);
        userName = (EditText) findViewById(R.id.usernameTV);
        passWord = (EditText) findViewById(R.id.passworsTV);
        semester = (EditText) findViewById(R.id.semesterTV);


        final Database_Handler db = new Database_Handler(this);


        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String userNameValue = userName.getText().toString();
            String passWordValue = passWord.getText().toString();
            String semesterValue = semester.getText().toString();

            if(userNameValue.equals("") || passWordValue.equals("") || semesterValue.equals(""))
                Toast.makeText (getApplicationContext(),"Information Missing",Toast.LENGTH_SHORT).show();

            else{
                db.adduser(new Contact(userNameValue, passWordValue, semesterValue));
                Toast.makeText (getApplicationContext(),"Sign up complete.",Toast.LENGTH_SHORT).show();

                Intent intente = new Intent(Create_New_Account_Activity.this,Login_activity.class);
                startActivity(intente);
                finish();
            }
            }
        });
    }
}
