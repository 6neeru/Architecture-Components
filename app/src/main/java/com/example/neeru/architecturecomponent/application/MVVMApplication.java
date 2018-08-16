package com.example.neeru.architecturecomponent.application;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class MVVMApplication extends Application implements HasActivityInjector {
    //private ApiComponent mApiComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependency();
    }

    private void resolveDependency() {
        /*DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);*/
       // mApiComponent = DaggerApiComponent.builder().networkComponent(getNetworkComponent()).build();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


    /*public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;

    }*/
}
