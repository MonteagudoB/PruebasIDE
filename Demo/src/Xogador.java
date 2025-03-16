import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Xogador {

	private String nome;
	private Stack<Carta> cartas;

	public Xogador(String nome) {
		this.nome = nome;
		this.cartas = new Stack<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Stack<Carta> getCartas() {
		return cartas;
	}

	/**
	 * Obtén a puntuación do xogador
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer obterPuntuacion() {
		int puntuacion = 0;
		Stack<Carta> copiaCartas = (Stack<Carta>) cartas.clone();

		while (!copiaCartas.isEmpty()) {
			Carta carta = copiaCartas.pop();
			puntuacion += carta.getNumero().getValor();
		}
		return puntuacion;
	}

	/**
	 * Comprobar carta sacada e comparalo coa opción seleccionada polo xogador, se é así engade a carta á pila
	 * de cartas do xogador e amosa unha mensaxe por consola
	 * 
	 * @param  carta
	 * @param  OpcionSeleccionada
	 * @return                    TRUE se o xogador atinou, e FALSE en caso contrario
	 */
	public boolean acertouCarta(Carta carta, XogoCartas.OpcionSeleccionada OpcionSeleccionada) {
		boolean acerto = false;
		Carta ultimaCarta = cartas.peek();
		switch (OpcionSeleccionada) {
			case MAIOR: {

				acerto = ultimaCarta.getNumero().getValor() > carta.getNumero().getValor();
				break;
			}
			case MENOR: {
				acerto = ultimaCarta.getNumero().getValor() < carta.getNumero().getValor();
			}
		}
		if (acerto == true) {
			cartas.push(carta);
		}
		return acerto;
	}

	protected Queue<Xogador> cloneXogador(Queue<Xogador> xogadores) {
		Queue<Xogador> copiaXogador = new LinkedList<>();
		for (Xogador xogador : xogadores) {
			Xogador xogadorCopia = (new Xogador(getNome()));
			xogadorCopia.getCartas().addAll(xogador.getCartas());
			copiaXogador.add(xogadorCopia);
		}
		return copiaXogador;
	}

	/**
	 * Devolve os datos completos dun xogador (nome e puntuación)
	 * 
	 * @return datos completos do xogador
	 */
	public String obterResumoCompleto() {
		return "-Nome: " + nome + "\n-Puntuación: " + cartas;
	}
}
