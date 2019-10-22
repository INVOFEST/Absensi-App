package invofest.com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

import invofest.com.app.Activity.SeminarActivity;
import invofest.com.app.Activity.TalkshowActivity;
import invofest.com.app.Activity.WorkshopActivity;

public class MainActivity extends AppCompatActivity {

    private Button btSeminar, btWorkshop, btTalkshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        actionLogic();
    }

    private void initView() {
        btSeminar = findViewById(R.id.btSeminar);
        btWorkshop = findViewById(R.id.btWorkshop);
        btTalkshow = findViewById(R.id.btTalkshow);
    }

    private void actionLogic() {
        btSeminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, SeminarActivity.class);
                startActivity(a);
            }
        });

        btWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, WorkshopActivity.class);
                startActivity(a);
            }
        });

        btTalkshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, TalkshowActivity.class);
                startActivity(a);
            }
        });
    }
}
