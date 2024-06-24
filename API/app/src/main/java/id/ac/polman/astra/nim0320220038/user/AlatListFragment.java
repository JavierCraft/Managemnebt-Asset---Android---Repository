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

public class AlatListFragment extends Fragment {
    private static final String TAG = "alatListFragment";

    private AlatListViewModel mAlatListViewModel;
    private RecyclerView mAlatRecycleView;
    private AlatAdapter mAdapter;
    private List<Alat> mAlats;

    interface Callbacks{
        public void onUserSelected(int userId);
    }
    private AlatListFragment.Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        Log.i(TAG,"OnAttach called");
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onDetach(){
        super.onDetach();
        Log.i(TAG,"onDetach called");
        mCallbacks = null;
    }

    private void updateUI(List<Alat> alats){
        Log.d(TAG,"updateUI called");
        mAdapter = new AlatAdapter(alats);
        mAlatRecycleView.setAdapter(mAdapter);
        mAlats = alats;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG,"AlatListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mAlatListViewModel = new ViewModelProvider(this)
                .get(AlatListViewModel.class);
    }

    public static AlatListFragment newInstance(){
        return new AlatListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        Log.i(TAG,"AlatListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_user_list,
                container,false);
        mAlatRecycleView = (RecyclerView)
                view.findViewById(R.id.user_recycler_view);
        mAlatRecycleView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        try {
            Thread.sleep(100);
        }catch (InterruptedException ie){
            Thread.currentThread().interrupt();
            Thread.currentThread().interrupt();
        }
        Log.i(TAG,"AlatListFragment.onViewCreated() called");
        mAlatListViewModel.getAlats().observe(
                getViewLifecycleOwner(),
                new Observer<List<Alat>>() {
                    @Override
                    public void onChanged(List<Alat> alats) {
                        updateUI(alats);
                        Log.i(TAG,"Got Alat: "+alats.size());
                    }
                });
    }

    private class AlatHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView mIdTextView;
        private TextView mUsernameTextView;
        private Alat mAlat;
        public AlatHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_user,parent,false));
            this.itemView.setOnClickListener(this);

            mIdTextView = this.itemView.findViewById(R.id.user_id);
            mUsernameTextView = this.itemView.findViewById(R.id.user_name);
        }
        public void bind(Alat alat){
            mAlat = alat;
            mIdTextView.setText(String.valueOf(mAlat.getAltId()));
            mUsernameTextView.setText(mAlat.getAltMerk());
        }

        @Override
        public void onClick(View v){
            /*Toast.makeText(getActivity(),
                    mUser.getUsername() + "clicked!",
                    Toast.LENGTH_SHORT)
                    .show();*/
            mCallbacks.onUserSelected(mAlat.getAltId());
        }
    }

    private class AlatAdapter extends RecyclerView.Adapter<AlatHolder>{
        private List<Alat> mAlatList;

        public AlatAdapter(List<Alat> alats){
            mAlatList = alats;
        }

        @Override
        public AlatHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new AlatHolder(layoutInflater,parent);
        }
        @Override
        public void onBindViewHolder(AlatListFragment.AlatHolder holder, int position){
            Alat alat = mAlatList.get(position);
            holder.bind(alat);
        }
        @Override
        public int getItemCount(){
            return mAlatList.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_user_list,menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_user) {
            Alat alat = new Alat();
            int maxId = 0;
            for (Alat aUser : mAlats) {
                if (aUser.getAltId() >= maxId) {
                    maxId = aUser.getAltId();
                }
            }
            alat.setAltId(maxId + 1);
            alat.setAltId(1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            mCallbacks.onUserSelected(alat.getAltId());
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
