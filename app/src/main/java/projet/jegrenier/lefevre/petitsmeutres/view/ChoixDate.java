package projet.jegrenier.lefevre.petitsmeutres.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.GregorianCalendar;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;

/**
 * Created by jegrenier on 15/03/16.
 */
public class ChoixDate extends Fragment {

    private DatePicker choixDate;
    private GregorianCalendar date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.choix_date,container,false);
        initialiserDate(view);
        return view;
    }

    private void initialiserDate(View view) {
        choixDate = (DatePicker) view.findViewById(R.id.choixDate);
        int dayToday = choixDate.getDayOfMonth();
        int monthToday = choixDate.getMonth();
        int yearToday = choixDate.getYear();
        DatePicker.OnDateChangedListener odcl = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((MasterActivity)getContext()).setDate(new GregorianCalendar(year,monthOfYear,dayOfMonth));
                System.out.println(((MasterActivity)getContext()).getDate());
            }
        };
        choixDate.init(yearToday, monthToday, dayToday, odcl);
    }
}
