package in.monte.spsu.Notice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

import in.monte.spsu.R;
import in.monte.spsu.tables.Notice;

public class ShowNotice extends Fragment {
    View view;
    WebView web;
    TextView title;
    String data="";
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_notice, container, false);

        web = view.findViewById(R.id.webView);
        title = view.findViewById(R.id.notice_head);

        List<Notice> notice = getNotice(id);
        title.setText(notice.get(0).getTitle());
        data = "<html><body><p align=\"justify\" style=\"font-size:18px;line-height:1.6\">";
        data += notice.get(0).getContent();
        data += "</p></body></html>";
        web.loadData(String.format(" %s ", data), "text/html", "utf-8");

        return view;
    }

    public List<Notice> getNotice(int id){
        return new Select()
                .from(Notice.class)
                .where("no = ?", id)
                .execute();
    }

    public void setNotice(int id){
        this.id = id;
    }
}
