package id.ac.polman.astra.nim0320220038.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements UserListFragment.Callbacks {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            //fragment = new UserFragment();
            fragment = UserListFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }
    @Override
    public void onUserSelected(int userId){
        //Log.i(TAG,"MainActivity.onUserSelected = "+userId);
        Fragment fragment = UserFragment.newInstance(userId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }

    public void listuser(){
        //Log.i(TAG,"MainActivity.onUserSelected = "+userId);
        Fragment fragment = UserListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }
}