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
package io.github.ldelpino.graph.graph_implementation;

import io.github.ldelpino.graph.api.DirectedGraph;
import io.github.ldelpino.graph.api.SimpleVertexGraph;
import io.github.ldelpino.graph.api.WeightedEdgeGraph;
import io.github.ldelpino.graph.edge.Edge;
import io.github.ldelpino.graph.edge.WeightedEdge;
import io.github.ldelpino.graph.vertex.DefaultVertex;
import io.github.ldelpino.graph.vertex.Vertex;

/**
 * Permite la creacion de un grafo dirigido con peso en las aristas y sin peso
 * en los vertices.
 * <p>
 * La clase es la encargada de implementar los metodos de insercion de vertices
 * y aristas.</p>
 * <p>
 * La clase tiene los metodos
 * {@link ldelpino.graph.graphextensions.SimpleDirectedGraph#isWeightedVertex()}
 * y
 * {@link ldelpino.graph.graphextensions.SimpleDirectedGraph#isWeigthedEdge()}
 * final para evitar incongrurencias entre los grafos con peso y sin peso.</p>
 *
 * @author EL ROJO
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @param <K> el tipo de dato del peso de las aristas.
 * @see io.github.ldelpino.graph.api.DirectedGraph
 * @see io.github.ldelpino.graph.api.SimpleVertexGraph
 * @see io.github.ldelpino.graph.api.WeightedEdgeGraph
 * @see ldelpino.graph.graphextensions.SimpleDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class WeightedEdgeDirectedGraph<T, K extends Number> extends DirectedGraph<T>
        implements SimpleVertexGraph<T>, WeightedEdgeGraph<T, K> {

    /**
     *
     * @return
     */
    @Override
    public final boolean isWeightedVertex() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public final boolean isWeigthedEdge() {
        return true;
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public boolean insertVertex(T info) {
        return insertVertex(new DefaultVertex<>(info));
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @param weight
     * @return
     */
    @Override
    public boolean insertEdge(T infoTail, T infoHead, K weight) {
        if (existVertex(infoTail) && existVertex(infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Vertex<T> vertexHead = getVertex(infoHead);
            WeightedEdge<T, K> edge = new WeightedEdge<>(vertexTail, vertexHead, weight);
            return vertexTail.insertEdge(edge) && vertexHead.insertEdge(edge);
        }
        return false;
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public K getEdgeWeight(T infoTail, T infoHead) {
        DefaultVertex<T> vertexTail = (DefaultVertex<T>) getVertex(infoTail);
        Edge<T> edge = vertexTail.getEdge(getVertex(infoHead));
        WeightedEdge<T, K> weightedEdge = (WeightedEdge<T, K>) edge;
        return weightedEdge.getWeight();
    }
}
