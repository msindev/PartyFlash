package net.penguincoders.partyflash;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class TorchFragment extends Fragment {

    ImageButton powerButton;
    private static final int CAMERA_REQUEST = 123;
    boolean hasCameraFlash = false;
    boolean flashOn = false;


    public TorchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_torch, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        hasCameraFlash = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        powerButton = getView().findViewById(R.id.powerButton);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasCameraFlash) {
                    if(flashOn) {
                        flashOn = false;
                        powerButton.setImageResource(R.drawable.poweroffbutton);
                        flashLightOff();
                    }
                    else
                    {
                        flashOn = true;
                        powerButton.setImageResource(R.drawable.poweronbutton);
                        flashLightOn();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "No flash available on your device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    hasCameraFlash = getActivity().getPackageManager().
                            hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
                } else {
                    powerButton.setEnabled(false);
                    Toast.makeText(getActivity(), "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
