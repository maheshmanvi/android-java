package com.example.apisample.di;

import com.example.apisample.data.repository.UserRepository;

public final class ServiceLocator {

    // Why??
    // Instead of creating new instance everywhere like 'new UserRepository();'
    // Now we simply write: 'ServiceLocator.provideUserRepository();'

    private static volatile UserRepository userRepository;

    // Private constructor to prevent instantiation of this utility class.
    // Even instantiated, then throws the 'IllegalStateException'.
    private ServiceLocator(){
        throw new IllegalStateException("Utility class, cannot instantiate!");
    }

    public static UserRepository provideUserRepository() {
        if(userRepository == null){
            synchronized (ServiceLocator.class){
                if(userRepository == null){
                    userRepository = new UserRepository();
                }
            }
        }
        return userRepository;
    }
}
