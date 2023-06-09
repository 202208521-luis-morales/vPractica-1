package vpractica_1;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class VPractica_1 {

    private static Scanner sn = new Scanner(System.in);
    private static String[] arrWords = null;
    private static char[] arrAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static RegistroDeJuego[] registroDeJuego = new RegistroDeJuego[20];
    private static GameHistory[] gameHistory = new GameHistory[20];
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
                    String username;
                    boolean goToPrincipalMenuIsSelected = false;

                    System.out.println("\n");
                    System.out.println(" *** ha elegido '1) Nueva Partida'");
                    System.out.println("Escriba su nombre de usuario");
                    username = sn.nextLine();

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
                                                                // Método: checar si la palabra ya existe
                                                                boolean wordAlreadyExists = false;
                                                                for (int i = 0; i < obtenerCasillasNoVacias(arrWords); i++) {
                                                                    if (arrWords[i].toLowerCase().equals(newWord.toLowerCase())) {
                                                                        wordAlreadyExists = true;
                                                                    }
                                                                }

                                                                if (!wordAlreadyExists) {
                                                                    arrWords[obtenerCasillasNoVacias(arrWords)] = newWord;
                                                                    isNewWordValid = true;
                                                                    System.out.println("Palabra agregada con éxito");
                                                                } else {
                                                                    System.out.println("Error: la palabra ya ha sido registrada");
                                                                }
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
                                                            boolean isNewWordValid = false;
                                                            while (!isNewWordValid) {
                                                                System.out.println("Escriba la nueva palabra:");
                                                                String newWord = sn.nextLine();

                                                                if (newWord.length() > 10 || newWord.length() < 5) {
                                                                    System.out.println("Error: la palabra debe de ser entre 5 y 10 caracteres. Ingrese la palabra de nuevo");
                                                                } else {
                                                                    // Método: checar si la palabra ya existe
                                                                    boolean wordAlreadyExists = false;
                                                                    for (int i = 0; i < obtenerCasillasNoVacias(arrWords); i++) {
                                                                        if (arrWords[i].toLowerCase().equals(newWord.toLowerCase()) && !newWord.toLowerCase().equals(arrWords[ind])) {
                                                                            wordAlreadyExists = true;
                                                                        }
                                                                    }

                                                                    if (!wordAlreadyExists) {
                                                                        arrWords[ind] = newWord;
                                                                        System.out.println("Palabra editada con éxito");
                                                                        isNewWordValid = true;
                                                                    } else {
                                                                        System.out.println("Error: la palabra ya ha sido registrada");
                                                                    }

                                                                }
                                                            }
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
                                GameHistory thisGame = new GameHistory(username, 20, 0, 0, exitIsSelected);
                                limpiarArrRegistroDeJuego();
                                int remainTrys = 3;

                                if (arrWords == null || obtenerCasillasNoVacias(arrWords) == 0) {
                                    System.out.println("\n");
                                    System.out.println("Error: primero debe de agregar palabras");
                                    System.out.println("\n");
                                } else {
                                    String[][] theMatrix = generateMatrix();

                                    System.out.println("X: " + theMatrix.length + "; Y: " + theMatrix[0].length);

                                    boolean isGameFinished = false;
                                    while (!isGameFinished) {
                                        int indexOfFoundWord = -1;
                                        int indexOfRegister = -1;
                                        int howManyWordsAreLeft = obtenerNumeroElementosFalse();

                                        // Método: imprimir la sopa de letras
                                        for (int j = 0; j < theMatrix[0].length; j++) {
                                            System.out.println("");
                                            for (int i = 0; i < theMatrix.length; i++) {
                                                System.out.print("|" + theMatrix[i][j]);
                                                if (i + 1 == theMatrix.length) {
                                                    System.out.print("|");
                                                }
                                            }
                                        }

                                        printWords();

                                        System.out.println("Cuantas palabras quedan: " + howManyWordsAreLeft);

                                        if (howManyWordsAreLeft > 0) {
                                            System.out.println("Escriba una palabra que vea en la sopa de letras");
                                            String insertedWord = sn.nextLine();

                                            // Método: checar si la palabra es parte de la sopa de letras, si lo es se procede con lo
                                            // debido
                                            boolean doesWordExists = false;
                                            for (int i = 0; i < obtenerCasillasNoVacias(arrWords); i++) {
                                                if (arrWords[i].toLowerCase().equals(insertedWord.toLowerCase()) && !doesWordExists) {
                                                    doesWordExists = true;
                                                    indexOfFoundWord = i;
                                                }
                                            }

                                            if (indexOfFoundWord >= 0) {
                                                // Método: buscar en el array registroJuego la palabra a cambiar por $$$ con el index encontrado

                                                for (int i = 0; i < obtenerCasillasNoVacias(registroDeJuego); i++) {
                                                    if (registroDeJuego[i].getIndiceDePalabra() == indexOfFoundWord && indexOfRegister < 0) {
                                                        indexOfRegister = i;
                                                    }
                                                }

                                                if (indexOfRegister >= 0) {
                                                    if (!registroDeJuego[indexOfRegister].isCompletada()) {
                                                        thisGame.setScore(thisGame.getScore() + arrWords[indexOfFoundWord].length());
                                                        thisGame.setAmountWordsFound(thisGame.getAmountWordsFound() + 1);
                                                        registroDeJuego[indexOfRegister].setCompletada(true);

                                                        System.out.println("\n");
                                                        System.out.println("/()/()/()/()/()/()");
                                                        System.out.println("/()/() CORRECTO! La palabra ingresada es correcta. Felicidades!");
                                                        System.out.println("/()/()/()/()/()/()");
                                                        System.out.println("\n");

                                                        // Método: modificar la matriz de la sopa de letras
                                                        if (registroDeJuego[indexOfRegister].isHorizontal) {
                                                            for (int i = registroDeJuego[indexOfRegister].getX(); i < (arrWords[indexOfFoundWord].length() + registroDeJuego[indexOfRegister].getX()); i++) {
                                                                theMatrix[i][registroDeJuego[indexOfRegister].getY()] = "$";
                                                            }
                                                        } else {
                                                            for (int i = registroDeJuego[indexOfRegister].getY(); i < (arrWords[indexOfFoundWord].length() + registroDeJuego[indexOfRegister].getY()); i++) {
                                                                theMatrix[registroDeJuego[indexOfRegister].getX()][i] = "$";
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("\n");
                                                        System.out.println("/()/()/()/()/()/()");
                                                        System.out.println("/()/() Esta palabra ya fue ingresada. No se tomará como incorrecto, vuelva a intentarlo");
                                                        System.out.println("/()/()/()/()/()/()");
                                                        System.out.println("\n");
                                                    }

                                                } else {
                                                    System.out.println("#ERROR 500");
                                                }
                                            } else {
                                                System.out.println("\n");
                                                System.out.println("/()/()/()/()/()/()");
                                                System.out.println("/()/() INCORRECTO! La palabra ingresada no es correcta. Intentelo de nuevo");
                                                System.out.println("/()/()/()/()/()/()");
                                                System.out.println("\n");
                                                remainTrys--;
                                                thisGame.setScore(thisGame.getScore() - 5);
                                                thisGame.setFails(thisGame.getFails() + 1);

                                                if (remainTrys == 0) {
                                                    isGameFinished = true;
                                                    System.out.println("No mas intentos. Has perdido :(");
                                                    thisGame.setWon(false);
                                                }
                                            }
                                        } else {
                                            isGameFinished = true;
                                            System.out.println("Felicidades!!! Has Ganado");
                                            thisGame.setWon(true);
                                        }

                                    }

                                    gameHistory[obtenerSiguienteIndexDeGameHistory()] = thisGame;
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

                    System.out.println("### HISTORIAL DE PARTIDAS");
                    System.out.println("");

                    if (obtenerCasillasNoVacias(gameHistory) == 0) {
                        System.out.println("no tienes partidas hechas :(. Ve a jugar y regresa mas tarde");
                    } else {
                        for (int i = 0; i < obtenerCasillasNoVacias(gameHistory); i++) {
                            System.out.println("+---------------");
                            System.out.println("| [" + i + "]");
                            System.out.println("| __ " + (gameHistory[i].isWon() ? "GANADO" : "PERDIDO"));
                            System.out.println("| Nombre de jugador: " + gameHistory[i].getUsername());
                            System.out.println("| Puntaje: " + gameHistory[i].getScore());
                            System.out.println("| Fallos: " + gameHistory[i].getFails());
                            System.out.println("| Cantidad de palabras encontradas: " + gameHistory[i].getAmountWordsFound());
                            System.out.println("+---------------");
                            System.out.println("");
                        }
                    }
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
                    System.out.println("---");
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

    private static void limpiarArrRegistroDeJuego() {
        registroDeJuego = new RegistroDeJuego[20];
    }

    private static <T> int obtenerCasillasNoVacias(T[] array) {
        int contador = 0;

        for (T elemento : array) {
            if (!Objects.isNull(elemento)) {
                contador++;
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
            dimensionsOfMatrix[1] = yMatrixLength * (obtenerTamanioElementoMasLargo(arrWords) + 2);
            wordFindMatrix = new String[dimensionsOfMatrix[0]][dimensionsOfMatrix[1]];

            int xCounter = 0;
            // PARA LAS PALABRAS HORIZONTALES
            for (int i = 0; i < dimensionsOfMatrix[1]; i++) { // Y
                for (int j = 4; j < dimensionsOfMatrix[0]; j++) { // X
                    if (i % 2 == 0 && obtenerElSiguienteIndexDeMatriz(wordsIsReady) >= 0 && currentXIndex < arrWords.length) { // La segunda condición básicamente chequea si hay palabras todavía para usar
                        if (j == 4) {
                            addingXWord = true;
                        }

                        if (j >= 4 && addingXWord == true) {
                            if ((arrWords[currentXIndex].length() - xCounter) == 0) {
                                addingXWord = false;
                                wordsIsReady[currentXIndex] = "y";
                                currentXIndex++;
                                xCounter = 0;
                                wordFindMatrix[j][i] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);

                                if ((currentXIndex + 1) % 4 == 0) {
                                    currentXIndex++;
                                }
                            } else {
                                if (xCounter == 0) {
                                    registroDeJuego[obtenerPrimeraCasillaDisponible(registroDeJuego)] = new RegistroDeJuego(j, i, currentXIndex, false, true);
                                }

                                wordFindMatrix[j][i] = Character.toString(arrWords[currentXIndex].charAt(xCounter)).toUpperCase();

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
                    // System.out.println(currentYIndex);
                    if (i % 2 == 0 && obtenerElSiguienteIndexDeMatriz(wordsIsReady) >= 0) {
                        if (addingYWord == true) {
                            if ((arrWords[currentYIndex].length() - yCounter) == 0) {
                                addingYWord = false;
                                wordsIsReady[currentYIndex] = "y";
                                currentYIndex = obtenerElSiguienteIndexDeMatriz(wordsIsReady);
                                yCounter = 0;
                                yNextPosition = j + 3;
                                wordFindMatrix[i][j] = Character.toString(arrAlphabet[random.nextInt(arrAlphabet.length)]);
                            } else {
                                if (yCounter == 0) {
                                    registroDeJuego[obtenerPrimeraCasillaDisponible(registroDeJuego)] = new RegistroDeJuego(i, j, currentYIndex, false, false);
                                }

                                wordFindMatrix[i][j] = Character.toString(arrWords[currentYIndex].charAt(yCounter)).toUpperCase();
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
            if (str != null) {
                int tamanioActual = str.length();
                if (tamanioActual > tamanioMasLargo) {
                    tamanioMasLargo = tamanioActual;
                }
            }
        }

        return tamanioMasLargo;
    }

    private static int obtenerNumeroElementosFalse() {
        int contador = 0;
        for (RegistroDeJuego elemento : registroDeJuego) {
            if (elemento != null) {
                if (!elemento.isCompletada()) {
                    contador++;
                }
            }

        }
        return contador;
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

    private static int obtenerSiguienteIndexDeGameHistory() {
        int nextIndex = -1;

        for (int i = 0; i < gameHistory.length; i++) {
            if (gameHistory[i] == null && nextIndex < 0) {
                nextIndex = i;
            }
        }

        return nextIndex;
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
        private boolean isHorizontal;

        public RegistroDeJuego(int x, int y, int indiceDePalabra, boolean completada, boolean isHorizontal) {
            this.x = x;
            this.y = y;
            this.indiceDePalabra = indiceDePalabra;
            this.completada = completada;
            this.isHorizontal = isHorizontal;
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

        public boolean isHorizontal() {
            return isHorizontal;
        }

        public void setIsHorizontal(boolean isHorizontal) {
            this.isHorizontal = isHorizontal;
        }

    }

    private static class GameHistory {

        private String username;
        private int score;
        private int fails;
        private int amountWordsFound;
        private boolean won;

        public GameHistory(String username, int score, int fails, int amountWordsFound, boolean won) {
            this.username = username;
            this.score = score;
            this.fails = fails;
            this.amountWordsFound = amountWordsFound;
            this.won = won;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getFails() {
            return fails;
        }

        public void setFails(int fails) {
            this.fails = fails;
        }

        public int getAmountWordsFound() {
            return amountWordsFound;
        }

        public void setAmountWordsFound(int amountWordsFound) {
            this.amountWordsFound = amountWordsFound;
        }

        public boolean isWon() {
            return won;
        }

        public void setWon(boolean won) {
            this.won = won;
        }

    }
}
