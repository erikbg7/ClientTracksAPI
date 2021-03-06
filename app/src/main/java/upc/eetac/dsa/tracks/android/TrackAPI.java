package upc.eetac.dsa.tracks.android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface TrackAPI {

    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST("tracks")
    Call<Track> createTrack(@Body Track track);

}
