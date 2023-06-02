package automatacion1.sister_gabby;

import java.io.RandomAccessFile;

class Lexico {
    Nodo Head = null, p; // Cabeza
    
    int State, Chara = 0; // Estado y Caracter
    int Column, TransArrayVal, line_num = 1; // Columna, Valor de matriz de transición, y Número de renglón
    
    String Lexeme = ""; // Lexema
    
    boolean bugFound = false; // Error encontrado durante el análisis léxico
    
    String Archivo = "/home/danny/Descargas/Sister_Gabby/src/main/java/automatacion1/sister_gabby/Codigo.txt";
    
    // Matriz de transición
    int Matrix [][] = {
        
            //     l    d    .    +    -    *    ^    /    <    >    =    !    &    |    ;    ,    :    (    )    "   eb   nl  tab  fda   oc
            //     0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24
        /* 0*/ {   1,   2, 120, 103, 104, 105, 107,   5,   8,   9,  10,  11,  12,  13, 117, 118,  14, 121, 122,  15,   0,   0,   0,   0, 506},
        /* 1*/ {   1,   1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100},
        /* 2*/ { 101,   2,   3, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101},
        /* 3*/ { 501,   4, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501},
        /* 4*/ { 102,   4, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102},
        /* 5*/ { 106, 106, 106, 106, 106,   6, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106},
        /* 6*/ {   6,   6,   6,   6,   6,   7,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6, 500,   6},
        /* 7*/ {   6,   6,   6,   6,   6,   6,   6,   0,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6},
        /* 8*/ { 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 110, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108},
        /* 9*/ { 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 111, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109},
        /*10*/ { 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 112, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502},
        /*11*/ { 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 113, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116},
        /*12*/ { 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 114, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503},
        /*13*/ { 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 115, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504},
        /*14*/ { 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 124, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502},
        /*15*/ {  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15,  15, 123,  15, 505,  15,  15,  15}

    };
    
    // Palabras reservadas
    String reservedWords [][] = {
        
        //              0      1
        /* 0*/ {      "break", "200"},
        /* 1*/ {       "case", "201"},
        /* 2*/ {       "chan", "202"},
        /* 3*/ {      "const", "203"},
        /* 4*/ {   "continue", "204"},
        /* 5*/ {    "default", "205"},
        /* 6*/ {      "defer", "206"},
        /* 7*/ {       "else", "207"},
        /* 8*/ {"fallthrough", "208"},
        /* 9*/ {        "for", "209"},
        /*10*/ {       "func", "210"},
        /*11*/ {         "go", "211"},
        /*12*/ {       "goto", "212"},
        /*13*/ {         "if", "213"},
        /*14*/ {     "import", "214"},
        /*15*/ {  "interface", "215"},
        /*16*/ {        "map", "216"},
        /*17*/ {    "package", "217"},
        /*18*/ {      "range", "218"},
        /*19*/ {     "return", "219"},
        /*20*/ {     "select", "220"},
        /*21*/ {     "struct", "221"},
        /*22*/ {     "switch", "222"},
        /*23*/ {       "type", "223"},
        /*24*/ {        "var", "224"}
            
    };
    
    // Errores
    String Errors [][] = {
        
        //                            0      1
        /* 0*/ {   "Faltó cerrar comentario", "500"},
        /* 1*/ {        "Número mal formado", "501"},
        /* 2*/ {        "Se espera un igual", "502"},
        /* 3*/ {            "Se espera un &", "503"},
        /* 4*/ {            "Se espera un |", "504"},
        /* 5*/ {"Se espera cierre de cadena", "505"},
        /* 6*/ {         "Símbolo no válido", "506"}
            
    };
    
    RandomAccessFile Code = null;
    
