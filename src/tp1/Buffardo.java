package tp1;

public class Buffardo {
    private Article[] lugares;
    int cantLugares;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[cantLugares];
    }
    
    public void addItem(Article art) {
    	
    	if( !this.isFull() ){
    		for (int i = 0; i < this.lugares.length; i++) {
                if(this.lugares[i]==null){
                	this.lugares[i]=art;
                    System.out.println("Se agregó el articulo exitosamente");
                    i=cantLugares+1;
                }
            }
        }else{
            System.out.println("Jodete puto, no entra");
        }
    }


    public void takeItem() {
        if( !this.isEmpty() ) {
        	for (int i = 0; i < this.lugares.length; i++) {
        		if(this.lugares[i]!=null){
        			this.lugares[i] = null;
        			System.out.println("Se consumio el articulo exitosamente");
        			i=this.lugares.length+1;
        		}
            }
        }else{
            System.out.println("No hay articulos, aguanta un cacho");
        }
    }

    public boolean isFull(){
        if(this.getCuantity()==this.cantLugares){return true;}else{return false;}
    }

    public boolean isEmpty(){
        if(this.getCuantity()<= 0){
            return true;
        }else{
            return false;
        }
    }

    public int getCuantity(){
        int x=0;
        for (int i = 0; i < this.lugares.length; i++) {
            if(this.lugares[i]!=null){
        		x++;
        	}
        }
        return x;
    }

}
