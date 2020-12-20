public class Gene {

    private RandomProvider randomProvider;
    private char[] values;
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
        int randomSize = randomProvider.getRandom();
        values = new char[randomSize];
    }

    private void assignValues() {
        for (int i = 0; i < values.length; i++) {
            values[i] = (char) randomProvider.getRandom();
        }
    }
}
