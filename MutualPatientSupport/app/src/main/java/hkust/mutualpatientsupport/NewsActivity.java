package hkust.mutualpatientsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private ImageButton new1 =null;
    private ImageButton new2 =null;
    private ListView news_list;
    private List<String> news_url= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        flipper.startFlipping();
        news_url.add("http://www.baidu.com");
        news_url.add("http://www.google.com");
        new1 =(ImageButton) findViewById(R.id.imageButton_new1);
        new1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this, news_detailActivity.class);
                intent.putExtra("url", news_url.get(0));
                NewsActivity.this.startActivity(intent);
            }
        });
        new2 =(ImageButton) findViewById(R.id.imageButton_new2);
        new2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this, news_detailActivity.class);
                intent.putExtra("url",  news_url.get(1));
                NewsActivity.this.startActivity(intent);
            }
        });
        news_list = (ListView) findViewById(R.id.news_list);
        news_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        news_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String url = news_url.get(position);
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this, news_detailActivity.class);
                intent.putExtra("url", url);
                NewsActivity.this.startActivity(intent);
            }
        });
    }

    private List<String> getData(){

        List<String> data = new ArrayList<>();
        data.add("百度");
        data.add("谷歌");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }
}
