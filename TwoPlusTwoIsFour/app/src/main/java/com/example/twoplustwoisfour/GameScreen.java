package com.example.twoplustwoisfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {
    private TextView oyunzorluk;
    private TextView skor;
    private TextView geri;
    private TextView sayi;
    private TextView cverdi;
    private EditText cevap;
    private int numberofnumbers;
    private int cskor = 0;
    private int result=0;
    private int prnum=0;
    private int speed=3000;
    private int range=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_game_screen);
        oyunzorluk = findViewById(R.id.oyunzorluk);
        skor = findViewById(R.id.skor);
        skor.setText("" + cskor);
        sayi=findViewById(R.id.sayilar);
        cevap=findViewById(R.id.editText);
        geri = findViewById(R.id.geri);
        cverdi=findViewById(R.id.cverdim);
        cverdi.setClickable(false);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String zorluk = getIntent().getStringExtra("difficulty");
        String style = getIntent().getStringExtra("style");
        oyunzorluk.setText("Zorluk: " + zorluk+ ", Stil: "+style);
        if (zorluk.equalsIgnoreCase("kolay")) {
            numberofnumbers = 3;
        } else if (zorluk.equalsIgnoreCase("orta")) {
            numberofnumbers = 4;
        } else {
            numberofnumbers = 6;
        }
        if (style.contains("Küçük")) {
            range=10;
            speed=1000;
        }else {
            range=100;
            speed=3000;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //(new Handler()).postDelayed(this::startTimer, 3000);
        startGame();
    }
    protected void startGame() {
        cevap.setText("Cevap");

        for (int i=1;i<=numberofnumbers;i++) {
            (new Handler()).postDelayed(this::shownumber, i*speed);

        }
        (new Handler()).postDelayed(this::waitAnswer, (numberofnumbers+1)*speed);

    }
    protected void shownumber(){
        cverdi.setVisibility(View.INVISIBLE);
        cverdi.setClickable(false);
        cevap.setText("");
        Random rand = new Random();
        // Generate random integers in range 0 to 99
        int rand_int1 = rand.nextInt(range);
        if (prnum==rand_int1)
            rand_int1=rand.nextInt(range);
        String plusminus="";
        int rand_int2 = rand.nextInt(2);
        if (rand_int2==0){
            plusminus="+";
            result+=rand_int1;
        }else{
            plusminus="-";
        result-=rand_int1;
        }

        sayi.setText(plusminus+rand_int1);

    }
    protected void waitAnswer(){
        sayi.setText("CEVAP?");
        cevap.setClickable(true);
        cverdi.setVisibility(View.VISIBLE);
        cverdi.setClickable(true);
        cverdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevap.getText().toString().equalsIgnoreCase(""+result)){
                    cskor+=1;
                    skor.setText("" + cskor);
                    result=0;
                    prnum=111;
                    startGame();
                    cevap.setText("Cevap");

                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "BİR SORUYU BİLEMEDİN AMİP!! CEVAP: "+result+" OLACAKTI.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    cevap.setClickable(false);
                    cverdi.setClickable(false);

                }
            }
        });
    }
}
