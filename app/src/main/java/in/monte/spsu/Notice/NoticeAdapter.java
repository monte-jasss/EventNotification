package in.monte.spsu.Notice;

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
import in.monte.spsu.tables.*;
import in.monte.spsu.tables.Notice;

/**
 * Created by Monte on 1/26/2018.
 */

public class NoticeAdapter extends ArrayAdapter {
    List<in.monte.spsu.tables.Notice> list = new ArrayList<Notice>();
    Manage data;

    public NoticeAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void clear() {
        super.clear();
        this.list.clear();
    }

    public void add(@Nullable Notice object) {
        super.add(object);
        this.list.add(object);
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
            row = in.inflate(R.layout.list_design, parent, false);

            data = new Manage();
            data.t1 = row.findViewById(R.id.item1);
            data.t2 = row.findViewById(R.id.item2);
            data.t3 = row.findViewById(R.id.item3);
            row.setTag(data);
        } else data = (Manage) row.getTag();

        if(list.get(position).getCheck()==1) {
            data.t3.setVisibility(View.VISIBLE);
        }
        else {
            data.t3.setVisibility(View.GONE);
        }

        data.t1.setText(""+list.get(position).getNum());
        data.t2.setText(list.get(position).getTitle());
        return row;
    }

    static class Manage{
        TextView t1, t2, t3;
    }
}
