package hkust.mutualpatientsupport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        flipper.startFlipping();
    }
}
