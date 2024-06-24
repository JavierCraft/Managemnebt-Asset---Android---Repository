package id.ac.polman.astra.nim0320220038.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.polman.astra.nim0320220038.user.api.UserVO;

public class UserListFragment extends Fragment{
    private static final String TAG = "UserListFragment";

    private UserListViewModel mUserListViewModel;
    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;
    private List<UserVO> mUsers;

    interface Callbacks{
        void onUserSelected(int userId);
    }

    private Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        Log.i(TAG,"onAttach called");
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.i(TAG,"onDetach called");
        mCallbacks = null;
    }

    private void updateUI(List<UserVO> users){
        Log.d(TAG,"updateUI called");
        mAdapter = new UserAdapter(users);
        mUserRecyclerView.setAdapter(mAdapter);
        mUsers = users;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG,"UserListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mUserListViewModel = new ViewModelProvider(this)
                .get(UserListViewModel.class);
    }

    public static UserListFragment newInstance(){
        return new UserListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Log.i(TAG,"UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        mUserRecyclerView = view.findViewById(R.id.user_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Log.i(TAG,"UserListFragment.onViewCreated() called");
        mUserListViewModel.getUsers().observe(
                getViewLifecycleOwner(),
                new Observer<List<UserVO>>() {
                    @Override
                    public void onChanged(List<UserVO> users) {
                        updateUI(users);
                        Log.i(TAG,"Got Users: "+users.size());
                    }
                });
    }

    private class UserHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView mIdTextView;
        private TextView mUsernameTextView;
        private UserVO mUser;

        public UserHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_user, parent, false));
            this.itemView.setOnClickListener(this);

            mIdTextView = this.itemView.findViewById(R.id.user_id);
            mUsernameTextView = this.itemView.findViewById(R.id.user_name);
        }

        public void bind(UserVO user){
            mUser = user;
            mIdTextView.setText(mUser.getId());
            mUsernameTextView.setText(mUser.getUsername());
        }

        @Override
        public void onClick(View v){
            mCallbacks.onUserSelected(Integer.parseInt(mUser.getId()));
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<UserVO> mUserList;

        public UserAdapter(List<UserVO> users){
            mUserList = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new UserHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position){
            UserVO user = mUserList.get(position);
            holder.bind(user);
        }

        @Override
        public int getItemCount(){
            return mUserList.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_user_list, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_user) {
            UserVO user = new UserVO();
            int maxId = 0;
            for (UserVO aUser : mUsers) {
                int userId = Integer.parseInt(aUser.getId());
                if (userId >= maxId) {
                    maxId = userId;
                }
            }
            user.setId(String.valueOf(maxId + 1));
            user.setProId(1);
            mUserListViewModel.addUser(user);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            mCallbacks.onUserSelected(Integer.parseInt(user.getId()));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
