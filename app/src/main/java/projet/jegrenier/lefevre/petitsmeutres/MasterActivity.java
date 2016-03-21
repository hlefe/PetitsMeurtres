package projet.jegrenier.lefevre.petitsmeutres;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import projet.jegrenier.lefevre.petitsmeutres.model.Player;
import projet.jegrenier.lefevre.petitsmeutres.view.ChoixDate;
import projet.jegrenier.lefevre.petitsmeutres.view.EnnonceEnquete;
import projet.jegrenier.lefevre.petitsmeutres.view.ListeMotTimer;
import projet.jegrenier.lefevre.petitsmeutres.view.StartView;
import projet.jegrenier.lefevre.petitsmeutres.view.listPlayers.SelectAvatar;
import projet.jegrenier.lefevre.petitsmeutres.view.listPlayers.SetPlayer;

public class MasterActivity extends AppCompatActivity {

    private AssistantBdd assistantBdd;
    public AssistantBdd getAssistantBdd() {return assistantBdd;}
    private DatePicker choixDate;
    private GregorianCalendar date;
    public GregorianCalendar getDate() {return date;}
    public void setDate(GregorianCalendar date) {this.date = date;}
    private int idJour;
    public int getIdJour() {return idJour;}
    public void setIdJour(int idJour) {this.idJour = idJour;}
    private FragmentManager manager;
    private StartView startView;
    private ChoixDate choixJour;
    private SetPlayer setPlayer;
    private ListeMotTimer listeMotTimer;
    private EnnonceEnquete ennonceEnquete;
    public ArrayList<Player> playerList;
    public Player currentPlayer;
    public Player inspecteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petits_meutres);
        assistantBdd=new AssistantBdd(this);
        manager = getSupportFragmentManager();
        playerList = new ArrayList<>();
        ajoutPremierJoueur();
        date = new GregorianCalendar();
        
        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            startView();
        }

    }

    private void startView() {
        startView = new StartView();
        startView.setArguments(getIntent().getExtras());
        manager.beginTransaction().add(R.id.container, startView).commit();
    }

    public void choixDate(View sender){
       if(playerList.size() >= 4) { // Create fragment and give it an argument specifying the article it should show
           choixJour = new ChoixDate();
           choixJour.setArguments(getIntent().getExtras());
           manager.beginTransaction().replace(R.id.container, choixJour).commit();
       }else{
           Toast.makeText(this,"il faut au moins 4 joueur pour commencer a jouer", Toast.LENGTH_LONG);
       }
    }

    public void setPlayer(View sender){
        // Create fragment and give it an argument specifying the article it should show
        setPlayer = new SetPlayer();
        setPlayer.setArguments(getIntent().getExtras());
        manager.beginTransaction().replace(R.id.container, setPlayer).commit();
    }

    public void selectAvatar( View sender) {
        final SelectAvatar selectAvatar = new SelectAvatar();
        selectAvatar.show(manager, "TEST2");
    }

    public void startGame(View sender){
        //genererLesRolesAleatoirement();
        for(Player player : playerList){
            player.setRole("LOL");
        }
        playerList.get(0).setRole("Enquêteur");
        ennonceEnquete = new EnnonceEnquete();
        ennonceEnquete.setArguments(getIntent().getExtras());
        manager.beginTransaction().replace(R.id.container, ennonceEnquete).commit();
        for(Player player : playerList){
            if(player.getRole() == "Enquêteur" ){
                inspecteur=player;
                Log.i("TEST",player.getNickname());
            }
        }
        if(inspecteur != null) {
            Toast.makeText(this, inspecteur.getNickname(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "inspecteur non sélectionné", Toast.LENGTH_LONG).show();
        }
    }

    public void interroger(View sender){
        listeMotTimer = new ListeMotTimer();
        listeMotTimer.setArguments(getIntent().getExtras());
        manager.beginTransaction().replace(R.id.container, listeMotTimer).commit();
    }


    private void ajoutPremierJoueur() {
        Resources res = getResources();
        TypedArray avatarArray = res.obtainTypedArray(R.array.array_avatars);
        Drawable drawable = avatarArray.getDrawable(0);
        playerList.add(new Player(drawable, ""));
    }

    public void addPlayer(View sender){
        ajoutPremierJoueur();
        setPlayer(sender);
    }



    public int random(String role){
        Random rand = new Random();
        int i = rand.nextInt(playerList.size());
        if(playerList.get(i).getRole() == role){
            i = random(role);
        }
        return i;
    }

    public int random(String role, String role2){
        Random rand = new Random();
        int i = random("Greffier");
        int i2 = random("Enquêteur");
        if (i == i2){
            i = random(role, role2);
        }
        return i;
    }

    public void genererLesRolesAleatoirement(){
        for (Player player:playerList) {
            player.setRole("Inocents");
        }
        int i = random("Enquêteur");
        playerList.get(i).setRole("Enquêteur");

        i = random("Enquêteur");
        playerList.get(i).setRole("Greffier");

        i = random("Enquêteur","Greffier");
        playerList.get(i).setRole("Coupable");
    }


}
