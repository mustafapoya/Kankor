package net.golbarg.kankor.model;

public class ExamCorrectAnswerCount {
    private int math;
    private int natural;
    private int social;
    private int alsana;

    public ExamCorrectAnswerCount() {
        this(0, 0, 0, 0);
    }
    public ExamCorrectAnswerCount(int math, int natural, int social, int alsana) {
        this.math = math;
        this.natural = natural;
        this.social = social;
        this.alsana = alsana;
    }

    public void incrementMath() {
        math++;
    }

    public void incrementNatural() {
        natural++;
    }

    public void incrementSocial() {
        social++;
    }

    public void incrementAlsana() {
        alsana++;
    }

    public int getMath() {
        return math;
    }

    public int getNatural() {
        return natural;
    }

    public int getSocial() {
        return social;
    }

    public int getAlsana() {
        return alsana;
    }

    public int getTotalCorrect() {
        return getMath() + getNatural() + getSocial() + getAlsana();
    }

    public int getScore() {
        return getTotalCorrect() * 2;
    }

    @Override
    public String toString() {
        return "CorrectAnswerCount{" +
                "math=" + math +
                ", natural=" + natural +
                ", social=" + social +
                ", alsana=" + alsana +
                '}';
    }
}
