package projet.jegrenier.lefevre.petitsmeutres.model;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import projet.jegrenier.lefevre.petitsmeutres.R;

/**
 * Created by hugol on 16/03/2016.
 */
public class Player {
    private Drawable avatar;
    private String nickname;
    private String role;


    public Player(Drawable avatar, String nickname) {
        this.avatar = avatar;
        this.nickname = nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;
    }


}
