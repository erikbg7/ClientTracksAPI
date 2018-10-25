package delete.example;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface TrackAPI {

    @GET("tracks")
    Call<List<Track>> getTracks();
}
