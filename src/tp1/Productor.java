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
		while(article_aux.getArtConsum()<1000) {
			
				sleep(60,100);
//				Long dormir=(long)( Math.random() );
//				TimeUnit.MILLISECONDS.sleep(dormir);

				generate();
				if( placement() == false )
					discard();
		}
	}
	
    public void sleep(int minimun, int maximun){
    	int max = maximun; int min = minimun; 
        int range = max - min+1; 
        
    	Long dormir=(long)(Math.random() * range) + min;
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }
	
}