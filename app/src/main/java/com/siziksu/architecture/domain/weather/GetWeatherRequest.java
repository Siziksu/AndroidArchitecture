package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.weather.IGetWeatherData;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Request class.
 */
public final class GetWeatherRequest implements IGetWeatherRequest {

    private static final long EXPIRY_TIME = 5000;
    private final IGetWeatherData getWeatherData;

    private String city;

    /**
     * Constructor.
     */
    public GetWeatherRequest(IGetWeatherData getWeatherData) {
        this.getWeatherData = getWeatherData;
    }

    @Override
    public IGetWeatherRequest city(String city) {
        this.city = city;
        return this;
    }

    @Override
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

    private Observable.OnSubscribe<OpenWeather> subscriber() {
        return subscriber -> {
            try {
                OpenWeather result = getWeatherData.city(city)
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
