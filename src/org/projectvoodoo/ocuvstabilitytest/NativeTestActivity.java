/* ----------------------------------------------------------------------------
 * Project Voodoo OC/UV Stability App
 *
 * NativeTestActivity.java
 *
 * Testing out interaction with native code
 * --------------------------------------------------------------------------*/

package org.projectvoodoo.ocuvstabilitytest;

// voodoo stuff
import org.projectvoodoo.ocuvstabilitytest.NativeTestLib;

// imports
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/** main home home view */
public class NativeTestActivity extends Activity {

    /** called when the Activity is created */
    @Override public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate UI
        setContentView(R.layout.native_test_layout);
    }

    /** handle a button click */
    public void onButtonClick (View v) {

        switch (v.getId()) {
        // native test
        case R.id.ui_button_dostuff:
            // get a string from the native lib and toast it
            NativeTestLib nativeLib = new NativeTestLib ();
            String msg = nativeLib.getMsg();
            Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_SHORT).show();

        break;
        }
    }
}
