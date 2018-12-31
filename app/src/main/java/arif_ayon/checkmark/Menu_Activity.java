package arif_ayon.checkmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Activity extends AppCompatActivity {

    private Button task, routine, aboutus, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        task = (Button) findViewById(R.id.taskbtn);
        routine = (Button) findViewById(R.id.routine_btn);
        aboutus = (Button) findViewById(R.id.aboutus_btn);
        logout = (Button) findViewById(R.id.logout_btn);


        routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intente = new Intent(Menu_Activity.this, Routine_Activity.class);
                startActivity(intente);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intente = new Intent(Menu_Activity.this, Login_activity.class);
                startActivity(intente);
                finish();
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intente = new Intent(Menu_Activity.this, Home_Activity.class);
                startActivity(intente);
            }
        });


    }
}
