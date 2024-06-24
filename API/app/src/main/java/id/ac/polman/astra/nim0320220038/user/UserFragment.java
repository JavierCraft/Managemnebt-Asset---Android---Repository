// UserFragment.java
package id.ac.polman.astra.nim0320220038.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.ac.polman.astra.nim0320220038.user.api.UserVO;

public class UserFragment extends Fragment {
    private static final String ARG_USER_ID = "user_id";
    private static final String TAG = "UserFragment";
    private UserVO mUser;
    private EditText mUserIdField;
    private EditText mUsernameField;
    private UserDetailViewModel mUserDetailViewModel;
    private String mUserId;

    private UserDetailViewModel getUserDetailViewModel(){
        if (mUserDetailViewModel == null){
            mUserDetailViewModel = new ViewModelProvider(this)
                    .get(UserDetailViewModel.class);
        }
        return mUserDetailViewModel;
    }

    public static UserFragment newInstance(int userId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID,userId);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG,"UserFragment.onCreate() called");
        int userId = getArguments().getInt(ARG_USER_ID);
        Log.i(TAG,"args bundle userId = "+userId);
        mUser = new UserVO();
        mUserId = String.valueOf(userId);
        mUserDetailViewModel = getUserDetailViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_user,container,false);

        mUserIdField = v.findViewById(R.id.userid);
        mUserIdField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    mUser.setId(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mUsernameField = v.findViewById(R.id.username);
        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    private void updateUI(){
        Log.i(TAG,"UserFragment.updateUI() called");
        mUserIdField.setText(mUser.getId());
        mUsernameField.setText(mUser.getUsername());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"userFragment.onViewCreated() called");
        mUserDetailViewModel.getUserLiveData().observe(
                getViewLifecycleOwner(),
                new Observer<UserVO>() {
                    @Override
                    public void onChanged(UserVO user) {
                        mUser = user;
                        updateUI();
                    }
                }
        );
        mUserDetailViewModel.loadUser(mUserId);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,"UserFragment.onStop() called");
        if (mUser != null) {
            mUserDetailViewModel.saveUser(mUser);
        }else {
            Log.i(TAG,"Data nya Kosong");
        }
    }
}
