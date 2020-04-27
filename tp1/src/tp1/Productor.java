package tp1;
import java.util.concurrent.TimeUnit;

public class Productor implements Runnable{

	private Buffer buffer;
	public Articulo articulo;
	private Articulo articulo_aux;

	public Productor(Buffer buffer) {
		this.buffer = buffer;
		articulo_aux = new Articulo();
	}

	
	private void generate() {											//metodo que genera un nuevo articulo
		Articulo articulo = new Articulo();
		this.articulo = articulo;
	}

	private void discard() {											//metodo que descarta un articulo 
		articulo.incrementArtDisc();
		articulo = null;
	}

	private boolean placement() {										//metodo que intenta añadir/colocar un articulo en el buffer
		System.out.printf("%s : Se intentara añadir un articulo\n",Thread.currentThread().getName());
		return buffer.addItem(articulo);
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

	
	@Override
	public void run() {
		while(articulo_aux.getArtConsum()<1000) {								//mientras la cantidad de articulos consumidos sea <1000 continuo la ejecucion
				sleep(60,100);						                       	//se genera un articulo en un tiempo aleatorio 
				generate();
				if( placement() == false )
					discard();

		}
	}

} 