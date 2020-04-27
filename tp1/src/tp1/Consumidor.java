package tp1;

public class Consumidor implements Runnable {

	private Buffer buffer;
	private Articulo articulo;

	public Consumidor(Buffer Buffer) {
		this.buffer = Buffer;
		articulo = new Articulo();
	}
	
	public void consume() {									//metodo en el cual el consumidor toma un articulo del buffer
		buffer.takeItem();
	}

	@Override
	public void run() {
		while(articulo.getArtConsum()<1000) {				//mientras la cantidad de articulos consumidos sea <1000 continuo la ejecucion		
				System.out.printf("%s : Voy a consumir un articulo\n",Thread.currentThread().getName());
				consume();
				buffer.setConsumerState(Thread.currentThread().getName(), Estados.DISPONIBLE.name());
			}
		}
}
