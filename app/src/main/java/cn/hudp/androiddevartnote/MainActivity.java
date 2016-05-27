package cn.hudp.androiddevartnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.hudp.androiddevartnote.IPC_2.IPC2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnChapter1,btnChapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChapter1 = (Button) findViewById(R.id.btn_chapter1);
        btnChapter2 = (Button) findViewById(R.id.btn_chapter2);

        btnChapter1.setOnClickListener(this);
        btnChapter2.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chapter2:
                Intent intent = new Intent(this, IPC2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
