package tp1;

public class Article {
    
	private static int artConsum = 0;
	private static int artDisc = 0;
	
	public Article() {
	}

	public int getArtConsum() {
		return artConsum;
	}

	public int getArtDisc() {
		return artDisc;
	}

	public void incrementArtConsum() {
		artConsum++;
	}

	public void incrementArtDisc() {
		artDisc++;
	}

}