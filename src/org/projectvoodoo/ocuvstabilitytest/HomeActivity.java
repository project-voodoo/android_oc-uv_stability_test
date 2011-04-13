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
import android.view.View;
import android.content.Intent;

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

    /** handle a button click */
    public void onButtonClick (View v) {

        switch (v.getId()) {

        // time in states view
        case R.id.ui_button_timeinstateview:
            startActivity ( new Intent (this, TimeInStateActivity.class) );
        break;

        // time in states view
        case R.id.ui_button_nativetestview:
            startActivity ( new Intent (this, NativeTestActivity.class) );
        break;
        }
    }
}
