# include <stdio.h>
# include <string.h>
# include "celula.c"

int main (){

    TipoPilha pilha;
    FPVazia(&pilha);
    printf("\nCriou lista vazia\n");

    TipoFila fila;
    FFVazia(&fila);
    printf("\nCriou fila vazia\n");

    Celula a;
    a.Item.chave = 1;
    strcpy(a.Item.nome, "a");
    a.Item.idade = 1;
    printf("\nCriou celula %d %s %d\n", a.Item.chave, a.Item.nome, a.Item.idade);

    Celula b;
    b.Item.chave = 2;
    strcpy(b.Item.nome, "b");
    b.Item.idade = 2;
    printf("\nCriou celula %d %s %d\n", b.Item.chave, b.Item.nome, b.Item.idade);
    
    Celula c;
    c.Item.chave = 3;
    strcpy(c.Item.nome, "c");
    c.Item.idade = 3;
    printf("\nCriou celula %d %s %d\n", c.Item.chave, c.Item.nome, c.Item.idade);

    Celula d;
    d.Item.chave = 4;
    strcpy(d.Item.nome, "d");
    d.Item.idade = 4;
    printf("\nCriou celula %d %s %d\n", d.Item.chave, d.Item.nome, d.Item.idade);

    
    // Empilha(a.Item, &pilha);
    // Empilha(b.Item, &pilha);
    // Empilha(c.Item, &pilha);
    // Empilha(d.Item, &pilha);

    // ImprimePilha(pilha);

    // printf("\n\n------------\n\n");

    // RetiraPilha(3, &pilha);

    // Desempilha(&pilha);
    // Desempilha(&pilha);
    // Desempilha(&pilha);

    Enfileira(a.Item, &fila);
    Enfileira(b.Item, &fila);
    Enfileira(c.Item, &fila);

    ImprimeFila(fila);


    printf("\n\ninverteu\n\n");
    
    InverteFila(&fila);

    ImprimeFila(fila);
    
    return 0;
}
