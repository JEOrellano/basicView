package com.example.basicview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.basicview.databinding.FragmentSecondBinding;

import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        getParentFragmentManager().setFragmentResultListener("numero", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String numero = result.getString("numero");
                binding.textviewHeader.setText("Aqui hay un número aleatorio entre 0 y " + numero);
                Random random = new java.util.Random();
                Integer randomNumber = 0;
                if (Integer.parseInt(numero) > 0) {
                    randomNumber = random.nextInt(Integer.parseInt(numero)+1);
                }
                binding.textviewRandom.setText(randomNumber.toString());
            }
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    public TextView getTextViewRandom() {
        return binding.textviewRandom;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}