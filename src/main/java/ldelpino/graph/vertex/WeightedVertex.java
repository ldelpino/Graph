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
package ldelpino.graph.vertex;

import java.util.Objects;
import ldelpino.graph.edge.Edge;

/**
 * Permite la creacion y utilizacion de vertices con pesos.
 * <p>
 * La clase permite la creacion de vertices con peso en una estructura de datos
 * en forma de grafo.</p>
 * <p>
 * La clase no permite la reimplementacion del metodo
 * {@link roj.redcorp.graph.vertex.DefaultVertex#isWeighted()}, el metodo es
 * <b>final</b> para evitar incongruencias entre los vertices sin peso y los
 * vertices con peso, ya que en un mismo grafo no pueden existir vertices con
 * peso y vertices sin peso. Si se desea crear un vertice sin peso debe
 * utilizarse la clase {@link roj.redcorp.graph.vertex.DefaultVertex} que
 * permite la creacion de vertices sin peso.</p>
 *
 * @param <T> el tipo de dato de la informacion del vertice.
 * @param <K> el tipo de dato del peso del vertice.
 * @see ldelpino.graph.vertex.Vertex
 * @see ldelpino.graph.vertex.AbstractVertex
 * @see ldelpino.graph.vertex.DefaultVertex
 * @see ldelpino.graph.edge.Edge
 * @see ldelpino.graph.Graph
 *
 * @author Lazaro Cesar del Pino Olvera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class WeightedVertex<T, K> extends AbstractVertex<T> {

    /**
     * El peso del vertice con peso.
     */
    private K weight;

    /**
     * Crea un nuevo vetice con peso a partir de la informacion y el peso del
     * vertice.
     *
     * @param info la informacion del vertice a almacenar.
     * @param weight el peso del vertice.
     */
    public WeightedVertex(T info, K weight) {
        super(info);
        this.weight = weight;
    }

    @Override
    public final boolean isWeighted() {
        return true;
    }

    /**
     * Devuelve el peso del vertice.
     *
     * @return el peso del vertice.
     */
    public K getWeight() {
        return weight;
    }

    /**
     * Establece el nuevo peso del vertice.
     *
     * @param weight el nuevo peso del vertice.
     */
    public void setWeight(K weight) {
        this.weight = weight;
    }

    @Override
    public Edge<T> getEdge(Vertex<T> head) {
        return super.getEdge(head);
    }

    /**
     * Establece si dos objetos son iguales o no.
     * <p>
     * Dos vertices son iguales si el vertice es instancia de WeightedVertex y
     * la informacion y el peso que almacenan tambien son iguales.</p>
     *
     * @param o el objeto verificar si igualdad con <b>este</b> objeto.
     * @return <b>true</b> si los dos objetos son iguales, de lo contrario
     * devuelve <b>false</b>.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof WeightedVertex && super.equals(o)) {
            WeightedVertex v = (WeightedVertex) o;
            return this.getWeight().equals(v.getWeight());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.weight);
        return hash;
    }

    /**
     * Devuelve una cadena de caracteres que representa este objeto de tipo
     * WeightedVertex.
     * <pre>
     * Sintaxis:
     * Vertex: info.toString() + .Weight: + weight.toString();
     * </pre>
     *
     * @return la represetacion en cadena de caracteres.
     */
    @Override
    public String toString() {
        return super.toString() + ".Weight: " + getWeight().toString();
    }
}
