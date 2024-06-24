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

public class AlatFragment extends Fragment {
    private static final String ARG_USER_ID = "altId";
    private static final String TAG = "AlatFragment";
    private Alat mAlat;
    private EditText mUserIdField;
    private EditText mUsernameField;
    private AlatDetailViewModel mAlatDetailViewModel;
    private String mAlatId;

    private AlatDetailViewModel getAlatDetailViewModel(){
        if (mAlatDetailViewModel == null){
            mAlatDetailViewModel = new ViewModelProvider(this)
                    .get(AlatDetailViewModel.class);
        }
        return mAlatDetailViewModel;
    }
    public static AlatFragment newInstance(int altId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID,altId);
        AlatFragment fragment = new AlatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG,"AlatFragment.onCreate() called");
        int altId = getArguments().getInt(ARG_USER_ID); // Mengambil nilai Integer dengan getInt()
        Log.i(TAG,"args bundle alatId = "+altId);
        mAlat = new Alat();
        mAlatId = String.valueOf(altId); // Mengonversi nilai Integer menjadi String
        mAlatDetailViewModel = getAlatDetailViewModel();
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
                if (!s.toString().isEmpty()) { // Memastikan bahwa teks tidak kosong sebelum mengonversinya menjadi bilangan bulat
                    mAlat.setAltId(Integer.parseInt(s.toString()));
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
                mAlat.setAltMerk(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    private void updateUI(){
        Log.i(TAG,"AlatFragment.updateUI() called");
        mUserIdField.setText(String.valueOf(mAlat.getAltId()));
        mUsernameField.setText(mAlat.getAltMerk());
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstateState){
        super.onViewCreated(view,savedInstateState);
        Log.i(TAG,"alatFragment.onViewCreted() called");
        mAlatDetailViewModel.getAlatLiveData().observe(
                getViewLifecycleOwner(),
                new Observer<Alat>() {
                    @Override
                    public void onChanged(Alat alat) {
                        mAlat = alat;
                        updateUI();
                    }
                }
        );
        mAlatDetailViewModel.loadAlat(mAlatId);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,"AlatFragment.onStop() called");
        if (mAlat != null) {
        }else {
            Log.i(TAG,"Hati nya Kosong");
        }
    }
}
