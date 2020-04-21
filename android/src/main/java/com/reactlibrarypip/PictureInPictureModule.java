package com.reactlibrarypip;

import android.app.Activity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import android.app.PictureInPictureParams;
import android.util.Rational;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class PictureInPictureModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public PictureInPictureModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "PictureInPicture";
    }

    @ReactMethod
    public void showAlertToast(String text) {
        Context context = getReactApplicationContext();
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void start(int width, int height) {
        final Activity activity = getCurrentActivity();
        if(hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)){

            Rational rational = new Rational(width, height);
            PictureInPictureParams params =
            new PictureInPictureParams.Builder()
            .setAspectRatio(rational)
            .build();

            activity.enterPictureInPictureMode(params);
            return;
        } else {
            Context context = getReactApplicationContext();
            String text = "Your device is not supported";
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }

    }

    public boolean hasSystemFeature (String featureName){
//        FeatureInfo.getSystemAvailableFeatures();
        return true;
    }
}
