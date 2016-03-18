package projet.jegrenier.lefevre.petitsmeutres.view.listPlayers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;
import projet.jegrenier.lefevre.petitsmeutres.model.Player;

/**
 * Created by hugol on 15/03/2016.
 */
public class SetPlayer extends Fragment {
    ListView listPlayers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choix_joueur, container, false);
        initialiserListView(view);
        return view;
    }

    private void initialiserListView(View view) {
        listPlayers = (ListView) view.findViewById(R.id.listPlayers);
        MasterActivity ma = (MasterActivity) getActivity();
        AdapterListPlayers adapter = new AdapterListPlayers(ma, R.layout.list_players_row_item, ma.playerList.toArray(new Player[ma.playerList.size()]));
        listPlayers.setAdapter(adapter);

    }
}
