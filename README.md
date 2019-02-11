# ANDROID HIDE SECRETS

A quick demo to show several ways of hiding secrets in a mobile app, like in:

* source code
* manifest file
* gradle file
* JNI/NDK


## SETUP

To set the API key for `GRADLE_ENV_API_KEY`:

```bash
$ export GRADLE_ENV_API_KEY=$(echo "api-key-for-gradle-file-from-env" | openssl dgst -binary -sha256 | openssl enc -base64)
$ echo $GRADLE_ENV_API_KEY
srqGFqMm23R6A7YJbAEmKRuSQ6TWnLq3YNtAWbEoSuE=
```

To set the API key for `JNI_API_KEY`:

```bash
$  cp -v app/src/main/cpp/api_key.h.example app/src/main/cpp/api_key.h
'app/src/main/cpp/api_key.h.example' -> 'app/src/main/cpp/api_key.h'
```

Now edit the file `app/src/main/cpp/api_key.h` and look for the palce holder
`ANDROID_HIDE_SECRETS_API_KEY_H` and replace its dummy value
`place-the-api-key-here` with your desired API key, maybe like:

```bash
echo "api-key-for-the-jni-file" | openssl dgst -binary -sha256 | openssl enc -base64
yDbx5R+a6zJ3H76iU9YB9U0GY6DjZ4FiWFb8vCMCdLg=
```

## RUN

Just start your Android Studio and build and run this project as usual for any
other mobile app.

The app is a dummy one that only shows in the main screen all API keys hidden in
the code, just to prove that we can retrieve them.
