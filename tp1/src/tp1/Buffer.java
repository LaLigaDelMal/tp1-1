package tp1;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class Buffer {

	private LinkedList<Articulo> lugares;
	private HashMap<String,String> consumerState;
	private Articulo articulo;
	private final int bufferSize;


	public Buffer(int i, int threadQuantity) {
		bufferSize = i;
		lugares = new LinkedList<Articulo>();
		consumerState = new HashMap<String,String>();
		articulo = new Articulo();
		for(int j=0; i<threadQuantity; j++) {
			consumerState.put(("Consumidor "+j), Estados.DISPONIBLE.name());
		}
	}


	public synchronized boolean addItem(Articulo art) {					//metodo sincronizado que agrega un articulo al buffer
		if(lugares.size()<bufferSize) {
			lugares.add(art);											//seccion critica
			System.out.printf("Articulo añadido por %s\n", Thread.currentThread().getName());
			notify();
			return true;
		}
		else {
			notify();
			return false;
		}
	}

	public synchronized void takeItem() {									//metodo sincronizado que toma un articulo del buffer
		while(lugares.size()==0) {											//mientras el buffer este vacio, el hilo queda dormido
			try {
				setConsumerState(Thread.currentThread().getName(),Estados.DISPONIBLE.name());
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(articulo.getArtConsum()<1000) {
			lugares.remove();												//seccion critica	
			articulo.incrementArtConsum();
			setConsumerState(Thread.currentThread().getName(),Estados.OCUPADO_CONSUMIENDO.name());
			sleep(60,100);
			System.out.printf("Articulo consumido por %s\n", Thread.currentThread().getName());
			notify();
		}
	}

	public int get_Counter() {														//metodo que devuelve la cantidad de lugares ocupados
		return lugares.size();
	}

	public void setConsumerState(String id, String state) {							//metodo que 
			consumerState.put(id, state);
	}

	public HashMap<String,String> getConsumerState(){
		return consumerState;
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