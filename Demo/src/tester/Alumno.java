package tester;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Clase que representa un alumno.
 */
class Alumno implements Cloneable {
	private String dni;
	private String nome;
	private TreeSet<String> materiasMatriculadas;
	private Enderezo enderezo;

	/**
	 * Constructor da clase Alumno
	 * 
	 * @param nome                 nome do alumno
	 * @param dni                  dni do alumno
	 * @param enderezo             enderezo do alumno
	 * @param materiasMatriculadas materias matriculadas polo alumno
	 */
	public Alumno(String nome, String dni, Enderezo enderezo, TreeSet<String> materiasMatriculadas) {
		this.nome = nome;
		this.dni = dni;
		this.enderezo = enderezo;
		this.materiasMatriculadas = (materiasMatriculadas != null) ? materiasMatriculadas : new TreeSet<>();
	}

	/**
	 * Constructor da clase Alumno
	 * 
	 * @param nome                 nome do alumno
	 * @param dni                  dni do alumno
	 * @param enderezo             enderezo do alumno
	 * @param materiasMatriculadas materias matriculadas polo alumno
	 */
	public Alumno(String nome, String dni, Enderezo enderezo, String... materiasMatriculadas) {
		this(nome, dni, enderezo, new TreeSet<>());
		if (materiasMatriculadas != null)
			this.materiasMatriculadas.addAll(Arrays.asList(materiasMatriculadas));
	}

	/**
	 * Constructor da clase Alumno
	 * 
	 * @param nome     nome do alumno
	 * @param dni      dni do alumno
	 * @param enderezo enderezo do alumno
	 */
	public Alumno(String nome, String dni, Enderezo enderezo) {
		this(nome, dni, enderezo, new TreeSet<>());
	}

	/**
	 * Engade unha materia á lista de materias matriculadas do alumno
	 * 
	 * @param materia materia a engadir
	 */
	public void engadirMateria(String materia) {
		materiasMatriculadas.add(materia);
	}

	/**
	 * Mostra a lista de materias matriculadas do alumno.
	 */
	public void mostrarMateriasMatriculadas() {
		System.out.println("- Materias matriculadas por " + nome + ":");
		for (String materia : materiasMatriculadas) {
			System.out.println("  . " + materia);
		}
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the materiasMatriculadas
	 */
	public TreeSet<String> getMateriasMatriculadas() {
		return materiasMatriculadas;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Devolve o nome do alumno.
	 * 
	 * @return nome do alumno.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the enderezo
	 */
	public Enderezo getEnderezo() {
		return enderezo;
	}

	/**
	 * @param enderezo the enderezo to set
	 */
	public void setEnderezo(Enderezo enderezo) {
		this.enderezo = enderezo;
	}

	/**
	 * Implementamos o método hashCode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	/**
	 * Implementamos o método para comprobar cando un obxecto é igual que outro
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return dni == other.dni;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Alumno clone() throws CloneNotSupportedException {
		Alumno al = (Alumno) super.clone();
		if (al.enderezo != null)
			al.enderezo = (Enderezo) al.enderezo.clone();
		if (al.materiasMatriculadas == null)
			al.materiasMatriculadas = new TreeSet<>();
		else if ((al.materiasMatriculadas != null) && (al.materiasMatriculadas.size() > 0)) {
			al.materiasMatriculadas = (TreeSet<String>) al.materiasMatriculadas.clone();
		}
		return al;
	}

	@SuppressWarnings("unchecked")
	protected Alumno myClone() {
		Enderezo end = new Enderezo(this.enderezo.getRua(), this.enderezo.getCp(), this.enderezo.getNum(), this.enderezo.getProvincia());
		TreeSet<String> materias = (TreeSet<String>) this.materiasMatriculadas.clone();
		return new Alumno(this.nome, this.dni, end, materias);
	}

	/**
	 * Método que convirte o obxecto nunha cadea de texto
	 */
	@Override
	public String toString() {
		return "- Alumno [dni=" + dni + ", nome=" + nome + ", enderezo=" + enderezo + "]";
	}
}