package com.example.apisample.ui.list;

import android.os.Bundle;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apisample.R;
import com.example.apisample.databinding.ActivityListBinding;
import com.example.apisample.utils.Status;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    private ListViewModel viewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        adapter = new UserAdapter();
        initViews();
        observeUsers();

        viewModel.loadUsers();
    }

    private void initViews(){
        binding.toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUsers.setAdapter(adapter);
    }

    private void observeUsers(){
        viewModel.getUsers().observe(this, resource -> {
            if (resource != null) {
                if (resource.getStatus() == Status.LOADING) {
                    binding.progressBar.setVisibility(android.view.View.VISIBLE);
                } else if (resource.getStatus() == Status.SUCCESS) {
                    binding.progressBar.setVisibility(android.view.View.GONE);
                    if (resource.getData() != null) {
                        adapter.submitList(resource.getData());
                    }
                } else if (resource.getStatus() == Status.ERROR) {
                    binding.progressBar.setVisibility(android.view.View.GONE);
                    Toast.makeText(this, resource.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}