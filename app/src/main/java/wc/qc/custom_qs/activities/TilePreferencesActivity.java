package wc.qc.custom_qs.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import wc.qc.custom_qs.services.NfcTileService;

public class TilePreferencesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        final Bundle b = intent.getExtras();
        if (b != null) {
            String key = "android.intent.extra.COMPONENT_NAME";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                key = Intent.EXTRA_COMPONENT_NAME;
            }

            final ComponentName componentName = (ComponentName) b.get(key);
            if (componentName != null) {
                handleLongClickedTile(componentName.getClassName());
            }
        }
        finish();
    }

    private void handleLongClickedTile(final String componentClassName) {
        if (componentClassName == null) return;

        Intent i = new Intent();

        if (componentClassName.equals(NfcTileService.class.getName())) {
            i.setAction(Settings.ACTION_NFC_SETTINGS);
        } else {
            i = null;
        }

        if (i != null) {
            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}