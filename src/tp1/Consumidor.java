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
				System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
				consume();
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
	
    public void sleep(int minimun, int maximun){
    	int max = maximun; int min = minimun; 
        int range = max - min+1; 
        
    	Long dormir=(long)(Math.random() * range) + min;
    	try {
    		System.out.println("-----------------------------------");
    		System.out.println(dormir);
    		
			TimeUnit.MILLISECONDS.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }

    
}