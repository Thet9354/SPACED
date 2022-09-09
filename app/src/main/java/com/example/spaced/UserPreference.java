package com.example.spaced;

import android.app.Application;

public class UserPreference extends Application {

    public static final String PREFERENCE = "preferences";

    public static final String CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";

    public static final String DELIVERY_OPTION = "deliveryOptions";
    public static final String BUSINESS_ADDRESS = "businessAddress";
    public static final String NORMAL_ADDRESS = "normalAddress";

    private String businessAddress, normalAddress;

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getNormalAddress() {
        return normalAddress;
    }

    public void setNormalAddress(String normalAddress) {
        this.normalAddress = normalAddress;
    }
}
