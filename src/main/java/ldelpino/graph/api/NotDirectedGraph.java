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
package ldelpino.graph.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import ldelpino.graph.vertex.Vertex;

/**
 * Permite la creacion de un grafo no dirigido.
 * <p>
 * Los grafos no dirigidos son aquellos en donde la direccion no importa, o sea
 * donde la direccion de un vertice A hasta un vertice B es la misma que del
 * vertice B hasta el vertice A, en un grafo no dirigido solo puede existir una
 * sola arista entre dos vertices y los dos vertices almacenan a la arista.</p>
 * <p>
 * La clase tiene el metodo
 * {@link roj.redcorp.graph.api.NotDirectedGraph#isDirected()} como final para
 * evitar incongruencias en futuras clases que hereden de esta.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.Graph
 * @see ldelpino.graph.api.AbstractGraph
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.SimpleVertexGraph
 * @see ldelpino.graph.api.WeightedVertexGraph
 * @see ldelpino.graph.api.SimpleEdgeGraph
 * @see ldelpino.graph.api.WeightedEdgeGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class NotDirectedGraph<T> extends AbstractGraph<T> {

    /**
     * Crea un nuevo grafo no dirigido.
     */
    public NotDirectedGraph() {
        super();
    }

    /**
     *
     * @return
     */
    @Override
    public final boolean isDirected() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCyclic() {
        boolean cycle = false;
        Iterator<Vertex<T>> iter = getVertices0().iterator();
        while (!cycle && iter.hasNext()) {
            Vertex<T> current = iter.next();
            cycle = cycleInNodeND(null, current, new LinkedList<>());
        }
        return cycle;
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public int degree(T info) {
        int degree = -1;
        if (existVertex(info)) {
            degree = outDegree(getVertex(info));
        }
        return degree;
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean areAdjacents(T infoTail, T infoHead) {
        Vertex<T> tailVertex = getVertex(infoTail);
        Vertex<T> headVertex = getVertex(infoHead);
        return tailVertex.isAdjacents(headVertex) && headVertex.isAdjacents(tailVertex);
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean removeEdge(T infoTail, T infoHead) {
        if (areAdjacents(infoTail, infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Vertex<T> vertexHead = getVertex(infoHead);
            return vertexTail.removeEdge(vertexHead) && vertexHead.removeEdge(vertexTail);
        }
        return false;
    }

    /**
     * Establece si el grafo tiene ciclos o no de forma recursiva.
     *
     * @param origin el vertice origen.
     * @param vertex el vertice con el cual encontar el origen.
     * @param visited la lista de vertices visitados.
     * @return <b>true</b> si el grafo tiene ciclos, de lo contrario devuelve
     * <b>false</b>.
     */
    protected boolean cycleInNodeND(Vertex<T> origin, Vertex<T> vertex, List<Vertex<T>> visited) {
        boolean cycle = false;
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            Iterator<Vertex<T>> iter = vertex.getAdjacents().iterator();
            while (!cycle && iter.hasNext()) {
                Vertex<T> current = iter.next();
                if (!current.equals(origin)) {
                    cycle = cycleInNodeND(vertex, current, visited);
                }
            }
        } else {
            cycle = true;
        }
        return cycle;
    }
}
