/*
 * Copyright (C) 2021 Lazaro Cesar del Pino Olivera
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.ldelpino.graph;

import java.util.Collection;
import java.util.List;
import io.github.ldelpino.graph.api.AritmethicNumber;

/**
 * Permite crear una estructura de datos en forma de grafo.
 * <p>
 * Un grafo es una estructura de datos que se representa como nodos(vertices) y
 * sus conexiones(aristas), donde los vertices representan el objeto de estudio
 * de la informacion a almacenar y las aristas representan la conexion entre dos
 * vertices, si existe.</p>
 * <p>
 * Existen diferentes clasificaciones para los grafos, que pueden ser:</p>
 * <ul>
 * <li>Grafos Dirigidos:
 * {@link ldelpino.graph.graphextensions.SimpleDirectedGraph}</li>
 * <li>Grafos No Dirigidos:
 * {@link ldelpino.graph.graphextensions.SimpleNotDirectedGraph}</li>
 * <li>Grafos Dirigidos con peso en los vertices:
 * {@link ldelpino.graph.graphextensions.WeightedVertexDirectedGraph}</li>
 * <li>Grafos No Dirigidos con peso en los vertices:
 * {@link ldelpino.graph.graphextensions.WeightedVertexNotDirectedGraph}</li>
 * <li>Grafos Dirigidos con peso en las aristas:
 * {@link ldelpino.graph.graphextensions.WeightedEdgeDirectedGraph}</li>
 * <li>Grafos No Dirigidos con peso en las aristas:
 * {@link ldelpino.graph.graphextensions.WeightedEdgeNotDirectedGraph}</li>
 * <li>Grafos Dirigidos con peso en los vertices y las aristas:
 * {@link ldelpino.graph.graphextensions.WeightedVertexEdgeDirectedGraph}</li>
 * <li>Grafos No Dirigidos con peso en los vertices y las aristas:
 * {@link ldelpino.graph.graphextensions.WeightedVertexEdgeNotDirectedGraph}</li>
 * </ul>
 * <p>
 * Los grafos no dirigidos establecen que la direccion de las aristas no tiene
 * significancia, en ese caso entre dos vertices solo existe una sola arista y
 * por tanto se establece que un vertice A es adyacente a un vertice B y
 * viceversa.</p>
 * <p>
 * Los grafos dirigidos establecen que la direccion de sus aristas si tiene una
 * importancia y por tanto pueden exitir multiples aristas desde A hasta B y
 * desde B hasta A, en este caso el vertice A puede ser adyacente a el vertice
 * B, pero el vertice B no necesariamente tiene que ser adyacente a el vertice
 * A.</p>
 * <p>
 * Los grafos ponderados son aquellos en que los vertices o las aristas tienen
 * un peso, el peso de una arisa o de un vertice establece su valor con respecto
 * a otros.</p>
 * <p>
 * En los grafos tambien pueden existir ciclos, un ciclo es cuando puede
 * llegarse desde un vertice A hasta el mismo vertice A pasando por sus aristas
 * a traves de otros vertices. Un vertice que no tiene aristas es un vertice
 * desconectado.</p>
 * <p>
 * El grado de un vertice dependera de si el grafo es dirigido o no, en un grafo
 * dirigido el grado de un vertice son la cantidad de adyacentes que el tiene,
 * mientras que un grafo no dirigido son la cantidad de adyacentes que el
 * vertice tiene mas los vertices que lo tienen a el como adyacente.</p>
 * <p>
 * En un grafo tambien existen caminos, los caminos es el recorrido desde un
 * vertice A hasta un vertice B, sin volver a pasar por A. La longitud del
 * camino es la cantidad de saltos o vertices que hay que recorrer para llegar
 * de A hasta B. El algoritmo de <b>Djisktra</b> permite calcular el camino mas
 * corto entre un vertice A y un vertice B.</p>
 * <p>
 * Los grafos tienen matrices de adyacencia y de incidencia, la matriz de
 * adyacencia muestra a cada vertice como columnas y filas de la matriz, donde
 * cada elemento de la matriz es <b>1</b> si existe una adyacencia entre los dos
 * vertices y <b>0</b> sino existe. La matriz de incidencia muestra la
 * incidencia de un vertice hacia otro, o sea, un vertice A es incidente con un
 * vertice B si la arista A-B tiene al vertice A como cola y al vertice B como
 * cabeza, entonces existe una incidencia de A hasta B.</p>
 * <p>
 * La interfaz hereda de la interfaz {@link java.util.Collection}, esto permite
 * establecer una relacion entre un grafo y las colecciones.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see io.github.ldelpino.graph.vertex.Vertex
 * @see io.github.ldelpino.graph.api.AbstractGraph
 * @see io.github.ldelpino.graph.api.DirectedGraph
 * @see io.github.ldelpino.graph.api.NotDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface Graph<T> extends Collection<T> {

    /**
     * Establece si el grafo es dirigido o no.
     *
     * @return <b>true</b> si el grafo es dirigido, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean isDirected();

    /**
     * Establece si el grafo es ponderado con peso en los vertices.
     *
     * @return <b>true</b> si el grafo tiene peso en los vertices, de lo
     * contrario devuelve
     * <b>false</b>.
     */
    public boolean isWeightedVertex();

    /**
     * Establece si el grafo esponderado con peso en las aristas.
     *
     * @return <b>true</b> si el grafo es tiene peso en las aristas, de lo
     * contrario devuelve <b>false</b>.
     */
    public boolean isWeigthedEdge();

    /**
     * Establece si el grafo contiene ciclos.
     * <p>
     * Un grafo contiene ciclos si existe un forma de llegar de un vertice A
     * hasta el mismo vetice pasando por otros vertices.</p>
     *
     * @return <b>true</b> si el grafo contiene ciclos, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean isCyclic();

    /**
     * Devuelve la cantidad de vertices en el grafo.
     *
     * @return la cantidad de vertices en el grafo.
     */
    public int getVertexCount();

    /**
     * Devuelve la cantidad de aristas de un vertice del grafo.
     *
     * @param info la informacion del vertice.
     * @return la cantidad de aristas del vertice.
     */
    public int getEdgesCount(T info);

    /**
     * Devuelve la cantidad total de aristas de todo el grafo.
     *
     * @return la cantidad total de aristas del grafo.
     */
    public int getTotalEdgesCount();

    /**
     * Devuelve una coleccion de todos los vertices en el grafo.
     *
     * @return una coleccion de todos los vertices del grafo.
     */
    public Collection<T> getVertices();

    /**
     * Establece si el grafo tiene vertices desconectados.
     * <p>
     * Un vertices desconectado es aquel que no es adyacente con ningun otro
     * vertice y ningun vertice es adyacente con el.</p>
     *
     * @return <b>true</b> si el grafo tiene vertices desconectados, de lo
     * contrario devuelve
     * <b>false</b>.
     */
    public boolean existVerticesDisconnected();

    /**
     * Devuelve la coleccion de vertices desconectados en el grafo.
     *
     * @return la coleccion de vertices desconectados.
     */
    public Collection<T> getDisconnectedVertices();

    /**
     * Elimina y devuelve los vertices desconectados en el grafo.
     *
     * @return la coleccion de vertices desconectados.
     */
    public Collection<T> removeDisconnectedVertices();

    /**
     * Devuelve el grado de un vertice en el grafo.
     * <p>
     * El grado de un vertice dependera de si el grafo es dirigido o no. Para un
     * grafo no dirigido el grado de un vertice sera su cantidad de vertices
     * adyacentes, mientras que para un grafo dirigido, el grado del vertice
     * sera su cantidad de vertices adyacentes, mas la cantidad de vertices que
     * lo tienen a el como adyacente.</p>
     *
     * @param info la informacion del vertice.
     * @return el grado del vertice en el grafo.
     */
    public int degree(T info);

    /**
     * Establece si dos vertices son adyacentes.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return <b>true</b> si los vertices son dyacentes, de lo contrario
     * devuelve <b>false</b>.
     */
    public boolean areAdjacents(T infoTail, T infoHead);

    /**
     * Devuelve los vertices adyacentes a un vertice establecido.
     *
     * @param info el vertice.
     * @return la coleccion de vertices adyacentes.
     */
    public Collection<T> getAdjacents(T info);

    /**
     * Elimina el vertice del grafo.
     *
     * @param info la informacion con la cual encontrar el vertice.
     * @return el vertice removido si el vertice fue eliminado correctamente, de
     * lo contrario devuelve
     * <b>null</b>.
     */
    public T removeVertex(T info);

    /**
     * Remueve todos los vertices enlazados en forma de cascada a partir de un
     * vertice inicial.
     * <p>
     * La eliminacion en cascada elimina el vertice inicial mas los vertices
     * adyacentes a el y para cada vertice adyacente sus adyacentes
     * sucesivamente hasta que se eliminen todos los vertices que son
     * adyacentes.</p>
     *
     * @param info el vertice inicial.
     * @return la coleccion de vertices eliminados del grafo.
     */
    public Collection<T> removeVertexCascade(T info);

    /**
     * Establece si un vertice existe en el grafo.
     *
     * @param info la informacion con la cual encontrar el vertice.
     * @return <b>true</b> si el vertice existe, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean existVertex(T info);

    /**
     * Remueve una arista dado sus vertices.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return <b>true</b> si la arista fue removida, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean removeEdge(T infoTail, T infoHead);

    /**
     * Establece si existe un camino entre dos vertices.
     *
     * @param infoTail el vertice inicial.
     * @param infoHead el vertice final.
     * @return <b>true</b> si existe un camino, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean existPath(T infoTail, T infoHead);

    /**
     * Establece si existe un camino de una longitud especifica entre dos
     * vertices.
     *
     * @param infoTail el vertice inicial.
     * @param infoHead el vertice final.
     * @param length la longitud del camino.
     * @return <b>true</b> si existe un camino con una longitud especificada, de
     * lo contrario devuelve <b>false</b>.
     */
    public boolean existPathWithLength(T infoTail, T infoHead, int length);

    /**
     * Devuelve el camino mas corto entre dos vertices.
     * <p>
     * El metodo esta pensado para devolver dos valores, un numero que
     * representa la distancia entre el vertice inicial y el vertice final y el
     * camino de vertices entre el vertice inicial y el vertice final, si este
     * existe. Es necesario que la lista del camino no sea nula y que este vacia
     * dado que sera modificada dentro del metodo, permitiendo adicionar los
     * vertices del camino. La distancia del camino se establece como un objeto
     * de tipo {@link io.github.ldelpino.graph.api.AritmethicNumber}, permitiendo en el
     * caso de un grafo con peso en las aristas y que el peso de las mismas sea
     * instancia de la interfaz, realzar una sumatoria de estos pesos y poder
     * devolver un camino mas exacto.</p>
     *
     * @param infoTail el vertice inicial.
     * @param infoHead el vertice final.
     * @param path el camino mas corto si es que existe.
     * @return la distancia que existe entre el vertice inicial y el vertice
     * final, si es que existe un camino.
     */
    public AritmethicNumber djisktra(T infoTail, T infoHead, List<T> path);

    /**
     * Establece si existe un camino de Euler.
     * <p>
     * El camino de Euler se establece como un algoritmo que permite pasar por
     * todos los vertices y las aristas de un grafo solo una vez. Para que esto
     * sea posible el grafo de be ser conexo, o sea no tener vertices
     * desconectados y cada vertice tener grado <b>par</b>.</p>
     *
     * @return <b>true</b> si existe un camino de Euler, de lo contrario de
     * vuelve <b>false</b>.
     * @see ldelpino.graph.Graph#existVerticesDisconnected()
     * @see ldelpino.graph.Graph#degree(java.lang.Object)
     */
    public boolean isEulerPath();

    /**
     * Devuelve una lista con el camino de Euler en el grafo, si este existe.
     *
     * @return la lista del camino de euler si este existe, de lo contrario
     * devuelve una lista vacia.
     * @see ldelpino.graph.Graph#isEulerPath()
     */
    public List<T> eulerPath();

    /**
     * Devuelve la matriz de incidencia en el grafo.
     * <p>
     * Una matriz de incidencia es una matriz donde los elementos tienen valor
     * <b>1</b> si el vertice es incidente con la arista, sino son incidentes
     * entonces el elemento toma valor <b>0</b>. Un vertice es incidente con una
     * arista, si la arista pertenece a ese vertice.</p>
     *
     * @return matriz de incidencia.
     */
    public int[][] getIncidenceMatrix();

    /**
     * Devuelve la matriz de adyacencia en el grafo.
     * <p>
     * Una matriz de adyacencia es un matriz donde los elementos de la misma
     * tienen valor <b>1</b> si dos vertices son adyacentes y tiene valor
     * <b>0</b> si no lo son.
     *
     * @return la matriz de adyacencia.
     */
    public int[][] getAdyacentsMatrix();

    /**
     * Vacia el grafo completamente.
     * <p>
     * Vaciar el grafo conlleva eliminar todos los vertices y sus aristas.</p>
     */
    public void cleanGraph();

    /**
     * Establece si existe una arista o no.
     * <p>
     * De forma predeterminada existe una arista si los vertices son
     * adyacentes.</p>
     *
     * @param infoTail el vertice que hace funcionde cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return <b>true</b> si existe la arista, de lo contrario devuelve
     * <b>false</b>.
     */
    public default boolean existEdge(T infoTail, T infoHead) {
        return areAdjacents(infoTail, infoHead);
    }
}
