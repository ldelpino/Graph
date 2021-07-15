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
 * Permite la creacion de aristas con peso.
 * <p>
 * La clase permite la creacion de aristas con peso a partir de los dos vertices
 * que establecen la conexion.</p>
 * <p>
 * La clase no permite la reimplementacion del metodo
 * {@link ldelpino.graph.edge.DefaultEdge#isWeighted()}, el metodo es
 * <b>final</b> para evitar incongruencias entre las aristas sin peso y las
 * aristas con peso, ya que en un mismo grafo no pueden existir aristas con peso
 * y aristas sin peso. Si se desea crear una arista sin peso debe utilizarse la
 * clase {@link ldelpino.graph.edge.DefaultEdge} que permite la creacion de
 * aristas sin peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @param <K> el tipo de dato del peso de la arista.
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.edge.AbstractEdge
 * @see ldelpino.graph.edge.DefaultEdge
 * @see ldelpino.graph.vertex.Vertex
 * 
 * @author Lazaro Cesar del Pino OLivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class WeightedEdge<T, K> extends AbstractEdge<T> {

    /**
     * El peso de la arista.
     */
    private K weight;

    /**
     * Crea una nueva arista con peso con los vertices que ella enlaza.
     *
     * @param tail el vertice que hace funcion de cola en la interconexion.
     * @param head el vertice que hace funcion de cabeza en la interconexion.
     * @param weight el peso de la arista.
     */
    public WeightedEdge(Vertex<T> tail, Vertex<T> head, K weight) {
        super(tail, head);
        this.weight = weight;
    }

    /**
     * Devuelve el peso de la arista.
     *
     * @return el peso de la arista.
     */
    public K getWeight() {
        return weight;
    }

    /**
     * Establece el nuevo peso de la arista.
     *
     * @param weight el nuevo peso de la arista.
     */
    public void setWeight(K weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    @Override
    public final boolean isWeighted() {
        return true;
    }
}
