#include <jni.h>
#include <string>
#include "api_key.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_criticalblue_androidhidesecrets_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    // To add the API_KEY to the mobile app when is compiled you need to:
    //   * copy `api_key.h.example` to `api_key.h`
    //   * edit the file and replace this text `place-the-api-key-here` with your desired API_KEY
    std::string JNI_API_KEY = ANDROID_HIDE_SECRETS_API_KEY_H;

    return env->NewStringUTF(JNI_API_KEY.c_str());
}
