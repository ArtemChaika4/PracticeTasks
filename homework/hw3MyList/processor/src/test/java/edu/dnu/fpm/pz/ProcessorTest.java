package edu.dnu.fpm.pz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import java.util.List;

public class ProcessorTest {
    @Mock
    Consumer mockConsumer;
    @Mock
    Producer mockProducer;
    @InjectMocks
    Processor processor;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    ArgumentCaptor<String> valueCaptureArg = ArgumentCaptor.forClass(String.class);
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTestNotNullValue() {
        Mockito.doNothing().when(mockConsumer).consume(valueCaptureArg.capture());
        Mockito.when(mockProducer.produce()).thenReturn("new Value");
        processor.process();
        List<String> actualListArgs = valueCaptureArg.getAllValues();
        Assert.assertEquals(1, actualListArgs.size());
        Assert.assertEquals("new Value", actualListArgs.get(0));

        Mockito.verify(mockConsumer, Mockito.times(1)).consume("new Value");
        Mockito.verify(mockProducer, Mockito.times(1)).produce();
        Mockito.verifyNoMoreInteractions(mockConsumer);
        Mockito.verifyNoMoreInteractions(mockProducer);
    }

    @Test
    public void processTestNullValue(){
        expectedException.expect(IllegalStateException.class);
        Mockito.doNothing().when(mockConsumer).consume(Mockito.any(String.class));
        Mockito.when(mockProducer.produce()).thenReturn(null);
        processor.process();
    }

}