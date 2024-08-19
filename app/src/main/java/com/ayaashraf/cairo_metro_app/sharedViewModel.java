package com.ayaashraf.cairo_metro_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class sharedViewModel extends ViewModel {
    private final MutableLiveData<String> screenName =new MutableLiveData<>();

    public void setScreenName(String name){
        screenName.setValue(name);
    }

    public LiveData<String> getScreenName()
    {
        return screenName;
    }
}
