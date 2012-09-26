package com.arne5.ezratictactoe;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

// manage sounds
public class SoundMangler {

	
	private SoundPool dSoundPool;
	private HashMap dSoundPoolMap;
	private AudioManager dAudioManager;
	private Context dContext;
	
	
	public void soundInit(Context c){
		dContext = c;
		dSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,0);
		dSoundPoolMap = new HashMap();
		dAudioManager = (AudioManager)dContext.getSystemService(Context.AUDIO_SERVICE);
		
	}
	public void addSound(int index, int SoundID){
		dSoundPoolMap.put(index, dSoundPool.load(dContext, SoundID,1));
		
		
		
	}
	public void playSound(int index){
		float streamVolume = dAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / dAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//dSoundPool.play(dSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
		dSoundPool.play(index, streamVolume, streamVolume, 1, 0, 1f);
		
		
	}
	public void playSoundLoop(int index){
		float streamVolume = dAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / dAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		dSoundPool.play(index, streamVolume, streamVolume, 1, -1, 1f);
		
	}
}
