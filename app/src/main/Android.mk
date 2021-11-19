LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := $(call all-java-files-under, java)
LOCAL_SRC_FILES += $(call all-Iaidl-files-under, aidl)
LOCAL_AIDL_INCLUDES += $(LOCAL_PATH)/aidl

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v4 \
    android-support-v13 \
    android-support-v7-appcompat \
    android-support-annotations \
    gson_2_8_5

LOCAL_RESOURCE_DIR += \
    $(LOCAL_PATH)/res \
    frameworks/support/v7/appcompat/res \

LOCAL_AAPT_FLAGS := \
    --auto-add-overlay \
    --extra-packages android.support.v7.appcompat

LOCAL_PACKAGE_NAME := GxaFirewallApp
LOCAL_MODULE_TAGS := optional
LOCAL_CERTIFICATE := platform
LOCAL_JACK_ENABLED := disabled

include $(GEN_PREBUILT_PACKAGE)
include $(BUILD_PACKAGE)
