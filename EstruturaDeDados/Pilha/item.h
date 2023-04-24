#ifndef ITEM
#define ITEM
#define CELULA
#define FILA
#define PILHA

typedef struct{
    int chave;
    char nome [30];
    int idade;
    //outros campos
}TipoItem;

typedef struct Celula{
    TipoItem Item;
    struct Celula* Prox;
}Celula;

typedef struct{
    struct Celula* Topo;
    struct Celula* Fim;
    int Tam;
}TipoPilha;


typedef struct{
    struct Celula* Inicio;
    struct Celula* Fim;
}TipoFila;

#endif // PONTO_H_INCLUDED