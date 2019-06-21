package in.monte.spsu.TimeTable;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.io.InputStream;
import java.util.List;

import in.monte.spsu.R;
import in.monte.spsu.Server.RestClient;
import in.monte.spsu.Server.ServerAPI;
import in.monte.spsu.tables.Branch;
import in.monte.spsu.tables.Notice;
import in.monte.spsu.tables.Semester;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTable extends Fragment {
    View view;
    Spinner sp1, sp2;
    ServerAPI serverAPI;
    ArrayAdapter<String> array;
    Button bt;
    ProgressDialog pd;
    AlertDialog alertDialog, alertDialog1;
    AlertDialog.Builder alert,alert1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_table, container, false);

        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        sp1 = view.findViewById(R.id.semester);
        sp2 = view.findViewById(R.id.branch);
        bt = view.findViewById(R.id.display);
        pd = new ProgressDialog(getContext());
        alert = new AlertDialog.Builder(getContext());
        alert1 = new AlertDialog.Builder(getContext());

        alert.setMessage("Not Found !");
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alert1.setMessage("Something went wrong !");
        alert1.setCancelable(false);
        alert1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog1.dismiss();
            }
        });

        alertDialog = alert.create();
        alertDialog1 = alert1.create();

        pd.setMessage("Loading...");
        pd.setCancelable(false);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()) {
                    String sem = sp1.getSelectedItem().toString();
                    String bra = sp2.getSelectedItem().toString();
                    if(sem.equals("Select Semester") || bra.equals("Select Branch")){
                        Toast.makeText(getContext(), "Please select branch and semester...", Toast.LENGTH_SHORT).show();
                    } else {
                        pd.show();
                        Call<ResponseBody> image = serverAPI.getTimeTable(sem,bra);
                        image.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                InputStream is = response.body().byteStream();
                                Bitmap bm = BitmapFactory.decodeStream(is);
                                showImage(bm);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                pd.dismiss();
                                alertDialog1.show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(getContext(), "Connection Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        refreshTimeTable();

        return view;
    }

    public void refreshTimeTable() {
        if(isNetworkConnected()) {
            Call<List<String>> sem = serverAPI.getSemester();
            sem.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    List<String> data = response.body();
                    try {
                        for (String item : data) {
                            Semester br = new Semester();
                            br.setSem(item);
                            br.save();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            });

            Call<List<String>> branch = serverAPI.getBranch();
            branch.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    List<String> data = response.body();
                    try {
                        for (String item : data) {
                            Branch br = new Branch();
                            br.setBranch(item);
                            br.save();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Connection Failed !", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                offlineBranch();
                offlineSemester();
            }
        }, 50);
    }

    private void showImage(Bitmap bm) {
        if(bm==null){
            alertDialog.show();
        } else {
            ImageView iv = view.findViewById(R.id.timeTable);
            iv.setImageBitmap(bm);
            iv.setVisibility(View.VISIBLE);
        }
        pd.dismiss();
    }

    private void offlineBranch() {
        List<Branch> data = getBranch();
        String[] br = new String[data.size()+1];
        int i=1;
        br[0] = "Select Branch";
        for(Branch item : data){
            br[i++] = item.getBranch();
        }
        array = new ArrayAdapter<String>(getContext(),R.layout.dropdown,br);
        sp2.setAdapter(array);
    }

    private List<Branch> getBranch() {
        return new Select()
                .from(Branch.class)
                .orderBy("branch")
                .execute();
    }

    private void offlineSemester() {
        List<Semester> data = getSemester();
        String[] sem = new String[data.size()+1];
        int i=1;
        sem[0] = "Select Semester";
        for(Semester item : data){
            sem[i++] = ""+item.getSem();
        }
        array = new ArrayAdapter<String>(getContext(),R.layout.dropdown,sem);
        sp1.setAdapter(array);
    }

    private List<Semester> getSemester(){
        return new Select()
                .from(Semester.class)
                .orderBy("semester ASC")
                .execute();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
