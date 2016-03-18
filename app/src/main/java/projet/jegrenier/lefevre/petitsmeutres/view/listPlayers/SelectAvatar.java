package projet.jegrenier.lefevre.petitsmeutres.view.listPlayers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;
import projet.jegrenier.lefevre.petitsmeutres.model.Player;

/**
 * Created by hugol on 15/03/2016.
 */
public class SelectAvatar extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ListView lv = getListView();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.selectAvatar).setView(lv);
        return builder.create();
    }

    @NonNull
    private ListView getListView() {
        ListView lv = new ListView(getActivity());

        //Adapter pour la listView
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), -1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Resources res = getResources();
                TypedArray avatarArray = res.obtainTypedArray(R.array.array_avatars);
                Drawable drawable = avatarArray.getDrawable(position);
                ImageView v = new ImageView(getActivity());
                v.setImageDrawable(drawable);
                return v;
            }

            @Override
            public int getCount() {
                Resources res = getResources();
                TypedArray drawableArray = res.obtainTypedArray(R.array.array_avatars);
                return drawableArray.length();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Resources res = getResources();
                TypedArray avatarArray = res.obtainTypedArray(R.array.array_avatars);
                Drawable drawable = avatarArray.getDrawable(position);
                MasterActivity ma = (MasterActivity) getActivity();
                ma.currentPlayer.setAvatar(drawable);
                ma.setPlayer(getView());
                getDialog().cancel();
            }
        });
        return lv;
    }

}
