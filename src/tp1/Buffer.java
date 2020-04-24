

public class Bufer {
    public Bufer(int cantLugar) {
        private Article[] lugares = new Article[cantLugares];
        int cantLugares = cantLugar;
    }

    public void addItem(Article articulo) {
        if(this.isFull()){
            for (Article i : lugares) {
                if(i=="null"){
                    i=articulo;
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
                if (i != "null") {
                    this.lugares[i] = null;
                    System.out.println("Se consumio el articulo exitosamente");
                    i=this.lugares.length;
                }
            }
        }else{
            System.out.println("Jodete, está vacio");
        }
    }

    public boolean isFull(){
        if(this.getCuantity()=>this.cantLugares){return true}else{return false}
    }

    public boolean isEmpty(){
        if(this.getCuantity()=<0){return true}else{return false}
    }

    public int getCuantity(){
        int x=0;
        for (Article i : lugares) {x++}
        return x;
    }

}