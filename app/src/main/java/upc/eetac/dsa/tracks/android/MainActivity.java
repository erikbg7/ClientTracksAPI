package upc.eetac.dsa.tracks.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NewTrkDialog.OnDialogListener {

    private final String BaseURL = "http://147.83.7.207:8080/dsaApp/";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Track> trackList = new ArrayList<>();
    private List<String> input = new ArrayList<>();

    @Override
    public void OnPositiveButtonClicked() {

    }

    @Override
    public void OnNegativeButtonClicked() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackAPI trackAPI = retrofit.create(TrackAPI.class);
        Call<List<Track>> callTrackList = trackAPI.getTracks();
        callTrackList.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if(response.isSuccessful()){
                    trackList = response.body();
                    Log.d("QuestionsCallback", "//////////////////////////////////  SUCCES!:  we have  " +trackList.size() + "  tracks  ///////////////////////////////");

                    for (int i = 0; i < trackList.size(); i++){
                        Log.d("QuestionsCallback", "//////////////////////////////////  Nombre del cantante 3:" +trackList.get(i).getSinger()+ "///////////////////////////////");
                    }
                    iniciarControles();
                    mAdapter = new MyAdapter(trackList);
                    recyclerView.setAdapter(mAdapter);
                }
                else
                    Log.d("QuestionsCallback", "////////////////////////////////////   ERROR   /////////////////////////////////////");
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.d("QuestionsCallback", "////////////////////////////////////////   ERROR  /////////////////////////////////");
                t.printStackTrace();
            }
        });

    }



    // Acción del button para abrir un dialogo, permite añadir un nuevo track
    public void onButton1Clicked(View view){
        DialogFragment df = new NewTrkDialog();
        df.show(getSupportFragmentManager(), "dialog");
    }


    private void iniciarControles(){
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // put this after your definition of your recyclerview
        // input in your data mode in this example a java.util.List, adjust if necessary
        // adapter is your adapter
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        trackList.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


}