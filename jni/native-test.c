/* ----------------------------------------------------------------------------
 * Project Voodoo - OC/UV Stablity Test
 *
 * native-test.c
 *
 * Messing around with native code for android
 * -------------------------------------------------------------------------*/

#include "org_projectvoodoo_ocuvstabilitytest_NativeTestLib.h"

/*
 * Class:     org_projectvoodoo_ocuvstabilitytest_NativeTestLib
 * Method:    getMsg
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_projectvoodoo_ocuvstabilitytest_NativeTestLib_getMsg
  (JNIEnv* env, jobject obj) {

    /* create a new string in the java environment and return */
    return (*env)->NewStringUTF (env, "Hello from native land!!");
}
