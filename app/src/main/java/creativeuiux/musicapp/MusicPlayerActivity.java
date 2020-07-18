package creativeuiux.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

public class MusicPlayerActivity extends AppCompatActivity  {
    ImageView btnplay,btnstop;
    ImageView imageView;
    TextView txttitle,txtartis,txtdura,txttotaldura;
    ProgressBar progressBar;

    String totaldura,currentdura,id,title,artis,imgurl,duration;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btnplay=findViewById(R.id.butplay);
        btnstop=findViewById(R.id.butpause);
        imageView=findViewById(R.id.image);
        txttitle=findViewById(R.id.txttitle);
        txtartis=findViewById(R.id.txtartist);
        txtdura=findViewById(R.id.totaldura);
        txttotaldura=findViewById(R.id.currendura);
        progressBar=findViewById(R.id.progressbar);


        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        artis = getIntent().getStringExtra("artist");
        imgurl = getIntent().getStringExtra("imgurl");
        duration=getIntent().getStringExtra("duration");
        Utils utils= new Utils();

        Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);
        txtdura.setText(utils.milliSecondsToTimer(Long.parseLong(duration)));
        txtartis.setText(artis);
        txttitle.setText(title);


        playmusic(id);



        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                btnplay.setVisibility(View.GONE);
                btnstop.setVisibility(View.VISIBLE);
            }
        });


        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                btnplay.setVisibility(View.VISIBLE);
                btnstop.setVisibility(View.GONE);
            }
        });


    }



    public void playmusic(String id){

        // Media PlayerActivity
        mp = new MediaPlayer();
        mp.stop();
        mp.reset();
        mp.release();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Changing button image to play button
//                bt_play.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            }
        });

        try {
            Uri myUri = Uri.parse(Konstanta.SERVERURL+id);
            mp = new MediaPlayer();
            mp.setDataSource(this, myUri);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.prepareAsync(); //don't use prepareAsync for mp3 playback
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot load audio file", LENGTH_SHORT).show();

        }

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onPrepared(MediaPlayer mplayer) {
                progressBar.setVisibility(View.GONE);

                if (mp.isPlaying()) {
                    mp.pause();
                    btnplay.setVisibility(View.VISIBLE);
                    btnstop.setVisibility(View.GONE);
                    // Changing button image to play button

                } else {
                    // Resume song
//                    tvartist.setText("artist");
//                    tvtitle.setText(title);
//                    progressBar.setVisibility(View.GONE);
//                    img_pause.setVisibility(View.VISIBLE);
//                    img_play.setVisibility(View.GONE);
                    mp.start();
                    btnplay.setVisibility(View.GONE);
                    btnstop.setVisibility(View.VISIBLE);
                    // Changing button image to pause button
//                    mHandler.post(mUpdateTimeTask);
                }

            }
        });

        mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {


            }
        });
    }








}
