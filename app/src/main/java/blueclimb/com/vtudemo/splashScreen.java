package blueclimb.com.vtudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jeevan on 02/07/17.
 */
public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }



    /*    String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.e("dev_id",android_id);
        if(!networkConnectivity())
        {
            Toast.makeText(this, "Please connect to internet", Toast.LENGTH_SHORT).show();
        }
        else
        {
            autologin(android_id);
        }
    }

    private void autologin(String dev_id) {
        class LoginAsync extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(String... params) {
                String dev_id = params[0];
                InputStream is = null;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("device_id", dev_id));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    String web = getResources().getString(R.string.web);
                    HttpPost httpPost = new HttpPost(web+"/login/autologin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    Log.e("log res",result);
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
                String s="";
                try {
                    s = result.trim();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(s.equalsIgnoreCase("failure")){
                    Intent intent = new Intent(splashScreen.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Please Login",Toast.LENGTH_SHORT).show();
                }
                else if(s.length()==10) {
                    Intent intent = new Intent(splashScreen.this, StudentMainActivity.class);
                    intent.putExtra("usn",s);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                }
                else if(s.length()<5)
                {
                    Intent intent = new Intent(splashScreen.this, TeacherMainActivity.class);
                    intent.putExtra("tid",s);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();

                }
                if(s.length()>10 && s.charAt(10)=='p')
                {
                    Intent intent = new Intent(splashScreen.this, StudentMainActivity.class);
                    intent.putExtra("pid",s);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(dev_id);

    }
    private boolean networkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }*/
}
