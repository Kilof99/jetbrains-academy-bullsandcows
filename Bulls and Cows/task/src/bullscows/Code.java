package bullscows;

import java.util.Arrays;

public class Code {
    int[] digits;

    public Code(String number) {
        digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = Character.getNumericValue(number.toCharArray()[i]);
        }
    }

    public Grade resolve(String answer) {
        Grade grade = new Grade();
        char[] answerArray = answer.toCharArray();
        for (int i = 0; i < answerArray.length; i++) {
            int charValue = Character.getNumericValue(answerArray[i]);
            if(charValue == digits[i]) {
                grade.addBull();
            } else if (this.isCow(charValue)) {
                grade.addCow();
            }
        }

        return grade;
    }

    private boolean isCow(int n) {
        for (int digit :
                this.digits) {
            if (digit == n) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int digit :
                digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
}
