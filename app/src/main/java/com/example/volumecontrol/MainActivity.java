package com.example.volumecontrol;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare Buttons
        Button muteBtn = findViewById(R.id.btnMute);
        Button unmuteBtn = findViewById(R.id.btnUnmute);

        // Declare an audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Set up the SeekBar to adjust volume
        SeekBar volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        muteBtn.setOnClickListener(v -> {
            // ADJUST_MUTE = Mutes the device, FLAG_SHOW_UI = show changes made to volume bar
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
        });

        unmuteBtn.setOnClickListener(v -> {
            // ADJUST_UNMUTE = Unmutes the device, FLAG_SHOW_UI = show changes made to volume bar
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
        });
    }
}
