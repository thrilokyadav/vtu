package blueclimb.com.vtudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Admin on 06-11-2017.
 */

public class gencode extends AppCompatActivity {

    private String tid,sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codegen);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            tid =(String) b.get("tid");
            sem =(String) b.get("sem");
        }

    }

    public void markatten(View view) {
    }
}
