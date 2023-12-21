package exercise;

public class ReversedSequence implements CharSequence{
    String str;

    public ReversedSequence(String str) {
        this.str = str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.substring(start, end);
    }

    @Override
    public String toString() {
        return new StringBuilder(str).reverse().toString();
    }
}
