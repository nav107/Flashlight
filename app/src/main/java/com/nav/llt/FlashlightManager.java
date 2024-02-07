package com.nav.llt;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

public class FlashlightManager {

    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;

    public FlashlightManager(Context context) {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void toggleFlashlight(boolean enable) {
        try {
            cameraManager.setTorchMode(cameraId, enable);
            isFlashlightOn = enable;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlashlightOn() {
        return isFlashlightOn;
    }
}
