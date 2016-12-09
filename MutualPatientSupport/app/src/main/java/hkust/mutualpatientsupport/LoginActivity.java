package hkust.mutualpatientsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button button_login = null;
    private Button button_register = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new LoginActivity.loginButtonListener());
        button_register = (Button)findViewById(R.id.button_register);
        button_register.setOnClickListener(new LoginActivity.registerButtonListener());
    }
    class loginButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, Homepage_cgActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }
    class registerButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }
}
