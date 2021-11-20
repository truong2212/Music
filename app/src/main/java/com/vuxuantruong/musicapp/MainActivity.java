package com.vuxuantruong.musicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button btnChonBai, btnPlay, btnPause;
    TextView txtIndex, txtSongName, txtTime;
    MediaPlayer player;
    List<Song> songs;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListSong();
        getView();

        btnChonBai.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChooseSongActivity.class);
            intent.putExtra("index", getCurrentSong().getId());
            if (player != null && player.isPlaying())
                player.stop();
            startActivity(intent);
        });

        btnPlay.setOnClickListener(v -> {
                player.start();
        });

        btnPause.setOnClickListener(v -> {
            if (player != null)
                player.pause();
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("idSelect", 1);
        updateView(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updateView(int id) {
        txtIndex.setText("Bài " + id);
        txtSongName.setText(getCurrentSong().getName());
        player = MediaPlayer.create(MainActivity.this, getCurrentSong().getResourceId());
        int duration = player.getDuration();

        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long sec = TimeUnit.MILLISECONDS.toMillis(duration);
        sec = sec % 60;

        txtTime.setText(minutes + "minus, " + sec + " sec");
        //long remainMinutes = min - TimeUnit.HOURS.toMinutes(hours);
    }

    private Song getCurrentSong() {
        String sIndex = txtIndex.getText().toString().split(" ")[1].toString();
        Integer index = Integer.parseInt(sIndex);
        return songs.get(index-1);
    }

    private void initListSong() {
        songs = new ArrayList<>();
        songs.add(new Song(1, "Bai hat 1", R.raw.bai1));
        songs.add(new Song(2, "Bai hat 2", R.raw.bai2));
        songs.add(new Song(3, "Bai hat 3", R.raw.bai3));
        songs.add(new Song(4, "Bai hat 4", R.raw.bai4));
        songs.add(new Song(5, "Bai hat 5", R.raw.bai5));
        songs.add(new Song(6, "Bai hat 6", R.raw.bai6));
    }

    private void getView() {
        btnChonBai = findViewById(R.id.btnChonBai);
        btnPause = findViewById(R.id.btnPause);
        btnPlay = findViewById(R.id.btnPlay);
        txtIndex = findViewById(R.id.txtIndex);
        txtSongName = findViewById(R.id.txtSongName);
        txtTime = findViewById(R.id.txtTime);
    }


}