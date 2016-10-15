package framework;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.util.Duration;

public class AudioHandler {
	static Mp3Player mp3=new Mp3Player();
	static WavPlayer mainPlayer;
	public static synchronized void playSound(String wav) {
		  Thread t=new Thread(new WavPlayer(wav));
		  t.start();		  
	}
	public static void playMusic(String mp3_file){
		mp3.playMedia(mp3_file);
	}	
	public static void changeTrack(String mp3_file){
		mp3.switchTrack(mp3_file);
	}
	public static void playBackground(String wav){
    	mainPlayer=new WavPlayer(wav);
    	Thread t=new Thread(mainPlayer);
    	t.start();
    }
	public static void stopBackgroundMusic(){
		mainPlayer.stop();
	}
	
}
class Mp3Player extends Application{
	 	MediaPlayer mediaPlayer;
		public void playMedia(String mp3) {	        
	        Media media = new Media(new File(mp3).toURI().toString());
	        mediaPlayer = new MediaPlayer(media);
	        //mediaPlayer.setAutoPlay(true);
	        mediaPlayer.setOnEndOfMedia(new Runnable() {
	            public void run() {
	                mediaPlayer.seek(Duration.ZERO);
	              }
	          });
	        mediaPlayer.play();
	    }
	    public void stop(){
	    	mediaPlayer.stop();
	    }	 
	    public void switchTrack(String mp3){
	    	stop();
	    	playMedia(mp3);	    	
	    }
	    
		@Override
		public void start(Stage arg0) throws Exception {
			// TODO Auto-generated method stub
			
		}
}
class WavPlayer implements Runnable,LineListener{
	boolean is_playing;
	String sound;
	Clip clip;
	public WavPlayer(String sound){
		this.sound=sound;
	}
	public void run(){
		try{
			File audioFile=new File(sound);
	        clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(audioFile);
	        AudioFormat format=inputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
	        clip.open(inputStream);
	        clip.start();
	        while (is_playing) {
				// wait for the playback completes
				try {
					Thread.sleep(1000);
					//wait();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}				        
			audioClip.close();
		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
	}	
	public void stop(){
		is_playing=false;
		clip.stop();
		clip.close();
	}
	@Override
	public synchronized void update(LineEvent event) {
		LineEvent.Type type = event.getType();		
		if (type == LineEvent.Type.START) {
			is_playing=true;
			System.out.println("Playback started.");
		} else if (type == LineEvent.Type.STOP) {
			is_playing = false;
			System.out.println("Playback completed.");
		}
	}
}
