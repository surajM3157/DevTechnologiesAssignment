package com.example.testprojectkuldewtechnologies.Adapter;

import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectkuldewtechnologies.Modal.DataModel;
import com.example.testprojectkuldewtechnologies.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<DataModel> mList;
    public List<String> list = new ArrayList<>();
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

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Edit Text");
                final EditText editText = new EditText(v.getContext());
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                alertDialog.setView(editText);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newText = editText.getText().toString();
                        DataModel model = mList.get(holder.getAdapterPosition());
                        model.itemText = newText;  // Update the text directly in the DataModel field
                        notifyItemChanged(holder.getAdapterPosition());   // Notify adapter about the data change

                        Toast.makeText(v.getContext(), "Updated Text: " + newText, Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel
                    }
                });
                alertDialog.show();
            }
        });

      /**  holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on cv view
                list.clear(); // Clear the list in the NestedAdapter
                adapter1.updateData(list); // Update NestedAdapter data
                adapter1.setCheckboxState(false); // Set checkbox state to false in NestedAdapter
                holder.nestedRecyclerView.getAdapter().notifyDataSetChanged(); // Notify NestedAdapter about the change

                // Call the method in productFilterAdapter to remove the item
                productFilterAdapter.removeItem(position);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

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