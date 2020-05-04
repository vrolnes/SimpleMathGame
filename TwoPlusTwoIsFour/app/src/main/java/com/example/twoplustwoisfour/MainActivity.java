package com.example.twoplustwoisfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView startgame;
    private RadioGroup difficulty;
    private RadioButton chosendifficulty;

    private RadioGroup style;
    private RadioButton chosenstyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_main);

        startgame=findViewById(R.id.start);
        difficulty=findViewById(R.id.difficultyradio);
        style=findViewById(R.id.styleRadio);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosendifficulty=findViewById(difficulty.getCheckedRadioButtonId());
                chosenstyle=findViewById(style.getCheckedRadioButtonId());
                if(chosendifficulty==null){
                    chosendifficulty=findViewById(R.id.Kolay);
                }
                if(chosenstyle==null){
                    chosenstyle=findViewById(R.id.ksayı);
                }
                Intent GameScreen= new Intent(getApplicationContext(), com.example.twoplustwoisfour.GameScreen.class);
                GameScreen.putExtra("difficulty",chosendifficulty.getText());
                GameScreen.putExtra("style",chosenstyle.getText());
                startActivity(GameScreen);
            }
    });
}
}