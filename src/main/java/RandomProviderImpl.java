import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider {

    private int rangeFrom;
    private int rangeTo;

    public RandomProviderImpl(int rangeFrom, int rangeTo) {
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    @Override
    public int getRandom() {
        return ThreadLocalRandom.current().nextInt(rangeFrom, rangeTo);
    }
}
