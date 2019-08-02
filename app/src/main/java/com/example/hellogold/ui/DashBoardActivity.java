package com.example.hellogold.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellogold.R;
import com.example.hellogold.adapters.RatesAdapter;
import com.example.hellogold.models.rates.RatesApiResponse;
import com.example.hellogold.network.ApiClient;
import com.example.hellogold.network.ApiServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {
    ApiServices apiInterface;
    ProgressDialog mProgressDialog;

    private ArrayList<String> timeStamp = new ArrayList<>();
    private ArrayList<String> buy = new ArrayList<>();
    private ArrayList<String> sell = new ArrayList<>();
    private ArrayList<String> spotPrice = new ArrayList<>();
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        SwipeRefreshLayout pullToRefresh = findViewById(R.id.refresh);
        pullToRefresh.setOnRefreshListener(() -> {
            populateUi();
            pullToRefresh.setRefreshing(false);
        });
        apiInterface = ApiClient.ratesInfo().create(ApiServices.class);
        Button refresh = findViewById(R.id.btn_refresh);
        refresh.setOnClickListener(v -> {
            populateUi();
        });

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        TextView tv_email=findViewById(R.id.tv_email);
        tv_email.setText(email);
        populateUi();
    }


    private void populateUi(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        timeStamp.clear();
        buy.clear();
        sell.clear();
        spotPrice.clear();
        count=0;
        for (int i=0;i<6;i++){
            getRatesData();
        }
    }

    private void initRecyclerView(ArrayList<String> timeStamp,ArrayList<String> buy,ArrayList<String> sell,ArrayList<String> spotPrice){
        RecyclerView recyclerView = findViewById(R.id.rv_rates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RatesAdapter ratesAdapter = new RatesAdapter(timeStamp,buy,sell,spotPrice,this);
        recyclerView.setAdapter(ratesAdapter);
    }

    public void getRatesData(){

        apiInterface.getRates().enqueue(new Callback<RatesApiResponse>() {
            @Override
            public void onResponse(Call<RatesApiResponse> call, Response<RatesApiResponse> response) {
                if(response.code()==200){
                    timeStamp.add(response.body().getData().getTimestamp());
                    buy.add(String.valueOf(response.body().getData().getBuy()));
                    sell.add(String.valueOf(response.body().getData().getSell()));
                    spotPrice.add(String.valueOf(response.body().getData().getSpotPrice()));
                    count++;
                    if(count==5){
                        initRecyclerView(timeStamp,buy,sell,spotPrice);
                        mProgressDialog.dismiss();
                        count=0;
                    }else{

                    }
                }else{
                    mProgressDialog.dismiss();
                    new AlertDialog.Builder(DashBoardActivity.this)
                            .setTitle("Error")
                            .setMessage("Error getting Rates data from the Api")
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<RatesApiResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                mProgressDialog.dismiss();

            }
        });

    }
    private void makeToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
