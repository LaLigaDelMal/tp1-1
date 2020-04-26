package tp1;

import java.util.LinkedList;
public class Buffardo {


	//private Article[] lugares;
	private LinkedList<Article> lugares;
	private Article article;
	private final int bufferSize;


	public Buffardo(int i) {
		bufferSize = i;
		//lugares = new Article[bufferSize];
		lugares = new LinkedList<Article>();
		article = new Article();

	}


	public synchronized boolean addItem(Article art) {
		if(lugares.size()<bufferSize) {
			lugares.add(art);
			System.out.printf("Articulo añadido por %s\n", Thread.currentThread().getName());
			return true;
		}
		else {
			return false;
		}
	}

	public synchronized void takeItem() {
		while(lugares.size()==0) {
			try {
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		notify();
		lugares.remove();
		article.incrementArtConsum();
		System.out.printf("Articulo consumido por %s\n", Thread.currentThread().getName());
	}
	
	public int get_Counter() {
		return lugares.size();
	}
}