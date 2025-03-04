// UserDetailViewModel.java
package id.ac.polman.astra.nim0320220038.user;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.ac.polman.astra.nim0320220038.user.api.UserVO;

public class UserDetailViewModel extends ViewModel {
    private static final String TAG = "UserDetailViewModel";

    private LiveData<UserVO> mUserLiveData;
    private UserRepository mUserRepository;
    private MutableLiveData<String> mIdMutableLiveData;

    public UserDetailViewModel(){
        mUserRepository = UserRepository.get();
        mIdMutableLiveData = new MutableLiveData<>();
        mUserLiveData = Transformations.switchMap(mIdMutableLiveData,
                userId -> mUserRepository.getUser(userId));
    }
    public void loadUser(String userId){
        Log.i(TAG,"loadUser() called");
        mIdMutableLiveData.setValue(userId);
    }
    public LiveData<UserVO> getUserLiveData(){
        Log.i(TAG,"getUserLiveData() called");
        return mUserLiveData;
    }
    public void saveUser(UserVO user){
        mUserRepository.updateUser(user);
    }
}
