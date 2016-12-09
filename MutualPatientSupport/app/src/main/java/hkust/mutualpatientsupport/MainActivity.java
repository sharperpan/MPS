package hkust.mutualpatientsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_cg = null;
    private Button button_pa = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_cg = (Button)findViewById(R.id.button_direct_cg);
        button_cg.setOnClickListener(new cgButtonListener());
        button_pa = (Button)findViewById(R.id.button_direct_pt);
        button_pa.setOnClickListener(new ptButtonListener());
    }
    class cgButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

    class ptButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MapsActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
}
