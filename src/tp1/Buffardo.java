package tp1;

import java.util.concurrent.Semaphore;

public class Buffardo {
    private Article[] lugares;
    private final Semaphore semaforo;
    int cantLugares;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[cantLugares];
        semaforo = new Semaphore(1);
    }
    
    public void addItem(Article art) {
    	try {
    		semaforo.acquire();
    		//TODO duracion en ms
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
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}finally {
    		semaforo.release();
    	}
    }


    public void takeItem() {
    	try {
    		semaforo.acquire();
    		//TODO duracion en ms
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
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}finally {
    		semaforo.release();
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
