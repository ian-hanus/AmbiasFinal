package com.example.ianha.findlocation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class papersActivity extends AppCompatActivity {
    Button paper1;
    Button paper2;
    Button paper3;
    Button paper4;
    Button paper5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);

        paper1 = (Button)findViewById(R.id.paper1);
        paper2 = (Button)findViewById(R.id.paper2);
        paper3 = (Button)findViewById(R.id.paper3);
        paper4 = (Button)findViewById(R.id.paper4);
        paper5 = (Button)findViewById(R.id.paper5);

        paper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNews1(paper1);
            }
        });

        paper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNews1(paper2);
            }
        });

        paper3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNews1(paper3);
            }
        });

        paper4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNews1(paper4);
            }
        });

        paper5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNews1(paper5);
            }
        });

        new findPapers().execute();



    }



    public void openNews1(Button id) {
        Intent intent = new Intent(this, news1Activity.class);
        intent.putExtra("paperName", id.getText());
        startActivity(intent);
    }


    public class findPapers extends AsyncTask<Void, Void, Void> {
        String words;
        String topic1;
        String topic2;
        String topic3;
        String topic4;
        String topic5;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/User:Littlebird12321/sandbox").get();
                words = doc.text();
                String[] allTopics = words.split("#");
                topic2 = allTopics[2];
                topic3 = allTopics[3];
                topic1 = allTopics[1];
                topic4 = allTopics[4];
                topic5 = allTopics[5];

            }catch(Exception e){e.printStackTrace();}


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            paper1.setText(topic1);
            paper2.setText(topic2);
            paper3.setText(topic3);
            paper4.setText(topic4);
            paper5.setText(topic5);
        }
    }
}
