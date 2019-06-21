package in.monte.spsu.Notice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.lang.reflect.Method;
import java.util.List;

import in.monte.spsu.R;
import in.monte.spsu.Server.RestClient;
import in.monte.spsu.Server.ServerAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notice extends Fragment {
    ListView listView;
    NoticeAdapter listAdapter;
    ServerAPI serverAPI;
    SwipeRefreshLayout swipe;
    OnShowNoticeListener onShowNoticeListener;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_notice, container, false);

        listView = view.findViewById(R.id.list);
        listAdapter = new NoticeAdapter(getContext(),R.layout.list_design);
        listView.setAdapter(listAdapter);
        swipe = view.findViewById(R.id.swipe);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        swipe.setColorSchemeColors(getResources().getColor(R.color.red),getResources().getColor(R.color.blue),getResources().getColor(R.color.colorPrimary));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressDialog.show();
                if(isNetworkConnected()){
                    refreshNotice();
                    offlineNotice();
                } else {
                    Toast.makeText(getContext(), "Connection Failed !", Toast.LENGTH_SHORT).show();
                    offlineNotice();
                }
                swipe.setRefreshing(false);
            }
        });

        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            view.setSelected(true);

            TextView temp = view.findViewById(R.id.item1);
            TextView seen = view.findViewById(R.id.item3);
            int id = Integer.parseInt(temp.getText().toString());

            seen.setVisibility(View.GONE);
            new Update(in.monte.spsu.tables.Notice.class)
                    .set("seen = ?", 0)
                    .where("no = ?", id)
                    .execute();
            onShowNoticeListener.showNotice(id);
            }
        });

        progressDialog.show();
        if(isNetworkConnected()){
            refreshNotice();
        } else {
            Toast.makeText(getContext(), "Connection Failed !", Toast.LENGTH_SHORT).show();
        }
        offlineNotice();
        return view;
    }

    public static List<in.monte.spsu.tables.Notice> getAll() {
        return new Select()
                .from(in.monte.spsu.tables.Notice.class)
                .orderBy("no ASC")
                .execute();
    }

    public void refreshNotice(){
        listAdapter.clear();
        Call<List<NoticeData>> noti = serverAPI.getNotice();
        noti.enqueue(new Callback<List<NoticeData>>() {
            @Override
            public void onResponse(Call<List<NoticeData>> call, Response<List<NoticeData>> response) {
                List<NoticeData> data = response.body();
                int i=0;
                for(NoticeData item : data){
                    in.monte.spsu.tables.Notice notice = new in.monte.spsu.tables.Notice();
                    notice.setNum(data.get(i).getNum());
                    notice.setTitle(data.get(i).getTitle());
                    notice.setContent(data.get(i++).getContent());
                    notice.save();
                    listAdapter.add(notice);
                }
            }

            @Override
            public void onFailure(Call<List<NoticeData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void offlineNotice(){
        List<in.monte.spsu.tables.Notice> notice = getAll();
        for(in.monte.spsu.tables.Notice item : notice){
            listAdapter.add(item);
        }
        progressDialog.dismiss();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public interface OnShowNoticeListener{
        void showNotice(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onShowNoticeListener = (OnShowNoticeListener) context;
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
