package Engine;

public enum Accion {
	buscar("Buscar"),
	pedir("Pedir"),
	recibir("Recibir"),
	abastecer("Abastecer");

	private final String text;

	/**
	 * @param text
	 */
	Accion(final String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
}
