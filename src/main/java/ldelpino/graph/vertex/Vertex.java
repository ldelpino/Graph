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
package ldelpino.graph.vertex;

import java.util.Collection;
import ldelpino.graph.edge.Edge;

/**
 * Permite establecer un vertice en una estrctura en forma de grafo.
 * <p>
 * Un vertice es un nodo que contiene informacion y que puede estar enlazado o
 * no a otros nodos dentro del grafo.</p>
 * <p>
 * Cada vertice contiene la informacion que el vertice almacena, si tiene peso o
 * no y las aristas al cual el vertice se enlaza. Un grafo ponderado es una
 * estructura de datos en el cual, o los vertices, o las aristas tienen
 * peso.</p>
 * <p>
 * Los vertices contienen las aristas que enlazan a el con otro vertice creando
 * una interconexion entre ellos, en cada arista se almacena una referencia de
 * los vertices que interconecta, los vertices que almacenan siempre a una
 * arista son los vertices que hacen funcion de cola, mientras que los vertices
 * que hacen funcion de cabeza solo almacenan a la arista que los interconecta
 * si el grafo es no dirigido.</p>
 *
 * @param <T> el tipo de dato de la informacion que almacena el vertice.
 * @see ldelpino.graph.vertex.AbstractVertex
 * @see ldelpino.graph.vertex.DefaultVertex
 * @see ldelpino.graph.vertex.WeightedVertex
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.Graph
 *
 * @author Lazaro Cesar del Pino Olvera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface Vertex<T> {

    /**
     * Establece si el vertice tiene peso o no.
     *
     * @return <b>true</b> si el vertice tiene peso, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean isWeighted();

    /**
     * Devuelve la informacion que almacena este vertice.
     *
     * @return la informacion del vertice.
     */
    public T getInfo();

    /**
     * Establece la informacion del vertice.
     *
     * @param info la nueva informacion del vertice.
     */
    public void setInfo(T info);

    /**
     * Devuelve una coleccion de los vertices adyacentes.
     *
     * @return la coleccion de vertices dyacentes.
     */
    public Collection<Vertex<T>> getAdjacents();

    /**
     * Devuelve la cantidad de vertices adyacentes.
     * <p>
     * La cantidad de adyacentes difiere de la cantidad de aristas en que la
     * cantidad de aristas, son todas las aristas del vertices, mientras, que la
     * cantidad de adyacentes es la cantidad de aristas que tienen a este
     * vertice como cola.</p>
     *
     * @return la cantidad de vertices adyacentes.
     */
    public int getAdjacentsCount();

    /**
     * Devuelve la cantidad de aristas en el vertice.
     *
     * @return la cantidad de aristas del vertice.
     */
    public int getEdgesCount();

    /**
     * Inserta una nueva arista en este vertice.
     *
     * @param edge la nueva arista a ser insertada en este vertice.
     * @return <b>true</b> si la arista fue insertada, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean insertEdge(Edge<T> edge);

    /**
     * Remueve una arista de este vertice.
     *
     * @param vertex el vertice que hace funcion de cabeza en la arista a ser
     * removida.
     * @return <b>true</b> si la arista fue removida, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean removeEdge(Vertex<T> vertex);

    /**
     * Establece si un vertice es adyacente con este vertice.
     * <p>
     * Dos vertices son adyacentes si existe una arista que tiene a este vertice
     * como cola y al vertice establecido como cabeza.</p>
     *
     * @param head el vertice con el cual verificar su adyacencia.
     * @return <b>true</b> si los dos vertices son adyacentes, de lo contrario
     * devuelve <b>false</b>.
     */
    public boolean isAdjacents(Vertex<T> head);

    /**
     * Elimina todas las conexiones entre este vertice y los vertices
     * adyacentes.
     * <p>
     * Elimina las aristas en el vertice.</p>
     */
    public void disconnect();
}
