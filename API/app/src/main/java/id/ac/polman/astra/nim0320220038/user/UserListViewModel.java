package id.ac.polman.astra.nim0320220038.user;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.api.UserVO;

public class UserListViewModel extends ViewModel {
    private static final String TAG = "UserListViewModel";
    private MutableLiveData<List<UserVO>> mUserListMutableLiveData;
    private UserRepository mUserRepository;

    public UserListViewModel() {
        Log.d(TAG,"UserListViewModel constructor called");
        mUserRepository = UserRepository.get();
    }

    public LiveData<List<UserVO>> getUsers(){
        mUserListMutableLiveData = mUserRepository.getUsers();
        Log.d(TAG,"UserListViewModel.getUsers() called = "+
                mUserListMutableLiveData.toString());
        return mUserListMutableLiveData;
    }

    public void addUser(UserVO user){
        mUserRepository.addUser(user);
    }
}
