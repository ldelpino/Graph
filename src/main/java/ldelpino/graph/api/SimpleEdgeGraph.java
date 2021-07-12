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
 * Permite la insercion de aristas sin peso en un grafo.
 * <p>
 * La interfaz es usada como extension para la insercion de aristas sin
 * peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.Graph
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.api.WeightedEdgeGraph
 * @see ldelpino.graph.api.SimpleVertexGraph
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.NotDirectedGraph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface SimpleEdgeGraph<T> {

    /**
     * Inserta una nueva arista en el grafo a partir de sus vertices.
     *
     * @param infoTail el vertice que hace funcion de cola.
     * @param infoHead el vertice que hace funcion de cabeza.
     * @return <b>true</b> si la rista fue insertada, de lo contrario devuelve
     * <b>false</b>.
     */
    public boolean insertEdge(T infoTail, T infoHead);
}
