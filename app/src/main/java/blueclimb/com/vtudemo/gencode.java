package blueclimb.com.vtudemo;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        sendcode(cde);
    }

    private void sendcode(final String val) {
        class sendtcodeasync extends AsyncTask<Void,Void,String>
        {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(gencode.this, "Please wait", "Updating");
            }


            @Override
            protected String doInBackground(Void... voids) {
                InputStream is = null;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("tid", tid));
                nameValuePairs.add(new BasicNameValuePair("code", val));
                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    String web = getResources().getString(R.string.web);
                    HttpPost httpPost = new HttpPost(web + "/attendance/attendance_t_code.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                    Log.e("log res", result);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result){
                loadingDialog.dismiss();
                String s="";
                try {
                    s = result.trim();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(s.equalsIgnoreCase("success"))
                {
                        final MyCounter timer = new MyCounter(60000, 1000, progressBar);
                        timer.start();
                }
            }
        }
        sendtcodeasync s =  new sendtcodeasync();
        s.execute();
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
