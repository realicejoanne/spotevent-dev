package id.co.ifest.marjan.spotevent;

import java.io.Serializable;

public class Sponsor implements Serializable{
    private String name;
    private String money;
    private String key;

    public Sponsor(String name, String money){
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
