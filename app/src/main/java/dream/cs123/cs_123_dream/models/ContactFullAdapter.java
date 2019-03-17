package dream.cs123.cs_123_dream.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dream.cs123.cs_123_dream.ContactEdit;
import dream.cs123.cs_123_dream.ContactFullView;
import dream.cs123.cs_123_dream.ContactLookEmergency;
import dream.cs123.cs_123_dream.CustomItemClickListener;
import dream.cs123.cs_123_dream.R;
import dream.cs123.cs_123_dream.ViewContact;
import io.realm.OrderedRealmCollection;
import dream.cs123.cs_123_dream.models.Contact;
import io.realm.RealmRecyclerViewAdapter;

public class ContactFullAdapter extends RealmRecyclerViewAdapter<Contact, ContactFullAdapter.MViewHolder> {

    private static final String TAG = "ContactFullAdapter";
    
    //private CustomItemClickListener listener; // not rly sure
    private OrderedRealmCollection<Contact> temp;
    private Context context;

    public ContactFullAdapter(Context context, OrderedRealmCollection<Contact> data) {
        super(data,true);
        this.temp = data;
        this.context = context;
        //setHasStableIds(true);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        MViewHolder mvh = new MViewHolder(itemView);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final Contact obj = getItem(position);
        holder.contact_name.setText(obj.get_name());
        holder.contact_number.setText(obj.get_contact_number());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + position);

                if (context instanceof ContactFullView) {
                    Intent intent = new Intent(context, ContactEdit.class);
                    intent.putExtra("contact_id", obj.get_id());
                    context.startActivity(intent);
                } else if (context instanceof ContactLookEmergency) {
                    Intent intent = new Intent(context, ViewContact.class); // edit this
                    intent.putExtra("contact_id", obj.get_id());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }

    public static class MViewHolder extends RecyclerView.ViewHolder {
        public TextView contact_name;
        public TextView contact_number;
        public LinearLayout ll;
        //public Contact data;
        MViewHolder(View view) {
            super(view);
            contact_name = (TextView) view.findViewById(R.id.contact_name);
            contact_number = (TextView) view.findViewById(R.id.contact_number);
            ll = (LinearLayout) view.findViewById(R.id.contact_ll_layout);
        }
    }

}
