package com.example.testprojectkuldewtechnologies.Adapter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectkuldewtechnologies.R;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {
    public List<String> mList;
    private NestedClickListener nestedClickListener;
    boolean isChecked1 = false;



    public NestedAdapter(List<String> mList, NestedClickListener nestedClickListener) {
        this.mList = mList;
        this.nestedClickListener = nestedClickListener;
    }
    public void updateData(List<String> newList) {
        mList = newList;
        notifyDataSetChanged();
    }

    public void setCheckboxState(boolean isChecked) {
        isChecked1 = isChecked;
        notifyDataSetChanged();
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

        holder.checkbox.setOnCheckedChangeListener(null);

        holder.checkbox.setChecked(isChecked1);
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (nestedClickListener != null) {
                nestedClickListener.onNestedItemClick(dataModel);
            }
        });

        holder.nestedItemTv.setOnClickListener(new View.OnClickListener() {
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
                        mList.set(position, newText);  // Update the dataset
                        notifyItemChanged(position);   // Notify adapter about the data change

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

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
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
