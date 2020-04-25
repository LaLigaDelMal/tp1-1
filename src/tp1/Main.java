package tp1;

public class Main {

    public static void main(String[] args) {
    	
        Buffardo coso = new Buffardo(2);
        Article articulo = new Article();
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        coso.addItem(articulo);
        
        System.out.println(coso.getCuantity());
        
        coso.takeItem();
        coso.takeItem();
        coso.takeItem();
        coso.takeItem();
        coso.takeItem();
        
        System.out.println("Termino");
    }
}
