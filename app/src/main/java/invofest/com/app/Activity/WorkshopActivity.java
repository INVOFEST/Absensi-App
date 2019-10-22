package invofest.com.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import invofest.com.app.Adapter.AdapterEvent;
import invofest.com.app.Converter.BaseListResponse;
import invofest.com.app.Model.User;
import invofest.com.app.Network.ApiUtil;
import invofest.com.app.Network.GetEvent;
import invofest.com.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkshopActivity extends AppCompatActivity {

    private ImageView ivScan;
    private RecyclerView rvEvent;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> users;
    private RecyclerView.Adapter adapter;
    private GetEvent getEvent;
    private RelativeLayout rootErrorView, rootEmptyView;
    private retrofit2.Call<BaseListResponse<User>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        getSupportActionBar().setTitle("Workshop Cuy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        scanLogic();
    }

    private void scanLogic() {
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkshopActivity.this, QRSeminar.class);
                startActivity(a);
            }
        });
    }

    private void initViews() {
        ivScan = findViewById(R.id.ivScan);
        getEvent = ApiUtil.getEvent();
        rvEvent = findViewById(R.id.rvEvent);
        layoutManager = new LinearLayoutManager(this);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        rvEvent.setHasFixedSize(true);
        rootEmptyView = findViewById(R.id.root_empty_view);
        rootErrorView =  findViewById(R.id.root_error_view);
    }

    private void fetchData() {
        call = getEvent.getWorkshop();
        call.enqueue(new Callback<BaseListResponse<User>>() {
            @Override
            public void onResponse(Call<BaseListResponse<User>> call, Response<BaseListResponse<User>> response) {
                if (response.isSuccessful()){
                    BaseListResponse a = response.body();
                    if (a.getStatus()){
                        if (a.getData().isEmpty()){
                            rootErrorView.setVisibility(View.GONE);
                            rootEmptyView.setVisibility(View.VISIBLE);
                        }else {
                            users = a.getData();
                            adapter = new AdapterEvent(WorkshopActivity.this, users);
                            rvEvent.setAdapter(adapter);
                            System.err.println("datane "+a.toString());
                            rootErrorView.setVisibility(View.GONE);
                            rootEmptyView.setVisibility(View.GONE);
                        }
                    }else {
                        rootErrorView.setVisibility(View.GONE);
                        rootEmptyView.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(WorkshopActivity.this, "Nyaris", Toast.LENGTH_SHORT).show();
                    rootErrorView.setVisibility(View.VISIBLE);
                    rootEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<User>> call, Throwable t) {
                Toast.makeText(WorkshopActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                System.err.println("kuwuk" + t.getMessage().toString());
                rootErrorView.setVisibility(View.VISIBLE);
                rootEmptyView.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
