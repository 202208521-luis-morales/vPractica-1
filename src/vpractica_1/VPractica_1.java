package vpractica_1;

import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class VPractica_1 {

    private static Scanner sn = new Scanner(System.in);
    private static String[] arrWords = {"Puerta", "Paloo"};
    private static char[] arrAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static RegistroDeJuego[] registroDeJuego = new RegistroDeJuego[20];
    private static int[] dimensionsOfMatrix = new int[2];

    public static void main(String[] args) {
        boolean exitIsSelected = false;

        while (!exitIsSelected) {
            System.out.println("\n");
            System.out.println("##################");
            System.out.println("### MENU PRINCIPAL");
            System.out.println("##################");
            System.out.println("Seleccione una opción:");
            System.out.println(" 1) Nueva Partida");
            System.out.println(" 2) Historial de Partidas");
            System.out.println(" 3) Mostrar información de estudiante");
            System.out.println(" 4) Salir");

            String opt1Selected = sn.nextLine();

            switch (opt1Selected) {
                case "1" -> {
                    boolean goToPrincipalMenuIsSelected = false;

                    System.out.println("\n");
                    System.out.println(" *** ha elegido '1) Nueva Partida'");

                    while (!goToPrincipalMenuIsSelected) {
                        System.out.println("\n");
                        System.out.println("Seleccione una opción");
                        System.out.println(" 1) Menú Palabras");
                        System.out.println(" 2) Jugar");
                        System.out.println(" 3) Terminar Partida");

                        String op2Selected = sn.nextLine();

                        switch (op2Selected) {
                            case "1" -> {
                                boolean exit2IsSelected = false;

                                System.out.println("\n");
                                System.out.println(" *** ha elegido '1) Menu de palabras'");

                                while (!exit2IsSelected) {
                                    System.out.println("\n");
                                    System.out.println("############");
                                    System.out.println("### MENU DE PALABRAS ###");
                                    System.out.println("####");
                                    System.out.println("Seleccione una opción: ");
                                    System.out.println(" 1) Insertar");
                                    System.out.println(" 2) Modificar");
                                    System.out.println(" 3) Eliminar");
                                    System.out.println(" 4) Salir");

                                    String op3Selected = sn.nextLine();

                                    switch (op3Selected) {
                                        case "1" -> {
                                            String amountOfWords = (arrWords != null ? Integer.toString(arrWords.length) : null);
                                            System.out.println("\n");
                                            System.out.println(" *** ha elegido '1) Insertar'");

                                            if (arrWords == null) {
                                                System.out.println(" *** no ha escrito ninguna palabra, una vez ingrese la cantidad de palabras con las que desea jugar, ya no se podra modificar a menos que reinicie el programa");
                                                System.out.println("\n");
                                                System.out.println("Escriba la cantidad (en números) de palabras con las que desea jugar");
                                                amountOfWords = sn.nextLine();

                                                arrWords = new String[Integer.parseInt(amountOfWords)];
                                            }

                                            boolean exit3IsSelected = false;
                                            // int count = (arrWords == null ? 1 : arrWords.length);

                                            while (!exit3IsSelected) {
                                                printWords();
                                                if (obtenerCasillasNoVacias(arrWords) == arrWords.length) {
                                                    System.out.println("Error: ya se alcanzó el máximo de palabras, puedes eliminar palabras o modificarlas");
                                                    exit3IsSelected = true;
                                                } else {
                                                    System.out.println("Ingrese la nueva palabra");
                                                    System.out.println("Si quiere dejar de ingresar palabras y regresar al menú, no escriba nada y presione ENTER");

                                                    boolean isNewWordValid = false;
                                                    while (!isNewWordValid) {
                                                        String newWord = sn.nextLine();

                                                        if ("".equals(newWord)) {
                                                            exit3IsSelected = true;
                                                            isNewWordValid = true;
                                                        } else {
                                                            if (newWord.length() > 10 || newWord.length() < 5) {
                                                                System.out.println("Error: la palabra debe de ser entre 5 y 10 caracteres. Ingrese la palabra de nuevo");
                                                            } else {
                                                                arrWords[obtenerCasillasNoVacias(arrWords)] = newWord;
                                                                isNewWordValid = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }

                                        case "2" -> {
                                            System.out.println("\n");
                                            System.out.println(" *** ha elegido '2) Modificar'");

                                            if (obtenerCasillasNoVacias(arrWords) == 0 || arrWords == null) {
                                                System.out.println("\n");
                                                System.out.println("Error: primero, debes de ingresar palabras");
                                                System.out.println("\n");
                                            } else {
                                                boolean exit3IsSelected = false;
                                                // int count = (arrWords == null ? 1 : arrWords.length);
                                                while (!exit3IsSelected) {
                                                    printWords();
                                                    System.out.println("Elija el índice de la palabra que quiere modificar");
                                                    System.out.println("Si quiere dejar de modificar palabras y regresar al menú, no escriba nada y presione ENTER");
                                                    String sInd = sn.nextLine();

                                                    if ("".equals(sInd)) {
                                                        exit3IsSelected = true;
                                                    } else {
                                                        Integer ind = Integer.valueOf(sInd);
                                                        if (ind < 0 || ind >= arrWords.length) {
                                                            System.out.println("\n");
                                                            System.out.println("Error: el índice no está dentro del rango");
                                                            System.out.println("\n");
                                                        } else if (arrWords[ind] == null) {
                                                            System.out.println("\n");
                                                            System.out.println("Error: El indice señalado no tiene ninguna palabra, elija uno que sí tenga palabra");
                                                            System.out.println("\n");
                                                        } else {
                                                            System.out.println("Escriba la nueva palabra:");
                                                            arrWords[ind] = sn.nextLine();
                                                            System.out.println("Palabra editada con éxito");
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        case "3" -> {
                                            System.out.println("\n");
                                            System.out.println(" *** ha elegido '3) Eliminar'");

                                            if (obtenerCasillasNoVacias(arrWords) == 0 || arrWords == null) {
                                                System.out.println("\n");
                                                System.out.println("Error: primero, debes de ingresar palabras");
                                                System.out.println("\n");
                                            } else {
                                                boolean exit3IsSelected = false;
                                                while (!exit3IsSelected) {
                                                    printWords();
                                                    System.out.println("Elija el índice de la palabra que quiere eliminar");
                                                    System.out.println("Si quiere dejar de eliminar palabras y regresar al menú, no escriba nada y presione ENTER");
                                                    String sInd = sn.nextLine();

                                                    if ("".equals(sInd)) {
                                                        exit3IsSelected = true;
                                                    } else {
                                                        Integer ind = Integer.valueOf(sInd);
                                                        if (ind < 0 || ind >= arrWords.length) {
                                                            System.out.println("\n");
                                                            System.out.println("Error: el índice no está dentro del rango");
                                                            System.out.println("\n");
                                                        } else if (arrWords[ind] == null) {
                                                            System.out.println("\n");
                                                            System.out.println("Error: El indice señalado no tiene ninguna palabra, elija uno que sí tenga palabra");
                                                            System.out.println("\n");
                                                        } else {
                                                            borrarElementoCorrerDatos(ind);
                                                            System.out.println("Palabra borrada con éxito");
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        case "4" -> {
                                            exit2IsSelected = true;
                                        }
                                    }
                                }
                            }

                            case "2" -> {
                                System.out.println("\n");
                                System.out.println(" *** ha elegido '2) Jugar'");

                                if (arrWords == null || obtenerCasillasNoVacias(arrWords) == 0) {
                                    System.out.println("\n");
                                    System.out.println("Error: primero debe de agregar palabras");
                                    System.out.println("\n");
                                } else {
                                    String[][] theMatrix = generateMatrix();

                                    System.out.println("theMatrix en X: " + theMatrix.length);
                                    System.out.println("theMatrix en Y: " + theMatrix[0].length);
                                    // Método: imprimir la sopa de letras
                                    for (int i = 0; i < theMatrix.length; i++) {
                                        System.out.println("");
                                        for (int j = 0; j < theMatrix[0].length; j++) {
                                            //System.out.print("|" + theMatrix[i][j]);
                                            
                                            if (j - 1 == theMatrix[0].length) {
                                                System.out.print("|");
                                            }
                                        }
                                    }
                                }

                            }

                            case "3" -> {
                                goToPrincipalMenuIsSelected = true;
                            }
                        }
                    }
                }

                case "2" -> {
                    System.out.println("\n");
                    System.out.println(" *** ha elegido '2) Historial de partidas'");
                }

                case "3" -> {
                    System.out.println("\n");
                    System.out.println(" *** ha elegido '3) Mostrar información de estudiante'");
                    System.out.println("\n");
                    System.out.println("--- INFORMACIÓN ESTUDIANTE ---");
                    System.out.println("@USAC - Vacaciones Junio 2023");
                    System.out.println(" Nombre: Luis Rodrigo Morales Florián");
                    System.out.println(" Carnet: 202208521");
                    System.out.println(" Sección: P");
                }

                case "4" -> {
                    exitIsSelected = true;
                }
            }
        }

        System.out.println("\n");
        System.out.println("Adios!!!");
    }

    private static void printWords() {
        System.out.println("\n");
        System.out.println("+-------------------------------");
        System.out.println("| --- PALABRAS GUARDADAS ---");
        System.out.println("+-------------------------------");

        if (arrWords != null) {
            if (obtenerCasillasNoVacias(arrWords) > 0) {
                for (int i = 0; i < obtenerCasillasNoVacias(arrWords); i++) {
                    System.out.println("| " + i + ") " + arrWords[i]);
                }
            } else {
                System.out.println("| * no hay ninguna palabra guardada");
            }

            System.out.println("+-------------------------------");
            System.out.println("MÁXIMA CANTIDAD DE PALABRAS POSIBLE: " + arrWords.length);
            System.out.println("\n");
        }
    }

    private static int obtenerCasillasNoVacias(String[] array) {
        int contador = -1; // Inicializar contador

        if (array != null) {
            contador = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    contador++;
                }
            }
        }

        return contador;
    }

    private static int obtenerPrimeraCasillaDisponible(RegistroDeJuego[] array) {
        boolean isNull = false;
        int nullCell = -1;

        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null && isNull == false) {
                    isNull = true;
                    nullCell = i;
                }
            }
        }

        return nullCell;
    }

    private static void borrarElementoCorrerDatos(int indice) {
        if (indice < 0 || indice >= arrWords.length) {
            throw new IndexOutOfBoundsException("El índice está fuera del rango del array");
        }

        for (int i = indice; i < arrWords.length - 1; i++) {
            arrWords[i] = arrWords[i + 1];
        }

        arrWords[arrWords.length - 1] = null; // Establecer el último elemento como nulo
    }

    private static String[][] generateMatrix() {
        String[][] wordFindMatrix = null;
        boolean addingXWord = false;
        boolean addingYWord = false;
        int yMatrixLength = obtenerGrupo(obtenerCasillasNoVacias(arrWords));

        int currentXIndex = 0;
        // [type][x][y]
        Random random = new Random();

        if (arrWords != null) {
            String[] wordsIsReady = new String[obtenerCasillasNoVacias(arrWords)];

            for (int i = 0; i < wordsIsReady.length; i++) {
                wordsIsReady[i] = "n";
            }

            dimensionsOfMatrix[0] = obtenerTamanioElementoMasLargo(arrWords) + 6;
            dimensionsOfMatrix[1] = obtenerTamanioElementoMasLargo(arrWords) + (2 * yMatrixLength);
            wordFindMatrix = new String[dimensionsOfMatrix[0]][dimensionsOfMatrix[1]];

            int xCounter = 0;
            // PARA LAS PALABRAS HORIZONTALES
            for (int i = 0; i < dimensionsOfMatrix[1]; i++) { // Y
                for (int j = 4; j < dimensionsOfMatrix[0]; j++) { // X
                    if (i % 2 == 0 && obtenerElSiguienteIndexDeMatriz(wordsIsReady) >= 0) { // La segunda condición básicamente chequea si hay palabras todavía para usar
                        if (j == 4) {
                            addingXWord = true;
                        }

                        if (j >= 4 && addingXWord == true) {
                            if ((arrWords[obtenerElSiguienteIndexDeMatriz(wordsIsReady)].length() - xCounter) == 0) {
                                addingXWord = false;
                                wordsIsReady[currentXIndex] = "y";
                                currentXIndex++;
                                xCounter = 0;
                                wordFindMatrix[j][i] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);

                                if (currentXIndex % 4 == 0) {
                                    currentXIndex++;
                                }
                            } else {
                                if (xCounter == 0) {
                                    registroDeJuego[obtenerPrimeraCasillaDisponible(registroDeJuego)] = new RegistroDeJuego(j, i, currentXIndex, false);
                                }
                                System.out.println("A");
                                System.out.println("Coordenadas: x: " + j + "; j: " + i);
                                wordFindMatrix[j][i] = Character.toString(arrWords[obtenerElSiguienteIndexDeMatriz(wordsIsReady)].charAt(xCounter)).toUpperCase();
                                
                                
                                xCounter++;
                            }
                        } else {
                            wordFindMatrix[j][i] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);
                        }
                    } else {
                        wordFindMatrix[j][i] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);
                    }
                }
            }

            int currentYIndex = obtenerElSiguienteIndexDeMatriz(wordsIsReady);
            int yCounter = 0;
            int yNextPosition = 0;
            // PARA LAS PALABRAS VERTICALES
            addingYWord = true;
            for (int i = 0; i < 4; i++) { // X
                for (int j = 0; j < dimensionsOfMatrix[1]; j++) { // Y
                    if (i % 2 == 0 && obtenerElSiguienteIndexDeMatriz(wordsIsReady) >= 0) {
                        if (addingYWord == true) {
                            if ((arrWords[currentYIndex].length() - yCounter) == 0) {
                                addingYWord = false;
                                wordsIsReady[currentYIndex] = "y";
                                currentYIndex = obtenerElSiguienteIndexDeMatriz(wordsIsReady);
                                yCounter = 0;
                                yNextPosition = j + 3;
                            } else {
                                if (yCounter == 0) {
                                    registroDeJuego[obtenerPrimeraCasillaDisponible(registroDeJuego)] = new RegistroDeJuego(i, j, currentYIndex, false);
                                }

                                wordFindMatrix[i][j] = Character.toString(arrWords[currentYIndex].charAt(yCounter++)).toUpperCase();
                                yCounter++;
                            }
                        } else {
                            if ((j == yNextPosition) && currentYIndex % 10 != 0) {
                                addingYWord = true;
                            }
                            wordFindMatrix[i][j] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);
                        }
                    } else {
                        wordFindMatrix[i][j] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);
                    }
                }
            }
        }

        return wordFindMatrix;
    }

    private static int obtenerTamanioElementoMasLargo(String[] strings) {
        int tamanioMasLargo = 0;

        for (String str : strings) {
            int tamanioActual = str.length();
            if (tamanioActual > tamanioMasLargo) {
                tamanioMasLargo = tamanioActual;
            }
        }

        return tamanioMasLargo;
    }

    private static int obtenerElSiguienteIndexDeMatriz(String[] mat) {
        int isAvailable = -1;
        boolean isFound = false;

        for (int i = 0; i < mat.length; i++) {
            if ("n".equals(mat[i]) && isFound == false) {
                isAvailable = i;
                isFound = true;
            }
        }

        return isAvailable;
    }

    private static int obtenerGrupo(int numero) {
        int rango = 8; // Cantidad de números en cada grupo
        int grupo = (numero / rango) + 1; // Obtener el grupo correspondiente
        return grupo;
    }

    private static class RegistroDeJuego {

        private int x;
        private int y;
        private int indiceDePalabra;
        private boolean completada;

        public RegistroDeJuego(int x, int y, int indiceDePalabra, boolean completada) {
            this.x = x;
            this.y = y;
            this.indiceDePalabra = indiceDePalabra;
            this.completada = completada;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getIndiceDePalabra() {
            return indiceDePalabra;
        }

        public void setIndiceDePalabra(int indiceDePalabra) {
            this.indiceDePalabra = indiceDePalabra;
        }

        public boolean isCompletada() {
            return completada;
        }

        public void setCompletada(boolean completada) {
            this.completada = completada;
        }

    }
}
