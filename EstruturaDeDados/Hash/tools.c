#include "types.h"

void vetorVazio (TipoLista *Lista){
    Lista -> Primeiro = (TipoElemento*) malloc(sizeof(TipoElemento));
    Lista -> Primeiro -> letra = '*';
    Lista -> Ultimo = Lista -> Primeiro;
    Lista -> Primeiro -> Prox = NULL;
}

void enderecamentoAberto(char *palavra, char *palavraHash, int tamanho){
    int posicaoHash = 0;

    for(int i = 0; i<tamanho; i++){
        palavraHash[i] = '*';
    }

    for(int i = 0; i<tamanho; i++){
        posicaoHash = (int)palavra[i]%tamanho;
        printf("\n%c - %d ", palavra[i], posicaoHash);
        int aux = 0;
        while(palavraHash[posicaoHash + aux] != '*'){
            printf(" pos+1 ");
            aux++;
            if(posicaoHash + aux >= tamanho){
                aux = i*-1;
            }
        }
        palavraHash[posicaoHash + aux] = palavra[i];
        printf(" coloquei %d - %c", posicaoHash+aux, palavraHash[posicaoHash + aux]);
    }
}

void listaEncadeada(char *palavra, TipoLista *palavraHash, int tamanho, int tamanhoHash){
    int posicaoHash = 0;
    TipoElemento *aux;

    for(int i = 0; i<tamanhoHash; i++){
        vetorVazio(&palavraHash[i]);
    }

    for(int i = 0; i<tamanho; i++){
        posicaoHash = (int)palavra[i]%tamanhoHash;
        aux = palavraHash[posicaoHash].Primeiro;
        printf("\n%c - %d ", palavra[i], posicaoHash);
        if(aux != '*'){
            while(aux->Prox != NULL){
                aux = aux->Prox;
            }
            aux->Prox = (TipoElemento*) malloc(sizeof(TipoElemento));
            aux->Prox = palavra[i];
            printf(" coloquei %d - %c", posicaoHash, palavraHash[posicaoHash]);
        }else{
            aux = palavra[i];
        }
    }
}

void imprimirStr(char *palavra, int tamanho){
    printf("\n");
    for(int i = 0; i<tamanho; i++){
        printf("%c", palavra[i]);
    }
}

void imprimirListaEncadeada(TipoLista *palavra, int tamanho){
    printf("\n");
    for(int i = 0; i<tamanho; i++){
        TipoElemento * aux = palavra[i].Primeiro;
        while(aux != NULL){
            printf("%c", aux->letra);
        }
    }
}