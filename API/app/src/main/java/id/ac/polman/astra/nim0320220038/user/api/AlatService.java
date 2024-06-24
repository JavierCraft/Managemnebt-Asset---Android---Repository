package id.ac.polman.astra.nim0320220038.user.api;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.Alat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlatService {
    @GET("alat")
    Call<Alat> getAlatById(@Query("id") String id);

    @GET("alat")
    Call<List<Alat>> getAlats();

}
