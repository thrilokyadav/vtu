package blueclimb.com.vtudemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 05/08/17.
 */
public class attendanceteacher extends AppCompatActivity implements OnItemSelectedListener {

    private String tid,item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendanceteacher);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            tid =(String) b.get("tid");
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Sem");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void generatecode(View view){

        if(item == "Select Sem")
        {
            Toast.makeText(this,"Please Select a sem",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(attendanceteacher.this, gencode.class);
            intent.putExtra("sem", item);
            intent.putExtra("tid", tid);
            startActivity(intent);
        }

    }
}
