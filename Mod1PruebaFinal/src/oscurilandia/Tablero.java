package oscurilandia;

import java.util.ArrayList;
import java.util.List;

import oscurilandia.Caguano;
import oscurilandia.Carro;
import oscurilandia.Huevo;
import oscurilandia.Kromi;
import oscurilandia.Trupalla;
import oscurilandia.Ubicacion;

// Esta clase genera un tablero de tamaÃ±o con tres tipos de vehÃ­culos: Kromi, Caguano 
// y Trupalla. AdemÃ¡s, se implementan funciones que manejan el comportamiento del juego de acorde 
// a los requerimientos solicitados.

public class Tablero {

	public final static int FILAS = 15;
	public final static int COLUMNAS = 15;

	// Cantidad de espacios verticales que ocuparÃ¡ el vehÃ­culo Kromi
	
	private final int SIZE_KROMI = 3;
	
	// Cantidad de espacios horizontales que ocuparÃ¡ el vehÃ­culo Caguano
	
	private final int SIZE_CAGUANO = 2;
	
	// Cantidad de espacion que ocuparÃ¡ el vehÃ­culo Trupalla
	
	private final int SIZE_TRUPALLA = 1;

	// Cantidad de Kromis que aparecerÃ¡n en el tablero
	
	private final int QTY_KROMI = 3;
	// Cantidad de Caguanos que aparecerÃ¡n en el tablero
	
	private final int QTY_CAGUANO = 5;
	// Cantidad de Trupallas que aparecerÃ¡n en el tablero
	
	private final int QTY_TRUPALLA = 10;

	// Puntaje que se obtendrÃ¡ cuando se le atine a una Kromi
	
	private final int SCORE_KROMI = 3;
	// Puntaje que se obtendrÃ¡ cuando se le atine a un Caguano
	
	private final int SCORE_CAGUANO = 2;
	// Puntaje que se obtendrÃ¡ cuando se le atine a una Trupalla
	
	private final int SCORE_TRUPALLA = 1;

	// Puntaje adicional que se obtendrÃ¡ cuando se elimine una Kromi por completo
	
	private final int ADICIONAL_KROMI = 10;
	// Puntaje adicional que se obtendrÃ¡ cuando se elimine un Caguano por completo
	
	private final int ADICIONAL_CAGUANO = 7;

	// Atributos

	// Variable para indicar si se muestran o no el contenido de las celdas
	
	private boolean juego;
	
	// Donde se almacenan las etiquetas del juego (K, C, T, H)
	
	private String[][] tablero;
	
	// Donde se almacena el puntaje obtenido por juego
	
	private int puntaje;

	// Listado de Huevos arrojados por el usuario
	
	private List<Huevo> huevos;
	
	// Listado de Carros generados por la aplicaciÃ³n
	
	private List<Carro> carros;

	// Constructores

	public Tablero(boolean juego) {
		this.juego = juego;
		this.tablero = new String[FILAS][COLUMNAS];
		this.puntaje = 0;

		this.huevos = new ArrayList<>();
		this.carros = new ArrayList<>();

		// Insertar Kromi
		
		for (int i = 0; i < QTY_KROMI; i++)
			insertarKromi();

		// Insertar Caguano
		
		for (int i = 0; i < QTY_CAGUANO; i++)
			insertarCaguano();

		// Insertar Trupalla
		
		for (int i = 0; i < QTY_TRUPALLA; i++)
			insertarTrupalla();
	}

	// Metodos
	
	// Agrega una Kromi al tablero en una celda que estÃ© desocupada, verificando que
	// las dos celdas que le siguen hacia abajo estÃ©n disponibles

	private void insertarKromi() {
		int fila;
		int columna;

		do {
			fila = (int) (Math.random() * (FILAS - (SIZE_KROMI + 1)));
			columna = (int) (Math.random() * COLUMNAS);
		} while (!isViable(fila, columna, true));

		Kromi kromi = new Kromi();
		for (int i = 0; i < SIZE_KROMI; i++) {
			kromi.addUbicacion(new Ubicacion(fila + i, columna));
			this.tablero[fila + i][columna] = "K";
		}

		carros.add(kromi);
	}

