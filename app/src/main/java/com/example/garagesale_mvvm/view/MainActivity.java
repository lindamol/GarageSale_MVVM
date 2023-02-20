package com.example.garagesale_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingComponent;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.garagesale_mvvm.R;
import com.example.garagesale_mvvm.databinding.ActivityMainBinding;
import com.example.garagesale_mvvm.model.LoginModel;
import com.example.garagesale_mvvm.viemodel.LoginViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
       private LoginViewModel loginViewModel;
    private  ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        activityMainBinding.setMyvariable("Welcome to GarageSale");

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //Viewmodel updates the model observing changes in the view
        activityMainBinding.setLifecycleOwner(this);
        //Binding to view using the LoignViewModel variable in the view
        activityMainBinding.setLoginViewModel(loginViewModel);
        //Observe method takes the lifecycle owner object, any changes will observe and give ot viewmodel
        loginViewModel.getUserDetails().observe(this, new Observer<LoginModel>() {
                    @Override
                    public void onChanged(LoginModel loginModel) {
                        if (TextUtils.isEmpty(Objects.requireNonNull(loginModel).getEmail())) {
                            activityMainBinding.inputEmailLogin.setError("Please Enter Email id");
                            activityMainBinding.inputEmailLogin.requestFocus();
                        } else if (!loginModel.isEmailvalid()) {
                            activityMainBinding.inputEmailLogin.setError("Please Enter Valid email");
                            activityMainBinding.inputEmailLogin.requestFocus();
                        } else if (TextUtils.isEmpty(Objects.requireNonNull(loginModel).getPassword())) {
                            activityMainBinding.inputPasswordLogin.setError("Please Enter Password");
                            activityMainBinding.inputPasswordLogin.requestFocus();
                        } else if (!loginModel.isPasswordlength8()) {
                            activityMainBinding.inputPasswordLogin.setError("Please Enter passwrod greater than 8");
                            activityMainBinding.inputPasswordLogin.requestFocus();
                        } else {
                            Toast.makeText(MainActivity.this, loginModel.getEmail(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, loginModel.getPassword(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }

        );


    }
}