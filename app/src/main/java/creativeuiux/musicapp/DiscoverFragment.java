package creativeuiux.musicapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ModalClass.GenreModalClass;
import ModalClass.ListModalClass;
import adapter.AlbumList_RecycleView_Adapter;
import adapter.Treding_RecycleView_Adapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    RecyclerView genre_recycleview;
    RecyclerView topchart_recycleview;
    Context   context;


    Treding_RecycleView_Adapter mTreding_Adapter;
    AlbumList_RecycleView_Adapter topchart_adapter;




    ArrayList<GenreModalClass> genreModalClassArrayList;


    List<ListModalClass> listsongTopchart = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        genre_recycleview = view.findViewById(R.id.treding_recycleview);
        topchart_recycleview=view.findViewById(R.id.top_chart);
        context=getContext();
        //Treding List
        genreModalClassArrayList = new ArrayList<>();
        genreModalClassArrayList.add(new GenreModalClass(R.drawable.treding_img1,"Alternative Rock"));
        genreModalClassArrayList.add(new GenreModalClass(R.drawable.treding_img2,"Ambient"));
        genreModalClassArrayList.add(new GenreModalClass(R.drawable.treding_img3,"Audiobooks"));
        genreModalClassArrayList.add(new GenreModalClass(R.drawable.treding_img1,"Business"));
        mTreding_Adapter = new Treding_RecycleView_Adapter(context,genreModalClassArrayList);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        genre_recycleview.setLayoutManager(layoutManager2);
        genre_recycleview.setAdapter(mTreding_Adapter);
        String customFont = "fonts/Poppins-SemiBold.ttf";
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), customFont);
        TextView textView2 = view. findViewById(R.id.treding_singles);
        textView2.setTypeface(typeface);
        topchart_adapter = new AlbumList_RecycleView_Adapter(context,listsongTopchart);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        topchart_recycleview.setLayoutManager(layoutManager1);
        topchart_recycleview.setAdapter(topchart_adapter);
        gettopchart();







    }

    public void gettopchart(){



        String url="https://api-v2.soundcloud.com/charts?charts-top:all-music&&high_tier_only=false&kind=top&limit=100&client_id=z7xDdzwjM6kB7fmXCd06c8kU6lFNtBCT";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//                linearLayout.setVisibility(View.GONE);
//                System.out.println(response);


                try {
                    JSONArray jsonArray1=response.getJSONArray("collection");

                    for (int i = 0;i<jsonArray1.length();i++){
                        JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                        JSONObject jsonObject=jsonObject1.getJSONObject("track");
                        ListModalClass listModalClass = new ListModalClass();
                        listModalClass.setId(jsonObject.getString("id"));
                        listModalClass.setTitle(jsonObject.getString("title"));
                        listModalClass.setImageurl(jsonObject.getString("artwork_url"));
                        listModalClass.setDuration(jsonObject.getString("full_duration"));


                        JSONObject   jsonArray3=jsonObject.getJSONObject("publisher_metadata");
                        listModalClass.setArtist(jsonArray3.getString("artist"));
                        System.out.println(jsonArray3);


                        listsongTopchart.add(listModalClass);
//



//                        Toast.makeText(getActivity(),id,Toast.LENGTH_LONG).show();


                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }
                topchart_adapter.notifyDataSetChanged();
//                songAdapter.notifyDataSetChanged();
                //    System.out.println("update"+listsongModalSearch);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(context).add(jsonObjectRequest);


    }
}
