package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ModalClass.ListModalClass;
import creativeuiux.musicapp.AlbumListActivity;
import creativeuiux.musicapp.DiscoverActivity;
import creativeuiux.musicapp.MusicPlayerActivity;
import creativeuiux.musicapp.MyMusicActivity;
import creativeuiux.musicapp.R;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_List extends RecyclerView.Adapter<RecycleAdapter_List.MyViewHolder> {


    Context context;
    private List<ListModalClass> listModalClassList;




    public RecycleAdapter_List(Context mainActivityContacts, List<ListModalClass> listModalClassList) {
        this.listModalClassList = listModalClassList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);



        return new MyViewHolder(itemView);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ListModalClass movie = listModalClassList.get(position);
        holder.name.setText(movie.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0)
                {
                    Intent i = new Intent(context, DiscoverActivity.class);
                    context.startActivity(i);
                }else if (position == 1)
                {
                    Intent i = new Intent(context, MyMusicActivity.class);
                    context.startActivity(i);
                }
                else if (position == 2)
                {
                    Intent i = new Intent(context, AlbumListActivity.class);
                    context.startActivity(i);
                }
                else if (position == 3)
                {
                    Intent i = new Intent(context, MusicPlayerActivity.class);
                    context.startActivity(i);
                }


               /* else if (position == 2)
                {
                    Intent i = new Intent(context, SignInActivity_3.class);
                    context.startActivity(i);
                }
                else if (position == 3)
                {
                    Intent i = new Intent(context, SignInActivity_4.class);
                    context.startActivity(i);
                }
                else if (position == 4)
                {
                    Intent i = new Intent(context, SignInActivity_5.class);
                    context.startActivity(i);
                }*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return listModalClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView name;


        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.txt);

        }

    }






}


