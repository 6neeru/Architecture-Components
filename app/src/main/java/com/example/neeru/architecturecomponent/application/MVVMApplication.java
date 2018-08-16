package com.example.neeru.architecturecomponent.application;

import android.app.Application;


public class MVVMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependency();
    }

    private void resolveDependency() {
    }

}
