/* ----------------------------------------------------------------------------
 * Project Voodoo OC/UV Stability App
 *
 * HomeActivity.java
 *
 * Home UI
 * --------------------------------------------------------------------------*/

package org.projectvoodoo.ocuvstabilitytest;

// imports
import android.app.Activity;
import android.os.Bundle;

/** main home home view */
public class HomeActivity extends Activity {

    /** called when the Activity is created */
    @Override public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate UI
        setContentView(R.layout.home_layout);

        // set title
        setTitle (getResources().getText(R.string.app_name) + " " +
            getResources().getText(R.string.version_name) );
    }
}
