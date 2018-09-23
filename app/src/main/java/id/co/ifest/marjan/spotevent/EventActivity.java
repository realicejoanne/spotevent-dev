package id.co.ifest.marjan.spotevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private final String nama_event[] = {
            "Java Jazz 2018",
            "Ifest 2018",
            "On Off Fest 2018",
            "WTF 2018",
            "Soundrenaline 2018",
            "Java Jazz 2018",
            "Ifest 2018",
            "On Off Fest 2018",
            "WTF 2018",
            "Soundrenaline 2018"
    };

    private final String price[] = {
            "Start From 100.000",
            "Start From 25.000",
            "Start From 300.000",
            "Start From 500.000",
            "Sraer From 250.000",
            "Start From 100.000",
            "Start From 25.000",
            "Start From 300.000",
            "Start From 500.000",
            "Sraer From 250.000"
    };

    private final String url_events[] = {
            "https://i0.wp.com/www.divertone.com/wp-content/uploads/2018/02/logo-color.png?fit=315%2C275&ssl=1",
            "http://ifest.unpad.ac.id/wp-content/uploads/2018/07/cap.png",
            "http://www.onofffestival.com/images/logo-venuedate.png",
            "http://wethefest.com/2017/assets/images/logo.png",
            "https://soundrenaline.co.id/assets/web/phase3/images/banner-logo.png",
            "https://i0.wp.com/www.divertone.com/wp-content/uploads/2018/02/logo-color.png?fit=315%2C275&ssl=1",
            "http://ifest.unpad.ac.id/wp-content/uploads/2018/07/cap.png",
            "http://www.onofffestival.com/images/logo-venuedate.png",
            "http://wethefest.com/2017/assets/images/logo.png",
            "https://soundrenaline.co.id/assets/web/phase3/images/banner-logo.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        viewEvent();
    }

    private void viewEvent() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Event> event = prepareData();
        EventAdapter adapter = new EventAdapter(getApplicationContext(),event);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Event> prepareData() {
        ArrayList<Event> events = new ArrayList<>();
        for(int i=0;i<nama_event.length;i++){
            Event event = new Event();
            event.setEvent(nama_event[i]);
            event.setPrice(price[i]);
            event.setUrl_event(url_events[i]);
            events.add(event);
        }
        return events;
    }
}
