package tp1;
//Diferencias con el diagrama de clases: 
//eliminada variable state
//eliminado metodo setState()

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
	
	
	private Buffardo buffardo;
	//private String state;
	
	public Consumidor(Buffardo Buffardo) {
		
		this.buffardo = Buffardo;
	}
	
	@Override
	public void run() {
		try {
			System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
			System.out.println(buffardo.getCuantity());
			consume();
			
			Long dormir=(long)( Math.random() );
			TimeUnit.MILLISECONDS.sleep(dormir);
			
			System.out.println(buffardo.getCuantity());
			System.out.printf("%s : I've just consume an article! yay\n",Thread.currentThread().getName());

		}catch(InterruptedException e){
				System.out.println("hay que traer el pan a la mesa");
			}
		
	}
	
	public void consume() {
		buffardo.takeItem();
	
	}
	public String getState() {
		return Thread.currentThread().getState().name();
	}
  /* public void setState(String state) {
    	this.state = state;
    }*/
    
}
