package com.example.testprojectkuldewtechnologies;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectkuldewtechnologies.Adapter.ItemAdapter;
import com.example.testprojectkuldewtechnologies.Adapter.NestedAdapter;
import com.example.testprojectkuldewtechnologies.Adapter.productFilterAdapter;
import com.example.testprojectkuldewtechnologies.Modal.DataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NestedAdapter.NestedClickListener {

    private RecyclerView recyclerView, recycler;
    private List<DataModel> mList;
    private ItemAdapter adapter;
    private NestedAdapter nestedAdapter;
    private productFilterAdapter productFilterAdapter;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycler = findViewById(R.id.SaveDataRecycler);


        mList = new ArrayList<>();
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Thyroid Disordes");
        nestedList1.add("Diabetes");

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Low Immunity");
        nestedList2.add("obesity");
        nestedList2.add("obesity");
        nestedList2.add("chillblains");
        nestedList2.add("heat Stroke");

        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add("Decorates");
        nestedList3.add("Tea Table");
        nestedList3.add("Wall Paint");
        nestedList3.add("Furniture");
        nestedList3.add("Bedsits");
        nestedList3.add("Certain");
        nestedList3.add("Namkeen and Snacks");
        nestedList3.add("Honey and Spreads");

        List<String> nestedList4 = new ArrayList<>();
        nestedList4.add("Test14");
        nestedList4.add("Test24");
        nestedList4.add("Test34");
        nestedList4.add("Test44");
        nestedList4.add("Test54");
        nestedList4.add("Test64");


        List<String> nestedList5 = new ArrayList<>();
        nestedList5.add("Test15");
        nestedList5.add("Test25");
        nestedList5.add("Test35");
        nestedList5.add("Test45");
        nestedList5.add("Test55");
        nestedList5.add("Test65");

        List<String> nestedList6 = new ArrayList<>();
        nestedList6.add("Test16");
        nestedList6.add("Test26");
        nestedList6.add("Test36");
        nestedList6.add("Test46");
        nestedList6.add("Test56");
        nestedList6.add("Test66");

        mList.add(new DataModel(nestedList1, "SHAKTIJAL"));
        mList.add(new DataModel(nestedList2, "Endocrine/Hormonal Disorders"));
        mList.add(new DataModel(nestedList3, "General Disorders"));
        mList.add(new DataModel(nestedList4, "Skin,Hair,Naile"));
        mList.add(new DataModel(nestedList5, "infectious System"));
        mList.add(new DataModel(nestedList6, "Respiratory System"));

        adapter = new ItemAdapter(mList, this);
        recyclerView.setAdapter(adapter);
        Log.i("recyclerView", "onCreate: " + recyclerView.toString());

    }

    public void getData() {
        if (recycler != null) {
            Log.d("fjfhd", "sssss");
            recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//            recyclerView.setLayoutManager(layoutManager)
            productFilterAdapter = new productFilterAdapter(MainActivity.this,stringList);
//            Log.i("productFilterAdapter", "onCreate: "+stringList.size());
//            productFilterAdapter.setCheckBoxClickListener(this);
            recycler.setAdapter(productFilterAdapter);
        }
    }

    @Override
    public void onNestedItemClick(String nestedItem) {
        if (stringList.contains(nestedItem)) {
            stringList.remove(nestedItem);
        } else {
            stringList.add(nestedItem);
        }
        getData();
    }
}
