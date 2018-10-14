package com.example.ianha.findlocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class news1Activity extends AppCompatActivity {
    TextView title;
    TextView biasText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);

        String paperName = "";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            paperName = extras.getString("paperName");

        }
        title = (TextView)findViewById(R.id.title);
        biasText = (TextView)findViewById(R.id.biasText);

        String oldPaperName = paperName;
        paperName = paperName.replace(" ", "");

        title.setText(oldPaperName);
        if(paperName.equals("LosAngelesTimes")){
            biasText.setText("Biased Liberal: 95.3%");
        }
        else if(paperName.equals("SanDiegoTribune")){
            biasText.setText("Biased Liberal: 57.6%");
        }
        else if(paperName.equals("SacramentoBee")){
            biasText.setText("Biased Liberal: 73.6%");
        }
        else if(paperName.equals("OrangeCountyRegister")){
            biasText.setText("Biased Conservative: 85.2%");
        }
        else{
            biasText.setText("Biased Liberal: 99.7%");
        }

    }
}
