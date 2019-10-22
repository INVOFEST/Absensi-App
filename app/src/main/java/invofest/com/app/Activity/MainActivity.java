package invofest.com.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import invofest.com.app.R;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSeminar, ivWorkshop, ivTalkshow;
    private TextView greetText;
    private ImageView greetImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        initView();
        actionLogic();
        greeting();
    }

    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText("Selamat Pagi \n      Panitia Invofest");
            greetText.setTextColor(Color.WHITE);
            greetImg.setImageResource(R.drawable.pagi);
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText("Selamat Siang \n     Panitia Invofest");
            greetText.setTextColor(Color.WHITE);
            greetImg.setImageResource(R.drawable.siang);
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText("Selamat Sore \n      Panitia Invofest");
            greetText.setTextColor(Color.WHITE);
            greetImg.setImageResource(R.drawable.sore);
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText("Selamat Malam \n     Panitia Invofest");
            greetText.setTextColor(Color.WHITE);
            greetImg.setImageResource(R.drawable.malam);
        }
    }

    private void initView() {
        ivSeminar = findViewById(R.id.ivSeminar);
        ivWorkshop = findViewById(R.id.ivWorkshop);
        ivTalkshow = findViewById(R.id.ivTalkshow);
        greetImg = findViewById(R.id.greetImg);
        greetText = findViewById(R.id.greetText);
    }

    private void actionLogic() {
        ivSeminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, SeminarActivity.class);
                startActivity(a);
            }
        });

        ivWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, WorkshopActivity.class);
                startActivity(a);
            }
        });

        ivTalkshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, TalkshowActivity.class);
                startActivity(a);
            }
        });
    }
}
