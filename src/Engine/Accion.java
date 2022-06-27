package Engine;

public enum Accion {
	buscar(Message.Buscar),
	pedir(Message.Pedir),
	recibir(Message.Recibir),
	abastecer(Message.Abastecer);

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
