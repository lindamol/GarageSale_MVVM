package com.example.garagesale_mvvm.viemodel;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.garagesale_mvvm.model.LoginModel;

public class LoginViewModel extends ViewModel {

    //Create Mutable Livedate : Which automaticaly update in the layout
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    private MutableLiveData<LoginModel> loginModelMutableLiveData;

    //Create a Mutable Method to get User details
    public MutableLiveData<LoginModel> getUserDetails(){
        if(loginModelMutableLiveData == null){
            loginModelMutableLiveData = new MutableLiveData<>();
        }
        return loginModelMutableLiveData;
    }

    // Onclick method for Login button

    public void onClick(View view){
        LoginModel loginModel = new LoginModel(Email.getValue(),Password.getValue());
        loginModelMutableLiveData.setValue(loginModel);

    }

 }
