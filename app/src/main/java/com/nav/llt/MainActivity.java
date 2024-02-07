package com.nav.llt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FlashlightManager flashlightManager;

    Button button1;
    TextView text1;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashlightManager = new FlashlightManager(this);

        button1 = findViewById(R.id.button);
        text1 = findViewById(R.id.flashtxt);

        button1.setOnClickListener(this);

        requestCameraPermission();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            boolean isOn = flashlightManager.isFlashlightOn();
            if (!isOn) { // Check if flashlight is off
                flashlightManager.toggleFlashlight(true);
                button1.setText("Turn OFF");
            } else {
                flashlightManager.toggleFlashlight(false);
                button1.setText("Turn ON");
            }
        }
    }


    private void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted, proceed with your code
            } else {
                // Camera permission denied, handle this case
                // You may show a message to the user indicating that the flashlight cannot be used without the camera permission
            }
        }
    }
}
