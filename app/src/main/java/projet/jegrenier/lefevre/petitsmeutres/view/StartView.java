package projet.jegrenier.lefevre.petitsmeutres.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projet.jegrenier.lefevre.petitsmeutres.R;

/**
 * Created by jegrenier on 15/03/16.
 */
public class StartView extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.start_view,container,false);
        return view;
    }
}
