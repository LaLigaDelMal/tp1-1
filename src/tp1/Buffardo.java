package tp1;

import java.util.LinkedList;
import java.util.HashMap;

public class Buffardo {


	private LinkedList<Article>    lugares;
	private HashMap<String,String> consumerState;
	private Article                article;
	private final int              bufferSize;
	private final Object           controlState;


	public Buffardo(int i) {
		
		bufferSize    = i;
		lugares       = new LinkedList<Article>();
		consumerState = new HashMap<String,String>();
		article       = new Article();
        controlState  = new Object();
	}


	public synchronized boolean addItem(Article art) {
		if(lugares.size()<bufferSize) {
			lugares.add(art);
			System.out.printf("Articulo añadido por %s\n", Thread.currentThread().getName());
			notify();
			return true;
		}
		else {
			notify();
			return false;
		}
	}

	public synchronized void takeItem() {
		while(lugares.size()==0) {
			try {
				setConsumerState(Thread.currentThread().getName(),Estados.OCUPADO.name());
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(article.getArtConsum()<1000) {
			lugares.remove();
			article.incrementArtConsum();
			setConsumerState(Thread.currentThread().getName(),Estados.CONSUMIENDO.name());
			System.out.printf("Articulo consumido por %s\n", Thread.currentThread().getName());
			notify();
		}
			//setConsumerState(Thread.currentThread().getName(),Estados.CONSUMIENDO.name());
	}
	
	public int get_Counter() {
		return lugares.size();
	}
	
	public void setConsumerState(String id, String state) {
		synchronized(controlState) {
			consumerState.put(id, state);
		}
	}
	
	public HashMap<String,String> getConsumerState(){
		return consumerState;
	}
}