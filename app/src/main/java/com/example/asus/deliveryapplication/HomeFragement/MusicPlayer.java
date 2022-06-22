package com.example.asus.deliveryapplication.HomeFragement;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.asus.deliveryapplication.R;

public class MusicPlayer extends Service {
    private  MediaPlayer mPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action=intent.getStringExtra("action");
        if(action.equals("play")){
            playMusic();
        }
        else if(action.equals("pause")){
            pauseMusic();
        }
        else if(action.equals("stop")){
            stopMusic();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void playMusic(){
        if(mPlayer==null){
            mPlayer= MediaPlayer.create(this, R.raw.music);
        }
        mPlayer.start();
    }
    public void pauseMusic(){
        if(mPlayer!=null&&mPlayer.isPlaying()){
            mPlayer.pause();
        }
    }
    public void stopMusic(){
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer.reset();
            mPlayer.release();
            mPlayer=null;
        }
    }
}
