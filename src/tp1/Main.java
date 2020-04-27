package tp1;


public class Main {
	public static final int CANT_PROD = 5;  //Cantidad de productores
	public static final int CANT_CONS = 10;  //Cantidad de Consumidores

    public static void main(String[] args) {
    	
        Buffardo buffer = new Buffardo(25,CANT_CONS);  //args: Cantidad de espacio en el Buffer			
		
		Thread productores  [] = new Thread [CANT_PROD];	    //array de hilos que contendran objetos del tipo Productor
		Thread consumidores [] = new Thread [CANT_CONS];		//array de hilos que contendran objetos del tipo Consumidor
		
		for (int i=0; i<CANT_PROD; i++) {
			
			Productor productor = new Productor(buffer);			//Se crean los objetos productor
			productores[i] = new Thread(productor);					//y se mandan a los hilos
			productores[i].setName("Productor " + i);				//Nombres para los hilos
			productores[i].start();									//Se ejecuta los hilos
		}
		
		for (int i=0; i<CANT_CONS; i++) {
			
			Consumidor consumidor = new Consumidor(buffer);
			consumidores[i] = new Thread(consumidor);
			consumidores[i].setName("Consumidor " + i);
			consumidores[i].start();
		}
		
		Log    log   = new Log(buffer, consumidores, CANT_CONS);				//Se crea el log
		Thread log_t = new Thread(log);
		log_t.start();

    }
}