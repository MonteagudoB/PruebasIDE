package tester;



import java.util.HashSet;
import java.util.TreeSet;

/**
 * Clase que xestiona o sistema de alumnos.
 */
public class XestionAlumnos {
	private HashSet<Alumno> alumnos;

	/**
	 * Constructor da clase XestionAlumnos.
	 */
	public XestionAlumnos() {
		this.alumnos = new HashSet<>();
	}

	/**
	 * Obtén o conxunto de alumnos
	 * 
	 * @return the alumnos
	 */
	public HashSet<Alumno> getAlumnos() {
		return alumnos;
	}

	/**
	 * Mostra a lista de alumnos ordenada por nome.
	 */
	public void mostrarAlumnos() {
		System.out.println("Lista de Alumnos:");
		for (Alumno alumno : alumnos) {
			System.out.println("");
			System.out.println(alumno);
			alumno.mostrarMateriasMatriculadas();
		}
	}

	/**
	 * Método principal para probar a clase
	 * 
	 * @param  args                       argumentos pasados ao invocar o método
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) {
		// Crear unha instancia de XestionAlumnos
		XestionAlumnos xestion = new XestionAlumnos();
		try {

			// // Engadir alguns alumnos de exemplo
			// Alumno alumno1 = new Alumno("Ana", "33359917F");
			// alumno1.engadirMateria("Matemáticas");
			// alumno1.engadirMateria("Física");
			// alumno1.engadirMateria("Lingua Galega");
			// xestion.getAlumnos().add(alumno1);
			//
			// Alumno alumno2 = new Alumno("Pedro", "53351187G");
			// alumno2.engadirMateria("Química");
			// alumno2.engadirMateria("Biología");
			// alumno2.engadirMateria("Informática");
			// alumno2.engadirMateria("Física");
			// xestion.getAlumnos().add(alumno2);
			//
			// Alumno alumno3 = new Alumno("María", "43752187H");
			// alumno3.engadirMateria("Historia");
			// alumno3.engadirMateria("Literatura");
			// alumno3.engadirMateria("Física");
			// xestion.getAlumnos().add(alumno3);
			//
			// Alumno alumno4 = new Alumno("Repetido", "53351187G");
			// alumno4.engadirMateria("Química");
			// alumno4.engadirMateria("Biología");
			// xestion.getAlumnos().add(alumno4);
			Enderezo enderezo = new Enderezo("Rúa Nova", "15705", 5, "A Coruña");
			
			TreeSet<String> materias = (new TreeSet<String>());
			materias.add("Matemáticas");
			materias.add("Física");
			Alumno alumno5 = new Alumno("Lía", "33351677F", enderezo, materias);
			xestion.getAlumnos().add(alumno5);

			// Alumno alumno6 = new Alumno("Manuel", "33789675L", "Matemáticas", "Física", "Química",
			// "Bioloxía");
			// xestion.getAlumnos().add(alumno6);

			Alumno alumno7 = (Alumno) alumno5.clone();
			alumno7.setNome("Pedro");
			alumno7.setDni("45651754M");
			alumno7.engadirMateria("Química");
			alumno7.engadirMateria("Bioloxía");
			alumno7.getEnderezo().setRua("Rúa Vella");
			xestion.getAlumnos().add(alumno7);

//			Alumno alumno8 = alumno7;
//			alumno8.setNome("María");
//			xestion.getAlumnos().add(alumno8);
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		// Mostrar a lista de alumnos
		xestion.mostrarAlumnos();
		System.out.println(xestion.getAlumnos());
	}
}
