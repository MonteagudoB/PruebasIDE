package tester;



/**
 * Clase que representa un alumno.
 */
class Enderezo implements Cloneable {
	private String rua;
	private String cp;
	private Integer num;
	private String provincia;
	
	
	/**
	 * @param rua
	 * @param cp
	 * @param num
	 * @param provincia
	 */
	public Enderezo(String rua, String cp, Integer num, String provincia) {
		this.rua = rua;
		this.cp = cp;
		this.num = num;
		this.provincia = provincia;
	}


	/**
	 * @return the rua
	 */
	public String getRua() {
		return rua;
	}


	/**
	 * @param rua the rua to set
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}


	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}


	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}


	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}


	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}


	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	@Override
	public String toString() {
		return "Enderezo [rua=" + rua + ", cp=" + cp + ", num=" + num + ", provincia=" + provincia + "]";
	}	
	
}