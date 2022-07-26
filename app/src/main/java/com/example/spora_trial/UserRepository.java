package com.example.spora_trial;

import android.app.Application;
import android.os.AsyncTask;

import com.example.spora_trial.db.UserDao;
import com.example.spora_trial.db.UserEntity;
import com.example.spora_trial.db.UserRoomDatabase;

import java.util.List;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public List<UserEntity> getAll(){ return userDao.getAll();}

    public void deleteById(int idUsr){
        userDao.deleteById(idUsr);
    }

    public List<UserEntity> getUser(String mail){
        return userDao.getUser(mail);
    }

    public void insert(UserEntity user){
        new insertAsyncTask(userDao).execute(user);
    }
    //Async insert operation
    private static  class insertAsyncTask extends AsyncTask<UserEntity, Void, Void>{
        private UserDao userDaoAsync;
        insertAsyncTask(UserDao dao){
            userDaoAsync=dao;
        }
        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDaoAsync.insert(userEntities[0]);
            return null;
        }
    }
}
