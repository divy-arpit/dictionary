package com.example.ecelldictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String url;
    Button search;
    EditText word;
    String s;
    TextView result ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(Button)findViewById(R.id.Search);
        word=(EditText)findViewById(R.id.enterWord);
        result=(TextView)findViewById(R.id.ResultShow);
        s=word.toString();
        url = inflections();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAPIButtonClick(result);
            }
        });


    }


    public void requestAPIButtonClick(View v)
    {
        MyRequest myrequest =new MyRequest(this);
        myrequest.execute(url);
    }

    private String inflections() {
        final String language = "en";
        final String word = s;
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/lemmas/" + language + "/" + word_id;
    }
}
