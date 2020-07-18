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

import ModalClass.ArtistModalClass;
import creativeuiux.musicapp.R;


public class Artist_RecycleView_Adapter extends RecyclerView.Adapter<Artist_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<ArtistModalClass> artistModalClassList;




public Artist_RecycleView_Adapter(Context mainActivityContacts, List<ArtistModalClass> listModalClassList) {
        this.artistModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_favourite_artist, parent, false);



        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
        ArtistModalClass modalClass = artistModalClassList.get(position);
        holder.image.setImageResource(modalClass.getImage());
        holder.name.setText(modalClass.getName());
        }

@Override
public int getItemCount() {
        return artistModalClassList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name;


    public MyViewHolder(View view) {
        super(view);


        image = (ImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);


    }

}
}
