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
package ldelpino.graph.edge;

import ldelpino.graph.vertex.Vertex;

/**
 * Permite establecer una arista en una estructura de datos en forma de grafo.
 * <p>
 * Una arista es la conexion entre dos vertices de un grafo.Las aristas pueden
 * tener peso o no, las aristas con peso son aquellas que representan un valor
 * medible y permite la comparacion entre diferentes aristas.</p>
 * <p>
 * La arista almacena tanto el vertice del cual ella se enlaza <b>(cola)</b>,
 * como el vertice al que ella enlaza <b>(cabeza)</b>.</p>
 * <p>
 * Un grafo puede ser dirigido o no dirigido, en caso del grafo ser dirigido
 * solo el vertice que hace funcion de cola es el que almacena a la arista en su
 * lista de aristas, dado que en un grafo dirigido la direccion tiene una
 * importancia.En el caso de que el grafo sea no dirigido la direccion de las
 * aristas tiene la misma significancia la direccion de <b>A</b> hasta <b>B</b>,
 * que desde <b>B</b> hasta <b>A</b>, por tanto, el vertice que hace funcion de
 * cola como el vertice que hace funcion de cabeza tienen almacenado a esta
 * arista en su lista de aristas.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.edge.AbstractEdge
 * @see ldelpino.graph.edge.DefaultEdge
 * @see ldelpino.graph.edge.WeightedEdge
 * @see ldelpino.graph.vertex.Vertex
 * @see ldelpino.graph.Graph
 *
 * @author Lazaro Cesar del Pino OLivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface Edge<T> {

    /**
     * Establece si la arista tiene peso o no.
     * <p>
     * Esto permite saber si una arista tiene peso o no sin tener que preguntar
     * por la instancia especifica de una arista con peso. En caso de necesitar
     * la obtencion del peso sabiendo que la arista tiene peso, debe convertirse
     * el objeto a {@link roj.redcorp.graph.edge.WeightedEdge}</p>
     *
     * @return <b>true</b> si la arista tiene peso, sino tiene peso entonces
     * devuelve <b>false</b>. @see roj.redcorp.graph.edge.WeightedEdge
     */
    public boolean isWeighted();

    /**
     * Devuelve el vertice de la arista que se establece como cabeza del enlace.
     * <p>
     * El vertice que hace funcion de cabeza tiene a la arista en su lista de
     * aristas si el grafo es no dirigido, si el grafo es no dirigido entonces
     * solo el vertice que hace funcion de cola es el que tiene a la arista
     * almacenada.</p>
     *
     * @return el vertice enlazado a esta arista.
     */
    public Vertex<T> getVertexHead();

    /**
     * Devuelve el vertice de la arista que se establece como cola del enlace.
     * <p>
     * El vertice que hace funcion de cola es el vertice que siempre tiene en su
     * lista de vertices a la arista almacenada.</p>
     *
     * @return el vertice que enlaza a esta arista.
     */
    public Vertex<T> getVertexTail();
}
