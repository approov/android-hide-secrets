package com.criticalblue.androidhidesecrets;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String SOURCE_CODE_API_KEY = "YXBpLWtleS1pbi1zb3VyY2UtY29kZQo=";

    final static String LOG_TAG = "ANDROID_HIDE_SECRETS";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderApiKeyForView(R.id.source_code_value, SOURCE_CODE_API_KEY);

        renderApiKeyForView(R.id.manifest_value, getManifestValueFor("MANIFEST_API_KEY"));
        renderApiKeyForView(R.id.gradle_value, getManifestValueFor("GRADLE_API_KEY"));
        renderApiKeyForView(R.id.gradle_placeholder_value, getManifestValueFor("GRADLE_API_KEY_PLACEHOLDER"));
        renderApiKeyForView(R.id.gradle_env_value, getManifestValueFor("GRADLE_ENV_API_KEY"));

        renderApiKeyForView(R.id.jni_value, stringFromJNI());

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void renderApiKeyForView(Integer view_id, String api_key) {
        TextView view = findViewById(view_id);
        view.setText(api_key);
    }

    private String getManifestValueFor(String key) {
        try {
            ApplicationInfo app = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return app.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }

        return "Failed to found value...";
    }
}
