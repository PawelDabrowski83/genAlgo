public class Gene {

    private RandomProvider randomProvider;
    char[] values;
    private float mutability;
    private float fitness;

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
