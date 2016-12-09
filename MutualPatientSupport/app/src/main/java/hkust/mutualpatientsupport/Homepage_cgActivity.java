package hkust.mutualpatientsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage_cgActivity extends AppCompatActivity {
    private Button button_location = null;
    private Button button_news = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_cg);
        button_location = (Button)findViewById(R.id.button_location);
        button_location.setOnClickListener(new Homepage_cgActivity.locationButtonListener());
        button_news = (Button)findViewById(R.id.button_news);
        button_news.setOnClickListener(new Homepage_cgActivity.newsButtonListener());
    }
    class locationButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            Intent intent = new Intent();
            intent.setClass(Homepage_cgActivity.this, MapsActivity.class);
            Homepage_cgActivity.this.startActivity(intent);
        }
    }
    class newsButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            Intent intent = new Intent();
            intent.setClass(Homepage_cgActivity.this, NewsActivity.class);
            Homepage_cgActivity.this.startActivity(intent);
        }
    }
}
