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
package ldelpino.graph.graph_implementation;

import ldelpino.graph.api.DirectedGraph;
import ldelpino.graph.api.SimpleEdgeGraph;
import ldelpino.graph.api.WeightedVertexGraph;
import ldelpino.graph.edge.DefaultEdge;
import ldelpino.graph.vertex.Vertex;
import ldelpino.graph.vertex.WeightedVertex;

/**
 * Permite la creacion de un grafo dirigido sin peso en las aristas y con peso
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
 * @param <K> el tipo de dato del peso de los vertices.
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.WeightedVertexGraph
 * @see ldelpino.graph.api.SimpleEdgeGraph
 * @see ldelpino.graph.graphextensions.SimpleDirectedGraph
 * @see ldelpino.graph.graphextensions.WeightedEdgeDirectedGraph
 * 
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class WeightedVertexDirectedGraph<T, K> extends DirectedGraph<T>
        implements WeightedVertexGraph<T, K>, SimpleEdgeGraph<T> {

    @Override
    public final boolean isWeightedVertex() {
        return true;
    }

    @Override
    public final boolean isWeigthedEdge() {
        return false;
    }

    @Override
    public boolean insertVertex(T info, K weight) {
        return insertVertex(new WeightedVertex<>(info, weight));
    }

    @Override
    public K getWeightVertex(T info) {
        WeightedVertex<T, K> vertex = (WeightedVertex<T, K>) getVertex(info);
        return vertex.getWeight();
    }

    @Override
    public boolean insertEdge(T infoTail, T infoHead) {
        if (existVertex(infoTail) && existVertex(infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Vertex<T> vertexHead = getVertex(infoHead);
            DefaultEdge<T> edge = new DefaultEdge<>(vertexTail, vertexHead);
            return vertexTail.insertEdge(edge) && vertexHead.insertEdge(edge);
        }
        return false;
    }
}
