package arif_ayon.checkmark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Routine_Activity extends AppCompatActivity {


    PDFView cseroutine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        cseroutine = (PDFView) findViewById(R.id.cseroutine_id);
        cseroutine.fromAsset("class_routine_cse.pdf").load();
    }
}
