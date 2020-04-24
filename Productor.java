import java.util.concurrent.TimeUnit;

public class Productor implements Runnable{

	private Buffer buffer;
	public Article article;
	
	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}
	
	private void generate() {
		Article article = new Article();
		this.article = article;
	}
	
	private void discard(Article article) {
		article = null;
	}
	
	private Boolean placement(Buffer buffer, Article article) {
		return buffer.additem(article);
	}

	@Override
	public void run() {
		
		try {
			
			Long dormir=(long)( Math.random() );
			TimeUnit.MILLISECONDS.sleep(dormir);
			
			generate();
			if( placement( buffer, article ) != true )
				discard(article);
			}
		
		catch(InterruptedException e){
			System.out.println("hay que traer el pan a la mesa");
		}
	}
	
}
