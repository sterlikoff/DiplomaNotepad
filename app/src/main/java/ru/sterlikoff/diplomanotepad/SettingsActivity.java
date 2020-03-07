package ru.sterlikoff.diplomanotepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.sterlikoff.diplomanotepad.components.App;

public class SettingsActivity extends AppCompatActivity {

    protected void startSetPinActivity() {

        Intent intent = new Intent(SettingsActivity.this, SetPinActivity.class);
        startActivity(intent);
        finish();

    }

    protected void initViews() {

        Button createPinBtn = findViewById(R.id.btn_create_pin);
        Button changePinBtn = findViewById(R.id.btn_change_pin);

        boolean hasPin = App.getKeyStore().hasPin();

        createPinBtn.setVisibility(hasPin ? View.GONE : View.VISIBLE);
        changePinBtn.setVisibility(hasPin ? View.VISIBLE : View.GONE);

        createPinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSetPinActivity();
            }
        });

        changePinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, PinActivity.class);
                intent.putExtra("resultCode", App.RESULT_PIN_CODE);

                startActivityForResult(intent, App.RESULT_PIN_CODE);

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case App.RESULT_PIN_CODE:

                startSetPinActivity();
                break;

        }

    }
}
