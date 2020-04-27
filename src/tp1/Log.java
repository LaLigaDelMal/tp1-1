package tp1;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Log implements Runnable {
	private Buffardo buffer;
	private Thread[] consumer_list;
	private FileWriter f;
	private PrintWriter pw;
	private Article article_aux;
	
	public Log(Buffardo buffer,Thread[] consumer_list) {
		this.article_aux = new Article();
		this.buffer = buffer;
		this.consumer_list = consumer_list;
		try {
			f = new FileWriter(".\\filename.txt");
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
					TimeUnit.MILLISECONDS.sleep(500);
					pw.println("Cantidad de lugares ocupados del buffer: "+buffer.get_Counter());
					pw.println("Cantidad de articulos descartados: "+article_aux.getArtDisc());
					pw.println("Cantidad de articulos consumidos: "+article_aux.getArtConsum());
					for(int i=0;i<consumer_list.length;i++) {
						pw.println(consumer_list[i].getState());
					}
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