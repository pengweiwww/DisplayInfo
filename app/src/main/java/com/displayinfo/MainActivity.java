package com.displayinfo;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Android Developer Document
 * https://developer.android.com/reference/android/util/DisplayMetrics.html
 */
public class MainActivity extends AppCompatActivity {

    private TextView mDIPHeight;
    private TextView mDIPWidth;
    private TextView mDPIHeight;
    private TextView mDPIWidth;
    private TextView mDensityDpi;
    private TextView mDesity;
    private TextView mResolutionHeight;
    private TextView mResolutionWidth;
    private TextView mScreenSizeHeight;
    private TextView mScreenSize;
    private TextView mScreenSizeWidth;
    private TextView mSuggestionLayout;
    private TextView mSuggestionLayoutLand;
    private TextView mSuggestionLayoutSimple;
    private TextView mSuggestionValues;
    private TextView mSuggestionValuesLand;
    private TextView mSuggestionValuesSimple;
    private TextView mStatusBaHheight;
    private TextView mNavigationBarHeight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Display Info for " + Build.MODEL);
        this.mScreenSizeWidth = (TextView) findViewById(R.id.screen_size_width);
        this.mScreenSizeHeight = (TextView) findViewById(R.id.screen_size_height);
        this.mScreenSize = (TextView) findViewById(R.id.screen_size);
        this.mResolutionWidth = (TextView) findViewById(R.id.resolution_width);
        this.mResolutionHeight = (TextView) findViewById(R.id.resolution_height);
        this.mDPIWidth = (TextView) findViewById(R.id.dpi_width);
        this.mDPIHeight = (TextView) findViewById(R.id.dpi_height);
        this.mDensityDpi = (TextView) findViewById(R.id.density_dpi);
        this.mDesity = (TextView) findViewById(R.id.density);
        this.mDIPWidth = (TextView) findViewById(R.id.dip_width);
        this.mDIPHeight = (TextView) findViewById(R.id.dip_height);
        this.mSuggestionLayout = (TextView) findViewById(R.id.suggestion_layout);
        this.mSuggestionLayoutLand = (TextView) findViewById(R.id.suggestion_layout_land);
        this.mSuggestionLayoutSimple = (TextView) findViewById(R.id.suggestion_layout_simple);
        this.mSuggestionValues = (TextView) findViewById(R.id.suggestion_values);
        this.mSuggestionValuesLand = (TextView) findViewById(R.id.suggestion_values_land);
        this.mSuggestionValuesSimple = (TextView) findViewById(R.id.suggestion_values_simple);
        this.mStatusBaHheight = (TextView) findViewById(R.id.status_bar_height);
        this.mNavigationBarHeight = (TextView) findViewById(R.id.navigation_bar_height);

        /**
         *  A structure describing general information about a display,
         *  such as its size, density, and font scaling.
         */
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Point realSize = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindowManager().getDefaultDisplay().getRealSize(realSize);
        } else {
            realSize.x = dm.widthPixels;
            realSize.y = dm.heightPixels;
        }
        //获取屏幕尺寸
        this.mScreenSizeWidth.setText(String.valueOf(((float) realSize.x) / dm.xdpi));
        this.mScreenSizeHeight.setText(String.valueOf(((float) realSize.y) / dm.ydpi));
        double x = Math.pow(realSize.x / dm.xdpi, 2);
        double y = Math.pow(realSize.y / dm.ydpi, 2);
        this.mScreenSize.setText(String.valueOf(Math.sqrt(x + y)));
        //获取屏幕分辨率
        this.mResolutionWidth.setText(String.valueOf(realSize.x));
        this.mResolutionHeight.setText(String.valueOf(realSize.y));
        //物理像素
        this.mDPIWidth.setText(String.valueOf(dm.xdpi));
        this.mDPIHeight.setText(String.valueOf(dm.ydpi));
        //The screen density expressed as dots-per-inch.
        this.mDensityDpi.setText(densityDpiToString(dm.densityDpi));

        this.mDesity.setText(String.valueOf(dm.density));

        float dipW = (((float) realSize.x) * 160.0f) / ((float) dm.densityDpi);
        float dipH = (((float) realSize.y) * 160.0f) / ((float) dm.densityDpi);
        this.mDIPWidth.setText(String.valueOf(dipW));
        this.mDIPHeight.setText(String.valueOf(dipH));

        this.mSuggestionLayout.setText("layout" + getSmallestWidthString((int) dipW, (int) dipH) + getResolutionString(realSize.x, realSize.y));
        this.mSuggestionLayoutLand.setText("layout-land" + getSmallestWidthString((int) dipW, (int) dipH) + getResolutionString(realSize.x, realSize.y));
        this.mSuggestionLayoutSimple.setText("layout" + getSmallestWidthString((int) dipW, (int) dipH));
        this.mSuggestionValues.setText("values" + getSmallestWidthString((int) dipW, (int) dipH) + getResolutionString(realSize.x, realSize.y));
        this.mSuggestionValuesLand.setText("values-land" + getSmallestWidthString((int) dipW, (int) dipH) + getResolutionString(realSize.x, realSize.y));
        this.mSuggestionValuesSimple.setText("values" + getSmallestWidthString((int) dipW, (int) dipH));

        this.mStatusBaHheight.setText(String.valueOf(getStatusBarHeight()));
        this.mNavigationBarHeight.setText(String.valueOf(getNavigationBarHeight()));

    }

    //获得导航栏的高度
    public int getNavigationBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android"); //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    //获取状态栏的高度
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private String densityDpiToString(int densityDpi) {
        return densityDpi + " (" + getString(R.string.dpi_suffix) + ")";
    }

    private String getResolutionString(int rw, int rh) {
        return rw > rh ? "-" + rw + "x" + rh : "-" + rh + "x" + rw;
    }

    private String getSmallestWidthString(int dipWidth, int dipHeight) {
        StringBuilder stringBuilder = new StringBuilder("-sw");
        if (dipWidth >= dipHeight) {
            dipWidth = dipHeight;
        }
        return stringBuilder.append(dipWidth).append("dp").toString();
    }

}
