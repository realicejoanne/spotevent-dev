package id.co.ifest.marjan.spotevent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SponsorshipAdapter extends RecyclerView.Adapter<SponsorshipAdapter.ViewHolder>{
    private ArrayList<Sponsor> sponsors;
    private Context context;
    FirebaseDataListener listener;

    public SponsorshipAdapter(Context context, ArrayList<Sponsor> sponsors) {
        this.context = context;
        this.sponsors = sponsors;
    }

    @Override
    public SponsorshipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsorship_row_layout, parent, false);
        return new SponsorshipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sponsorDesc.setText(sponsors.get(position).getDesc());
        holder.sponsorName.setText(sponsors.get(position).getName());
        holder.sponsorMoney.setText(sponsors.get(position).getMoney());
        holder.sponsorTime.setText(sponsors.get(position).getTime());
        Picasso.with(context).load(sponsors.get(position).getImageUrl()).resize(500, 300).into(holder.sponsorImage);
    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView sponsorImage;
        public TextView sponsorName;
        public TextView sponsorDesc;
        public TextView sponsorMoney;
        public TextView sponsorTime;

        public ViewHolder(View itemView) {
            super(itemView);

            sponsorName = (TextView)itemView.findViewById(R.id.tv_sponsor_name);
            sponsorImage = (ImageView)itemView.findViewById(R.id.img_sponsor);
            sponsorDesc = (TextView)itemView.findViewById(R.id.tv_sponsor_desc);
            sponsorMoney = (TextView)itemView.findViewById(R.id.tv_sponsor_money);
            sponsorTime = (TextView)itemView.findViewById(R.id.tv_sponsor_time);
        }
    }

    public interface FirebaseDataListener{
        void onDeleteData(Sponsor sponsor, int position);
    }
}
