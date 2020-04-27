package tp1;
//Diferencias con el diagrama de clases: 
//eliminada variable state
//eliminado metodo setState()

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
	
	//private String state;
	private Buffardo buffardo;
	private Article article;
	
	
	public Consumidor(Buffardo Buffardo) {
		this.buffardo = Buffardo;
		//state = "Disponible";
		article = new Article();
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
		buffardo.takeItem();
	}
	
//	public String getEstados() {
//		return state;
//	}
//	
//	public void setState(String state) {
//    	this.state = state;
//    }
    
}