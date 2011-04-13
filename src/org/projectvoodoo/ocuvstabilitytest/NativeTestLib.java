/* ----------------------------------------------------------------------------
 * Project Voodoo OC/UV Stability App
 *
 * NativeTestLib.java
 *
 * Messing with native code
 * --------------------------------------------------------------------------*/

package org.projectvoodoo.ocuvstabilitytest;

/** class to mess with native code */
public class NativeTestLib {

    // load the lib
    static {
        System.loadLibrary("native-test");
    }

    /** return a string */
    public native String getMsg();
}
