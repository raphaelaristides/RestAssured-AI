package com.projeto.api.listeners;

import com.projeto.api.core.Relatorio;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Listener para capturar eventos de execução dos testes
 * Responsável por finalizar o relatório após todos os testes
 */
public class TestListener extends RunListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void testRunFinished(Result result) {
        super.testRunFinished(result);
        logger.info(">>> Execução de testes finalizada");
        logger.info(">>> Testes executados: " + result.getRunCount());
        logger.info(">>> Testes falhados: " + result.getFailureCount());
        logger.info(">>> Testes ignorados: " + result.getIgnoreCount());
        
        // Finaliza o relatório
        Relatorio.finalizar();
    }

    @Override
    public void testStarted(Description description) {
        super.testStarted(description);
        logger.info(">>> Iniciando: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        super.testFinished(description);
        logger.info(">>> Finalizado: " + description.getMethodName());
    }

}