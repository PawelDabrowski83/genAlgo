package com.facebook.genAlgo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneTest {

  RandomProvider randomProviderMock = mock(RandomProvider.class);

  @Test
  public void shouldGeneValuesLengthBeRandomized() {
    // given
    int lengthExpected = 113;
    when(randomProviderMock.getRandom(any())).thenReturn(lengthExpected);

    // when
    Gene actual = new Gene(randomProviderMock);

    // then
    assertEquals(lengthExpected, actual.getValues().length);
  }

  @Test
  public void shouldGeneValuesBeFilledRandomly() {
    // given
    int valueExpected = 113;
    when(randomProviderMock.getRandom(any())).thenReturn(valueExpected);

    // when
    Gene actual = new Gene(randomProviderMock);

    // then
    for (char value : actual.getValues()) {
        assertEquals(valueExpected, value);
    }
  }
}
