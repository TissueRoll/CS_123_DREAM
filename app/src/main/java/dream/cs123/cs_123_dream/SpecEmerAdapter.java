package dream.cs123.cs_123_dream;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class SpecEmerAdapter extends RecyclerView.Adapter<SpecEmerAdapter.M2ViewHolder> {

    private static final String TAG = "SpecEmerAdapter";
    
    private ArrayList<String> forms;
    //private int row;
    private Context context;
    private CustomItemClickListener mListener;
    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }

    public SpecEmerAdapter (Context context, ArrayList<String> forms) {
        this.forms = forms;
        //this.row = row;
        this.context = context;
    }

    @Override
    public M2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_se_item, parent, false);
        M2ViewHolder mvh = new M2ViewHolder(itemView);
        if (context instanceof VerySpecificEmergency) {
            mvh.button.setBackground(context.getResources().getDrawable(R.drawable.greenbutton));
        }
        return mvh;
    }

    @Override
    public void onBindViewHolder(M2ViewHolder holder, final int position) {
        holder.button.setText(forms.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof SpecificEmergency) {
                    Intent intent = new Intent(context, DisplayInfoEmergency.class);
                    String sel = forms.get(position);
                    if (sel == null) {
                        Log.d(TAG, "onClick: shit its null");
                    } else {
                        Log.d(TAG, "onClick: " + sel);
                    }
                    intent.putExtra("EMERGENCY_CODE", sel);
                    context.startActivity(intent);
                } else if (context instanceof VerySpecificEmergency) {
                    Intent intent = new Intent(context, DisplayInfoBrowse.class);
                    String sel = forms.get(position);
                    if (sel == null) {
                        Log.d(TAG, "onClick: shit its null");
                    } else {
                        Log.d(TAG, "onClick: " + sel);
                    }
                    intent.putExtra("EMERGENCY_CODE", sel);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void setOnItemClick(CustomItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public class M2ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout fLayout;
        Button button;
        public M2ViewHolder (View v) {
            super(v);
            fLayout = (LinearLayout) v.findViewById(R.id.ll2);
            button = (Button) v.findViewById(R.id.button_se);
        }
    }


}
