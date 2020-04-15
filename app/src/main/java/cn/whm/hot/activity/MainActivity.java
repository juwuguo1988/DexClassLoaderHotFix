package cn.whm.hot.activity;

import androidx.appcompat.app.AppCompatActivity;
import cn.whm.hot.R;
import cn.whm.hot.impl.IAction;
import cn.whm.hot.impl.ActionException;
import dalvik.system.DexClassLoader;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btn_say;
    private IAction iAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "" + MainActivity.class.getClassLoader());

        setContentView(R.layout.activity_main);
        btn_say = findViewById(R.id.btn_say);
        setListener();
    }

    /**
     * dx --dex --output=/Users/hello/Desktop/backProject/HotFixProject/output.jar /Users/hello/Desktop/backProject/HotFixProject/src.jar
     */

    private void setListener() {
        btn_say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File jarFile = new File(Environment.getExternalStorageDirectory().getPath() + "/XZLFile/media/output.jar");
                if (!jarFile.exists()) {
                    iAction = new ActionException();
                    Log.e(TAG, "output.jar not exist");
                    Toast.makeText(MainActivity.this, iAction.doAction(), Toast.LENGTH_LONG).show();
                } else {
                    //需要有读写权限
                    DexClassLoader classLoader = new DexClassLoader(jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
                    try {
                        Class clazz = classLoader.loadClass("cn.whm.hot.impl.ActionNormal");
                        iAction = (IAction) clazz.newInstance();
                        Toast.makeText(MainActivity.this, iAction.doAction(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
