package automatacion1.sister_gabby;

class Nodo {
    String Lexeme;
    int Token;
    int Line;
    Nodo Next = null;
    
    Nodo(String Lexeme, int Token, int Line){
        this.Lexeme = Lexeme;
        this.Token = Token;
        this.Line = Line;
    }
}
