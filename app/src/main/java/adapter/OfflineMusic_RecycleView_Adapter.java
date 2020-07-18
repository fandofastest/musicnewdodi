package adapter;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ModalClass.OfflineModalClass;
import creativeuiux.musicapp.R;


public class OfflineMusic_RecycleView_Adapter extends RecyclerView.Adapter<OfflineMusic_RecycleView_Adapter.MyViewHolder> {

    Context context;
    private List<OfflineModalClass> offlineModalClassList;




public OfflineMusic_RecycleView_Adapter(Context mainActivityContacts, List<OfflineModalClass> listModalClassList) {
        this.offlineModalClassList = listModalClassList;
        this.context = mainActivityContacts;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_offline_music, parent, false);



        return new MyViewHolder(itemView);


        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onBindViewHolder(final MyViewHolder holder, final int position){
        OfflineModalClass modalClass = offlineModalClassList.get(position);
        holder.name1.setText(modalClass.getName1());
        holder.name2.setText(modalClass.getName2());
        }

@Override
public int getItemCount() {
        return offlineModalClassList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView name1,name2;


    public MyViewHolder(View view) {
        super(view);


        name1 = (TextView) view.findViewById(R.id.name1);
        name2 = (TextView) view.findViewById(R.id.name2);


    }

}
}
