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
 * Permite la creacion de aristas sin peso.
 * <p>
 * La clase permite la creacion de aristas sin peso a partir de los dos vertices
 * que establecen la conexion.</p>
 * <p>
 * La clase no permite la reimplementacion del metodo
 * {@link roj.redcorp.graph.edge.DefaultEdge#isWeighted()}, el metodo es
 * <b>final</b> para evitar incongruencias entre las aristas sin peso y las
 * aristas con peso, ya que en un mismo grafo no pueden existir aristas con peso
 * y aristas sin peso. Si se desea crear una arista con peso debe utilizarse la
 * clase {@link roj.redcorp.graph.edge.WeightedEdge} que permite la creacion de
 * aristas con peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.edge.AbstractEdge
 * @see ldelpino.graph.edge.WeightedEdge
 * @see ldelpino.graph.vertex.Vertex
 *
 * @author Lazaro Cesar del Pino OLivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class DefaultEdge<T> extends AbstractEdge<T> {

    /**
     * Crea una nueva arista sin peso con los vertices que ella enlaza.
     *
     * @param tail el vertice que hace funcion de cola.
     * @param head el vertice que hace funcion de cabeza.
     */
    public DefaultEdge(Vertex<T> tail, Vertex<T> head) {
        super(tail, head);
    }

    @Override
    public final boolean isWeighted() {
        return false;
    }
}
