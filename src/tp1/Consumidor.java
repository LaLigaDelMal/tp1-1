package tp1;

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
	private Buffardo buffardo;
	private Article article;
	private Estados estado;
	
	
	public Consumidor(Buffardo Buffardo) {
		this.buffardo = Buffardo;
		article = new Article();
		this.setEstado(Estados.DISPONIBLE);
	}
	@Override
	public void run() {
		while(article.getArtConsum()<1000) {
			try {	
				System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
				consume();
				
				Long dormir=(long)( Math.random() );
				TimeUnit.MILLISECONDS.sleep(dormir);
				buffardo.setConsumerState(Thread.currentThread().getName(), Estados.DISPONIBLE.name());
				
			}

			catch(InterruptedException e){
				System.out.println("hay que traer el pan a la mesa");
			}
		}
	}
	
	public void consume() {
		buffardo.takeItem(this);
	}
	public Estados getEstado() {
		return estado;
	}
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
	

    
}