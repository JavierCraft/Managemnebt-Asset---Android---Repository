package id.ac.polman.astra.nim0320220038.user;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.api.ApiUtils;
import id.ac.polman.astra.nim0320220038.user.api.UserService;
import id.ac.polman.astra.nim0320220038.user.api.UserVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private static UserRepository INSTANCE;
    private UserService mUserService;

    private UserRepository(Context context){
        mUserService = ApiUtils.getUserService();
    }

    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new UserRepository(context);
        }
    }

    public static UserRepository get(){
        return INSTANCE;
    }

    public MutableLiveData<List<UserVO>> getUsers(){
        MutableLiveData<List<UserVO>> users = new MutableLiveData<>();

        Call<List<UserVO>> call = mUserService.getUsers();
        call.enqueue(new Callback<List<UserVO>>() {
            @Override
            public void onResponse(Call<List<UserVO>> call,
                                   Response<List<UserVO>> response) {
                if (response.isSuccessful()){
                    users.setValue(response.body());
                    Log.d(TAG,"getUsers.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<UserVO>> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
        return users;
    }

    public MutableLiveData<UserVO> getUser(String userId){
        MutableLiveData<UserVO> user = new MutableLiveData<>();

        Call<UserVO> call = mUserService.getUserById(userId);
        call.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                if (response.isSuccessful()){
                    user.setValue(response.body()); 
                    Log.d(TAG,"getUserById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
        return user;
    }

    public void updateUser(UserVO user){
        Log.i(TAG,"updateUser() called");
        Call<UserVO> call = mUserService.updateuser(user);
        call.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                if (response.isSuccessful()){
                    Log.i(TAG,"User updated "+ user.getUsername());
                }
            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
    }

    public void addUser(UserVO user){
        Log.i(TAG,"addUser() called");
        Call<UserVO> call = mUserService.addUser(user);
        call.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                if (response.isSuccessful()){
                    Log.i(TAG,"User added "+user.getUsername());
                }
            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
    }
}
