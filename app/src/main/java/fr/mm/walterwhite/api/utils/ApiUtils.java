package fr.mm.walterwhite.api.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import fr.mm.walterwhite.BuildConfig;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiUtils {
    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int RW_TIMEOUT = 30000;
    public static final String SPACE = " ";
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 2;
    private static final String UPLOAD_JOB_TAG = "upload_saved_product_job";
    private static boolean isUploadJobInitialised;
    public static final String HEADER_USER_AGENT_SCAN = "Scan";
    public static final String HEADER_USER_AGENT_SEARCH = "Search";
    public static final int NO_DRAWABLE_RESOURCE = 0;
    public static final String FORCE_REFRESH_TAXONOMIES = "force_refresh_taxonomies";

    private ApiUtils() {
        // Utility class
    }


    /**
     * Return a round float value <b>with 2 decimals</b>
     * <b>BE CAREFUL:</b> THE METHOD DOESN'T CHECK THE NUMBER AS A NUMBER.
     *
     * @param value float value
     * @return round value <b>with 2 decimals</b> or 0 if the value is empty or equals to 0
     */
    public static String getRoundNumber(String value) {
        if ("0".equals(value)) {
            return value;
        }

        if (TextUtils.isEmpty(value)) {
            return "?";
        }

        String[] strings = value.split("\\.");
        if (strings.length == 1 || (strings.length == 2 && strings[1].length() <= 2)) {
            return value;
        }

        return String.format(Locale.getDefault(), "%.2f", Double.valueOf(value));
    }

    /**
     */
    public static String getRoundNumber(float value) {
        return getRoundNumber(Float.toString(value));
    }


    /**
     * Check if the device has a camera installed.
     *
     * @return true if installed, false otherwise.
     */
    public static boolean isHardwareCameraInstalled(@NonNull Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


    public static OkHttpClient httpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(RW_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(RW_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS));


            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));

        return builder.build();
    }

    /**
     * Check if the user is connected to a network. This can be any network.
     *
     * @param context of the application.
     * @return true if connected or connecting. False otherwise.
     */
    public static boolean isNetworkConnected(@NonNull Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            return false;
        }
        return activeNetwork.isConnectedOrConnecting();
    }


    @NonNull
    public static String getVersionName(@Nullable Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            Log.e(ApiUtils.class.getSimpleName(), "getVersionName", e);
            return "(version unknown)";
        }
    }

    /**
     * @param type Type of call (Search or Scan)
     * @return Returns the header to be put in network call
     */
    @NonNull
    public static String getUserAgent(@NonNull String type) {
        return getUserAgent() + " " + type;
    }

    @NonNull
    public static String getUserAgent() {
        return BuildConfig.APP_NAME + " Official Android App " + BuildConfig.VERSION_NAME;
    }

    /**
     * @param response Takes a string
     * @return Returns a Json object
     */
    @Nullable
    public static JSONObject createJsonObject(@NonNull String response) {
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            Log.e(ApiUtils.class.getSimpleName(), "createJsonObject", e);
            return null;
        }
    }





}
