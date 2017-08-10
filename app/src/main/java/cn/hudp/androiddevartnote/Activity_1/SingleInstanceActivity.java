package cn.hudp.androiddevartnote.Activity_1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.hudp.androiddevartnote.R;

public class SingleInstanceActivity extends AppCompatActivity {
    private static final String TAG = "SingleInstanceActivity";
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInstanceActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
        });

    }

}