	 // Agrega un Caguano al tablero en una fila,columna que estÃ©n desocupadas.
	 // AdemÃ¡s verifica que la celda de la derecha estÃ© desocupada y dentro del rango permitido.

	private void insertarCaguano() {
		int fila;
		int columna;

		do {
			fila = (int) (Math.random() * FILAS);
			columna = (int) (Math.random() * (COLUMNAS - SIZE_CAGUANO));
		} while (!isViable(fila, columna, true));

		Caguano caguano = new Caguano();
		for (int i = 0; i < SIZE_CAGUANO; i++) {
			caguano.addUbicacion(new Ubicacion(fila, columna + i));
			this.tablero[fila][columna + i] = "C";
		}

		carros.add(caguano);
	}

	 // Agrega una trupalla al tablero en una fila,columna que estÃ©n desocupadas

	private void insertarTrupalla() {
		int fila;
		int columna;

		do {
			fila = (int) (Math.random() * FILAS);
			columna = (int) (Math.random() * (COLUMNAS - SIZE_TRUPALLA));
		} while (!isViable(fila, columna, true));

		Trupalla trupalla = new Trupalla();
		for (int i = 0; i < SIZE_TRUPALLA; i++) {
			trupalla.addUbicacion(new Ubicacion(fila, columna + i));
			this.tablero[fila][columna + i] = "T";
		}

		carros.add(trupalla);

	}


	 // Permite verificar que la celda definida por fila y columna estÃ© desocupada
	 // @param fila -->fila que se quiere verificar
	 // @param columna --> columna que se quiere verificar
	 // @return verdadero o falso segÃºn la disponibilidad

	private boolean isViable(int fila, int columna) {
		if (tablero[fila][columna] != null)
			return false;

		return true;
	}

	 // Verifica que la celda definida por el programa, ademÃ¡s de la derecha para el
	 // Caguano y las dos siguientes para la Kromi estÃ©n disponibles para su uso.
	 // @param fila --> fila que se quiere verificar
	 // @param columna --> columna que se quiere verificar
	 // @param horizontal Verdadero si vehÃ­culo == Caguano, falso si vehÃ­culo == Kromi
	 // @return verdadero o falso segÃºn corresponda

	private boolean isViable(int fila, int columna, boolean horizontal) {
		if (horizontal) {
			for (int i = 0; i < SIZE_CAGUANO; i++) {
				if (!isViable(fila, columna + i))
					return false;
			}
		} else {
			for (int i = 0; i < SIZE_KROMI; i++) {
				if (!isViable(fila + i, columna))
					return false;
			}
		}

		return true;
	}

	 // Arroja un huevo al tablero en la celda de coordenadas [fila, columna]
	 // verificando que estÃ© o no ocupada y actuando segÃºn corresponda

	public String arrojarHuevo(int fila, int columna) {
		int puntaje = 0;
		String celda = tablero[fila][columna];

		if (celda != null) {
			if (!celda.equals("H")) {

				if (celda.equals("K"))
					puntaje += SCORE_KROMI;
				else if (celda.equals("C"))
					puntaje += SCORE_CAGUANO;
				else
					puntaje += SCORE_TRUPALLA;

			}
		}

		Huevo huevo = new Huevo(puntaje, new Ubicacion(fila, columna));
		huevos.add(huevo);

		Carro carro = existeCarro(fila, columna);
		if (carro != null && eliminado(carro)) {
			String etiqueta = tablero[fila][columna];

			if (etiqueta != null) {
				if (etiqueta.equals("K"))
					puntaje += ADICIONAL_KROMI;
				else if (etiqueta.equals("C"))
					puntaje += ADICIONAL_CAGUANO;
			}
		}

		tablero[fila][columna] = "H";
		this.puntaje += puntaje;
		return celda;
	}

