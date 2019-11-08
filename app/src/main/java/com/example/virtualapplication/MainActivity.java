package com.example.virtualapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            public void onClick(View v) {
                ContentProviderClient client = callProvider();
                try {
                    Bundle  ret = client.call("update",null,null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public ContentProviderClient callProvider(){
        return getApplication().getContentResolver().acquireContentProviderClient("com.jay.example.providers.myprovider");

    }
}
