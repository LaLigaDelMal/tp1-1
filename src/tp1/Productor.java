package tp1;

import java.util.concurrent.TimeUnit;

public class Productor implements Runnable{

	private Buffardo buffer;
	public Article article;
	private Article article_aux;
	
	public Productor(Buffardo buffer) {
		this.buffer = buffer;
		article_aux = new Article();
	}
	
	private void generate() {
		Article article = new Article();
		this.article = article;
	}
	
	private void discard() {
		article.incrementArtDisc();
		article = null;
	}
	
	private boolean placement() {
		System.out.printf("%s : Voy a ponerla\n",Thread.currentThread().getName());
		return buffer.addItem(article);
	}

	@Override
	public void run() {
		while(article_aux.getArtConsum()<100) {
			try {

				Long dormir=(long)( Math.random() );
				TimeUnit.MILLISECONDS.sleep(dormir);

				generate();
				if( placement() == false )
					discard();
			}

			catch(InterruptedException e){
				System.out.println("hay que traer el pan a la mesa");
			}
		}
	}
	
}
