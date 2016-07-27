package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.Injector;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Request class.
 */
public final class GetWeatherRequest {

    private static final long EXPIRY_TIME = 5000;

    private String city;

    /**
     * Constructor.
     */
    public GetWeatherRequest() {
        // Constructor
    }

    /**
     * Sets the city for the request.
     *
     * @param city the city
     *
     * @return {@link GetWeatherRequest}
     */
    public GetWeatherRequest city(String city) {
        this.city = city;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response if the
     * request ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the request fails.
     * <br />
     * Sets the {@link Done} used to emit when the response of the
     * request ends.
     * <br />
     * And runs the request.
     *
     * @param success the success function
     * @param fail    the fail function
     * @param done    the done function
     */
    public void subscribe(final Success<OpenWeather> success, final Fail fail, final Done done) {
        Observable.create(subscriber())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.newThread())
                  .subscribe(
                          success::success,
                          fail::fail,
                          done::done
                  );
    }

    public Observable.OnSubscribe<OpenWeather> subscriber() {
        return subscriber -> {
            try {
                OpenWeather result = Injector.provideGetWeatherData()
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
}
