package tp1;
//Diferencias con el diagrama de clases: 
//eliminada variable state
//eliminado metodo setState()


public class Consumidor implements Runnable {
	
	
	private Buffardo buffardo;
	//private String state;
	
	public Consumidor(Buffardo Buffardo) {
		
		this.buffardo = Buffardo;
	}
	
	@Override
	public void run() {
		System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
		consume();
		System.out.printf("%s : I've just consume an article! yay\n",Thread.currentThread().getName());
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
