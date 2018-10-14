package com.example.ianha.findlocation;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
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

        String packageName = getPackageName();
        int resId = getResources().getIdentifier(paperName, "string", packageName);

        String newOut = getResources().getString(resId);
        Resources res = getResources();
        String[] articles = res.getStringArray(resId);
        String combinedArticles = articles[0] + articles[1] + articles[2];
//
        title.setText(oldPaperName);

        try{
            response = predict("plucky-snowfall-219318", "east4", "TCN5419756126499879", combinedArticles);
        } catch(Exception e){
            e.printStackTrace();
        }

        String classNames = "";
        double classScore = 0;
        for (AnnotationPayload annotationPayload : response.getPayloadList()){
            classNames = annotationPayload.getDisplayName();
            classScore = annotationPayload.getClassification().getScore();
            classScore = classScore * 100;
        }

        biasText.setText("Biased " + classNames + ": " + classScore + "%");



    }

    public static PredictResponse predict(String projectId, String computRegion, String modelId, String article) throws IOException {
        PredictionServiceClien predicitonClient = PredictionServiceClient.create();
        ModelName name = ModelName.of(projectId, computeRegion, modelId);
        String content = article;
        TextSnippet texSnippet = TextSnipet.newBuilder().setContent(content).setMimeType("text/plain").build();
        ExamplePayload payload = ExamplePayload.newBuilder().setTextSnippet(textSnippet).build();
        Map<String, String> params = new HashMap<String, String>();
        PredictResponse respones = predictionClient.predict(name, payload, params);
        return response;
    }
}


