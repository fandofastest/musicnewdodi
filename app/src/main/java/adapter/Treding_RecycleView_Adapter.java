package adapter;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ModalClass.GenreModalClass;

import creativeuiux.musicapp.DiscoverActivity;
import creativeuiux.musicapp.DiscoverFragment;
import creativeuiux.musicapp.R;


public class Treding_RecycleView_Adapter extends RecyclerView.Adapter<Treding_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<GenreModalClass> tredingModalClassList;
    DiscoverFragment discoverFragment;



public Treding_RecycleView_Adapter(Context mainActivityContacts, List<GenreModalClass> listModalClassList, DiscoverFragment discoverFragment) {
        this.discoverFragment=discoverFragment;
        this.tredingModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_treding, parent, false);
        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
    final GenreModalClass modalClass = tredingModalClassList.get(position);
        holder.image.setImageResource(modalClass.getImage());
        holder.name1.setText(modalClass.getGenrename());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre =modalClass.getGenrename();
                String genrelower=genre.toLowerCase();
                String lineWithoutSpaces = genrelower.replaceAll("\\s+","");
                System.out.println("genrenew "+lineWithoutSpaces);
                discoverFragment.getgenrechart(lineWithoutSpaces);

            }
        });

        }


@Override
public int getItemCount() {
        return tredingModalClassList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name1;

    public MyViewHolder(View view) {
        super(view);
        image = (ImageView) view.findViewById(R.id.image);
        name1 = (TextView) view.findViewById(R.id.name1);



    }

}
}
