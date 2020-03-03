package com.github.ayltai.pricecalculator.config;

import javax.inject.Singleton;

import androidx.annotation.NonNull;

import dagger.Component;

@Singleton
@Component(modules = { ConfigModule.class })
public interface ConfigComponent {
    @NonNull
    UserConfigurations userConfigurations();
}
