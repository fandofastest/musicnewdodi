package creativeuiux.musicapp;

import android.content.Context;
import android.graphics.Shader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import ModalClass.GenreModalClass;
import ModalClass.ListModalClass;

import adapter.AlbumList_RecycleView_Adapter;
import adapter.TabAdapter;
import adapter.Treding_RecycleView_Adapter;

public class DiscoverActivity extends AppCompatActivity {

    RecyclerView genre_recycleview;
    RecyclerView topchart_recycleview;


    Treding_RecycleView_Adapter mTreding_Adapter;
    AlbumList_RecycleView_Adapter topchart_adapter;

    public  static  List <ListModalClass> recentlist = new ArrayList<>();


    private ArrayList<GenreModalClass> genreModalClassArrayList;


    private List<ListModalClass> listsongTopchart = new ArrayList<>();


    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int mWidth;
    private int mHeight;
    private Random mRandom = new Random();
    private Shader shader;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new DiscoverFragment(), "Discover");
        adapter.addFragment(new RecentFragment(), "Recent");
        adapter.addFragment(new DiscoverFragment(), "Local");
        adapter.addFragment(new DiscoverFragment(), "Playlist");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


}


    public void updatelist(){
        adapter.notifyDataSetChanged();
    }







}
