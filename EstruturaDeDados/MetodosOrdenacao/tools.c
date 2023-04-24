#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void selectionSort(int vetor[], int tam){
    int min = 0;
    int aux = 0;
    for(int i=0; i < tam-1; i++){
        min = i;
        for(int j=i+1; j < tam; j++){
            if(vetor[j] < vetor[min]){
                min = j;
            }
        }
        aux = vetor[min];
        vetor[min] = vetor[i];
        vetor[i] = aux;
    }
}

void insertionSort(int vetor[], int tam){
    int aux, prox;

    for(int i = 1; i < tam; i++){

        aux = vetor[i];
        prox = i-1;

        while((aux < vetor[prox]) && (prox>=0)){
            vetor[prox+1] = vetor[prox];
            prox--;
        }

        vetor[prox+1] = aux;
    }
}

void bubbleSort(int vetor[], int tam){
    int aux;

    for(int i=0; i < tam-1; i++){
        for(int j=0; j < tam-i; j++){
            if(vetor[j] > vetor[j+1]){
                aux = vetor[j];
                vetor[j] = vetor[j+1];
                vetor[j+1] = aux;
            }
        }
    }
}

void quickSort(int vetor[], int inicio, int tam){
    if(tam - inicio <= 1)return;

    int i = inicio;
    int f = tam-1;

    int x = vetor[i];

    int seletor = 1;

    while(i != f){
        if(seletor == 1){
            if(vetor[f] < x){
                vetor[i] = vetor[f];
                i++;
                seletor = 0;
            }else{
                f--;
            }
        }else{
            if(vetor[i] > x){
                vetor[f] = vetor[i];
                f--;
                seletor = 1;
            }else{
                i++;
            }
        }
    }

    vetor[i] = x;

    quickSort(vetor, inicio, i);
    quickSort(vetor, i+1 , tam);

}

void imprimirVetor(int vetor[], int tam){
    printf("\n[");
    for(int i=0; i<tam; i++){
        printf("%d;", vetor[i]);
    }
    printf("]");
}