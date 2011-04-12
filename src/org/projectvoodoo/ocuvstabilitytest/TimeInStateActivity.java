/* ----------------------------------------------------------------------------
 * Project Voodoo OC/UV Stability App
 *
 * TimeInStateActivity.java
 *
 * View showing time in state info from CpuSpy metric class
 * --------------------------------------------------------------------------*/

package org.projectvoodoo.ocuvstabilitytest;

// voodoo stuff
import org.projectvoodoo.ocuvstabilitytest.metrics.CpuSpy;
import org.projectvoodoo.ocuvstabilitytest.metrics.CpuSpy.CpuState;

// imports
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.List;
import java.util.ArrayList;

/** main home home view */
public class TimeInStateActivity extends Activity {

    // views
    private LinearLayout mUiStatesView = null;
    private TextView mUiAdditionalStates = null;
    private TextView mUiHeaderAdditionalStates = null;
    private TextView mUiStatesWarning = null;

    /** what to call CPU off state */
    private static final String STR_SLEEP_STATE = "Deep Sleep";

    /** called when the Activity is created */
    @Override public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate UI
        setContentView(R.layout.time_in_state_layout);

        // find views
        mUiStatesView = (LinearLayout)findViewById (R.id.ui_states_view);
        mUiAdditionalStates = (TextView)findViewById (R.id.ui_additional_states);
        mUiHeaderAdditionalStates = (TextView)findViewById (
            R.id.ui_header_additional_states);
        mUiStatesWarning = (TextView)findViewById (R.id.ui_states_warning);

        // fetch data
        CpuSpy.getInstance().updateTimeInStates();
    }

    /** called when we want to infalte the menu */
    @Override public boolean onCreateOptionsMenu (Menu menu) {
        // request inflater from activity and inflate into its menu
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.time_in_state_menu, menu);

        // made it
        return true;
    }

    /** called to handle a menu event */
    @Override public boolean onOptionsItemSelected (MenuItem item) {
        // what it do mayne
        switch (item.getItemId () ) {
        /* pressed the load menu button */
        case R.id.menu_refresh:
            CpuSpy.getInstance().updateTimeInStates();
            updateView ();
            break;
        }

        // made it
        return true;
    }

    //-------------------------------------------------------------------------
    // managing view
    //-------------------------------------------------------------------------

    /** resume */
    @Override public void onResume () {
        super.onResume ();
        updateView ();
    }

    /** update the view */
    public void updateView () {
        CpuSpy cpuspy = CpuSpy.getInstance();
        List<CpuState> states = cpuspy.getTimeInStates();

        /* fill up the layout with views for the states, recording which ones
        * are empty */
        mUiStatesView.removeAllViews();
        List<String> extraStates = new ArrayList<String> ();
        for (CpuState state : states ) {
            if (state.duration > 0) {
                generateStateRow (state, mUiStatesView);
            } else {
                if (state.freq == 0) {
                    extraStates.add (STR_SLEEP_STATE);
                } else {
                extraStates.add (state.freq/1000 + " MHz");
                }
            }
        }

        // no states?!
        if ( states.size() == 0) {
            mUiStatesWarning.setVisibility (View.VISIBLE);
        }

        // for all empty views, edit the additional line textview
        if (extraStates.size() > 0) {
            int n = 0;
            String str = "";
            // construct nice looking comma-seperated list
            for (String s : extraStates) {
                if (n++ > 0)
                str += ", ";
                str += s;
            }
            mUiAdditionalStates.setVisibility(View.VISIBLE);
            mUiHeaderAdditionalStates.setVisibility (View.VISIBLE);
            mUiAdditionalStates.setText (str);
        } else {
            mUiAdditionalStates.setVisibility(View.GONE);
            mUiHeaderAdditionalStates.setVisibility(View.GONE);
        }
    }


    /** spit out a view representing a cpustate so we can cram it into a ScrollView */
    private View generateStateRow (CpuState state, ViewGroup parent) {
        // inflate the XML into a view in the parent
        LayoutInflater inf = LayoutInflater.from ( getApplicationContext() );
        LinearLayout theRow = (LinearLayout)inf.inflate(
            R.layout.state_row, parent, false);

        // what percetnage we've got
        CpuSpy cpuspy = CpuSpy.getInstance();
        float per = (float)state.duration * 100 / cpuspy.getTotalStateTime ();
        String sPer = (int)per + "%";

        // state name
        String sFreq;
        if (state.freq == 0) {
            sFreq = STR_SLEEP_STATE;
        } else {
            sFreq = state.freq / 1000 + " MHz";
        }

        // duration
        int tSec = state.duration / 100;
        int h = (int)Math.floor (tSec / (60*60) );
        int m = (int)Math.floor ( (tSec - h*60*60) / 60);
        int s = tSec % 60;
        String sDur;
        sDur = h + ":";
        if (m < 10)
            sDur += "0";
        sDur += m + ":";
        if (s < 10)
            sDur += "0";
        sDur += s;

        // map UI elements to objects
        TextView freqText = (TextView)theRow.findViewById(R.id.ui_freq_text);
        TextView durText = (TextView)theRow.findViewById(R.id.ui_duration_text);
        TextView perText = (TextView)theRow.findViewById(R.id.ui_percentage_text);
        ProgressBar bar = (ProgressBar)theRow.findViewById(R.id.ui_bar);

        // modify the row
        freqText.setText (sFreq);
        perText.setText (sPer);
        durText.setText (sDur);
        bar.setProgress ( (int)per);

        // add it to parent and return
        parent.addView(theRow);
        return theRow;
    }


}
