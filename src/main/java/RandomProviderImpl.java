import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider {

    @Override
    public int getRandom(int rangeFrom, int rangeTo) {
        return ThreadLocalRandom.current().nextInt(rangeFrom, rangeTo);
    }
}
