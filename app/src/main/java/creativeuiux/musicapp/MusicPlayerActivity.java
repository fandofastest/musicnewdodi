package creativeuiux.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import ModalClass.ListModalClass;
import ModalClass.OfflineModalClass;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;
import static creativeuiux.musicapp.DiscoverActivity.listlocal;
import static creativeuiux.musicapp.DiscoverFragment.listdiscover;

public class MusicPlayerActivity extends AppCompatActivity  {
    ImageView btnplay,btnstop,prev,next,loopingon,loopingoff,randomb;
    ImageView imageView;
    TextView txttitle,txtartis,txtdura,txttotaldura;
    ProgressBar progressBar;
    SeekBar seekBar;
    private Handler mHandler = new Handler();
     String type;
    int pos;
    boolean statusloop =false;

    String totaldura,currentdura,id,title,artis,imgurl,duration,path;
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
        txtdura=findViewById(R.id.currendura);
        txttotaldura=findViewById(R.id.totaldura);
        progressBar=findViewById(R.id.progressbar);
        seekBar=findViewById(R.id.seekbar);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        loopingon=findViewById(R.id.loopingbutton);
        loopingoff=findViewById(R.id.loopingbuttoff);
        randomb =findViewById(R.id.randombutton);

        loopingon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusloop=false;
                loopingoff.setVisibility(View.VISIBLE);
                loopingon.setVisibility(View.GONE);
            }
        });

        loopingoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusloop=true;
                loopingoff.setVisibility(View.GONE);
                loopingon.setVisibility(View.VISIBLE);
            }
        });




        seekBar.setProgress(0);

        seekBar.setMax(Utils.MAX_PROGRESS);

        type= getIntent().getStringExtra("type");
        if (type.equals("online")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            artis = getIntent().getStringExtra("artist");
            imgurl = getIntent().getStringExtra("imgurl");
            duration=getIntent().getStringExtra("duration");
            pos=getIntent().getIntExtra("pos",0);
            Utils utils= new Utils();

            Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);
            txttotaldura.setText(utils.milliSecondsToTimer(Long.parseLong(duration)));
            txtartis.setText(artis);
            txttitle.setText(title);




            playmusic(id);


        }else {

            title = getIntent().getStringExtra("title");
            path=getIntent().getStringExtra("path");
            pos=getIntent().getIntExtra("pos",0);

            playmusicoffline(path);

            txtartis.setText("Offline");
            txttitle.setText(title);


        }






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

        prev.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                if (pos>0){



                System.out.println("tombol "+btnstop.getVisibility());
                System.out.println("tombol1 "+btnplay.getVisibility());
                if (btnplay.getVisibility()==0){
                    btnplay.setVisibility(View.GONE);

                }

                if (btnstop.getVisibility()==0){
                    btnstop.setVisibility(View.GONE);




                }
                Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);

                txtartis.setText(artis);
                txttitle.setText(title);
                progressBar.setVisibility(View.VISIBLE);


                mp.release();
                if (type.equals("online")){
                     ListModalClass modalClass = listdiscover.get(pos-1);
                    artis=modalClass.getArtist();
                    title=modalClass.getTitle();
                    imgurl=modalClass.getImageurl();
                    duration=modalClass.getDuration();
                    id=modalClass.getId();
                    id=modalClass.getId();
                    Utils utils= new Utils();
                    txttotaldura.setText(utils.milliSecondsToTimer(Long.parseLong(duration)));
                }
                else {

                    final OfflineModalClass modalClass = listlocal.get(pos-1);

                    title=modalClass.getFilename();
                    path=modalClass.getFilepath();

                    playmusicoffline(path);
                    String duration=String.valueOf(mp.getDuration());
                    txttotaldura.setText(duration);
                    System.out.println("poss" +pos);



                }








                Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);
                txtartis.setText(artis);
                txttitle.setText(title);
                progressBar.setVisibility(View.VISIBLE);







                System.out.println("play " +id);


                pos=pos-1;


            }
                else {

                    new SweetAlertDialog(MusicPlayerActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("This is Last Song")
                            .show();
                }



            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (pos < 100) {
                    if (btnplay.getVisibility() == 0) {
                        btnplay.setVisibility(View.GONE);

                    }

                    if (btnstop.getVisibility() == 0) {
                        btnstop.setVisibility(View.GONE);

                    }
                    Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);

                    txtartis.setText(artis);
                    txttitle.setText(title);
                    progressBar.setVisibility(View.VISIBLE);


                    mp.release();
                    if (type.equals("online")) {
                        final ListModalClass modalClass = listdiscover.get(pos + 1);
                        artis = modalClass.getArtist();
                        title = modalClass.getTitle();
                        imgurl = modalClass.getImageurl();
                        duration = modalClass.getDuration();
                        id = modalClass.getId();
                        Utils utils = new Utils();
                        txttotaldura.setText(utils.milliSecondsToTimer(Long.parseLong(duration)));
                        playmusic(id);
                    } else {

                        OfflineModalClass modalClass = listlocal.get(pos + 1);

                        title = modalClass.getFilename();
                        path = modalClass.getFilepath();

                        playmusicoffline(path);
                        String duration = String.valueOf(mp.getDuration());
                        txttotaldura.setText(duration);
                        System.out.println("poss" +pos);


                    }


                    System.out.println("play " + id);


                    pos = pos + 1;


                }
                else {

                    new SweetAlertDialog(MusicPlayerActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("This is Last Song")
                            .show();
                }

            }

        });

    }

    @SuppressLint("WrongConstant")
    public void next(){
        if (pos < 100) {
            if (btnplay.getVisibility() == 0) {
                btnplay.setVisibility(View.GONE);

            }

            if (btnstop.getVisibility() == 0) {
                btnstop.setVisibility(View.GONE);

            }
            Glide.with(getApplicationContext()).load(imgurl).error(R.drawable.play).into(imageView);

            txtartis.setText(artis);
            txttitle.setText(title);
            progressBar.setVisibility(View.VISIBLE);


            mp.release();
            if (type.equals("online")) {
                final ListModalClass modalClass = listdiscover.get(pos + 1);
                artis = modalClass.getArtist();
                title = modalClass.getTitle();
                imgurl = modalClass.getImageurl();
                duration = modalClass.getDuration();
                id = modalClass.getId();
                Utils utils = new Utils();
                txttotaldura.setText(utils.milliSecondsToTimer(Long.parseLong(duration)));
                playmusic(id);
            } else {

                 OfflineModalClass modalClass = listlocal.get(pos + 1);

                title = modalClass.getFilename();
                path = modalClass.getFilepath();

                playmusicoffline(path);
                String duration = String.valueOf(mp.getDuration());
                txttotaldura.setText(duration);


            }



//            pos = pos + 1;
            System.out.println("poss" +pos);


        }
        else {

            new SweetAlertDialog(MusicPlayerActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("This is Last Song")
                    .show();
        }
    }


    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            updateTimerAndSeekbar();
            // Running this thread after 10 milliseconds
            if (mp.isPlaying()) {
                mHandler.postDelayed(this, 100);
            }
        }
    };

    private void updateTimerAndSeekbar() {
        long totalDuration = mp.getDuration();
        long currentDuration = mp.getCurrentPosition();

        // Displaying Total Duration time
        Utils utils = new Utils();
        txttotaldura.setText(utils.milliSecondsToTimer(totalDuration));
        // Displaying time completed playing
        txtdura.setText(utils.milliSecondsToTimer(currentDuration));

        // Updating progress bar
        int progress = (int) (utils.getProgressSeekBar(currentDuration, totalDuration));
        seekBar.setProgress(progress);
    }

    public void playmusicoffline(String path){

        // Media PlayerActivity
        mp = new MediaPlayer();
        mp.stop();
        mp.reset();
        mp.release();




        try {
            Uri myUri = Uri.parse(path);
            mp = new MediaPlayer();
            mp.setDataSource(this, myUri);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setLooping(statusloop);
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
                    mHandler.post(mUpdateTimeTask);
                    mp.start();
                    btnplay.setVisibility(View.GONE);
                    btnstop.setVisibility(View.VISIBLE);

                }

            }
        });

        mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {


            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnplay.setVisibility(View.VISIBLE);
                btnstop.setVisibility(View.GONE);

//                next();


            }
        });
    }


    public void playmusic(String id){
        final SweetAlertDialog pDialog = new SweetAlertDialog(MusicPlayerActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Please Wait, Need More Times");
        pDialog.setCancelable(false);



        // Media PlayerActivity
        mp = new MediaPlayer();
        mp.stop();
        mp.reset();
        mp.release();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
               mp.setLooping(statusloop);

            }
        });

        try {
            Uri myUri = Uri.parse(Konstanta.SERVERURL+id);
            mp = new MediaPlayer();
            mp.setDataSource(this, myUri);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setLooping(statusloop);
//            mp.prepareAsync(); //don't use prepareAsync for mp3 playback
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot load audio file", LENGTH_SHORT).show();

        }


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                    if (!mp.isPlaying()){
                        pDialog.show();
                    }

            }
        }.start();



        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onPrepared(MediaPlayer mplayer) {
                pDialog.hide();

                progressBar.setVisibility(View.GONE);

                if (mplayer.isPlaying()) {
                    mplayer.pause();
                    btnplay.setVisibility(View.VISIBLE);
                    btnstop.setVisibility(View.GONE);
                    // Changing button image to play button

                } else {
                    mHandler.post(mUpdateTimeTask);


                    mplayer.start();
                    btnplay.setVisibility(View.GONE);
                    btnstop.setVisibility(View.VISIBLE);

                }

            }
        });

        mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                System.out.println("buffers "+i);


            }
        });

        mp.prepareAsync();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mp.release();
    }










}
