package in.monte.spsu.Result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.monte.spsu.R;

/**
 * Created by Monte on 1/26/2018.
 */

public class ResultAdapter extends ArrayAdapter {
    List<ResultData> list = new ArrayList<ResultData>();
    Manage data;

    public ResultAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(@Nullable ResultData object) {
        super.add(object);
        this.list.add(object);
    }

    @Override
    public void clear() {
        super.clear();
        this.list.clear();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        if(row==null){
            LayoutInflater in = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = in.inflate(R.layout.res_design, parent, false);

            data = new ResultAdapter.Manage();
            data.t1 = row.findViewById(R.id.item_1);
            data.t2 = row.findViewById(R.id.item_2);
            data.t3 = row.findViewById(R.id.item_3);
            row.setTag(data);
        } else data = (ResultAdapter.Manage) row.getTag();

        data.t1.setText(list.get(position).getCode());
        data.t2.setText(list.get(position).getSubject());
        data.t3.setText(list.get(position).getGrade());

        return row;
    }

    static class Manage{
        TextView t1, t2, t3;
    }
}
