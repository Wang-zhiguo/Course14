package cn.androidstudy.course14;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HeaderFragment.OnTitleSelectedListener{
    private ArrayList<NewsBean> datas = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNewsData();
        // 通过是否包含R.id.fragment_container判断使用的是哪个布局
        if (findViewById(R.id.fragment_container) != null) {
            // 创建Fragment用来动态加载
            HeaderFragment firstFragment = new HeaderFragment();
            // 动态加载
            //getSupportFragmentManager() 使用V4包创建Fragment
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }else{//横屏，默认选中第一条新闻
            ArticleFragment articleFrag = (ArticleFragment)
                    getFragmentManager().findFragmentById(R.id.article);
            Bundle args = new Bundle();
            args.putInt("pos", 0);
            articleFrag.setArguments(args);
        }
    }
    //初始化数据
    public ArrayList<NewsBean> getNewsData() {
        if(datas==null) {
            datas = new ArrayList<>();
            NewsBean newsBean;
            for (int i = 0; i < 10; i++) {
                newsBean = new NewsBean("title" + i, "this is " + i + " News!");
                datas.add(newsBean);
            }
        }
        return datas;
    }
    //在用户点击新闻标题时，如果是
    //竖屏：动态加载ArticleFragment，替换原来的
    //横屏：更新ArticleFragment中的内容显示
    @Override
    public void onArticleSelected(int position) {
        // 获取ArticleFragment
        ArticleFragment articleFrag = (ArticleFragment)
                getFragmentManager().findFragmentById(R.id.article);

        if (articleFrag != null) {// 横屏状态，刷新内容
            articleFrag.updateArticleView(position);
        } else {// 否则，竖屏，则动态加载ArticleFragment
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt("pos", position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
