package projet.jegrenier.lefevre.petitsmeutres.view.listPlayers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import projet.jegrenier.lefevre.petitsmeutres.MasterActivity;
import projet.jegrenier.lefevre.petitsmeutres.R;
import projet.jegrenier.lefevre.petitsmeutres.model.Player;

/**
 * Created by hugol on 18/03/2016.
 */
public class AdapterListPlayers extends ArrayAdapter<Player> {

    Context mContext;
    int layoutResourceId;
    Player data[] = null;

    public AdapterListPlayers(Context mContext, int layoutResourceId, Player[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

    /*
     * The convertView argument is essentially a "ScrapView" as described is Lucas post
     * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
     * It will have a non-null value when ListView is asking you recycle the row layout.
     * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
     */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        final Player player = data[position];

        //Bouton supprimer
        final ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deletePlayer);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MasterActivity)mContext).playerList.remove(player);
                ((MasterActivity)mContext).setPlayer(v);
            }
        });

        // Set le text et le tag pour le nomjoueur
        final EditText nomJoueur = (EditText) convertView.findViewById(R.id.nomJoueur);
        nomJoueur.setText(player.getNickname());
        nomJoueur.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                player.setNickname(nomJoueur.getText().toString());
            }
        });

        // Set le drawable et le tag pour le avPlayer
        final ImageButton avPlayer = (ImageButton) convertView.findViewById(R.id.avPlayer);
        avPlayer.setImageDrawable(player.getAvatar());
        avPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MasterActivity)mContext).currentPlayer = player;
                ((MasterActivity)mContext).selectAvatar(v);
            }
        });
        return convertView;

    }

}

