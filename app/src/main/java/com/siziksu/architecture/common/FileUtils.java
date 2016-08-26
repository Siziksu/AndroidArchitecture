package com.siziksu.architecture.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * FileUtils class.
 */
public final class FileUtils {

    /**
     * The constant CHARSET_UTF_8.
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

    private static FileUtils instance;

    private final Context context;

    private FileUtils(Context context) {
        this.context = context;
    }

    /**
     * This class must be initialized before asking for an instance.
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            instance = new FileUtils(context);
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     *
     * @return {@link FileUtils} object
     */
    public static FileUtils get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    /**
     * Gets a string resource by its name.
     *
     * @param string the identifier
     *
     * @return the string
     */
    @NonNull
    public String getStringResource(String string) {
        return context.getString(context.getResources().getIdentifier(string, "string", context.getPackageName()));
    }

    /**
     * Gets the content of a file in the assets folder.
     *
     * @param file the name of the file
     *
     * @return the file in String format
     */
    public String getStringFromFile(String file) {
        String string = "";
        AssetManager manager = context.getAssets();
        try {
            InputStream stream = manager.open(file);
            string = convertStreamToString(stream);
            stream.close();
        } catch (Exception e) {
            Log.d(Constants.TAG, e.getMessage(), e);
        }
        return string;
    }

    private String convertStreamToString(InputStream stream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, CHARSET_UTF_8));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
