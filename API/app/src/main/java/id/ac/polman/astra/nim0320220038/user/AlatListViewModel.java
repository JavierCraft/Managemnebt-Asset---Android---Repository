package id.ac.polman.astra.nim0320220038.user;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AlatListViewModel extends ViewModel {
    private static final String TAG = "AlatListViewModel";
    private MutableLiveData<List<Alat>> mUserListMutableLiveData;
    private AlatRepository mAlatRepository;

    public AlatListViewModel() {
        Log.d(TAG,"AlatListViewModel contructor called");
        mAlatRepository = AlatRepository.get();
    }

    public MutableLiveData<List<Alat>> getAlats(){
        mUserListMutableLiveData = mAlatRepository.getAlats();
        Log.d(TAG,"UserListViewModel.getUsers() called = "+
                mUserListMutableLiveData.toString());
        return mUserListMutableLiveData;
    }
}
