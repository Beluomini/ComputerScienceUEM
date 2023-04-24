#ifndef ITEM
#define ITEM
#define CELULA
#define LISTA

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
    struct Celula* Inicio;
    struct Celula* Fim;
}TipoFila;

#endif // PONTO_H_INCLUDED