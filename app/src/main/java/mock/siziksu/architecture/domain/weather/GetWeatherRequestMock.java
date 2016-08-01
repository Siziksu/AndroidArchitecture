package mock.siziksu.architecture.domain.weather;

import android.util.Log;

import com.google.gson.Gson;
import com.siziksu.architecture.common.Constants;
import com.siziksu.architecture.common.FileUtils;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.weather.IGetWeatherData;
import com.siziksu.architecture.domain.weather.IGetWeatherRequest;

/**
 * Request class.
 */
public final class GetWeatherRequestMock implements IGetWeatherRequest {

    /**
     * Constructor.
     */
    public GetWeatherRequestMock(IGetWeatherData getWeatherData) {
        // No need for the mock
    }

    @Override
    public GetWeatherRequestMock city(String city) {
        // No need for the mock
        return this;
    }

    @Override
    public void subscribe(final Success<OpenWeather> success, final Fail fail, final Done done) {
        Log.d(Constants.TAG, "GetWeather mocked response");
        String response = FileUtils.get().getStringFromFile("get_weather/response.json");
        OpenWeather openWeather = new Gson().fromJson(response, OpenWeather.class);
        success.success(openWeather);
//        fail.fail(new Throwable("GetWeather mocked error"));
        done.done();
    }
}
