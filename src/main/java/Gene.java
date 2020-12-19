public class Gene {

    private RandomProvider randomProvider;
    char[] values;

    public Gene(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
        generateValuesSize();
        assignValues();
    }

    public char[] getValues() {
        return values;
    }

    private void generateValuesSize() {
    }

    private void assignValues() {

    }
}
