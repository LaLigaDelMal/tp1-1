package tp1;

public class Buffardo {
    private Article[] lugares;
    int cantLugares;

    public Buffardo(int i) {
        cantLugares = i;
        lugares = new Article[25];
    }
    
    public void addItem(Article art) {
    	
    	if( this.isFull() ){
            for (Article i : lugares) {
                if(i==null){
                    i=art;
                    System.out.println("Se agregó el articulo exitosamente");
                    break;
                }
            }
        }else{
            System.out.println("Jodete puto, no entra");
        }
    }


    public void takeItem() {
    	
        if(this.isEmpty()) {
            for (int i = 0; i < this.lugares.length; i++) {
                //this.lugares[i] = null;
                System.out.println("Se consumio el articulo exitosamente");
                i=this.lugares.length;
            }
        }else{
            System.out.println("Jodete, esta vacio");
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
            x++;
        }
        return x;
    }

}
