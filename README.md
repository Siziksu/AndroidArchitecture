# Architecture
Demo of architecture for Android.

## Libraries used
This demo uses Retrofit 2, Okhttp 3 and Gson.
```
com.squareup.retrofit2:retrofit:2.0.2
com.squareup.retrofit2:converter-gson:2.0.2
com.squareup.okhttp3:okhttp:3.3.1
com.squareup.okhttp3:logging-interceptor:3.3.1
```

## Use Case
With this demo we will request to the OpenWeatherMap service for the current temperature of Barcelona, Spain.

## Architecture
This demo uses a layered architecture.

+ User interface
+ Presenter
+ Domain
+ Data
+ Common

It uses the dependency rule, where:

    User interface -> Presenter -> Domain -> Data

All the layers has access to the `Common` layer which contains the `Model` and global objects. This simplifies a lot the amount of objects used.
The `Data` layer includes the `Cloud` and the `Persistence`.

## How it works
The `UI` will register in the `Presenter` and ask him for the weather in Barcelona, Spain.

Then, the `Presenter` will communicate with the `Domain` layer by asking for this data to the `WeatherDomain` class, and after receiving the response, process it and deliver it to the `UI`.

In the `Domain` layer, this `WeatherDomain` class will be a `Facade` that will communicate with the `Data` layer through the `GetWeatherRequest` class. This `Request` class will communicate with the `Data` layer asking for this data to the `WeatherData` class.

In the `Data` layer, this `WeatherData` class will be a `Facade` that will manage the `cache` and the `cloud` through the `WeatherClient` class. Finally, this `WeatherClient` class will manage the Retrofit service call.

As said before, all the layers will have access to the `Common` layer which contains the `Model` and global objects.

Each layer has a `Listener` to pass the data.

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
