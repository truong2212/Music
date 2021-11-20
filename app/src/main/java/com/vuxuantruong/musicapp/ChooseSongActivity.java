package com.vuxuantruong.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseSongActivity extends AppCompatActivity {
    TextView txtCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 1);

        getView();

        txtCurrentIndex.setText("" + index);
    }

    private void getView() {
        txtCurrentIndex = findViewById(R.id.txtCurrentIndex);
    }

    public void onSelectedSong(View view) {
        Button button = (Button) view;
        txtCurrentIndex.setText(button.getText());
    }

    public void changeSong(View view) {
        int id = Integer.parseInt(txtCurrentIndex.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("idSelect", id);
        startActivity(intent);
        finish();
    }
}