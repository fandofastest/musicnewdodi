package creativeuiux.musicapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


import ModalClass.GenreModalClass;
import ModalClass.ListModalClass;

import ModalClass.OfflineModalClass;
import adapter.AlbumList_RecycleView_Adapter;
import adapter.TabAdapter;
import adapter.Treding_RecycleView_Adapter;

public class DiscoverActivity extends AppCompatActivity {

    RecyclerView genre_recycleview;
    RecyclerView topchart_recycleview;


    Treding_RecycleView_Adapter mTreding_Adapter;
    AlbumList_RecycleView_Adapter topchart_adapter;

    public  static  List <ListModalClass> recentlist = new ArrayList<>();
    public  static  List <ListModalClass> playlists = new ArrayList<>();

    private ArrayList<GenreModalClass> genreModalClassArrayList;


    private List<ListModalClass> listsongTopchart = new ArrayList<>();

    public static List<OfflineModalClass> listlocal = new ArrayList<>();

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public static final String PREFS_NAME = "MY_APP";
    public static final String PLAYLISTS = "playlists";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);



        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new DiscoverFragment(), "Discover");
        adapter.addFragment(new RecentFragment(), "Recent");
        adapter.addFragment(new LocalFragment(), "Local");
        adapter.addFragment(new PlaylistsFragment(), "Playlist");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
            } else {
            }
        } else {
        }


}


    public void updatelist(){
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {

        storePlaylists(DiscoverActivity.this,playlists);
        super.onBackPressed();

    }

    public void storePlaylists(Context context, List playlists) {
        // used for store arrayList in json format
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson sExposeGson = builder.create();
        String jsonFavorites = sExposeGson.toJson(playlists);
        editor.putString(PLAYLISTS, jsonFavorites);
        editor.apply();
    }


    public ArrayList loadPlaylists(Context context) {
        SharedPreferences settings;
        List favorites;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(PLAYLISTS)) {
            String jsonFavorites = settings.getString(PLAYLISTS, null);

            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
            builder.excludeFieldsWithoutExposeAnnotation();
            Gson sExposeGson = builder.create();
            ListModalClass[] favoriteItems = sExposeGson.fromJson(jsonFavorites, ListModalClass[].class);
            favorites = Arrays.asList(favoriteItems);
            playlists = new ArrayList(favorites);
        }
        return (ArrayList) playlists;
    }


}
