package id.co.ifest.marjan.spotevent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by FZulfikar on 9/23/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<Event> events;
    private Context context;

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.event.setText(events.get(position).getEvent());
        holder.price.setText(events.get(position).getPrice());
        Picasso.with(context).load(events.get(position).getUrl_event()).resize(240, 240).into(holder.img_event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_event;
        public TextView event;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            event = (TextView)itemView.findViewById(R.id.event);
            img_event = (ImageView)itemView.findViewById(R.id.img_event);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}
