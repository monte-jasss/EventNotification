package in.monte.spsu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

public class Home extends Fragment {
    ViewFlipper flipper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        flipper = view.findViewById(R.id.viewFlipper);

        flipper.setAutoStart(true);
        flipper.setFlipInterval(2500);
        flipper.startFlipping();

        return view;
    }


}
