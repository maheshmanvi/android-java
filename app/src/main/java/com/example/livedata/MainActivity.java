package com.example.livedata;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainViewModel model;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        applyWindowInsets();

        // Get ViewModel
        model = new ViewModelProvider(this).get(MainViewModel.class);

        // METHOD 1:
        // Create the observer which updates the UI.
        // This Observer can be reused or removed later.
        final Observer<String> dataObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // Update the UI
                binding.liveTextView.setText(s);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getLiveText().observe(this, dataObserver);

        // Reusing same Observer
        // anotherLiveData.observe(this, dataObserver);

        // Removing the Observer
         model.getLiveText().removeObserver(dataObserver);


        // METHOD 2:
        // 1. Observe LiveData (Without Lambda)
        model.getLiveText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // Automatically runs whenever the value changes
                binding.liveTextView.setText(s);
            }
        });

        // OR
        // 2. Observe LiveData (With Lambda)
        // model.getLiveText().observe(this, text -> binding.liveTextView.setText(text));


        // Change the value when button is clicked
        binding.btnChange.setOnClickListener(v -> {
            // Updates LiveData
             model.setLiveText(String.valueOf(binding.editTextView.getText()));
        });
    }

    private void applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}