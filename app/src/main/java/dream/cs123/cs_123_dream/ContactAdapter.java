package dream.cs123.cs_123_dream;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import dream.cs123.cs_123_dream.models.Contact;

public class ContactAdapter extends RealmBaseAdapter<Contact> implements ListAdapter {

    private ContactView activity;

    private static class ViewHolder {
        TextView contactName;
        TextView contactNumber;
    }

    ContactAdapter(ContactView activity, OrderedRealmCollection<Contact> data) {
        super(data);
        this.activity = activity;
    }

    // probably edit this so that your view can also display number
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.contactName = (TextView) convertView.findViewById(R.id.contact_name);
            viewHolder.contactNumber = (TextView) convertView.findViewById(R.id.contact_number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            Contact contact = adapterData.get(position);
            viewHolder.contactName.setText(contact.get_name());
            viewHolder.contactNumber.setText(contact.get_contact_number()); // consider making contact number a string
        }

        return convertView;
    }

    /* changes the thing to done in the original
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                Contact contact = adapterData.get(position);
                activity.changeContactName(contact.get_id());
            }
        }
    };
    */
}