    public Lexico(){
        try {
            Code = new RandomAccessFile(Archivo, "r");
            
            while (Chara != -1){ // Se lee caracter por caracter mientras no sea fin del archivo, o fda
                Chara =  Code.read();
                
                // Se posiciona la columna de la matriz acorde al caracter leído                
                if (Character.isLetter((char)Chara)){
                    Column = 0;
                }
                else if (Character.isDigit((char)Chara)){
                    Column = 1;
                }
                else {
                    switch((char)Chara){
                        case '.':
                            Column = 2;
                            break;
                        case '+':
                            Column = 3;
                            break;
                        case '-':
                            Column = 4;
                            break;
                        case '*':
                            Column = 5;
                            break;
                        case '^':
                            Column = 6;
                            break;
                        case '/':
                            Column = 7;
                            break;
                        case '<':
                            Column = 8;
                            break;
                        case '>':
                            Column = 9;
                            break;
                        case '=':
                            Column = 10;
                            break;
                        case '!':
                            Column = 11;
                            break;
                        case '&':
                            Column = 12;
                            break;
                        case '|':
                            Column = 13;
                            break;
                        case ';':
                            Column = 14;
                            break;
                        case ',':
                            Column = 15;
                            break;
                        case ':':
                            Column = 16;
                            break;
                        case '(':
                            Column = 17;
                            break;
                        case ')':
                            Column = 18;
                            break;
                        case '"':
                            Column = 19;
                            break;
                        case ' ':
                            Column = 20;
                            break;
                        case 10: // Nueva línea
                            {
                                Column = 21;
                                line_num += 1;
                            }
                            break;
                        case 9: // Tabulador horizontal
                            Column = 22;
                            break;
                        default:
                            Column = 24;
                            break;
                    }
                }
                
                TransArrayVal = Matrix[State][Column];
                
                if (TransArrayVal < 100){ // Cambiar de estado
                    State = TransArrayVal;
                                        
                    if (State == 0){
                        Lexeme = "";
                    }
                    else {
                        Lexeme = Lexeme + (char)Chara;
                    }
                }
                else if (TransArrayVal >= 100 && TransArrayVal < 500){ // Estado final
                    if (TransArrayVal == 100){
                        ValidaSiEsPalabraReservada();
                    }
                    
                    if (TransArrayVal == 100 || TransArrayVal == 101 || TransArrayVal == 102 || TransArrayVal == 106 || TransArrayVal == 108 || TransArrayVal == 109 || TransArrayVal == 116 || TransArrayVal == 125 || TransArrayVal == 203){
                        Code.seek(Code.getFilePointer() - 1); // Retrocede a una posición el apuntador
                    }
                    else { 
                        Lexeme = Lexeme + (char)Chara;
                    }
                    
                    InserteNodo();
                    State = 0;
                    Lexeme = "";
                } else { // Estado de error
                    ImprimeMensajeError();
                    break;
                }
            }
            
            ImprimeNodos();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(Code != null){
                    Code.close();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void ImprimeNodos(){
        p = Head;
        
        while(p != null){
            System.out.println(p.Lexeme + " " + p.Token + " " + p.Line);
            p = p.Next;
        }
    }
    
    private void ValidaSiEsPalabraReservada(){
        for(String[] reservedWord: reservedWords){
            if(Lexeme.equals(reservedWord[0])){
                TransArrayVal = Integer.valueOf(reservedWord[1]);
            }
        }
    }
    
    private void ImprimeMensajeError(){
        if(Chara != 1 && TransArrayVal >= 500){
            for(String[] Errol: Errors){
                if(TransArrayVal == Integer.valueOf(Errol[1])){
                    System.out.println("El error encontrado es: " + Errol[0] + " error " + Errol[1] + " en caracter " + (char)Chara + " en el renglón " + line_num);
                }
            }
            
            bugFound = true;
        }
    }
    
    private void InserteNodo(){
        Nodo Node = new Nodo(Lexeme, TransArrayVal, line_num);
        
        if (Head == null){
            Head = Node;
            p = Head;
        }
        else {
            p.Next = Node;
            p = Node;
        }
    }
}