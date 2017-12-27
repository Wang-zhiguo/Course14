package cn.androidstudy.course14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取显示屏对象
        Display dis = getWindowManager().getDefaultDisplay();
        //若屏幕宽度大于高度，相当于是横屏
        if (dis.getWidth() > dis.getHeight()) {
            FragmentOne f1 = new FragmentOne();
            //动态加载Fragment，注意：此处为了简单起见，把四步合并在一行代码实现
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.mylayout, f1).commit();
            /*
            注意：如果创建的Fragment是继承自import android.support.v4.app.Fragment;需要
            使用getSupportFragmentManager()，否则使用getFragmentManager()
             */
        } else {//否则，为竖屏
            Fragment2 f2 = new Fragment2();
            getSupportFragmentManager().beginTransaction().replace(R.id.mylayout, f2).commit();
        }
    }
}
