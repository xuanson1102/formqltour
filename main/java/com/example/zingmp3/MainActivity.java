package com.example.zingmp3;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    TextView txtSong, txtTime, txtAll;
    SeekBar skSong;
    ImageView imgView;
    ImageButton btnBack, btnNext, btnPlay, btnStop;
    ArrayList<Song> songs;
    int postition =0;
    MediaPlayer mediaPlayer;
    Animation animaTion;
    ListView lvSong;
    ListviewAdapter listviewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }
    private void AddSong() {
        songs = new ArrayList<>();
        songs.add(new Song("Anh ghét làm bạn em", R.raw.anhghetlambanem));
        songs.add(new Song("Buồn làm chi em ơi", R.raw.buonlamchiemoi));
        songs.add(new Song("Nghĩ lại", R.raw.nghilai));
        songs.add(new Song("Nhạt", R.raw.nhat));
        songs.add(new Song("Vợ người ta", R.raw.vonguoita));
    }

    private void setEvent() {
        animaTion = AnimationUtils.loadAnimation(this,R.anim.xoay);
        //Listview
        AddSong();
        listviewAdapter = new ListviewAdapter(MainActivity.this, R.layout.song, songs);
        lvSong.setAdapter(listviewAdapter);
        listviewAdapter.setOnItemlickListener(new OnItemlickListener() {
            @Override
            public void OnClick(Song song, int position) {
                postition = position;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this,songs.get(position).getFile());
                    txtSong.setText(songs.get(position).getName());
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                SetTime();
                UpdateTime();
                imgView.startAnimation(animaTion);
            }
        });

        //Ket thuc listview
        MediaPlay();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
                else
                {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);

                }
                SetTime();
                UpdateTime();
                imgView.startAnimation(animaTion);

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                MediaPlay();

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postition++;
                if(postition > songs.size()-1)
                {
                    postition =0;
                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                MediaPlay();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                SetTime();
                UpdateTime();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postition--;
                if(postition < 0)
                {
                    postition = songs.size()-1;
                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                MediaPlay();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                SetTime();
                UpdateTime();
            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());

            }
        });

    }
    private  void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTime.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        postition++;
                        if(postition > songs.size()-1)
                        {
                            postition =0;
                        }
                        if(mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                        MediaPlay();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        SetTime();
                        UpdateTime();
                    }
                });
                handler.postDelayed(this, 500);
            }
        },100);
    }

    private void SetTime()
    {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtAll.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        //gan max skSon = mediaPlayer.getDuration()
        skSong.setMax(mediaPlayer.getDuration());


    }

    private void MediaPlay() {
       mediaPlayer = MediaPlayer.create(MainActivity.this,songs.get(postition).getFile());
       txtSong.setText(songs.get(postition).getName());
    }



    private void setControl() {
        lvSong = findViewById(R.id.lvSong);
        txtSong = findViewById(R.id.txtSong);
        txtTime = findViewById(R.id.txtTime);
        txtAll = findViewById(R.id.txtAll);
        skSong = findViewById(R.id.sbSong);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        imgView = findViewById(R.id.imgView);
    }
}
