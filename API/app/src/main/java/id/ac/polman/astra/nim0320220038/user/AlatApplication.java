package id.ac.polman.astra.nim0320220038.user;

import android.app.Application;
import android.util.Log;

public class AlatApplication extends Application {
    private static final String TAG = "AlatApplication";

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG,"AlatApplication.onCreate() called");
        UserRepository.initialize(this);
    }
}
