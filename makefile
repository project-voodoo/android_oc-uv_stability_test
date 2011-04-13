# makefile for use in developing Android applications
# used mainly since I have 'make' commands mapped in my editor

# app info
APPNAME=ocuvstabilitytest
NAMESPACE=org.projectvoodoo.ocuvstabilitytest
START_ACTIVITY=HomeActivity

# default target is to install the debug APK on whatever device/emu
# we currently have
all : build-debug install-debug

# clean bin and gen
clean :
	ant clean
	ndk-build clean

# build APK and sign with debug key
build-debug :
	ant -q -e debug

# build native code
build-native :
	ndk-build

# create all the .h files for our native implementations
# this will have to been done after the corresponding java class files
# have been compiled
build-headers :
	javah -classpath bin/classes/ -verbose -d jni/ \
		org.projectvoodoo.ocuvstabilitytest.NativeTestLib

# push the debug APK to the emulator or phone and run it
install-debug :
	adb install -rt bin/$(APPNAME)-debug.apk
	adb shell am start -a android.intent.action.MAIN \
		-n $(NAMESPACE)/$(NAMESPACE).$(START_ACTIVITY)

# same as install-debug but will use a device even if an emulator is running
install-debug-device:
	adb -d install -rt bin/$(APPNAME)-debug.apk
	adb -d shell am start -a android.intent.action.MAIN \
		-n $(NAMESPACE)/$(NAMESPACE).$(START_ACTIVITY)

