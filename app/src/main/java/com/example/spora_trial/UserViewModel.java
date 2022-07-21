package com.example.spora_trial;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.spora_trial.db.UserEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRep;
    private LiveData<List<UserEntity>> mUsers;

    public UserViewModel(Application application){
        super(application);
        mRep=new UserRepository(application);
        mUsers=mRep.getAll();
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mUsers;
    }

    public void insert(UserEntity user){ mRep.insert(user);}
    public void delete(int idUsr){ mRep.deleteById(idUsr);}
    public List<UserEntity> getUser(String mail) { return mRep.getUser(mail);}
}
