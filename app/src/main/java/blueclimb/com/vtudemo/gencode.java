package blueclimb.com.vtudemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 06-11-2017.
 */

public class gencode extends AppCompatActivity {

    ProgressBar progressBar;
    private Spinner randsp;
    private String tid,sem;
    private String[] randnum = new String[3];
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
        randsp= (Spinner) findViewById(R.id.codespinner);
        randomc();
        ArrayAdapter<String> randad = new ArrayAdapter<String>(gencode.this,android.R.layout.simple_spinner_item,randnum);
        randad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        randsp.setAdapter(randad);
        progressBar = (ProgressBar)findViewById(R.id.timer);

    }

    private void randomc() {
        for(int i=0;i<3;i++) {
            randnum[i] = UUID.randomUUID().toString().substring(2,8);
            randnum[i] = randnum[i].toUpperCase();
        }
    }

    public void markatten(View view) {
        String cde = randnum[randsp.getSelectedItemPosition()];
        Toast.makeText(this,cde,Toast.LENGTH_SHORT).show();
        final MyCounter timer = new MyCounter(60000,1000,progressBar);
        timer.start();
    }
    public class MyCounter extends CountDownTimer {
     ProgressBar pb;

        public MyCounter(long millisInFuture, long countDownInterval,ProgressBar p) {
            super(millisInFuture, countDownInterval);
            pb=p;
        }

        @Override
        public void onFinish() {
            Toast.makeText(gencode.this,"The time for code entering is finished",Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(pb.getProgress()<=pb.getMax()+1)
            {
                pb.setProgress(pb.getProgress()+1);
            }
        }
    }
}
