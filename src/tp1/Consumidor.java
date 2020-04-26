package tp1;
//Diferencias con el diagrama de clases: 
//eliminada variable state
//eliminado metodo setState()

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
	
	private String state;
	private Buffardo buffardo;
	private Article article;
	//private String state;
	
	public Consumidor(Buffardo Buffardo) {
		this.buffardo = Buffardo;
		state = "Disponible";
		article = new Article();
	}
	
	@Override
	public void run() {
		while(article.getArtConsum()<100) {
			try {	
				System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
				consume();
				setState("Consumiendo");
				Long dormir=(long)( Math.random() );
				TimeUnit.MILLISECONDS.sleep(dormir);
				setState("Disponible");
			}

			catch(InterruptedException e){
				System.out.println("hay que traer el pan a la mesa");
			}
		}
	}
	
	public void consume() {
		buffardo.takeItem();
	
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
    	this.state = state;
    }
    
}
