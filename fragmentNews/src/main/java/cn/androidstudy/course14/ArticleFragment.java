package cn.androidstudy.course14;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {
    private TextView textView;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        textView = (TextView)view.findViewById(R.id.content);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            int pos = bundle.getInt("pos");
            updateArticleView(pos);
        }else
            updateArticleView(0);
        return view;
    }
    public void updateArticleView(int pos){
        String content = ((MainActivity)getActivity()).getNewsData().get(pos).getContent();

        textView.setTextSize(25);
        textView.setText(content);
    }

}
