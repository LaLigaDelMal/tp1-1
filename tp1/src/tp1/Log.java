package tp1;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;

public class Log implements Runnable {

	private Buffer buffer;
	private HashMap<String,String> consumerState;
	private FileWriter f;
	private PrintWriter pw;
	private Articulo articulo_aux;
	private int threadQuantity;

	public Log(Buffer buffer, int cant) {

		this.articulo_aux = new Articulo();
		this.buffer = buffer;
		this.threadQuantity = cant;
		consumerState = new HashMap<String,String> ();

		for(int i=0; i<threadQuantity; i++) {
			consumerState.put(("Consumidor "+i), Estados.DISPONIBLE.name());
		}

		try {
			f  = new FileWriter(".\\filename.txt");
			pw = new PrintWriter(f);
			pw.println("Prueba");
		}
		catch(IOException e){
			System.out.println("IOException (log)");
		}
	}

	
	@Override
	public void run() {
			try {
				pw.println("Fecha inicio: "+new Date());
				while(articulo_aux.getArtConsum()<1000) {

					TimeUnit.SECONDS.sleep(2);
					pw.println("Cantidad de lugares ocupados del buffer: "+buffer.get_Counter());
					pw.println("Cantidad de articulos descartados: "+articulo_aux.getArtDisc());
					pw.println("Cantidad de articulos consumidos: "+articulo_aux.getArtConsum());
					
					pw.println("--------------------------Thread States--------------------------");
					consumerState = buffer.getConsumerState();
					for(String s: consumerState.keySet()) {
						pw.println(s+" "+consumerState.get(s));
					}
					pw.println("-----------------------------------------------------------------");
					pw.println("Fecha de fin: "+new Date());

				}
				pw.close();
				f.close();

			}
			catch(InterruptedException | IOException e) {
				System.out.println("IOException o InterruptedException (run log)");
			}
	}
} 