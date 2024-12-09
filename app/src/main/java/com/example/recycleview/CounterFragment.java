package com.example.recycleview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycleview.databinding.FragmentCounterBinding;

public class CounterFragment extends Fragment {
    private FragmentCounterBinding binding;
    private int counter = 0;

    public CounterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCounterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.counterText.setText(String.valueOf(counter));

        binding.incrementButton.setOnClickListener(v -> {
            counter++;
            binding.counterText.setText(String.valueOf(counter));
        });

        binding.navigateButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("counter_value", counter);

            ResultFragment resultFragment = new ResultFragment();
            resultFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, resultFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}