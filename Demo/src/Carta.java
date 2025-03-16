import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Carta implements Cloneable {

	public enum NumeroCarta {
		AS(1),
		DOUS(2),
		TRES(3),
		CATRO(4),
		CINCO(5),
		SEIS(6),
		SETE(7),
		SOTA(10),
		CABALO(11),
		REI(12);

		private final int valor;

		private NumeroCarta(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return valor;
		}

	}

	public enum PauCarta {
		OUROS,
		COPAS,
		ESPADAS,
		GARROTE;

	}

	private NumeroCarta numero;
	private PauCarta pau;

	public Carta(Carta.NumeroCarta numero, Carta.PauCarta pau) {

		this.numero = numero;
		this.pau = pau;
	}

	public NumeroCarta getNumero() {
		return numero;
	}

	public PauCarta getPau() {
		return pau;
	}

	@Override
	public String toString() {
		return "Carta [numero=" + numero + ", pau=" + pau + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, pau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return numero == other.numero && pau == other.pau;
	}

	/**
	 * Obtén unha copia da carta--Copia Superficial
	 */
	@Override
	protected Carta clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Carta) super.clone();
	}

	/**
	 * Método de cola unha copia de cartas--Copia profunda
	 */
	protected Queue<Carta> clone(Queue<Carta> cartas) throws CloneNotSupportedException {
		Queue<Carta> copia = new LinkedList<>();
		for (Carta carta : cartas) {
			copia.add(carta.clone());
		}
		return copia;
	}
}
