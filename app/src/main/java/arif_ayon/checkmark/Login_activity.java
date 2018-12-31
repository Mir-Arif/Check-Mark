package arif_ayon.checkmark;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import arif_ayon.checkmark.Database.Database_Handler;
import arif_ayon.checkmark.Model.Contact;

public class Login_activity extends AppCompatActivity {
    private Button login;
    private TextView create_new;

    private EditText userName, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login_btn_id);
        create_new = (TextView) findViewById(R.id.create_new_acc_textview);
        userName = (EditText) findViewById(R.id.usernameET);
        passWord = (EditText) findViewById((R.id.passwordET));

        final Database_Handler db = new Database_Handler(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String userNameValue = userName.getText().toString().trim();
            String passWordValue = passWord.getText().toString().trim();

            if (userNameValue.equals("") || passWordValue.equals(""))
                Toast.makeText(getApplicationContext(), "Information Missing", Toast.LENGTH_SHORT).show();

            else {
                int i = 10;
                i = db.CheckUser(new Contact(userNameValue, passWordValue));
                if(i == 1){
                    Intent intente = new Intent(Login_activity.this, Menu_Activity.class);
                    startActivity(intente);
                    finish();
                }
                else if(i==0)
                    Toast.makeText(getApplicationContext(), "Username or Password was wrong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Problem!!!", Toast.LENGTH_SHORT).show();
            }
            }
        });


        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intente = new Intent(Login_activity.this, Create_New_Account_Activity.class);
                startActivity(intente);
                finish();
            }
        });
    }
}
