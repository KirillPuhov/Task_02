//По заданным двум клеткам на шахматной доске, определить (и напечатать), какие
//белые фигуры могут совершить ход из первой клетки во вторую. Клетки задаются в
//шахматной нотации, например d1 и b3 (из первой во вторую может пойти ферзь и
//слон). Для каждой фигуры написать функцию, проверяющую возможность хода
//данной фигурой. (Предполагается, что на шахматной доске нет других фигур,
//которые могу помешать совершить ход нужной фигурой.)

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите фигуру: ");
        String figure = scan.nextLine();

        System.out.print("Введите координаты фигуры (В шахматной нотации): ");
        String cell1 = scan.nextLine();

        System.out.print("Введите координаты клетки (В шахматной нотации): ");
        String cell2 = scan.nextLine();

        System.out.println(possibilityOfMove(figure, cell1, cell2));
    }

    private static boolean possibilityOfMove(String figure, String cell1, String cell2) {
        boolean result;

        int[] figureCoordinates = getCoordinates(cell1);
        int[] cellCoordinates = getCoordinates(cell2);

        switch (figure.toUpperCase()) {
            case "КОРОЛЬ":
                result = kingBehavior(figureCoordinates, cellCoordinates);
                break;

            case "ФЕРЗЬ":
                result = queenBehavior(figureCoordinates, cellCoordinates);
                break;

            case "ЛАДЬЯ":
                result = rookBehavior(figureCoordinates, cellCoordinates);
                break;

            case "СЛОН":
                result = bishopBehavior(figureCoordinates, cellCoordinates);
                break;

            case "КОНЬ":
                result = knightBehavior(figureCoordinates, cellCoordinates);
                break;

            case "ПЕШКА":
                result = pawnBehavior(figureCoordinates, cellCoordinates);
                break;

            default:
                result = false;
                break;
        }
        return result;
    }

    private static boolean kingBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        //Проверка клеток по горизонтали
        if(figureCoordinates[1] == cellCoordinates[1]){
            if ((figureCoordinates[0] + 1) == cellCoordinates[0] || (figureCoordinates[0] - 1) == cellCoordinates[0]){
                return true;
            }
        }


        //Проверка клеток по вертикали
        if(figureCoordinates[0] == cellCoordinates[0]){
            if((figureCoordinates[1] + 1) == cellCoordinates[1] || (figureCoordinates[1] - 1) == cellCoordinates[1]){
                return true;
            }
        }

        //Проверка верхней правой и нижней правой клетки
        if((figureCoordinates[0] + 1) == cellCoordinates[0]){
            if((figureCoordinates[1] + 1) == cellCoordinates[1] || (figureCoordinates[1] - 1) == cellCoordinates[1]){
                return true;
            }
        }

        //Проверка верхней левой и нижней левой клетки
        if((figureCoordinates[0] - 1) == cellCoordinates[0]){
            if((figureCoordinates[1] + 1) == cellCoordinates[1] || (figureCoordinates[1] - 1) == cellCoordinates[1]){
                return true;
            }
        }

        return false;
    }

    private static boolean queenBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        if (rookBehavior(figureCoordinates, cellCoordinates))
            return true;

        if (bishopBehavior(figureCoordinates, cellCoordinates))
            return true;

        return false;
    }

    private static boolean rookBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        if (figureCoordinates[0] == cellCoordinates[0] || figureCoordinates[1] == cellCoordinates[1])
            return true;
        else
            return false;
    }

    private static boolean bishopBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        if (Math.abs(cellCoordinates[0] - figureCoordinates[0]) == Math.abs(cellCoordinates[1] - figureCoordinates[1]))
            return true;

        return false;
    }

    private static boolean knightBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        //Проверка правой верхней и правой нижней клетки
        if ((figureCoordinates[0] + 2) == cellCoordinates[0]){
            if((figureCoordinates[1] + 1) == cellCoordinates[1] || (figureCoordinates[1] - 1) == cellCoordinates[1]){
                return true;
            }
        }

        //Проверка левой верхней и левой нижней клетки
        if ((figureCoordinates[0] - 2) == cellCoordinates[0]){
            if((figureCoordinates[1] + 1) == cellCoordinates[1] || (figureCoordinates[1] - 1) == cellCoordinates[1]){
                return true;
            }
        }

        //Проверка верхней правой и верхней левой клетки
        if ((figureCoordinates[1] + 2) == cellCoordinates[1]){
            if ((figureCoordinates[0] + 1) == cellCoordinates[0] || (figureCoordinates[0] - 1) == cellCoordinates[0]){
                return true;
            }
        }


        //Проверка нижней правой и нижней левой клетки
        if ((figureCoordinates[1] - 2) == cellCoordinates[1]){
            if ((figureCoordinates[0] + 1) == cellCoordinates[0] || (figureCoordinates[0] - 1) == cellCoordinates[0]){
                return true;
            }
        }

        return false;
    }

    private static boolean pawnBehavior(int[] figureCoordinates, int[] cellCoordinates) {

        if ((figureCoordinates[1] + 1) == cellCoordinates[1] && figureCoordinates[0] == cellCoordinates[0])
            return true;

        return false;
    }

    //| a | b | c | d | e | f | g | h |
    //---------------------------------
    //| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
    private static int[] getCoordinates(String cell) {

        int[] coordinates = new int[2];
        char[] letters = cell.toLowerCase().toCharArray();

        char x = (char)((int)letters[0] - 48);
        char y = letters[1];

        coordinates[0] = Character.getNumericValue(x);
        coordinates[1] = Character.getNumericValue(y);

        return coordinates;
    }
}
