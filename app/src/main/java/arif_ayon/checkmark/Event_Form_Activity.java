package arif_ayon.checkmark;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import arif_ayon.checkmark.Database.Database_Handler;
import arif_ayon.checkmark.Model.Contact;

public class Event_Form_Activity extends AppCompatActivity {

    private EditText eventName, eventRoom, stime, etime;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        eventName = (EditText) findViewById(R.id.eventNameET);
        eventRoom = (EditText) findViewById((R.id.roonNumberET));
        stime = (EditText) findViewById((R.id.stimeET));
        etime = (EditText) findViewById(R.id.etimeET);

        save = (Button) findViewById(R.id.event_save_btn);

        final Database_Handler db=new Database_Handler(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String eName = eventName.getText().toString();
            String eRoom = eventRoom.getText().toString();
            String startTime = stime.getText().toString();
            String endTime = etime.getText().toString();

            if(eName.equals("") || eRoom.equals("") || startTime.equals("") || endTime.equals(""))
            {
                Toast.makeText (getApplicationContext(),"Information Missing",Toast.LENGTH_SHORT).show();
            }
            else{
                db.addEvent(new Contact(eName, eRoom, startTime, endTime));
                Toast.makeText (getApplicationContext(),"Event added.",Toast.LENGTH_SHORT).show();

                Intent intente = new Intent(Event_Form_Activity.this,Home_Activity.class);
                startActivity(intente);
                finish();
            }
            }
        });



    }

}
