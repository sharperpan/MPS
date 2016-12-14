package hkust.mutualpatientsupport;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage_paActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_pa);


        ((Button) findViewById(R.id.button_name_card)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, NameCardActivity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.button_contact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, contact_Activity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.button_news)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, NewsActivity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.button_location)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, MapsActivity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.button_gohome)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, MapsActivity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.button_reminder)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homepage_paActivity.this, reminder_detail_cgActivity.class);
                Homepage_paActivity.this.startActivity(intent);
            }
        });
    }
}
