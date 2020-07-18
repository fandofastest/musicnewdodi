package creativeuiux.musicapp;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ModalClass.AlbumListModalClass;
import adapter.AlbumList_RecycleView_Adapter;

public class AlbumListActivity extends AppCompatActivity implements View.OnClickListener {


    public final static int LOOPS = 1000;
    public CarouselPagerAdapter adapter;
    public ViewPager pager;
    public static int count = 10; //ViewPager items size
    /**
     * You shouldn't define first page = 0.
     * Let define firstpage = 'number viewpager size' to make endless carousel
     */
    public static int FIRST_PAGE = 10;


    RecyclerView albumList_recycleview;

    AlbumList_RecycleView_Adapter mAlbumList_Adapter;

    private ArrayList<AlbumListModalClass> albumListModalClassArrayList;

    TextView follow,playall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_album_list);

        pager = (ViewPager) findViewById(R.id.myviewpager);
        albumList_recycleview = (RecyclerView)findViewById(R.id.albumList_recycleview);

        follow = (TextView)findViewById(R.id.follow);
        playall = (TextView)findViewById(R.id.playall);

        follow.setOnClickListener(this);
        playall.setOnClickListener(this);

        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 5) * 2);
        pager.setPageMargin(-pageMargin);

        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(3);







    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.follow:

                follow.setBackgroundResource(R.drawable.fill_roundshape);
                playall.setBackgroundResource(R.drawable.border_roundshape);
                follow.setTextColor(getResources().getColor(R.color.white));
                playall.setTextColor(getResources().getColor(R.color.pink));

                break;

            case R.id.playall:

                playall.setBackgroundResource(R.drawable.fill_roundshape);
                follow.setBackgroundResource(R.drawable.border_roundshape);
                follow.setTextColor(getResources().getColor(R.color.pink));
                playall.setTextColor(getResources().getColor(R.color.white));
                break;

        }

    }
}