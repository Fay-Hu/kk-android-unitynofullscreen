# kk-android-unitynofullscreen
Avoid Unity android IMMERSIVE FullScreen. alternative solution `Screen.fullScreen = false`

C# MonoBehaviour.Awake too late.

But this solution is very very early.

Use this solution at last line, which Android Activity#onCreate.

# Build

## AndroidStudio build error

`Test Artifact: Android Intrumentation Tests` failed on processDebugAndroidTestResources and processReleaseAndroidTestResources.

http://tools.android.com/tech-docs/unit-testing-support#TOC-Setting-up-Android-Studio
> 4.Open the "Build variants" tool window (on the left) and change the test artifact to "Unit tests"


## CUI build

 Set environment `ANDROID_HOME`

  Linux, Mac (bash)

 ```
  export ANDROID_HOME=`~/android-sdk/sdk-r24.3.4`
 ```

  Windows

 ```
  set ANDROID_HOME=`d:\android\sdk-r24.3.4`
 ```

### gradle

 ```
  gradle wrapper
  gradlew build
 ```

### android ant

 ```
  cd src
  cd main
  android update lib-project -p .
  ant release
 ```

### maven

 ```
  mvn package
 ```


