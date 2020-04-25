package tp1;
//Diferencias con el diagrama de clases: 
//eliminada variable state
//eliminado metodo setState()

public class Consumidor implements Runnable {
	
	
	private Buffardo buffer;
	//private String state;
	
	public Consumidor(Buffardo buffer) {
		
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		
		System.out.printf("%s : I'm going to consume an article\n",Thread.currentThread().getName());
        consume();
        System.out.printf("%s : I've just consume an article! yay\n",Thread.currentThread().getName());
	}
	
	public void consume() {
		buffer.takeItem();
	
	}
	public String getState() {
		return Thread.currentThread().getState().name();
	}
  /* public void setState(String state) {
    	this.state = state;
    }*/
    
}
