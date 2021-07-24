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
package io.github.ldelpino.graph.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import io.github.ldelpino.graph.vertex.Vertex;

/**
 * Permite la creacion de un grafo dirigido.
 * <p>
 * Los grafos dirigidos son aquellos en donde la direccion tine una importancia,
 * o sea, donde el vertice A es adyacente con el vertice B, significa que el
 * vertice A tiene una arista hacia el vertice B, pero el vertice B no
 * necesariamente tiene una arista hacia el vertice A.</p>
 * <p>
 * En un grafo dirigido pueden haber tantas aristas como se necesiten desde un
 * vertice A hasta el vertice B.</p>
 * <p>
 * La clase tiene el metodo
 * {@link roj.redcorp.graph.api.DirectedGraph#isDirected()} como final para
 * evitar incongruencias en futuras clases que hereden de esta.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see io.github.ldelpino.graph.Graph
 * @see io.github.ldelpino.graph.api.AbstractGraph
 * @see io.github.ldelpino.graph.api.NotDirectedGraph
 * @see io.github.ldelpino.graph.api.SimpleVertexGraph
 * @see io.github.ldelpino.graph.api.WeightedVertexGraph
 * @see io.github.ldelpino.graph.api.SimpleEdgeGraph
 * @see io.github.ldelpino.graph.api.WeightedEdgeGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class DirectedGraph<T> extends AbstractGraph<T> {

    /**
     * Crea un nuevo grafo dirigido.
     */
    public DirectedGraph() {
        super();
    }

    /**
     * Establece si el grafo es dirigido o no.
     *
     * @return <b>true</b> si el grafo es dirigido, de lo contrario
     * devuelve<b>false</b>.
     */
    @Override
    public final boolean isDirected() {
        return true;
    }

    /**
     * Establece si existe al menos un ciclo dentro del grafo.
     *
     * @return <b>true</b> si el grafo contiene al menos un ciclo, de lo
     * contrario devuelve<b>false</b>.
     */
    @Override
    public boolean isCyclic() {
        boolean cycle = false;
        Iterator<Vertex<T>> iter = getVertices0().iterator();
        while (!cycle && iter.hasNext()) {
            Vertex<T> current = iter.next();
            cycle = cycleInNodeDG(current.getAdjacents(), current, new LinkedList<>());
        }

        return cycle;
    }

    /**
     * Devuelve el grado de un grafo dirigido.
     * <p>
     * En un grafo dirigido, el grado del vertice sera su cantidad de vertices
     * adyacentes, mas la cantidad de vertices que lo tienen a el como
     * adyacente.</p>
     *
     * @return el grado de un grafo dirigido.
     */
    @Override
    public int degree(T info) {
        int degree = -1;
        if (existVertex(info)) {
            Vertex<T> vertex = getVertex(info);
            degree = inDegreeDG(vertex) + outDegree(vertex);
        }
        return degree;
    }

    /**
     * Remueve una arista del grafo, si los vertices de la arista existen.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return <b>true</b> si la arista fue eliminada correctamente, de lo
     * contrario devuelve <b>false</b>.
     */
    @Override
    public boolean removeEdge(T infoTail, T infoHead) {
        return getVertex(infoTail).removeEdge(getVertex(infoHead));
    }

    /**
     * Establece si dos vertices son adyacentes entre si.
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return
     */
    @Override
    public boolean areAdjacents(T infoTail, T infoHead) {
        return getVertex(infoTail).isAdjacents(getVertex(infoHead));
    }

    /**
     * Devuelve la cantidad de adyacentes que hay con el vertice establecido.
     *
     * @param vertex el indice del vertice a buscar los adyacentes con el.
     * @return la cantidad de adyacentes con el vertice.
     */
    protected int inDegreeDG(Vertex<T> vertex) {
        int degree = 0;
        Iterator<Vertex<T>> iter = getVertices0().iterator();
        while (iter.hasNext()) {
            Vertex<T> next = iter.next();
            if (next.isAdjacents(vertex)) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * Establece si el grafo tiene ciclos o no de forma recursiva.
     *
     * @param adjacents la lista de adyacentes del vertice inicial.
     * @param vertex el vertice inicial con el cual encontrar un ciclo.
     * @param visited la lista de vertices visitados.
     * @return <b>true</b> si el grafo tiene cicos, de lo contrario devuelve
     * <b>false</b>.
     */
    protected boolean cycleInNodeDG(Collection<Vertex<T>> adjacents, Vertex<T> vertex, Collection<Vertex<T>> visited) {
        boolean cycle = false;
        Iterator<Vertex<T>> iter = adjacents.iterator();
        while (!cycle && iter.hasNext()) {
            Vertex<T> current = iter.next();
            if (!current.equals(vertex)) {
                if (!visited.contains(current)) {
                    visited.add(current);
                    cycle = cycleInNodeDG(current.getAdjacents(), vertex, visited);
                }
                continue;
            }
            cycle = true;
        }
        return cycle;
    }
}
