package com.example.livedata;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    MainViewModel model;
    TextView liveTextView;
    TextInputEditText editTextView;
    MaterialButton dataChangeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find views
        findViews();

        // Get ViewModel
        model = new ViewModelProvider(this).get(MainViewModel.class);

        // METHOD 1:

        // Create the observer which updates the UI.
        final Observer<String> dataObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // Update the UI
                liveTextView.setText(s);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getLiveText().observe(this, dataObserver);


        // METHOD 2:
        // Observe LiveData
        model.getLiveText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // Automatically runs whenever the value changes
                liveTextView.setText(s);
            }
        });



        // Change the value when button is clicked
        dataChangeButton.setOnClickListener(v ->{
            // Updates LiveData
            model.setLiveText(String.valueOf(editTextView.getText()));
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void findViews() {
        liveTextView = findViewById(R.id.liveTextView);
        editTextView = findViewById(R.id.editTextView);
        dataChangeButton = findViewById(R.id.btnChange);
    }
}