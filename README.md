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
