import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class XogoCartas {

	public enum OpcionSeleccionada {

		MAIOR,
		MENOR
	}

	/**
	 * Atributos:
	 */
	private Stack<Carta> baralla;
	private Queue<Xogador> xogadores;
	private static Scanner scanner = new Scanner(System.in);
	private static final int XOGADORES_MAXIMO = 8;
	private static final int XOGADORES_MINIMO = 1;

	/**
	 * Constructor
	 * 
	 * @param baralla
	 * @param xogadores
	 */
	public XogoCartas() {
		this.baralla = crearBaralla();
		this.xogadores = crearXogadoresPorConsola();
	}

	public static Stack<Carta> crearBaralla() {
		Stack<Carta> baralla = new Stack<>();
		for (Carta.NumeroCarta numero : Carta.NumeroCarta.values()) {
			for (Carta.PauCarta pau : Carta.PauCarta.values()) {
				baralla.push(new Carta(numero, pau));
			}
		}
		return baralla;
	}

	public static void barallar(Stack<Carta> baralla) {
		Random aleatorio = new Random();
		int indice = 0;
		ArrayList<Carta> novaBaralla = new ArrayList<Carta>();

		while (!baralla.isEmpty()) {
			novaBaralla.add(baralla.pop());
		}
		while (!novaBaralla.isEmpty()) {
			indice = aleatorio.nextInt(novaBaralla.size()); // Índice aleatorio
			Carta carta = novaBaralla.remove(indice); // Sacar la carta en la posición aleatoria
			baralla.push(carta); // Agregar la carta a la pila original
		}
	}

	/**
	 * Obtén a opción seleccionada polo xogador (maior ou menor), que é un tipo enumerado.
	 *
	 * @param  xogador
	 * @return         opción seleccionada polo xogador
	 */
	private static XogoCartas.OpcionSeleccionada consultarOpcionSeleccionada(Xogador xogador) {
		System.out.println("<O xogador: " + xogador.getNome() + "predice que vai a ser[MAIOR/MENOR]:");
		String prediccion = scanner.nextLine().toUpperCase();
		return XogoCartas.OpcionSeleccionada.valueOf(prediccion);
	}

	/**
	 * Método que permite crear xogadores por consola solicitando os datos ao xogador
	 * 
	 * @return xogadores
	 */
	private static Queue<Xogador> crearXogadoresPorConsola() {
		Queue<Xogador> xogadores = new LinkedList<>();
		int numeroXogadores = seleccionarNumeroXogadoresPorConsola();
		for (int i = 1; i < numeroXogadores; i++) {
			System.out.println(">Nome do xogador:");
			String nome = scanner.nextLine();
			xogadores.add(new Xogador(nome));
		}
		return xogadores;
	}

	/**
	 * Método que permite configurar o número de xogadores do xogo pola consola, solicitando eses datos ao
	 * xogador.
	 * 
	 * @return nº xogadores
	 */
	private static int seleccionarNumeroXogadoresPorConsola() {
		int opcion;
		do {
			System.out.println("<<Introduza o número de xogadores [MIN:" + XOGADORES_MINIMO + "/ MAX:" + XOGADORES_MAXIMO + "]");
			opcion = scanner.nextInt();
			scanner.nextLine(); // limpa buffer
		}
		while (opcion > XOGADORES_MAXIMO || opcion < XOGADORES_MINIMO);
		return opcion;
	}

	/**
	 * Mostra unha mensaxe de fin de xogo para os xogadores
	 */
	public void mostrarMensaxeFinDoXogo() {
		System.out.println("--FIN DO XOGO--");
	}

	/**
	 * Método que permite a un xogador realizar a súa ronda, neste caso consiste en ir sacando cartas do
	 * montón, mentres atine se é maior ou menor con respecto a anterior, e repítese até que erra ou xa non
	 * quedan cartas que sacar.
	 * 
	 * @param xogador
	 */
	private void xogarRonda(Xogador xogador) {
		if (baralla.isEmpty()) {
			System.out.println("--Baralla vacía--");
			return;
		}
		Carta cartaActual = baralla.pop();

		System.out.println("<<O xogador " + xogador.getNome() + " sacou : " + cartaActual.getNumero() + cartaActual.getPau() + " >> Valor: " + cartaActual.getNumero().getValor() + "--");
		if (xogador.getCartas().isEmpty()) {
			xogador.getCartas().push(cartaActual);
		}
		else {
			OpcionSeleccionada opcion = consultarOpcionSeleccionada(xogador);
			Carta adiviñar = baralla.pop();
			System.out.println("<<A carta é: " + adiviñar.getNumero() + adiviñar.getPau() + ">>");
			if (xogador.acertouCarta(adiviñar, opcion)) {
				System.out.println("--Acertaches! Continúas!");
				xogador.getCartas().push(adiviñar);
				xogarRonda(xogador);
			}
			else {
				System.out.println("--Lástima! Seguinte turno!");
			}
		}
	}

	/**
	 * Este método recibe por parámetro unha cola de xogadores, e busca nela ao xogador ou xogadores que teñen
	 * a máxima puntuación, que son son devoltos nunha lista ao tempo que son eliminados da cola de xogadores.
	 * 
	 * @param  xogadores cola de xogadores entre os que se quere buscar os que teñen a máxima puntuación, que
	 *                   ademais son removidos desta cola.
	 * @return           Listado de xogadores coa máxima puntuación
	 */
	private List<Xogador> obterCampions(Queue<Xogador> xogadores) {

		List<Xogador> campions = new ArrayList<>();
		int maxPuntuación = -1;

		for (Xogador xogador : xogadores) {
			int puntuacion = xogador.obterPuntuacion();
			if (puntuacion > maxPuntuación) {
				maxPuntuación = puntuacion;
				campions.clear();
				campions.add(xogador);
			}
			else if (xogador.obterPuntuacion() == maxPuntuación) {
				campions.add(xogador);
			}
		}
		xogadores.removeAll(campions);
		return campions;
	}

	/**
	 * Método que obtén a clasificación a partires dos datos de puntuación que ten cada xogador
	 * 
	 * @return clasificación representada por un mapa clave-valor, onde a clave é a posición acadada e o valor
	 *         a lista de xogadores
	 */
	private HashMap<Integer, List<Xogador>> obterClasificacion() {

	}

	/**
	 * Método estático que mostra a clasificación do xogo a partires do mapa de clasificación recibido
	 * 
	 * @param clasificación representada por un mapa clave-valor, onde a clave é a posición acadada e o valor
	 *                      a lista de xogadores que acadaron esa posición.
	 */
	private void mostrarClasificacion(HashMap<Integer, List<Xogador>> clasificacion) {

	}

	/**
	 * Método de que inicia o xogo
	 */
	public void xogar() {
		barallar(baralla);

		while (!baralla.isEmpty()) {
			for (Xogador xogador : xogadores) {
				xogarRonda(xogador);
			}
		}

		mostrarMensaxeFinDoXogo();
	}

}
