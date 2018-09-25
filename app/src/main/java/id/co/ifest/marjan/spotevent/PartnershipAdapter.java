package id.co.ifest.marjan.spotevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PartnershipAdapter extends RecyclerView.Adapter<PartnershipAdapter.ViewHolder>{
    private ArrayList<Sponsor> sponsors;
    private Context context;

    public PartnershipAdapter(Context context, ArrayList<Sponsor> sponsors) {
        this.context = context;
        this.sponsors = sponsors;
    }

    @Override
    public PartnershipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partnership_row_layout, parent, false);
        return new PartnershipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.sponsorDesc.setText(sponsors.get(position).getDesc());
        holder.sponsorName.setText(sponsors.get(position).getName());
        holder.sponsorTime.setText(sponsors.get(position).getTime());
        Picasso.with(context).load(sponsors.get(position).getImageUrl()).resize(500, 300).into(holder.sponsorImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PartnerReadSingleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sponsorObject", sponsors.get(position));
                context.startActivity(intent);
            }
        });
    }

//    private void fragmentJump(Sponsor sponsorSelected){
//        SponsorReadSingleFragment mFragment = new SponsorReadSingleFragment();
//        Bundle mBundle = new Bundle();
//        mBundle.putParcelable("item_selected_key", (Parcelable) sponsorSelected);
//        mFragment.setArguments(mBundle);
//        switchContent(R.id.sponsor_layout, mFragment);
//    }

//    public void switchContent(int id, Fragment fragment){
//        if(context == null)
//            return;
//        if(context instanceof SponsorshipUpcomingEventActivity){
//            SponsorshipUpcomingEventActivity sponsorshipActivity = (SponsorshipUpcomingEventActivity) context;
//            Fragment fragment1 = fragment;
//            SponsorshipUpcomingEventActivity.switchContent(id, fragment1);
//        }
//    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView sponsorImage;
        public TextView sponsorName;
        public TextView sponsorDesc;
        public TextView sponsorTime;

        public ViewHolder(View itemView) {
            super(itemView);

            sponsorName = (TextView)itemView.findViewById(R.id.tv_partner_name);
            sponsorImage = (ImageView)itemView.findViewById(R.id.img_partner);
            sponsorDesc = (TextView)itemView.findViewById(R.id.tv_partner_desc);
            sponsorTime = (TextView)itemView.findViewById(R.id.tv_partner_time);
        }
    }
}
