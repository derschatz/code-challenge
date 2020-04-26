package com.example.searchapp.ui.main;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.searchapp.R;
import com.example.searchapp.services.EmailProcessorService;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private boolean isBound;
    private EmailProcessorService mService;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void bindService(FragmentActivity activity) {
        if (activity == null) {
            return;
        }
        final Intent intent = new Intent(activity, EmailProcessorService.class);
        activity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        if (isBound) {
            unbindService(getActivity());
        }
        super.onStop();
    }

    private void unbindService(FragmentActivity activity) {
        if (activity == null) {
            return;
        }
        activity.unbindService(mConnection);
    }


    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final EmailProcessorService.EmailProcessorBinder binder = (EmailProcessorService.EmailProcessorBinder) service;
            mService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
