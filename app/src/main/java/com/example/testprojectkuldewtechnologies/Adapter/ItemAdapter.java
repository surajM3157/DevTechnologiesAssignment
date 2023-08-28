package com.example.testprojectkuldewtechnologies.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectkuldewtechnologies.Modal.DataModel;
import com.example.testprojectkuldewtechnologies.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> /*implements NestedAdapter.NestedClickListener*/ {

    private List<DataModel> mList;
    private List<String> list = new ArrayList<>();
    private NestedAdapter.NestedClickListener nestedClickListener;

    public ItemAdapter(List<DataModel> mList, NestedAdapter.NestedClickListener nestedClickListener) {
        this.mList = mList;
        this.nestedClickListener = nestedClickListener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        DataModel model = mList.get(position);
        Log.i("dataModel1", "onBindViewHolder: " + model);
        holder.mTextView.setText(model.getItemText());

        boolean isExpandable = model.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        } else {
            holder.mArrowImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }

        NestedAdapter adapter1 = new NestedAdapter(list, nestedClickListener);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter1);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setExpandable(!model.isExpandable());
                list = model.getNestedList();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    @Override
//    public void onNestedItemClick(String nestedItem) {
//        // Update the checked state of the nested item in the DataModel
////        int adapterPosition = holder.getAdapterPosition(); // Get the adapter position of the main item
////        DataModel dataModel = mList.get(adapterPosition);
////        int nestedIndex = dataModel.getNestedList().indexOf(nestedItem);
////
////        if (nestedIndex != -1) {
////            boolean isChecked = !dataModel.isNestedItemChecked(nestedIndex);
////            dataModel.setNestedItemChecked(nestedIndex, isChecked);
////            notifyItemChanged(adapterPosition);
////        }
//    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public RelativeLayout expandableLayout;
        public TextView mTextView;
        public ImageView mArrowImage;
        public RecyclerView nestedRecyclerView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }

}