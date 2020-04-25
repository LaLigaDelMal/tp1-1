package tp1;
/*
public class Bufer {
    private int[] lugares;
    int cantLugares;

    public Bufer(int cantLugar) {
        cantLugares = cantLugar;
        Article[] lugares;
        lugares = new Article[cantLugares];
    }

    public void addItem(Article articulo) {
        if(this.isFull()){
            for (int i = 0; i < this.lugares.length; i++) {
                if (this.lugares[i] == null) {
                    this.lugares[i] = articulo;
                    System.out.println("Se agregó el articulo exitosamente");
                }
            }
        }else{
            System.out.println("Jodete puto, no entra");
        }
    }

    public void takeItem() {
        if(this.isEmpty()) {
            for (int i = 0; i < this.lugares.length; i++) {
                this.lugares[i] = null;
                System.out.println("Se consumio el articulo exitosamente");
                i=this.lugares.length;
            }
        }else{
            System.out.println("Jodete, está vacio");
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
        for (Article i : lugares) {x++;}
        return x;
    }

}
*/