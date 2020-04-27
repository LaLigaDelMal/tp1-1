package tp1;

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
	
	private Buffardo buffardo;
	private Article article;
	
	
	public Consumidor(Buffardo Buffardo) {
		this.buffardo = Buffardo;
		article = new Article();
	}
	
	@Override
	public void run() {
		while(article.getArtConsum()<1000) {
			//try {	
				System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
				consume();
				
				//Long dormir=(long)( Math.random() );
				//TimeUnit.MILLISECONDS.sleep(dormir);
				//sleep(500,700);
				buffardo.setConsumerState(Thread.currentThread().getName(), Estados.DISPONIBLE.name());
				
				
		//	}

//			catch(InterruptedException e){
//				System.out.println("hay que traer el pan a la mesa");
//			}
		}
	}
	
	public void consume() {
		buffardo.takeItem();
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