package hkust.mutualpatientsupport;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Add the following imports to your libraries
import com.loopj.android.http.*;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import org.json.*;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.SyncBasicHttpContext;

import com.google.gson.*;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {
    private Button button_login = null;
    private Button button_register = null;
    private EditText et_account= null;
    private EditText et_pw= null;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_account = (EditText) findViewById(R.id.account);
        et_pw = (EditText) findViewById(R.id.pw);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new LoginActivity.loginButtonListener());
        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new LoginActivity.registerButtonListener());
    }
    private void showSimpleDialog(String message) {
        builder=new AlertDialog.Builder(this);
        builder.setTitle("登入失敗");
        builder.setMessage(message);

        //监听下方button点击事件
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                et_pw.setText("123");
            }
        });
        builder.setNegativeButton("忘記密碼", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        });

        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    class loginButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            String id = et_account.getText().toString();
            String pw = et_pw.getText().toString();
            RequestParams params = new RequestParams();
            try {
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("Email", id); //Pick username from user control
                //Pick password from user control and encypte by md5 before sending to server side
                jsonParams.put("Password",pw);
                //Content type is mandatory otherwise format error would be catched at server side
                StringEntity se = new StringEntity(jsonParams.toString(), ContentType.APPLICATION_JSON);
                System.out.println("start to connect");
                client.removeAllHeaders();
                client.addHeader("Content-Type", "application/json");
                client.post(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Common/Login", se, "application/json", new AsyncHttpResponseHandler(
                ) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String responseMessage = new String(responseBody).trim();
                        //you can get any items from the message for storing cookie
                        System.out.println(responseMessage);
                        JsonObject jsobj = new JsonParser().parse(responseMessage).getAsJsonObject();
                        String CID = jsobj.get("CID").getAsString();
                        System.out.println(CID);
                        if (CID.equals("-1")) {
                            //no record
                            Toast.makeText(getApplicationContext(), "No record of this user", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (CID.equals("-2")) {
                            //password is wrong
                            showSimpleDialog("Password is wrong");
                            return;
                        }
                        if (CID.equals("999")) {
                            //server error or internal exception
                            System.out.println("Server error");
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, Homepage_cgActivity.class);
                        LoginActivity.this.startActivity(intent);
                        //To Do....Cookies,Redirect

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                            throwable) {
                        //A message can used to alert end user here.
                        System.out.println("fail");
                        System.out.println(new String(responseBody));
                        System.out.println("throwable");
                        System.out.println(throwable.getMessage());
                    }
                });

            } catch (JSONException jex) {
                jex.printStackTrace();
            }
        }
    }

    class registerButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // need to check id and pw
            //System.out.println("fuck ex");
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }
}
