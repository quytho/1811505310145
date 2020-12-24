package com.example.baithithuchanh;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    public ImageView back, next, play, stop;
    public TextView name;
    ArrayList<Song> arraySong;
    public MediaPlayer mediaPlayer;
    int position = 0;
    CircleImageView hinhnhac;
    Animation animationRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        addSong();

        //nut back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position --;
                if(position < 0){
                    position = arraySong.size()-1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMedia();
                mediaPlayer.start();
                play.setImageResource(R.drawable.ic_baseline_pause_24);
                quay();
            }
        });

        //nút next
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position ++;
                if(position > arraySong.size()-1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                khoiTaoMedia();
                mediaPlayer.start();
                play.setImageResource(R.drawable.ic_baseline_pause_24);
                quay();
            }
        });
        khoiTaoMedia();
        //nút play
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_baseline_play_arrow_24);


                }
                else{
                    mediaPlayer .start();
                    play.setImageResource(R.drawable.ic_baseline_pause_24);
                    quay();
                }
            }
        });
        // nut stop
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play.setImageResource(R.drawable.ic_baseline_play_arrow_24);


            }
        });


    }

    //hiệu ứng quay đĩa nhạc
    private void quay(){
        final CircleImageView hinhnhac = (CircleImageView) findViewById(R.id.hinhnhac);
        final Animation animationRotate = AnimationUtils.loadAnimation(this, R.anim.anim_quay);
        hinhnhac.startAnimation(animationRotate);

    }
    //ngừng hiệu ứng quay

    //ánh xạ các phần tử
    private void anhxa(){
        //về sau
        back = findViewById(R.id.back);
        //tới
        next = findViewById(R.id.next);
        //chơi nhạc
        play = findViewById(R.id.play);
        //ten bài hát
        name = findViewById(R.id.namebh);
        //dừng bài hát
        stop = findViewById(R.id.stop);
    }
    //thêm bài hát vào mảng
    private void addSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Lộn Xộn",R.raw.den1 ));
        arraySong.add(new Song("Ta và Nàng",R.raw.den2 ));
        arraySong.add(new Song("KHông Phải Dạng Vừa Đâu",R.raw.stung ));
        arraySong.add(new Song("Em Của Ngày Hôm Qua",R.raw.stung2 ));
    }

    // khởi tạo media để phát nhạc + tên bài hát
    private void khoiTaoMedia(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        name.setText(arraySong.get(position).getTitle());
    }

}