package tp1;

import jdk.nashorn.api.tree.ForInLoopTree;

public class Main {
	public static final int CANT_PROD = 5;
	public static final int CANT_CONS = 5;

    public static void main(String[] args) {
    	
        Buffardo buffer = new Buffardo(7);
        Article articulo = new Article();					
		
		Thread productores [] = new Thread [CANT_PROD];			//creo array de hilos que contendran objetos del tipo Productor
		Thread consumidores [] = new Thread [CANT_CONS];		//creo array de hilos que contendran objetos del tipo Consumidor
		
		
		for (int i=0; i<CANT_PROD; i++) {
			
			Productor productor = new Productor(buffer);			//creo un objeto productor
			productores[i] = new Thread(productor);					//seteo el hilo con el objeto productor
			productores[i].setName("Productor " + i);				//declaro nombres para los hilos
			productores[i].start();									//ejecuto el hilo
		}
		
		
		
		for (int i=0; i<CANT_CONS; i++) {
			
			Consumidor consumidor = new Consumidor(buffer);
			consumidores[i] = new Thread(consumidor);
			consumidores[i].setName("Consumidor " + i);
			consumidores[i].start();
		}
		
		//Log log = new Log(buffer, consumidores);					//creo un objeto log que contiene la info de los resultados


    }
}
