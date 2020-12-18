import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomProviderTest {

  @Test
  public void testArraySizeGeneWithMock() {
    // Given
    int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 5);
    RandomProvider randomProviderMock = mock(RandomProvider.class);

    char[] values = new char[5];

    values[0] = (char) boundedRandomValue;
    values[1] = (char) boundedRandomValue;
    values[2] = (char) boundedRandomValue;
    values[3] = (char) boundedRandomValue;
    values[4] = (char) boundedRandomValue;

    Gene gene = new Gene(randomProviderMock);

    when(randomProviderMock.getRandom()).thenReturn(values.length);

    // When
    int quantityOfNumbersInGene = gene.getValues().length;

    // Then
    Assert.assertEquals(5, quantityOfNumbersInGene);
  }

  @Test
  public void testValueUnderTheIndexGeneWithMock() {
    // Given
    int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 5);
    RandomProvider randomProviderMock = mock(RandomProvider.class);

    char[] values = new char[5];

    values[0] = (char) boundedRandomValue;
    values[1] = (char) boundedRandomValue;
    values[2] = (char) boundedRandomValue;
    values[3] = (char) boundedRandomValue;
    values[4] = (char) boundedRandomValue;

    Gene gene = new Gene(randomProviderMock);

    when(randomProviderMock.getRandom()).thenReturn((int) values[1]);

    // When
    int valueUnderTheIndex = gene.values[1];

    // then
    Assert.assertEquals(values[1], valueUnderTheIndex);
  }
}
