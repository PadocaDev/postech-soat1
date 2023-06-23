package com.padocadev;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestContainerConfiguracao.class)
public abstract class TestContainerTesteDeIntegracao {
}
