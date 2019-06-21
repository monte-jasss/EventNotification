package in.monte.spsu.Result;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.List;

import in.monte.spsu.R;
import in.monte.spsu.Server.RestClient;
import in.monte.spsu.Server.ServerAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result extends Fragment {
    ListView listView;
    ResultAdapter resultAdapter;
    ServerAPI serverAPI;
    String enrollment;
    EditText enrol;
    Button show;
    AlertDialog alertDialog, alertDialog1;
    AlertDialog.Builder alert, alert1;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        listView = view.findViewById(R.id.list_result);
        resultAdapter = new ResultAdapter(getContext(),R.layout.res_design);
        listView.setAdapter(resultAdapter);
        show = view.findViewById(R.id.show);
        enrol = view.findViewById(R.id.enrol);
        dialog = new ProgressDialog(getContext());

        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        alert = new AlertDialog.Builder(getContext());
        alert.setCancelable(false);
        alert.setMessage("Result Not Found !");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alert1 = new AlertDialog.Builder(getContext());
        alert1.setCancelable(false);
        alert1.setMessage("Enter Enrollment !");
        alert1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog1.dismiss();
            }
        });

        alertDialog = alert.create();
        alertDialog1 = alert1.create();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()) {
                    enrollment = enrol.getText().toString();
                    if(!enrollment.equals("")) {
                        dialog.show();
                        serverAPI = RestClient.getRestClient().create(ServerAPI.class);
                        Call<List<ResultData>> result = serverAPI.getResult(enrollment);
                        result.enqueue(new Callback<List<ResultData>>() {
                            @Override
                            public void onResponse(Call<List<ResultData>> call, Response<List<ResultData>> response) {
                                resultAdapter.clear();
                                List<ResultData> data = response.body();
                                for (ResultData item : data) {
                                    resultAdapter.add(item);
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<ResultData>> call, Throwable t) {
                                dialog.dismiss();
                                alertDialog.show();
                            }
                        });
                    } else {
                        alertDialog1.show();
                    }
                } else {
                    Toast.makeText(getContext(), "Connection Failed !!", Toast.LENGTH_SHORT).show();
                }
                enrol.setText("");
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
            }
        });*/



        return view;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress i = InetAddress.getByName("http://www.google.com");
            return i.equals("");
        } catch (Exception e) {
            return false;
        }
    }

}
