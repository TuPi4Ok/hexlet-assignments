package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    private String text;

    public ReversedSequence(String text) {
        this.text = new StringBuilder(text).reverse().toString();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int i) {
        return this.text.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.text.substring(i, i1);
    }

    @Override
    public String toString() {
        return this.text;
    }
}
// END
