# include <stdio.h>
# include <string.h>
# include "tools.c"

int main (){
    int vetor[10] = {5, 2, 6, 9, 1, 4, 7, 8, 3, 0};
    size_t tam = sizeof(vetor) / sizeof(*vetor);
    // int vetor[2] ; vetor[1]
    //x, 2, 6, 9, 1, 4, 7, 8, 3, 0 -- 5
    //0, 2, 6, 9, 1, 4, 7, 8, 3, x -- 5
    //0, 2, 6, 9, 1, 4, 7, 8, 3, x -- 5
    //0, 2, x, 9, 1, 4, 7, 8, 3, 6 -- 5
    //0, 2, 3, 9, 1, 4, 7, 8, x, 6 -- 5
    //0, 2, 3, x, 1, 4, 7, 8, 9, 6 -- 5
    //0, 2, 3, 4, 1, x, 7, 8, 9, 6 -- 5
    
    //0, 2, 3, 4, 1, 5, 7, 8, 9, 6 -- 5
    
    //x, 2, 3, 4, 1 -- 0
    //x, 2, 3, 4, 1 -- 0

    //x, 8, 9, 6 -- 7
    //6, 8, 9, x -- 7
    //6, x, 9, 8 -- 7

    //6, 7, 9, 8 -- 7



    printf("\nVetor anterior: ");
    imprimirVetor(vetor, tam);
    quickSort(vetor, 0 , tam);
    printf("\nVetor ordenado: ");
    imprimirVetor(vetor, tam);

    printf("\n\nPrograma Finalizado :)\n");

    return 0;
}