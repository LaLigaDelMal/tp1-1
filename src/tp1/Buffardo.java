package tp1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
public class Buffardo {
    private Article[] lugares;
    //private final Semaphore semaforo;
    int cantLugares;
    private Article article = new Article();
    private ReadWriteLock lock[];
    private int buff_counter;
    private ReadWriteLock counter_lock;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[cantLugares];
        //semaforo = new Semaphore(25);
        lock = new ReentrantReadWriteLock[cantLugares];
        buff_counter = 0;
        counter_lock = new ReentrantReadWriteLock();
    }
    
    
	public boolean addItem(Article art) {
    	boolean success=false;
    	//try {
    		//semaforo.tryAcquire(); //Se pelean por el semaforo 
    		for (int i = 0; i < this.lugares.length; i++) {
    			if(get_RLock(i)==false){
    				if(lock[i].writeLock().tryLock()==true) {
    					this.lugares[i]=art;
    					System.out.println("Se agregó el articulo exitosamente");
    					i=cantLugares+1;
    					success=true;
    					inc_Counter();
    					lock[i].writeLock().unlock();
    				}
    			}
    		}
    	//}
    	//catch(InterruptedException e){ 
    	//	e.printStackTrace();   //Los que no consiguen el semaforo se vienen acá
    	//}
    	//finally {
    	//	semaforo.release();   //el que ganó el semaforo se viene acá
    	//}
    	return success;
    }


    public void takeItem() {
    	boolean success = false;
    	//try {
    		//semaforo.acquire();
    	while(success == false) {
    		for (int i = 0; i < this.lugares.length; i++) {
    			if(get_RLock(i)==true){
    				if(lock[i].writeLock().tryLock()==true) {
    					this.lugares[i]=null;
    					System.out.println("Se consumió el articulo exitosamente");
    					i=cantLugares+1;
    					success=true;
    					dec_Counter();
    					article.incrementArtConsum();
    					lock[i].writeLock().unlock();
    				}
    			}
    		}
    	}
    	//}catch(InterruptedException e){
    	//	e.printStackTrace();
    	//}finally {
    		//semaforo.release();
    	//}
    }
    
    public boolean get_RLock(int pos) {
    	System.out.println("Pos: "+pos);
    	lock[pos].readLock().lock();
    	System.out.println(lock[pos]);
    	if(this.lugares[pos]==null){
			lock[pos].readLock().unlock();
			return false;
		}
    	lock[pos].readLock().unlock();
    	return true;
    }
    
    public void inc_Counter() {
    	counter_lock.writeLock().lock();
    	buff_counter ++;
    	counter_lock.writeLock().unlock();
    }
    
    public void dec_Counter() {
    	counter_lock.writeLock().lock();
    	buff_counter --;
    	counter_lock.writeLock().unlock();
    }
    
    public int get_Counter() {
    	int x;
    	counter_lock.readLock().lock();
    	x = buff_counter;
    	counter_lock.readLock().unlock();
    	return x;
    }

}
