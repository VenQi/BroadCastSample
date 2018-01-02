package broadcast.sample.qwh.com.broadcastsample.util;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwh on 2018/1/2.
 */

public class ActivityCollector {
    private static final String TAG ="ActivityCollector";
    public static List<Activity> activities = new ArrayList<>();
    public static void add(Activity activity){
        activities.add(activity);
    }
    public static void remove(Activity activity){
        activities.remove(activity);
    }
    public static  void finishAll(){
        Log.e("finishAll",activities.size()+"");
        for (Activity a:activities
             ) {
            if (!a.isFinishing()) {
                a.finish();
            }
        }
    }
}
