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

import ModalClass.ChartModalClass;
import creativeuiux.musicapp.R;


public class Charts_RecycleView_Adapter extends RecyclerView.Adapter<Charts_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<ChartModalClass> chartModalClassList;




public Charts_RecycleView_Adapter(Context mainActivityContacts, List<ChartModalClass> listModalClassList) {
        this.chartModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_charts, parent, false);



        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
        ChartModalClass modalClass = chartModalClassList.get(position);
        holder.image.setImageResource(modalClass.getImage());
        holder.name.setText(modalClass.getName());
        }

@Override
public int getItemCount() {
        return chartModalClassList.size();
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
