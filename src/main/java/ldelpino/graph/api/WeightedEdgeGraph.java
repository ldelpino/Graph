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
 * Permite la insercion de aristas con peso en un grafo.
 * <p>
 * La interfaz es usada como extension para la insercion de aristas con
 * peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @param <K> el tipo de dato del peso de las aristas.
 * @see ldelpino.graph.Graph
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.api.SimpleEdgeGraph
 * @see ldelpino.graph.api.WeightedVertexGraph
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.NotDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface WeightedEdgeGraph<T, K> {

    /**
     * Inserta una nueva arista en el grafo a partir de sus vertices y su peso.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @param weight el peso de la arista a insertar.
     * @return <b>true</b> si la arista fue insertada, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean insertEdge(T infoTail, T infoHead, K weight);

    /**
     * Devuelve el peso de la arista dado los vertices.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return el peso de la arista.
     */
    public K getEdgeWeight(T infoTail, T infoHead);
}
