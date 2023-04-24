#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "item.h"

void FFVazia (TipoFila *Fila){
    Fila->Inicio = NULL;
    Fila->Fim = Fila->Inicio;
}

int VaziaFila (TipoFila Fila){
    return (Fila.Inicio == NULL);
}

void Enfileira(TipoItem x, TipoFila *Fila){
    if (Fila->Fim == NULL){
        Fila->Fim = (Celula *) malloc(sizeof(Celula));
    }else{
        Fila->Fim->Prox = (Celula *) malloc(sizeof(Celula));
        Fila->Fim = Fila->Fim->Prox;
    }
    Fila->Fim->Item = x;
    Fila->Fim->Prox = NULL;
    if(Fila->Inicio==NULL){
        Fila->Inicio=Fila->Fim;
    }
}

void Desenfileira(TipoFila *Fila){
    Celula *q;

    if (VaziaFila(*Fila)){
        printf(" Erro: fila está vazia\n");
        return;
    }

    q = Fila->Inicio;
    Fila->Inicio = Fila->Inicio->Prox;
    free(q);

    if(Fila->Inicio==NULL){
        Fila->Fim = NULL;
    }
}

void ImprimeFila (TipoFila Fila){
    Celula* Aux;
    int i=1;
    Aux = Fila.Inicio;
    while (Aux != NULL){
        printf ("\n\nCodigo do elemento %d: %d", i, Aux->Item.chave);
        printf ("\nNome do elemento %d: %s", i, Aux->Item.nome);
        printf ("\nIdade do elemento %d: %d", i, Aux->Item.idade);
        getch();
        Aux=Aux->Prox;
        i++;
    }
}