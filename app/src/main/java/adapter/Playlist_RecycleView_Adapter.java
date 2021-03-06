package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.krossovochkin.bottomsheetmenu.BottomSheetMenu;

import java.util.List;

import ModalClass.ListModalClass;
import ModalClass.PlaylistModalClass;
import creativeuiux.musicapp.DiscoverActivity;
import creativeuiux.musicapp.MusicPlayerActivity;
import creativeuiux.musicapp.R;
import creativeuiux.musicapp.Utils;

import static creativeuiux.musicapp.DiscoverActivity.playlists;
import static creativeuiux.musicapp.DiscoverActivity.recentlist;


public class Playlist_RecycleView_Adapter extends RecyclerView.Adapter<Playlist_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<ListModalClass> playlistModalClassList;




public Playlist_RecycleView_Adapter(Context mainActivityContacts, List<ListModalClass> listModalClassList) {
        this.playlistModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_album_list, parent, false);



        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
        final ListModalClass modalClass = playlistModalClassList.get(position);
        holder.name1.setText(modalClass.getTitle());
        holder.name2.setText(modalClass.getArtist());

    Utils utils= new Utils();

    holder.duration.setText(utils.milliSecondsToTimer(Long.parseLong(modalClass.getDuration())));
    Glide
            .with(context)
            .load(modalClass.getImageurl())
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.image);

    holder.lysong.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new BottomSheetMenu.Builder(context, new BottomSheetMenu.BottomSheetMenuListener() {
                @Override
                public void onCreateBottomSheetMenu(MenuInflater inflater, Menu menu) {
                    inflater.inflate(R.menu.menuplaylist, menu);

                }

                @Override
                public void onBottomSheetMenuItemSelected(MenuItem item) {
                    final int itemId = item.getItemId();
                    switch (itemId) {
                        case R.id.play:

                            Intent intent = new Intent(context, MusicPlayerActivity.class);
                            intent.putExtra("id",modalClass.getId());
                            intent.putExtra("title",modalClass.getTitle());
                            intent.putExtra("artist",modalClass.getArtist());
                            intent.putExtra("duration",modalClass.getDuration());
                            intent.putExtra("imgurl",modalClass.getImageurl());
                            context.startActivities(new Intent[]{intent});

                            recentlist.add(modalClass);


                            if (context instanceof DiscoverActivity) {
                                ((DiscoverActivity)context).updatelist();
                            }



                            break;


                    }

                }
            }).show();

        }
    });


        }

@Override
public int getItemCount() {
        return playlistModalClassList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name1,name2,duration;
    LinearLayout lysong;


    public MyViewHolder(View view) {
        super(view);


        image = (ImageView) view.findViewById(R.id.imageart);
        name1 = (TextView) view.findViewById(R.id.name1);
        name2=view.findViewById(R.id.name2);
        duration=view.findViewById(R.id.txtduration);
        lysong=view.findViewById(R.id.lysong);


    }

}
}
