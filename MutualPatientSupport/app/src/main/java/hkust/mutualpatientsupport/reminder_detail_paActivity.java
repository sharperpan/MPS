package hkust.mutualpatientsupport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class reminder_detail_paActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail_pa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_reminder_detail_pa);
        setSupportActionBar(toolbar);

    }

}