	 // Recorre la lista carros en bÃºsqueda de la celda con coordenadas [fila,
	 // columna]. Si la ubicaciÃ³n estÃ¡ dentro del carro en cuestiÃ³n, devuelve el
	 // carro, sino, devuelve nulo.
	 // @param fila --> fila
	 // @param columna --> columna
	 // @return Carro encontrado o nulo

	private Carro existeCarro(int fila, int columna) {
		for (Carro carro : this.carros) {
			List<Ubicacion> ubicaciones = carro.getUbicaciones();

			for (Ubicacion u : ubicaciones) {
				if (u.getFila() == fila && u.getColumna() == columna)
					return carro;
			}
		}

		return null;
	}

	 // Compara el carro con la lista de huevo arrojados por el usuario. Si todas las
	 // posiciones del carro se vieron afectadas por el atentado, entonces devuelve
	 // verdadero. En caso contrario, devuelve falso.
	 // @param carro Carro a analizar
	 // @return verdadero o falso segÃºn sea el caso

	private boolean eliminado(Carro carro) {
		List<Ubicacion> ubicaciones = carro.getUbicaciones();
		int posicionesAtacadas = 0;

		for (Ubicacion ubicacion : ubicaciones) {
			for (Huevo huevo : this.huevos) {
				Ubicacion unHuevo = huevo.getUbicacion();

				if (ubicacion.getFila() == unHuevo.getFila() && ubicacion.getColumna() == unHuevo.getColumna())
					posicionesAtacadas++;
			}
		}

		if (posicionesAtacadas == ubicaciones.size())
			return true;

		return false;
	}

	 // Ordena la cadena
	 // @param separador --> Separador que se ocuparÃ¡ entre celdas
	 // @param tope --> Tope que se usarÃ¡ para el Ãºltimo valor
	 // @return Espacio formateado

	private String ordenalacadena(String separador, String tope) {
		String espacio = "";

		for (int i = 0; i < COLUMNAS; i++) {
			espacio += "â•�â•�â•�";

			if (i == (COLUMNAS - 1))
				espacio += tope + "\n";
			else
				espacio += separador;
		}

		return espacio;
	}

	 // Devuelve el puntaje obtenido por el usuario
	 // @return puntaje

	public int getPuntaje() {
		return this.puntaje;
	}

	 // Devuelve el listado de carros generados por la aplicacion
	 // @return carros

	public List<Carro> getCarros() {
		return this.carros;
	}

	 // Devuelve el listado de huevos lanzados por el usuario
	 // @return huevos

	public List<Huevo> getHuevos() {
		return this.huevos;
	}

	 // Cambia el valor de la variable juego
	 // @param juego nuevo valor

	public void setjuego(boolean juego) {
		this.juego = juego;
	}

	// Muestra de forma ordenada el tablero del juego actual

	public String toString() {
		String espacio = "â•”" + ordenalacadena("â•�", "â•—");
		espacio += String.format("â•‘ Oscurilandia %" + ((COLUMNAS * 4) - 30) + "s Puntaje: %4d â•‘\n", "", puntaje);
		espacio += "â• " + ordenalacadena("â•¦", "â•£");

		for (int i = 0; i < FILAS; i++) {
			espacio += "â•‘";

			for (int j = 0; j < COLUMNAS; j++) {
				espacio += " ";

				if (juego) {
					if (tablero[i][j] != null)
						espacio += tablero[i][j];
					else
						espacio += " ";
				} else {
					if (tablero[i][j] != null && tablero[i][j].equals("H"))
						espacio += "H";
					else
						espacio += " ";
				}

				espacio += " â•‘";
			}

			if (i != (FILAS - 1))
				espacio += "\nâ• " + ordenalacadena("â•¬", "â•£");
		}

		espacio += "\nâ•š" + ordenalacadena("â•©", "â•�");

		return espacio;
	}

}