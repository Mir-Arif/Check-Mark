package arif_ayon.checkmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import arif_ayon.checkmark.Database.Database_Handler;
import arif_ayon.checkmark.Model.Contact;

public class Home_Activity extends AppCompatActivity {


    private ImageButton addevent, refresh;
    TextView updisplay, currentdisplay;
    int chour, cmin, eshour, esmin, eehour, eemin; //'c' means current, 'e' means event, 's' means start

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addevent = (ImageButton) findViewById(R.id.add_event_btn);
        refresh = (ImageButton) findViewById(R.id.refresh_btn);
        final Database_Handler db=new Database_Handler(this);

        updisplay = (TextView) findViewById(R.id.displayTV);
        currentdisplay = (TextView) findViewById(R.id.current_display_TV);
        updisplay.setMovementMethod(new ScrollingMovementMethod());
        currentdisplay.setMovementMethod(new ScrollingMovementMethod());


        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intente = new Intent(Home_Activity.this, Event_Form_Activity.class);
            startActivity(intente);
            finish();
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTimeRecover();

                List<Contact> myContactList = db.getAllEvent();
                String resultup = "", resultcur="";
                for(Contact myContact : myContactList)
                {
                    eventTimeRecover(myContact.getStime(), myContact.getEtime());

                    if( (chour<eshour) || (chour==eshour && cmin<esmin) ) {
                        //Upcomming
                        resultup += "Event: " + myContact.getEvent() + ", Room: " + myContact.getRoom() + ", Start: " +
                                myContact.getStime() + ", End: " + myContact.getEtime();
                        resultup += "\n\n";
                        Log.d("Result", resultup);
                    }
                    else if((chour==eshour && cmin>=esmin) || (chour>eshour && chour<eehour) || (chour==eehour && cmin<=eemin) ){
                        //Current
                        resultcur += "Event: " + myContact.getEvent() + ", Room: " + myContact.getRoom() + ", Start: " +
                                myContact.getStime() + ", End: " + myContact.getEtime();
                        resultcur += "\n\n";
                        Log.d("Result", resultcur);
                    }
                    else{
                        //past event will be delete
                        //db.deleteEvent(myContact.getEvent());
                    }
                }
                if(myContactList.size()  == 0)
                {
                    resultup = "No event to display.";
                }
                updisplay.setText(resultup);
                currentdisplay.setText(resultcur);
            }
        });

    }

    private void eventTimeRecover(String start, String end) {
        String parts1[] = start.split(":");
        eshour = Integer.parseInt(parts1[0]);
        esmin = Integer.parseInt(parts1[1]);

        String parts2[] = end.split(":");
        eehour = Integer.parseInt(parts2[0]);
        eemin = Integer.parseInt(parts2[1]);
    }

    public void currentTimeRecover() {
        Date currentTime = Calendar.getInstance().getTime();
        String currenttime = currentTime.toString();
        currenttime = currenttime.substring(11,19);
        //Log.i("currentims", currenttime);
        //currentdisplay.setText(currenttime);

        String parts[] = currenttime.split(":");
        chour = Integer.parseInt(parts[0]);
        cmin = Integer.parseInt(parts[1]);
    }


}
