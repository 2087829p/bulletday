package testGame;

import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.util.*;

public class ScoreBoard {
	private ArrayList<Integer> scores;
	private static final String FILENAME = "scoreboard.txt";
	public ScoreBoard(){
		File f = new File(FILENAME);
		scores = new ArrayList<Integer>();
		if(f.isFile()){
			BufferedReader r = null;
			try {
				r = new BufferedReader(new FileReader(f));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(r != null) {
				String l = "";
				try {
				l = r.readLine();
				} catch (Exception e){
					e.printStackTrace();
				}
				while(l != "" && l != null) {
					System.out.println(l);
					try {
						scores.add(new Integer(Integer.parseInt(l)));
						l = r.readLine();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				System.out.println(scores.toString());
				try {
				r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void addScore(int score) {
		scores.add(new Integer(score));
		Collections.sort(scores);
		Collections.reverse(scores);
	}
	
	public String toString() {
		return getNtoM(0,scores.size());
	}
	public ArrayList<Integer> getList() {
		return scores;
	}
	
	public String getN(int n){
		return getNtoM(0,n);
	}
	public String getNtoM(int m, int n) {
		StringBuilder sb = new StringBuilder();
		for(Integer i : scores.subList(m, n)) {
			sb.append(i.toString() + '\n');
		}
		return sb.toString();
	}
	public void saveScores() {
		String sb = this.toString();
		BufferedWriter r = null;
		try {
			r = new BufferedWriter(new FileWriter(FILENAME));
			r.write(sb);
			System.out.println(sb);
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
