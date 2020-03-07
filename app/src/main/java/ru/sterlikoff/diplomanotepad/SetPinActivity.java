package ru.sterlikoff.diplomanotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.sterlikoff.diplomanotepad.components.App;

public class SetPinActivity extends AppCompatActivity {

    EditText pinEdit;

    protected void initViews() {

        Button saveBtn = findViewById(R.id.setting_submit_btn);
        pinEdit = findViewById(R.id.pin_edit);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String newPin = pinEdit.getText().toString();

                if (newPin.length() == 4) {

                    App.getKeyStore().setPin(newPin);
                    finish();

                } else {

                    Toast.makeText(SetPinActivity.this, R.string.minPingLengthMessage, Toast.LENGTH_LONG).show();

                }

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        initViews();

    }
}
