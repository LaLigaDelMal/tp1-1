package tp1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
public class Buffardo {
    private Article[] lugares;
    private final Semaphore semaforo;
    int cantLugares;
    private Article article = new Article();
    private ReadWriteLock lock;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[cantLugares];
        semaforo = new Semaphore(1);
        lock = new ReentrantReadWriteLock();
    }
    
    
	public boolean addItem(Article art) {
    	boolean success=false;
    	try {
    		semaforo.acquire(); //Se pelean por el semaforo   
			
    		if( !this.isFull() ){
    			for (int i = 0; i < this.lugares.length; i++) {
    				if(this.lugares[i]==null){
    					this.lugares[i]=art;
    					System.out.println("Se agreg� el articulo exitosamente");
    					i=cantLugares+1;
    					success=true;
    				}
    			}
    		}else{
    			System.out.println("Buffer lleno!");
    		}
    	}catch(InterruptedException e){ 
    		e.printStackTrace();   //Los que no consiguen el semaforo se vienen ac�
    	}finally {
    		semaforo.release();   //el que gan� el semaforo se viene ac�
    	}
    	return success;
    }


	public void takeItem(int pos) {
		this.lugares[pos] = null;
		article.incrementArtConsum();
		System.out.println("Se consumio el articulo exitosamente");
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
            if(this.lugares[i]!=null){x++;}
        }
        return x;
    }
    
    public void aguanta(int minimun, int maximun){
    	int max = maximun; int min = minimun; 
        int range = max - min + 1; 
        
    	Long dormir=(long)( Math.random() * range);
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }
    
    public void get_WLock() {
    	lock.writeLock().tryLock();
    }
    
    public boolean get_RLock() {
    	lock.readLock().lock();
    	if( !this.isEmpty() ) {
    		for (int i = 0; i < this.lugares.length; i++) {
    			if(this.lugares[i]!=null){
    				this.takeItem(i);
    				lock.readLock().unlock();
    				return false;
    			}
    		}
    	}
		lock.readLock().unlock();
		return true;
    }

}
