package com.projeto.api.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável pela geração de relatórios HTML com Extent Reports
 * Implementa padrão Singleton para garantir uma única instância
 * Suporta múltiplos testes em um único relatório HTML
 */
public class Relatorio {

    private static final Logger logger = LogManager.getLogger(Relatorio.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    // Caminho fixo onde o relatório será salvo
    private static final String CAMINHO_PASTA = "target/relatorios";
    private static final String CAMINHO_ARQUIVO = CAMINHO_PASTA + "/Relatorio.html";

    /**
     * Inicializa o Extent Reports na primeira chamada
     * Cria a pasta de destino se não existir
     */
    private static void inicializar() {
        if (extent == null) {
            logger.info(">>> Inicializando Extent Report");
            
            try {
                File pasta = new File(CAMINHO_PASTA);
                if (!pasta.exists()) {
                    pasta.mkdirs();
                    logger.info(">>> Pasta de relatórios criada: " + CAMINHO_PASTA);
                }

                ExtentSparkReporter html = new ExtentSparkReporter(CAMINHO_ARQUIVO);
                html.config().setTheme(Theme.DARK);
                html.config().setDocumentTitle("Relatório de Testes - Rest Assured");
                html.config().setReportName("Automação de Testes API");
                html.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");

                extent = new ExtentReports();
                extent.attachReporter(html);
                extent.setSystemInfo("Ambiente", "Teste");
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Java Version", System.getProperty("java.version"));
                extent.setSystemInfo("Executado em", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

                logger.info(">>> Extent Report inicializado com sucesso");
            } catch (Exception e) {
                logger.error(">>> Erro ao inicializar Extent Report: " + e.getMessage(), e);
                throw new RuntimeException("Falha ao inicializar relatório", e);
            }
        }
    }

    /**
     * Cria um novo teste no relatório
     * 
     * @param nomeTeste Nome do teste a ser criado
     */
    public static void criarTeste(String nomeTeste) {
        inicializar();
        try {
            ExtentTest extentTest = extent.createTest(nomeTeste);
            test.set(extentTest);
            logger.info(">>> Teste criado: " + nomeTeste);
        } catch (Exception e) {
            logger.error(">>> Erro ao criar teste: " + e.getMessage(), e);
        }
    }

    /**
     * Obtém o teste atual da thread
     * 
     * @return ExtentTest do teste atual
     */
    public static ExtentTest getTest() {
        ExtentTest extentTest = test.get();
        if (extentTest == null) {
            logger.warn(">>> Teste não foi inicializado. Certifique-se de chamar criarTeste() primeiro.");
        }
        return extentTest;
    }

    /**
     * Loga uma mensagem de informação no relatório
     * 
     * @param mensagem Mensagem a ser logada
     */
    public static void logInfo(String mensagem) {
        try {
            inicializar();
            ExtentTest extentTest = getTest();
            if (extentTest != null) {
                extentTest.info(mensagem);
            }
            logger.info(">>> INFO: " + mensagem);
        } catch (Exception e) {
            logger.error(">>> Erro ao logar info: " + e.getMessage(), e);
        }
    }

    /**
     * Loga uma mensagem de sucesso (PASS) no relatório
     * 
     * @param mensagem Mensagem a ser logada
     */
    public static void logPass(String mensagem) {
        try {
            inicializar();
            ExtentTest extentTest = getTest();
            if (extentTest != null) {
                extentTest.pass(mensagem);
            }
            logger.info(">>> PASS: " + mensagem);
        } catch (Exception e) {
            logger.error(">>> Erro ao logar pass: " + e.getMessage(), e);
        }
    }

    /**
     * Loga uma mensagem de falha (FAIL) no relatório
     * 
     * @param mensagem Mensagem a ser logada
     */
    public static void logFail(String mensagem) {
        try {
            inicializar();
            ExtentTest extentTest = getTest();
            if (extentTest != null) {
                extentTest.fail(mensagem);
            }
            logger.error(">>> FAIL: " + mensagem);
        } catch (Exception e) {
            logger.error(">>> Erro ao logar fail: " + e.getMessage(), e);
        }
    }

    /**
     * Loga uma mensagem de aviso (WARNING) no relatório
     * 
     * @param mensagem Mensagem a ser logada
     */
    public static void logWarning(String mensagem) {
        try {
            inicializar();
            ExtentTest extentTest = getTest();
            if (extentTest != null) {
                extentTest.warning(mensagem);
            }
            logger.warn(">>> WARNING: " + mensagem);
        } catch (Exception e) {
            logger.error(">>> Erro ao logar warning: " + e.getMessage(), e);
        }
    }

    /**
     * Finaliza o relatório gerando o arquivo HTML
     * Deve ser chamado ao final de todos os testes
     */
    public static void finalizar() {
        try {
            if (extent != null) {
                extent.flush();
                logger.info(">>> Relatório salvo em: " + CAMINHO_ARQUIVO);
                System.out.println("\n✓ Relatório de testes gerado com sucesso em: " + CAMINHO_ARQUIVO);
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao finalizar relatório: " + e.getMessage(), e);
        } finally {
            limparThread();
        }
    }

    /**
     * Limpa a ThreadLocal para evitar memory leaks
     */
    private static void limparThread() {
        try {
            test.remove();
        } catch (Exception e) {
            logger.error(">>> Erro ao limpar thread: " + e.getMessage(), e);
        }
    }

    /**
     * Retorna o caminho completo do arquivo de relatório
     * 
     * @return Caminho do arquivo HTML
     */
    public static String getCaminhoRelatorio() {
        return CAMINHO_ARQUIVO;
    }

}