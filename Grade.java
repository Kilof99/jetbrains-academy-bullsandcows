package bullscows;

public class Grade {
    int cows;
    int bulls;

    public Grade() {
        this.cows = 0;
        this.bulls = 0;
    }

    public void addCow(){
        this.cows++;
    }

    public void addBull(){
        this.bulls++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grade: ");
        if (this.cows == 0 && this.bulls == 0) {
            sb.append("None.");
        } else {
            if (this.bulls > 0) {
                sb.append(this.bulls)
                        .append(" bulls");
                sb.append(this.cows > 0 ? " and " : ".");
            }
            if (this.cows > 0) {
                sb.append(this.cows)
                        .append(" cows.");
            }
        }

        return sb.toString();
    }
}
