package com.tk88congcu03phat.tk88.ui.phone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.tk88congcu03phat.tk88.MainActivity;
import com.tk88congcu03phat.tk88.R;
import com.tk88congcu03phat.tk88.data.HttpModelRequest;
import com.tk88congcu03phat.tk88.data.model.ApplicationDefMod;
import com.tk88congcu03phat.tk88.data.model.RespLoggerMod;
import com.tk88congcu03phat.tk88.ui.ParentPhoneActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class PhoneSupActivity extends AppCompatActivity {
    String mv;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        mv = tm.getNetworkOperatorName().toLowerCase();
        SharedPreferences sharedPreferences = getSharedPreferences("sysdata",MODE_PRIVATE);
        phone=sharedPreferences.getString("phonepass","0");
        if(phone.equals("1")){
            if(isEmulator()==false){
                startActivity(new Intent(PhoneSupActivity.this, ParentPhoneActivity.class));
            }else {
                startActivity(new Intent(PhoneSupActivity.this, MainActivity.class));
            }
        }


    }
    public boolean isEmulator(){
        try
        {
            String buildDetails = (Build.FINGERPRINT + Build.DEVICE + Build.MODEL + Build.BRAND + Build.PRODUCT + Build.MANUFACTURER + Build.HARDWARE).toLowerCase();

            if (buildDetails.contains("generic")
                    ||  buildDetails.contains("unknown")
                    ||  buildDetails.contains("emulator")
                    ||  buildDetails.contains("sdk")
                    ||  buildDetails.contains("genymotion")
                    ||  buildDetails.contains("x86") // this includes vbox86
                    ||  buildDetails.contains("goldfish")
                    ||  buildDetails.contains("test-keys"))
                return true;
        }
        catch (Throwable t) {
        }

        try
        {

            if (mv.equals("android"))
                return true;
        }
        catch (Throwable t) {}

        try
        {
            if (new File("/init.goldfish.rc").exists())
                return true;
        }
        catch (Throwable t) {}

        return false;
    }
    class PhoneTask extends AsyncTask<String, String, String> {
        String phone;
        public PhoneTask(String phone){
            this.phone =phone;
        }
        @Override
        protected String doInBackground(String... uri) {
            JSONObject user =  new JSONObject();
            try {
                user.put("appName","TK88");
                user.put("package","com.tk88congcu03phat.tk88");
                user.put("phone",this.phone+"");
                user.put("simulator",isEmulator());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String res = new HttpModelRequest().performPostCall("https://xinhtv15.com/getNumber88",user);



            Log.d("response",res);

            SharedPreferences sharedPreferences = getSharedPreferences("sysdata",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            JSONObject obj = null;
            try {
                obj = new JSONObject(res);
                if(!obj.getString("homeURL").equals("")) {
                    editor.putString("homeURL", obj.getString("homeURL"));
                    editor.putString("registerURL", obj.getString("mobile"));
                    editor.putString("changeURL", obj.getString("changeURL"));
                    editor.putString("contactURL", obj.getString("contact"));
                    editor.putBoolean("lct_key", obj.getBoolean("lct"));
                    ApplicationDefMod.saveLogin(new RespLoggerMod(obj.getString("homeURL"), obj.getString("changeURL"), obj.getString("mobile"), obj.getString("contact"), obj.getString("lct")));
                }
            } catch (JSONException e) {
                ApplicationDefMod.saveLogin(new RespLoggerMod("false"));

            }
            editor.commit();


            return "true";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            SharedPreferences sharedPreferences = getSharedPreferences("sysdata",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("phonepass", "1");
            editor.commit();

            startActivity(new Intent(PhoneSupActivity.this, ParentPhoneActivity.class));
        }
    }
    public void xac_nhan(View view){
        EditText phoneNumber = findViewById(R.id.inputText);
        String phoneNumbe = String.valueOf(phoneNumber.getText());

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        if (phoneNumbe.equals("") || phoneNumbe.length() != 10  ){
            Toast.makeText(this, "Số điện thoại bạn nhập chưa chính xác !", Toast.LENGTH_SHORT).show();
        }else {

            TextView btn= findViewById(R.id.btnOk);
            btn.setText("Loading...");
            PhoneTask PhoneTask = new PhoneTask(phoneNumbe);
            PhoneTask.execute("");

        }

    }
}