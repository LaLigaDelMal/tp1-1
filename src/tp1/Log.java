package tp1;
import java.io.*;
import java.util.concurrent.TimeUnit;


public class Log implements Runnable {
	private Buffardo buffer;
	private Thread[] consumer_list;
	private FileWriter f;
	private PrintWriter pw;
	
	public Log(Buffardo buffer,Thread[] consumer_list) {
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
		//while(true) {
			try {
				TimeUnit.SECONDS.sleep(2);
				pw.println("Cantidad de lugares ocupados del buffer: "+buffer.get_Counter());
				for(int i=0;i<consumer_list.length;i++) {
					pw.println(consumer_list[i].getState());
				}
				pw.close();
				f.close();
			}
			catch(InterruptedException | IOException e) {
				System.out.println("IOException o InterruptedException (we dont want 2 know)");
			}
		//}
	}	
}