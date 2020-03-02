package ru.sterlikoff.diplomanotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.sterlikoff.diplomanotepad.components.App;

public class PinActivity extends AppCompatActivity {

    private String currentPin = "";

    View pinItem1, pinItem2, pinItem3, pinItem4;

    protected void checkPin() {

        if (currentPin.length() < 4) return;

        if (App.getKeyStore().checkPin(currentPin)) {

            startApp();

        } else {

            Toast.makeText(this, R.string.incorrectPinMessage, Toast.LENGTH_LONG).show();
            currentPin = "";
            updatePinViews();

        }

    }

    protected void updatePinViews() {

        pinItem1.setBackground(getDrawable(currentPin.length() >= 1 ? R.drawable.active_circle : R.drawable.circle));
        pinItem2.setBackground(getDrawable(currentPin.length() >= 2 ? R.drawable.active_circle : R.drawable.circle));
        pinItem3.setBackground(getDrawable(currentPin.length() >= 3 ? R.drawable.active_circle : R.drawable.circle));
        pinItem4.setBackground(getDrawable(currentPin.length() >= 4 ? R.drawable.active_circle : R.drawable.circle));

    }

    public void onNumberButtonClick(View v) {

        Button button = (Button)v;
        currentPin += button.getText().toString();

        updatePinViews();
        checkPin();

    }

    protected void initViews() {

        pinItem1 = findViewById(R.id.pin_item_1);
        pinItem2 = findViewById(R.id.pin_item_2);
        pinItem3 = findViewById(R.id.pin_item_3);
        pinItem4 = findViewById(R.id.pin_item_4);

        updatePinViews();

    }

    protected void startApp() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        initViews();

        if (!App.getKeyStore().hasPin()) startApp();

    }

}
