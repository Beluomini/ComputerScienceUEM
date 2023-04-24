/*
Atividade 1 
Lucas Beluomini
120111
Listas Encadeadas
*/

/*Exercicio 1 - Supondo que o campo chave dos itens estejam em ordem crescente. Refa ̧ca a fun ̧c ̃ao “Insere”
para que ela mantenha os itens ordenados ap ́os a inser ̧c ̃ao do item desejado.*/


void Insere (TipoItem x, TipoLista *Lista){

    struct Celula* novo;
    novo = (struct Celula*) malloc(sizeof(struct Celula));
    novo->Item = x;

    int cont = 0;

    struct Celula* Aux;
    Aux = Lista -> Primeiro -> Prox;
    while (cont == 0){
        if(Aux == NULL){
            Aux = novo;
            Aux -> Prox = NULL;
            Lista -> Primeiro -> Prox = Aux;
            Lista -> Ultimo = Aux;
            cont++;
        }else if(Aux->Prox == NULL){
            Aux->Prox = novo;
            novo -> Prox = NULL;
            Lista -> Ultimo = novo;
            cont++;
        }else if(Aux->Prox->Item.chave > x.chave){
            novo->Prox = Aux->Prox;
            Aux->Prox = novo;
            cont++;
            printf("Adicionou");
        }
        Aux = Aux->Prox;
    }
}


/*Exercicio 2 - Refa ̧ca a fun ̧c ̃ao “Insere” para que a inser ̧c ̃ao ocorra sempre no in ́ıcio da lista, ou seja, inde-
pendente de ordenacao.*/
void Insere (TipoItem x, TipoLista *Lista){

    struct Celula *Aux;
    Aux = (struct Celula*) malloc(sizeof(struct Celula));
    Aux -> Prox = Lista -> Primeiro -> Prox;
    Aux -> Item = x;
    Lista -> Primeiro -> Prox = Aux ;
}


/*Exercicio 3 - Refa ̧ca a fun ̧c ̃ao “Retira” para que a remo ̧c ̃ao ocorra sempre no final da lista, ou seja, n ̃ao
ser ́a preciso informar o item a ser removido. A cada solicita ̧c ̃ao de “Retira” o  ́ultimo item  ́e
removido.*/
void Retira (TipoLista *Lista){
    struct Celula* q;
    struct Celula* Aux;
    int i=1;
    if (Vazia(*Lista)){
        printf ("Erro: lista vazia.\n");
        getch();
    }else{
        Aux = Lista -> Primeiro -> Prox;
        while (Aux != NULL){
            if(Aux -> Prox == Lista -> Ultimo){
                q = Lista -> Ultimo;
                Lista -> Ultimo = Aux;
                Lista -> Ultimo -> Prox = NULL;
                free(q);
            }
            Aux = Aux->Prox;
            i++;
        }
    }
}

/*Exercicio 4 - Refa ̧ca a fun ̧c ̃ao “Retira” para que a remo ̧c ̃ao ocorra sempre no in ́ıcio da lista.*/
void Retira (TipoLista *Lista){
    struct Celula* q;
    struct Celula* Aux;
    int i=1;
    if (Vazia(*Lista)){
        printf ("Erro: lista vazia.\n");
        getch();
    }else{
        Aux = Lista -> Primeiro -> Prox;
        q = Aux -> Prox;
        Lista -> Primeiro -> Prox = q;
        free(Aux);
    }
}
