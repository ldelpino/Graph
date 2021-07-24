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
import io.github.ldelpino.graph.api.WeightedEdgeGraph;
import io.github.ldelpino.graph.api.WeightedVertexGraph;
import io.github.ldelpino.graph.edge.Edge;
import io.github.ldelpino.graph.edge.WeightedEdge;
import io.github.ldelpino.graph.vertex.DefaultVertex;
import io.github.ldelpino.graph.vertex.Vertex;
import io.github.ldelpino.graph.vertex.WeightedVertex;

/**
 * Permite la creacion de grafos dirigidos con peso en las aristas y en los
 * vertices
 *
 * @author EL ROJO
 * @param <T> El tipo de dato de la informacion de los vertices.
 * @param <K> El tipo de dato del peso de los vertices.
 * @param <R> El tipo de dato del peso de las aristas.
 */
public class WeightedVertexEdgeDirectedGraph<T, K, R> extends DirectedGraph<T> implements
        WeightedVertexGraph<T, K>, WeightedEdgeGraph<T, R> {

    /**
     *
     * @return
     */
    @Override
    public final boolean isWeightedVertex() {
        return true;
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
     * @param weight
     * @return
     */
    @Override
    public boolean insertVertex(T info, K weight) {
        return insertVertex(new WeightedVertex<>(info, weight));
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public K getWeightVertex(T info) {
        WeightedVertex<T, K> vertex = (WeightedVertex<T, K>) getVertex(info);
        return vertex.getWeight();
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @param weight
     * @return
     */
    @Override
    public boolean insertEdge(T infoTail, T infoHead, R weight) {
        if (existVertex(infoTail) && existVertex(infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Vertex<T> vertexHead = getVertex(infoHead);
            WeightedEdge<T, R> edge = new WeightedEdge<>(vertexTail, vertexHead, weight);
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
    public R getEdgeWeight(T infoTail, T infoHead) {
        DefaultVertex<T> vertexTail = (DefaultVertex<T>) getVertex(infoTail);
        Edge<T> edge = vertexTail.getEdge(getVertex(infoHead));
        WeightedEdge<T, R> weightedEdge = (WeightedEdge<T, R>) edge;
        return weightedEdge.getWeight();
    }

}
