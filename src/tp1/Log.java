package tp1;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;

public class Log implements Runnable {
	
	private Buffardo    buffer;
	private HashMap<String,String> consumerState;
	private FileWriter  f;
	private PrintWriter pw;
	private Article     article_aux;
	
	
	public Log(Buffardo buffer,Thread[] consumer_list, int cant) {
		
		this.article_aux    = new Article();
		this.buffer         = buffer;

		
		
		try {
			f  = new FileWriter(".\\filename.txt");
			pw = new PrintWriter(f);
			pw.println("Prueba");
		}
		catch(IOException e){
			System.out.println("IOException, al lobby pt");
		}
	}
	
	@Override
	public void run() {
			try {
				pw.println("Fecha inicio: "+new Date());
				while(article_aux.getArtConsum()<1000) {
					
					TimeUnit.MILLISECONDS.sleep(100);
					pw.println("Cantidad de lugares ocupados del buffer: "+buffer.get_Counter());
					pw.println("Cantidad de articulos descartados: "+article_aux.getArtDisc());
					pw.println("Cantidad de articulos consumidos: "+article_aux.getArtConsum());
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
				System.out.println("IOException o InterruptedException (we dont want 2 know)");
			}
	}

}