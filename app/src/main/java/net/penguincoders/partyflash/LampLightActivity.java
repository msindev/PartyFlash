package net.penguincoders.partyflash;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class LampLightActivity extends AppCompatActivity {

    String bgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_light);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            bgColor = extras.getString("bgColor");
        }
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(bgColor));
    }
}
