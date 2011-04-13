/* ----------------------------------------------------------------------------
 * Project Voodoo OC/UV Stability App
 *
 * NativeTestActivity.java
 *
 * Testing out interaction with native code
 * --------------------------------------------------------------------------*/

package org.projectvoodoo.ocuvstabilitytest;

// imports
import android.app.Activity;
import android.os.Bundle;

/** main home home view */
public class NativeTestActivity extends Activity {

    /** called when the Activity is created */
    @Override public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate UI
        setContentView(R.layout.native_test_layout);
    }
}
