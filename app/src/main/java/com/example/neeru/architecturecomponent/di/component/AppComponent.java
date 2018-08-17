package com.example.neeru.architecturecomponent.di.component;

import com.example.neeru.architecturecomponent.application.MVVMApplication;
import com.example.neeru.architecturecomponent.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class, dependencies = NetworkComponent.class)
public interface AppComponent {
    void inject(MVVMApplication app);
}