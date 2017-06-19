package com.example.shchaura.sugamyatra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.id.closeButton;

public class Index extends AppCompatActivity {
    int temp = 5;
    int hot = 1;
    int pp3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        int checkVariable = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Button closButton = (Button) this.findViewById(R.id.listtrains);
        closButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Index.this, FormFill.class);
                Index.this.startActivity(intent);
                finish();
            }
        });
    }


}