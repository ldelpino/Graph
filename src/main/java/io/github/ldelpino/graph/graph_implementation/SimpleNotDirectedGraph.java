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

import io.github.ldelpino.graph.api.NotDirectedGraph;
import io.github.ldelpino.graph.api.SimpleEdgeGraph;
import io.github.ldelpino.graph.api.SimpleVertexGraph;
import io.github.ldelpino.graph.edge.DefaultEdge;
import io.github.ldelpino.graph.vertex.DefaultVertex;
import io.github.ldelpino.graph.vertex.Vertex;

/**
 * Permite la creacion de un grafo no dirigido sin peso en las aristas ni en los
 * vertices.
 * <p>
 * La clase es la encargada de implementar los metodos de insercion de vertices
 * y aristas.</p>
 * <p>
 * La clase tiene los metodos
 * {@link ldelpino.graph.graphextensions.SimpleDirectedGraph#isWeightedVertex()}
 * y {@link ldelpino.graph.graphextensions.SimpleDirectedGraph#isWeigthedEdge()}
 * <b>final</b> para evitar incongrurencias entre los grafos con peso y sin
 * peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see io.github.ldelpino.graph.api.NotDirectedGraph
 * @see io.github.ldelpino.graph.api.SimpleVertexGraph
 * @see io.github.ldelpino.graph.api.SimpleEdgeGraph
 * @see ldelpino.graph.graphextensions.WeightedVertexNotDirectedGraph
 * @see ldelpino.graph.graphextensions.WeightedEdgeNotDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class SimpleNotDirectedGraph<T> extends NotDirectedGraph<T> implements SimpleEdgeGraph<T>, SimpleVertexGraph<T> {

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
        return false;
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean insertEdge(T infoTail, T infoHead) {
        if (existVertex(infoTail) && existVertex(infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Vertex<T> vertexHead = getVertex(infoHead);
            DefaultEdge<T> edgeTail = new DefaultEdge<>(vertexTail, vertexHead);
            DefaultEdge<T> edgeHead = new DefaultEdge<>(vertexHead, vertexTail);
            return vertexTail.insertEdge(edgeTail) && vertexHead.insertEdge(edgeHead);
        }
        return false;
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
}
