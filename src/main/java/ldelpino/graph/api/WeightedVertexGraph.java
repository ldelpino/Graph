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

/**
 * Permite la insercion de vertices con peso en un grafo.
 * <p>
 * La interfaz es usada como extension para la insercion de vertices con
 * peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @param <K> el tipo de dato del peso de los vertices.
 * @see ldelpino.graph.Graph
 * @see ldelpino.graph.vertex.Vertex
 * @see ldelpino.graph.api.SimpleVertexGraph
 * @see ldelpino.graph.api.WeightedEdgeGraph
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.NotDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface WeightedVertexGraph<T, K> {

    /**
     * Inserta un nuevo vertice en el grafo.
     *
     * @param info la informacion del vertice.
     * @param weight el peso del vertice.
     * @return <b>true</b> si el vertice fue insertado, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean insertVertex(T info, K weight);

    /**
     * Devuelve el peso del vertice.
     *
     * @param info la informacion del vertice.
     * @return el peso del vertice.
     */
    public K getWeightVertex(T info);
}
