package broadcast.sample.qwh.com.broadcastsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import broadcast.sample.qwh.com.broadcastsample.util.ActivityCollector;

/**
 * Created by qwh on 2018/1/2.
 */

public class BaseActivity extends AppCompatActivity {
    private ExitBroadCastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.sample.qwh.exit");
        receiver = new ExitBroadCastReceiver();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver!=null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.remove(this);
        Log.e("onDestory",getClass().getName()+"destory");
    }

    class ExitBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            if ("com.sample.qwh.exit".equalsIgnoreCase(intent.getAction())){
                new AlertDialog.Builder(context).setCancelable(true)
                        .setTitle("Exit App")
                        .setMessage("If you click yes,you will exit the app")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCollector.finishAll();
                                Intent intent1 = new Intent(context,LoginActivity.class);
//                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent1);
                            }
                        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

            }
        }
    }
}
