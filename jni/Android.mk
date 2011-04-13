LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := native-test
LOCAL_SRC_FILES := native-test.c

include $(BUILD_SHARED_LIBRARY)
