package ec.com.cellecapp;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CellecApplication extends Application {

    private Retrofit restAdapter;
    private static CellecApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        restAdapter = retrofitCellec();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .threadPoolSize(4)
                .discCacheSize(10 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private Retrofit retrofitCellec() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return client;
    }

    public Retrofit getRestAdapter() {
        return restAdapter;
    }

    public static CellecApplication getmInstance() {
        return mInstance;
    }
}
