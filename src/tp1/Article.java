package tp1;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Article {
    
	private static int artConsum = 0;
	private static int artDisc = 0;
	
	private Lock consumLock;
	private Lock discLock;
	
	public Article() {
		consumLock = new ReentrantLock();
		discLock = new ReentrantLock();
	}
	
	public int getArtConsum() {
		return artConsum;
	}
	
	public int getArtDisc() {
		return artDisc;
	}
	
	public void incrementArtConsum() {
		consumLock.lock();
		try {
			artConsum++;
		}
		finally {
			consumLock.unlock();
		}
	}
	
	public void incrementArtDisc() {
		discLock.lock();
		try {
			artDisc++;
		}
		finally {
			discLock.unlock();
		}
	}
	
}
