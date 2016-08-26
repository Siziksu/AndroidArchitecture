# Architecture

Demo of architecture for Android.

## Highlights

This demo uses:

+ `Butterknife 8.2.1`
+ `RxAndroid 1.2.1`
+ `Retrolambda 3.2.5`
+ `Retrofit 2.0.2`
+ `Okhttp 3.3.1`
+ `Gson`

### Project build.gradle

```java
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.3'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        classpath 'me.tatarka:gradle-retrolambda:3.2.5'
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
```

### Module build.gradle


```java
apply plugin: 'com.android.application'
apply plugin: 'android-apt'
apply plugin: 'me.tatarka.retrolambda'

android {
    compileSdkVersion 22
    buildToolsVersion "24.0.0"

    defaultConfig {
        applicationId "com.siziksu.architecture"
        minSdkVersion 19
        targetSdkVersion 22
        versionCode 2219001
        versionName "0.0.1"
        testInstrumentationRunner test_instrumentation_runner
    }
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    apt "com.jakewharton:butterknife-compiler:8.2.1"
    compile "com.jakewharton:butterknife:8.2.1"
    compile "com.squareup.retrofit2:retrofit:2.0.2"
    compile "com.squareup.retrofit2:converter-gson:2.0.2"
    compile "com.squareup.okhttp3:okhttp:3.3.1"
    compile "com.squareup.okhttp3:logging-interceptor:3.3.1"
    compile "io.reactivex:rxjava:1.1.8"
    compile "io.reactivex:rxandroid:1.2.1"
}
```

## Use Case

With this demo we will request to the [OpenWeatherMap](http://openweathermap.org/) service for the current temperature of Barcelona, Spain.

## RxAndroid

This demo uses RxAndroid.

```java

public void getWeather(final String city) {
    Observable.create(subscriber(city))
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.newThread())
              .subscribe(
                      response -> Log.d(Constants.TAG, "Temperature: " + response.getMain().getTemperature()),
                      throwable -> Log.d(Constants.TAG, throwable.getMessage(), throwable),
                      () -> Log.d(Constants.TAG, "Action successfully completed")
              );
}

public Observable.OnSubscribe<OpenWeather> subscriber(final String city) {
    return subscriber -> {
        try {
            OpenWeather result = new WeatherData.Weather()
                    .city(city)
                    .useCache()
                    .cacheExpiryTime(EXPIRY_TIME)
                    .run();
            subscriber.onNext(result);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    };
}
```

## Architecture

This demo uses a layered architecture.

+ User interface
+ Presenter
+ Domain
+ Data
+ Common

Applying the dependency rule in this direction:

    User Interface -> Presenter -> Domain -> Data

All the layers has access to the `Common` layer which contains the `Model` and global objects. This simplifies a lot the amount of objects used.
The `Data` layer includes the `Cloud` and the `Persistence`.

## Layer communication

```
app.view.ui    -> app.presenter

app.presenter  ^  return result
               -> domain

domain.request -> data.facade

data.facade    -> data.database  ^  return response
               -> data.client    ^  return response
```

## How it works

1. The `User Interface` will register in the `Presenter` and ask him for the things it needs.
2. Then, the `Presenter` will communicate with the `Domain` by asking for this data to the domain classes (`Facades` or `Requests`), and after receiving the response, process it and deliver it to the `User Interface`.
3. In the `Domain` layer, the `Facade` through the `Request` classes or this last directly will communicate with the `Data` layer. This `Request` classes will communicate with the `Data` layer asking for this data through the data classes (`Facades`).
4. In the Data layer, this `Facade` classes will manage the `cache` and the `cloud` through the data `Client` classes. Finally, this `Client` class will manage the `Retrofit` service calls.

As said before, all the layers will have access to the `Common` layer which contains the `Model` and `global objects`.

## License

    Copyright 2016 Esteban Latre

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
