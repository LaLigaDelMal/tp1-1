package tp1;

public class Articulo {

	private static int artConsum = 0;
	private static int artDisc = 0;

	public Articulo() {
	}

	
	public int getArtConsum() {					//devuelve la cantidad de articulos consumidos
		return artConsum;
	}

	public int getArtDisc() {					//devuelve la cantidad de articulos consumidos
		return artDisc;
	}

	public void incrementArtConsum() {			//incrementa la vble articulos consumidos
		artConsum++;
	}

	public void incrementArtDisc() {			//incrementa la vble articulos descartados
		artDisc++;
	}

} 