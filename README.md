# Go-based-compiler
Two-semester school project to develop a compiler written in Java, based on the Go programming language

As of today, May 1, 2023, the first phase of compiler development has been completed: the lexical analyzer.

By the end of this month it is expected to complete the second phase of its development: the parser.

The operation of this first part of the compiler is simple. Inside the folder is a text file called Codigo.txt. When you start the program, what it will do is analyze the text file to display the list of tokens it identifies in the terminal.

It will do all this until it can no longer find a line of code to parse or until it flags an error from the list inside the Lexico.java class.

If the operation occurred without any error being thrown, the program, in addition to showing the list of tokens and reserved words found in the analyzed text file, will throw a message in the terminal saying "Lexical analysis finished".
