package com.example.testprojectkuldewtechnologies.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectkuldewtechnologies.R;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {
    private List<String> mList;
    private NestedClickListener nestedClickListener;
    boolean isChecked1 = false;


    public NestedAdapter(List<String> mList, NestedClickListener nestedClickListener) {
        this.mList = mList;
        this.nestedClickListener = nestedClickListener;
    }


    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item, parent, false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {

        String dataModel = mList.get(position);
        holder.nestedItemTv.setText(mList.get(position));
        Log.i("dataModel", "onBindViewHolder: " + dataModel);

        holder.checkbox.setOnCheckedChangeListener(null); // Reset the listener to avoid multiple callbacks
        holder.checkbox.setChecked(isChecked1);

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (nestedClickListener != null) {
                nestedClickListener.onNestedItemClick(dataModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView nestedItemTv;
        private CheckBox checkbox;


        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            nestedItemTv = (TextView) itemView.findViewById(R.id.nestedItemTv);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);


        }
    }
    public interface NestedClickListener {
        void onNestedItemClick(String nestedItem);
    }
}
