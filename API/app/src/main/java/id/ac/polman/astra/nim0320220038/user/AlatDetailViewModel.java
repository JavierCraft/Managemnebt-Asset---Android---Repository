package id.ac.polman.astra.nim0320220038.user;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class AlatDetailViewModel extends ViewModel {
    private static final String TAG = "AlatDetailViewModel";

    private LiveData<Alat> mAlatLiveData;
    private AlatRepository mAlatRepository;
    private MutableLiveData<String> mIdMutableLiveData;

    public AlatDetailViewModel() {
        mAlatRepository = AlatRepository.get();
        mIdMutableLiveData = new MutableLiveData<String>();

        mAlatLiveData = Transformations.switchMap(mIdMutableLiveData,
                altId -> mAlatRepository.getAlat(altId));
    }

    public void loadAlat(String altId) {
        Log.i(TAG, "loadAlat() called");
        mIdMutableLiveData.setValue(altId);
    }

    public LiveData<Alat> getAlatLiveData() {
        Log.i(TAG, "getAlatLiveData() called");
        return mAlatLiveData;
    }
}
