package com.example.apisample.ui.main;

import androidx.lifecycle.ViewModel;

// Even though 'MainActivity' contains only one button, yet we have created a ViewModel.
// Why? Because: later if we add -> Authentication, User Session, Deep links, Feature flags - we won't need to refactor the Activity.
public class MainViewModel extends ViewModel {
    // As of now this is empty. It's okay.
    // Not every ViewModel needs a business logic.
}
