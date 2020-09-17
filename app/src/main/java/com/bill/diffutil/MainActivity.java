package com.bill.diffutil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnUpdate;
    RecyclerView rvData;
    List<String> data = new ArrayList<>();
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);

        rvData = findViewById(R.id.rv_data);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(this));

        initData();

        final MyAdapter myAdapter = new MyAdapter(data);
        rvData.setAdapter(myAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> newList = new ArrayList<>();
                String aa = null;
                for (int i=0; i<3; i++){
                    newList.add(UUID.randomUUID().toString());
                }
                myAdapter.insertData(newList);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> updateList = new ArrayList<>();
                String aa = null;
                for (int i=0; i<3; i++) {
                    aa = UUID.randomUUID().toString();
                    updateList.add(aa);
                }
                updateList.add("1");
                updateList.add("1");
                click++;
                if (click == 2){
                    updateList.add("1");
                }

                myAdapter.updateData(updateList);
            }
        });
    }





    private void initData() {
        for (int i=0; i<3; i++)
            data.add(UUID.randomUUID().toString());
    }
}