from asyncio.windows_events import INFINITE


class Vertice():
    def __init__(self, id):
        self.id = id
        self.conexoes = []
        self.visited = False
        
        self.pai = None
        self.distancia = float('inf')


    def addAresta(self, dst, weight):
        self.conexoes.append(Aresta(dst, weight))

    def getArestaFromVertice(self, dst):
        for aresta in self.conexoes:
            if aresta.dst == dst:
                return aresta


class Aresta():
    def __init__(self, dst, w):
        self.dst = dst
        self.w = w


def resetVisitedNodes(g):
    for v in g:
        v.visited = False




def bfs(g, pai):

    q = []
    seq = []

    q.append(pai)
    seq.append(pai)

    print(f'\nComecou em {pai.id}')

    while q != []:
        pai = q.pop(0)
        pai.visited = True  

        for ar in pai.conexoes:
            if not ar.dst.visited:
                q.append(ar.dst)
                seq.append(ar.dst)
                ar.dst.visited = True
                print(f'-> {ar.dst.id} ', end='')
        if q == []:
            for v in g:
                if v.visited == False:
                    q.append(v)
                    break

    print(f'\nSequencia de visita em lista: ')
    for v in seq:
        print(v.id, end=" ") 
    print()

    resetVisitedNodes(g)
    return seq

def dfs(g, pai):

    q = []
    seq = []

    q.append(pai)
    seq.append(pai)

    print(f'\nComecou em {pai.id}')

    while q != []:
        pai = q.pop(0)
        pai.visited = True  

        for ar in pai.conexoes:
            if not ar.dst.visited:
                q = [ar.dst] + q
                seq.append(ar.dst)
                ar.dst.visited = True
                print(f'-> {ar.dst.id} ', end='')
        if q == []:
            for v in g:
                if v.visited == False:
                    q.append(v)
                    break

    print(f'\nSequencia de visita em lista: ')
    for v in seq:
        print(v.id, end=" ") 
    print()

    resetVisitedNodes(g)
    return seq

def relax(pai, vizinho):
    print(f'\n relaxando {pai.id} -> {vizinho.id}')
    if pai.distancia + pai.getArestaFromVertice(vizinho).w < vizinho.distancia:
        vizinho.distancia = pai.distancia + pai.getArestaFromVertice(vizinho).w
        vizinho.pai = pai

def bellman_ford(vertices, pai):
    pai.distancia = 0
    for v in vertices[:-1]:
        for a in v.conexoes:
            relax(pai, a.dst)


def main():
    # vertices ficao guardados como ponteiros dentro de 'vertices'
    vertices = []

    # arestas determina quais conexoes entre os nos sao feitas
    arestas = [(1,2, 1), (1,4, 2), (2,3, 1), (2,4, 3), (3,7, 1), (4,8, 2), (5,7, 1), (5,6, 2), (8,7, 1)]
    
    # * = (nao tem conexao para o resto do grafo entao eles entram depois)
    # bfs -> 1 2 4 3 8 7 * 5 6
    # dfs -> 1 2 3 7 4 8 7 * 5 6



    for i in range(8):
        v = Vertice(i+1)
        vertices.append(v)
   
    # trata de fazer as arestas entre os vertices, arestas orientadas
    # for i in range(len(arestas)):
    #     ipv, iuv = arestas[i]
    #     pv, uv = vertices[ipv-1], vertices[iuv-1]
    #     pv.conexoes.append(uv)
    #     uv.conexoes.append(pv)

    for src, dst, w in arestas:
        vertices[src-1].addAresta(vertices[dst-1], w)

    bfs(vertices, vertices[0])
    dfs(vertices, vertices[0])
    bellman_ford(vertices, vertices[0])

    print(f'\n nois é loco mano, muito loco: {vertices[3].distancia}')


if __name__ == "__main__":
    main()