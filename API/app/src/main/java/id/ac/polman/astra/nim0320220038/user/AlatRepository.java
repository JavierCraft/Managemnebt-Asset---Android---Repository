package id.ac.polman.astra.nim0320220038.user;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.api.ApiUtils;
import id.ac.polman.astra.nim0320220038.user.api.AlatService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlatRepository {
    private static final String TAG = "AlatRepository";
    private static AlatRepository INSTANCE;
    private AlatService mAlatService;

    private AlatRepository(Context context) {
        mAlatService = ApiUtils.getAlatService();
    }

    public static void initialize(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AlatRepository(context);
        }
    }

    public static AlatRepository get() {
        return INSTANCE;
    }

    public MutableLiveData<List<Alat>> getAlats() {
        MutableLiveData<List<Alat>> alats = new MutableLiveData<>();

        Call<List<Alat>> call = mAlatService.getAlats();
        call.enqueue(new Callback<List<Alat>>() {
            @Override
            public void onResponse(Call<List<Alat>> call, Response<List<Alat>> response) {
                if (response.isSuccessful()) {
                    alats.setValue(response.body());
                    Log.d(TAG, "getAlats.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Alat>> call, Throwable t) {
                Log.e("Error API call: ", t.getMessage());
            }
        });
        return alats;
    }

    public MutableLiveData<Alat> getAlat(String alatId) {
        MutableLiveData<Alat> alat = new MutableLiveData<>();

        Call<Alat> call = mAlatService.getAlatById(alatId);
        call.enqueue(new Callback<Alat>() {
            @Override
            public void onResponse(Call<Alat> call, Response<Alat> response) {
                if (response.isSuccessful()) {
                    alat.setValue(response.body());
                    Log.d(TAG, "getAlatById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Alat> call, Throwable t) {
                Log.e("Error API call: ", t.getMessage());
            }
        });
        return alat;
    }
}
