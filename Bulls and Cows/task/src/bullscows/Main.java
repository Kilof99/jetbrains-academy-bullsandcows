package bullscows;

import java.util.*;

public class Main {
    private static final List<String> digits = new ArrayList<>(List.of(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    ));
    private static List<String> availableDigits;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the code's length and the range of symbols:");
        int size, range;
        try {
            size = scanner.nextInt();
            range = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: one of the inputs was not a number");
            return;
        }

        Code code;
        if (range > 36) {
            System.out.printf(
                    "Error: can't generate a secret number with %d allowed characters because there aren't enough unique digits.",
                    range);
            return;
        } else if (size == 0) {
            System.out.println("Error: can't generate code with length 0");
            return;
        } else if (size > range) {
            System.out.printf(
                    "Error: can't generate a secret number with the length of %d because there aren't enough unique digits.",
                    size);
            return;
        } else {
            code = new Code(generateCode(size, range));
            System.out.printf("The secret is prepared: %s (%s).",
                    "*".repeat(size),
                    getRanges(range));
        }
        System.out.println("Let us begin.");
        play(code);

    }

    private static String getRanges(int range) {
        StringBuilder sb = new StringBuilder();
        sb.append("0-");
        if (range < 11) {
            sb.append(range - 1);
        } else {
            sb.append("9, a-").append(availableDigits.get(availableDigits.size() - 1));
        }
        return sb.toString();
    }


    private static String generateCode(int size, int allowedChars) {
        Random random = new Random();

        availableDigits = digits.subList(0, allowedChars);


        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < size) {
            int index = random.nextInt(availableDigits.size());
            String digit = availableDigits.get(index);
            availableDigits.remove(digit);
            sb.append(digit);
            i++;
        }
        return sb.toString();
    }

    public static void play(Code code) {
        Scanner scanner = new Scanner(System.in);
        int turn = 1;
        Grade g;
        do {
            System.out.printf("\nTurn %d. Answer:\n", turn);
            String answer = scanner.next();
            g = code.resolve(answer);
            System.out.println(g.toString());
            turn++;
        } while (g.bulls < code.digits.length);
        System.out.printf("Congrats! The secret code is %s.\n", code.toString());
//
//        String answer  = scanner.next();
//        System.out.println(code.resolve(answer));
//        System.out.printf("The secret code is %s.\n", code.toString());

    }

}
