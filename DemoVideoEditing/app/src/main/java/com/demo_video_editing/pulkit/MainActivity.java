package com.demo_video_editing.pulkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo_video_editing.pulkit.video_editing.activities.VideoEditing;
import com.demo_video_editing.pulkit.video_effects.activities.VideoEffectsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_video_edit, btn_video_effects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findIds();
        init();

    }

    private void findIds() {

        btn_video_edit = findViewById(R.id.btn_video_edit);
        btn_video_effects = findViewById(R.id.btn_video_effects);
    }

    private void init() {

        btn_video_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoEditing.class);
                startActivity(intent);
            }
        });

        btn_video_effects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoEffectsActivity.class);
                startActivity(intent);
            }
        });
    }

}
