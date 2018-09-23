package id.co.ifest.marjan.spotevent;

/**
 * Created by FZulfikar on 9/23/2018.
 */

public class Event {
    private String nama_event;
    private String url_event;
    private String price;

    public String getUrl_event() {
        return url_event;
    }

    public void setUrl_event(String url_event) {
        this.url_event = url_event;
    }

    public String getEvent() {
        return nama_event;
    }

    public void setEvent(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
