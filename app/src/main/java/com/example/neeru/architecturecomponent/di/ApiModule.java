package com.example.neeru.architecturecomponent.di;

import com.example.neeru.architecturecomponent.rest.ApiServices;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    @CustomScope
    ApiServices providesApiServices(Retrofit retrofit) {
        return retrofit.create(ApiServices.class);
    }
}
