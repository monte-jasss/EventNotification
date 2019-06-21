package in.monte.spsu.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Method;

import in.monte.spsu.R;

public class ContactUs extends AppCompatActivity {
    Toolbar toolbar;
    Button send;
    EditText to, subject, body;
    String Subject, Body;
    String[] To={"monu.lakshkar@spsu.ac.in"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar = findViewById(R.id.toolbar);
        send = findViewById(R.id.send);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Contact Us");
        toolbar.setNavigationIcon(R.drawable.ic_back);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subject = subject.getText().toString();
                Body = body.getText().toString();
                if(getMobileDataState()) {
                    onSendClick(view);
                } else {
                    Snackbar.make(view,"No Internet Connection !",Snackbar.LENGTH_INDEFINITE)
                            .setAction("Turn On", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setMobileDataState(true);
                                }
                            }).show();
                }
            }
        });
    }

    private void onSendClick(View view) {
        if(sendMail(To, Subject, Body)){
            Snackbar.make(view,"Email Sent Successfully !",Snackbar.LENGTH_LONG)
                    .setAction("",null).show();
        } else {
            Snackbar.make(view,"Failed to send Email !",Snackbar.LENGTH_LONG)
                    .setAction("RESEND", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onSendClick(view);
                        }
                    }).show();
        }
    }


    private boolean sendMail(String to[], String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setMobileDataState(boolean mobileDataEnabled)
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
            }
        }
        catch (Exception ex)
        {
            Log.e("ERROR", "Error setting mobile data state", ex);
        }
    }

    public boolean getMobileDataState()
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

            if (null != getMobileDataEnabledMethod)
            {
                boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

                return mobileDataEnabled;
            }
        }
        catch (Exception ex)
        {
            Log.e("ERROR", "Error getting mobile data state", ex);
        }

        return false;
    }
}
