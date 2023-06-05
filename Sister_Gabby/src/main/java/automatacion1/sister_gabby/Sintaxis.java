package automatacion1.sister_gabby;

class Sintaxis {
    Nodo Head = null, p;
    
    public Sintaxis(){
        p = Head;
        
        while(p != null){
            if(p.Token  == 217){
                p = p.Next;
            } else{
                System.out.println("Se espera la palabra 'package'");
            }
        }
    }
}
