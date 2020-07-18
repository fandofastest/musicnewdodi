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

import ModalClass.AlbumModalClass;
import creativeuiux.musicapp.R;


public class Albums_RecycleView_Adapter extends RecyclerView.Adapter<Albums_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<AlbumModalClass> albumModalClassList;




public Albums_RecycleView_Adapter(Context mainActivityContacts, List<AlbumModalClass> listModalClassList) {
        this.albumModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_albums, parent, false);



        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
        AlbumModalClass modalClass = albumModalClassList.get(position);
        holder.image.setImageResource(modalClass.getImage());
        holder.name1.setText(modalClass.getName1());
        holder.name2.setText(modalClass.getName2());
        }

@Override
public int getItemCount() {
        return albumModalClassList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name1,name2;


    public MyViewHolder(View view) {
        super(view);


        image = (ImageView) view.findViewById(R.id.image);
        name1 = (TextView) view.findViewById(R.id.name1);
        name2 = (TextView) view.findViewById(R.id.name2);


    }

}
}
