package com.example.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> liveText = new MutableLiveData<String>();

    public LiveData<String> getLiveText(){
        return  liveText;
    }

    public void setLiveText(String text){
        liveText.setValue(text);
    }

    public void setPostLiveText(String text){
        liveText.postValue(text);
    }
}
