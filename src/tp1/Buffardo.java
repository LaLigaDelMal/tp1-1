package tp1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Buffardo {
    private Article[] lugares;
    private final Semaphore semaforo;
    int cantLugares;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[cantLugares];
        semaforo = new Semaphore(1);
    }
    
    
	public boolean addItem(Article art) {
    	boolean success=false;
    	try {
    		semaforo.acquire(); //Se pelean por el semaforo
    		
    		aguanta(1,1000); //product/consum tardan un rato en meter/sacar       
			
    		if( !this.isFull() ){
    			for (int i = 0; i < this.lugares.length; i++) {
    				if(this.lugares[i]==null){
    					this.lugares[i]=art;
    					System.out.println("Se agregó el articulo exitosamente");
    					i=cantLugares+1;
    					success=true;
    				}
    			}
    		}else{
    			System.out.println("Jodete puto, no entra");
    		}
    	}catch(InterruptedException e){ 
    		e.printStackTrace();   //Los que no consiguen el semaforo se vienen acá
    	}finally {
    		semaforo.release();   //el que ganó el semaforo se viene acá
    	}
    	return success;
    }


    public void takeItem() {
    	try {
    		semaforo.acquire();
    		
    		aguanta(1,1000); //product/consum tardan un rato en meter/sacar   
    		
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
    
    public void aguanta(int minimun, int maximun){
    	int max = maximun; 
        int min = minimun; 
        int range = max - min + 1; 
    	Long dormir=(long)( Math.random() * range);
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }
    

}
