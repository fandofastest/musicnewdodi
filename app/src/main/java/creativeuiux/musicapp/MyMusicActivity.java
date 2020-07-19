package creativeuiux.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ModalClass.ArtistModalClass;
import ModalClass.OfflineModalClass;
import ModalClass.PlaylistModalClass;
import adapter.Artist_RecycleView_Adapter;
import adapter.OfflineMusic_RecycleView_Adapter;
import adapter.Playlist_RecycleView_Adapter;

public class MyMusicActivity extends AppCompatActivity {

    RecyclerView offline_recycleview,artist_recycleview,playlist_recycleview;

    OfflineMusic_RecycleView_Adapter mOffline_Adapter;
    Artist_RecycleView_Adapter mArtist_Adapter;
    Playlist_RecycleView_Adapter mPlaylist_Adapter;

    private ArrayList<OfflineModalClass> offlineModalClassArrayList;
    private ArrayList<ArtistModalClass> artistModalClassArrayList;
    private ArrayList<PlaylistModalClass> playlistModalClassArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);


        offline_recycleview = (RecyclerView) findViewById(R.id.offline_recycleview);
        artist_recycleview = (RecyclerView) findViewById(R.id.artist_recycleview);
        playlist_recycleview = (RecyclerView) findViewById(R.id.playlist_recycleview);



        // Offline Music List
        offlineModalClassArrayList = new ArrayList<>();
        offlineModalClassArrayList.add(new OfflineModalClass("All offline songs","384 Songs"));
        offlineModalClassArrayList.add(new OfflineModalClass("Downloaded songs","121 Songs"));
        offlineModalClassArrayList.add(new OfflineModalClass("Local MP3 songs","36 Songs"));


        mOffline_Adapter = new OfflineMusic_RecycleView_Adapter(MyMusicActivity.this,offlineModalClassArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MyMusicActivity.this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        offline_recycleview.setLayoutManager(layoutManager);
        offline_recycleview.setAdapter(mOffline_Adapter);



        // Atrist  List
        artistModalClassArrayList = new ArrayList<>();
        artistModalClassArrayList.add(new ArtistModalClass(R.drawable.artist1,"Selena Gomez"));
        artistModalClassArrayList.add(new ArtistModalClass(R.drawable.artist2,"Badshah"));
        artistModalClassArrayList.add(new ArtistModalClass(R.drawable.artist3,"A R Rehman"));
        artistModalClassArrayList.add(new ArtistModalClass(R.drawable.artist1,"Shreya Goshal"));
        artistModalClassArrayList.add(new ArtistModalClass(R.drawable.artist3,"Selena Gomez"));



        mArtist_Adapter = new Artist_RecycleView_Adapter(MyMusicActivity.this,artistModalClassArrayList);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MyMusicActivity.this);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        artist_recycleview.setLayoutManager(layoutManager1);
        artist_recycleview.setAdapter(mArtist_Adapter);




        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(MyMusicActivity.this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        playlist_recycleview.setLayoutManager(layoutManager2);
        playlist_recycleview.setAdapter(mPlaylist_Adapter);


    }
}
