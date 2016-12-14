package hkust.mutualpatientsupport;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RegisterActivity extends AppCompatActivity {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ((Button) findViewById(R.id.button_location)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }
    void upload(){
        String FirstName = ((EditText)findViewById(R.id.et_register_caregiver_firstname)).getText().toString();
        String Gender = ((EditText) findViewById(R.id.et_register_caregiver_gender)).getText().toString();
        String LastName = ((EditText) findViewById(R.id.et_register_caregiver_lastname)).getText().toString();
        String Phone = ((EditText) findViewById(R.id.et_register_caregiver_phone)).getText().toString();
        String Email = ((EditText) findViewById(R.id.et_register_caregiver_email)).getText().toString();
        String Password = ((EditText) findViewById(R.id.et_register_caregiver_password)).getText().toString();
        String BuildingAddress = ((EditText) findViewById(R.id.et_register_caregiver_add1)).getText().toString();
        String StreetAddress = ((EditText) findViewById(R.id.et_register_caregiver_add2)).getText().toString();
        RequestParams params = new RequestParams();
        try {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("FirstName", FirstName);
            jsonParams.put("Gender",Gender);
            jsonParams.put("LastName", LastName);
            jsonParams.put("Phone",Phone);
            jsonParams.put("Email",Email);
            jsonParams.put("Password", Password);
            jsonParams.put("BuildingAddress",BuildingAddress);
            jsonParams.put("StreetAddress", StreetAddress);
            //Content type is mandatory otherwise format error would be catched at server side
            StringEntity se = new StringEntity(jsonParams.toString(), ContentType.APPLICATION_JSON);
            System.out.println("start to connect");
            client.removeAllHeaders();
            client.addHeader("Content-Type", "application/json");
            client.post(getApplicationContext(), "http://mspcognix.azurewebsites.net/api/Caregiver/Register", se, "application/json", new AsyncHttpResponseHandler(
            ) {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String responseMessage = new String(responseBody).trim();
                    //you can get any items from the message for storing cookie
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
                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);
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
    private void showSimpleDialog(String message) {
        builder=new AlertDialog.Builder(this);
        builder.setTitle("登入失敗");
        builder.setMessage(message);

        //监听下方button点击事件
        builder.setPositiveButton("前往登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });
        //builder.setNegativeButton("忘記密碼", new DialogInterface.OnClickListener() {
        //    @Override
        //    public void onClick(DialogInterface dialogInterface, int i) {
        //        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        //    }
        //});

        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}

