package com.projeto.api.listeners;

import com.projeto.api.core.Relatorio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.Failure;

/**
 * Listener customizado para capturar eventos dos testes
 * Integrado com RelatorioHTML para geração de relatório
 */
public class TestListener extends RunListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    /**
     * Chamado quando um teste inicia
     * @param description Descrição do teste
     */
    @Override
    public void testStarted(Description description) {
        try {
            String nometeste = description.getMethodName();
            System.out.println("\n" + "=".repeat(80));
            System.out.println(">>> [LISTENER] Iniciando teste: " + nometeste);
            System.out.println("=".repeat(80));
            
            logger.info(">>> Teste iniciado: " + nometeste);
            Relatorio.criarTeste(nometeste);
            Relatorio.logInfo("Teste iniciado automaticamente pelo Listener");
            
        } catch (Exception e) {
            logger.error(">>> Erro ao iniciar listener de teste: " + e.getMessage(), e);
        }
    }

    /**
     * Chamado quando um teste termina com sucesso
     * @param description Descrição do teste
     */
    @Override
    public void testFinished(Description description) {
        try {
            String nometeste = description.getMethodName();
            logger.info(">>> Teste finalizado com sucesso: " + nometeste);
            Relatorio.logInfo("Teste finalizado com sucesso");
            
        } catch (Exception e) {
            logger.error(">>> Erro ao finalizar listener de teste: " + e.getMessage(), e);
        }
    }

    /**
     * Chamado quando um teste falha
     * @param failure Informações da falha
     */
    @Override
    public void testFailure(Failure failure) {
        try {
            String nometeste = failure.getDescription().getMethodName();
            String mensagemErro = failure.getMessage();
            String stackTrace = failure.getTrace();
            
            System.out.println("\n" + "!".repeat(80));
            System.out.println(">>> [LISTENER] TESTE FALHOU: " + nometeste);
            System.out.println(">>> Erro: " + mensagemErro);
            System.out.println("!".repeat(80) + "\n");
            
            logger.error(">>> Teste FALHOU: " + nometeste);
            logger.error(">>> Mensagem de erro: " + mensagemErro);
            logger.error(">>> Stack trace: " + stackTrace);
            
            Relatorio.logFail("TESTE FALHOU: " + mensagemErro);
            Relatorio.logFail("Stack Trace: " + stackTrace);
            
        } catch (Exception e) {
            logger.error(">>> Erro ao processar falha do teste: " + e.getMessage(), e);
        }
    }

    /**
     * Chamado quando um teste é ignorado/pulado
     * @param description Descrição do teste
     */
    @Override
    public void testIgnored(Description description) {
        try {
            String nometeste = description.getMethodName();
            System.out.println(">>> [LISTENER] Teste ignorado: " + nometeste);
            
            logger.warn(">>> Teste ignorado: " + nometeste);
            Relatorio.logWarning("Teste ignorado/pulado");
            
        } catch (Exception e) {
            logger.error(">>> Erro ao processar teste ignorado: " + e.getMessage(), e);
        }
    }

    /**
     * Chamado quando um teste é assumido como verdadeiro mas falha
     * @param failure Informações da falha de assunção
     */
    @Override
    public void testAssumptionFailure(Failure failure) {
        try {
            String nometeste = failure.getDescription().getMethodName();
            String mensagemErro = failure.getMessage();
            
            System.out.println(">>> [LISTENER] Assunção falhou para: " + nometeste);
            logger.warn(">>> Assunção falhou para: " + nometeste);
            logger.warn(">>> Mensagem: " + mensagemErro);
            
            Relatorio.logWarning("Assunção falhou: " + mensagemErro);
            
        } catch (Exception e) {
            logger.error(">>> Erro ao processar falha de assunção: " + e.getMessage(), e);
        }
    }
}
