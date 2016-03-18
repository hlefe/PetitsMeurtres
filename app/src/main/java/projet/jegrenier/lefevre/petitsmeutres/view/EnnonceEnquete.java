package projet.jegrenier.lefevre.petitsmeutres.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;

/**
 * Created by jegrenier on 18/03/16.
 */
public class EnnonceEnquete extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ennonce_enquete,container,false);
        GregorianCalendar date= ((MasterActivity) getContext()).getDate();
        //((TextView) view.findViewById(R.id.date)).setText(date.getTime().toString());
        Log.d("DEBUG", date.toString());
        ((TextView) view.findViewById(R.id.date)).setText(date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.FRANCE) + " " + date.get(Calendar.DAY_OF_MONTH) + " " + date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE) + " " + date.get(Calendar.YEAR));
        SQLiteDatabase database= ((MasterActivity)getContext()).getAssistantBdd().getReadableDatabase();
        Cursor cursor=database.query("jour", new String[]{"date", "description"}, "date = 'Mardi 2 Mars'", null, null, null, null);
        cursor.moveToFirst();
        String ennonce = cursor.getString(1);
        ((TextView) view.findViewById(R.id.ennonce)).setText(ennonce);
        return view;
    }
}