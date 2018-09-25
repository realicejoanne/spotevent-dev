package id.co.ifest.marjan.spotevent;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.System.out;

public class Sponsor implements Serializable{
    private String name, desc;
    private String imageUrl, money, time;
    private String targetMoney;

    public Sponsor(){

    }

    public String getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(String targetMoney) {
        this.targetMoney = targetMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
