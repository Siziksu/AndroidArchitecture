package com.siziksu.architecture.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import com.siziksu.architecture.common.model.Connection;

/**
 * Connection utils class.
 */
public class ConnectionUtils {

    /**
     * The constant NOT_CONNECTED.
     */
    public static final String NOT_CONNECTED = "NOT_CONNECTED";
    /**
     * The constant WIFI.
     */
    public static final String WIFI = "WIFI";
    /**
     * The constant MOBILE.
     */
    public static final String MOBILE = "MOBILE";
    /**
     * The constant OTHER.
     */
    public static final String OTHER = "OTHER";

    private static ConnectionUtils instance;
    private final Context context;

    private ConnectionUtils(Context context) {
        this.context = context;
    }

    /**
     * This class must be initialized before asking for an instance.
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            instance = new ConnectionUtils(context);
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     *
     * @return {@link ConnectionUtils} object
     */
    public static ConnectionUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    /**
     * This method returns the connection state and name.
     *
     * @return true if connected or false if not. The name can be "WIFI" or "MOBILE"
     */
    public Connection getConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = connectivityManager.getAllNetworks();
                for (Network mNetwork : networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        break;
                    }
                }
            } else {
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                if (networkInfos != null) {
                    for (NetworkInfo info : networkInfos) {
                        if (info.getState() == NetworkInfo.State.CONNECTED) {
                            networkInfo = info;
                            break;
                        }
                    }
                }
            }
        }
        if (networkInfo != null) {
            return new Connection(true, networkInfo.getTypeName());
        } else {
            return new Connection(false, null);
        }
    }

    /**
     * Gets the connection type.
     *
     * @return the connection type
     */
    public String getConnectionType() {
        Connection connection = getConnection();
        if (connection.isConnected()) {
            switch (connection.getName()) {
                case WIFI:
                    return WIFI;
                case MOBILE:
                    return MOBILE;
                default:
                    return OTHER;
            }
        } else {
            return NOT_CONNECTED;
        }
    }

    /**
     * Gets the ssid.
     *
     * @return the ssid
     */
    public String getSSID() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getSSID();
    }
}
