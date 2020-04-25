package tp1;

import java.util.concurrent.TimeUnit;

public class Productor implements Runnable{

	private Buffardo buffer;
	public Article article;
	
	public Productor(Buffardo buffer) {
		this.buffer = buffer;
	}
	
	private void generate() {
		Article article = new Article();
		this.article = article;
	}
	
	private void discard(Article article) {
		article = null;
	}
	
	private boolean placement(Article article) {
		System.out.printf("%s : Voy a ponerla\n",Thread.currentThread().getName());
		return buffer.addItem(article);
	}

	@Override
	public void run() {
		
		try {
			
			Long dormir=(long)( Math.random() );
			TimeUnit.MILLISECONDS.sleep(dormir);
			
			generate();
			if( placement(article) == false )
				discard(article);
			}
		
		catch(InterruptedException e){
			System.out.println("hay que traer el pan a la mesa");
		}
	}
	
}
