# Architecture

Demo of architecture for Android.

## Highlights

This demo uses:

+ `Jack toolchain`
+ `Lambdas`
+ `Retrofit 2`
+ `Okhttp 3`
+ `Gson`

```
android {

    compileSdkVersion 23
    buildToolsVersion 24.0.0

    defaultConfig {
        jackOptions {
            enabled true
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    compile com.squareup.retrofit2:retrofit:2.0.2
    compile com.squareup.retrofit2:converter-gson:2.0.2
    compile com.squareup.okhttp3:okhttp:3.3.1
    compile com.squareup.okhttp3:logging-interceptor:3.3.1
}
```

## Use Case
With this demo we will request to the [OpenWeatherMap](http://openweathermap.org/) service for the current temperature of Barcelona, Spain.

## AsyncObject for Android

This demo uses a version for Android of my own [JavaAsyncObject](https://github.com/Siziksu/JavaAsyncObject).

```java
new AsyncObject<OpenWeather>()
                .runOnMainThread()
                .action(() -> new WeatherData.Weather().setCity(city).useCache().run())
                .success(response -> Log.d(Constants.TAG, "Temperature: " + response.getMain().getTemperature()))
                .error(e -> Log.d(Constants.TAG, e.getMessage(), e))
                .done(() -> Log.d(Constants.TAG, "Action successfully completed"))
                .run();
```

```java
new AsyncObject<OpenWeather>()
                .runOnMainThread()
                .action(() -> new WeatherData.Weather().setCity(city).useCache().run())
                .subscribe(
                    response -> Log.d(Constants.TAG, "Temperature: " + response.getMain().getTemperature()),
                    e -> Log.d(Constants.TAG, e.getMessage(), e),
                    () -> Log.d(Constants.TAG, "Action successfully completed")
                )
                .run();
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
app.view.ui   -> app.presenter

app.presenter ^  return result through a listener
              -> domain.facade

domain.facade -> domain.request -> data.facade

data.facade   -> data.database  ^  return response through a listener
              -> data.client    ^  return response through a listener
```

## How it works

1. The `User Interface` will register in the `Presenter` and ask him for the things it needs.
2. Then, the `Presenter` will communicate with the `Domain` by asking for this data to the domain classes (`Facades`), and after receiving the response, process it and deliver it to the `User Interface`.
3. In the `Domain` layer, this `Facade` classes will communicate with the `Data` layer through the domain `Request` classes. This `Request` classes will communicate with the `Data` layer asking for this data to the data classes (`Facades`).
4. In the Data layer, this `Facade` classes will manage the `cache` and the `cloud` through the data `Client` classes. Finally, this `Client` class will manage the `Retrofit` service calls.

As said before, all the layers will have access to the `Common` layer which contains the `Model` and `global objects`.

Each layer has a `Listener` to pass the data back (`Callbacks`).

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
