package projet.jegrenier.lefevre.petitsmeutres.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;

/**
 * Created by Jérémie on 19/03/2016.
 */
public class ListeMotTimer extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.liste_mot_timer,container,false);
        Chronometer chrono=((Chronometer) view.findViewById(R.id.chronometer));
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        ((ListView) view.findViewById(R.id.listeMots)).setAdapter(mArrayAdapter);
        SQLiteDatabase database= ((MasterActivity)getContext()).getAssistantBdd().getReadableDatabase();
        Cursor cursor=database.query("listeMots", new String[]{"mot1", "mot2","mot3","mot4","mot5","mot6","mot7","mot8","mot9","mot10","mot11","mot12",}, "idJour = 1", null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0) {
            for(int i=0; i<12; i++) {
                String mot = cursor.getString(i);
                mArrayAdapter.add(mot);
            }
        }else {

        }
        return view;
    }
}