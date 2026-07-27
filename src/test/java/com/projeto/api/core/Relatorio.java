package com.projeto.api.core;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

/**
 * Classe responsável por gerar relatório HTML de testes
 * Utiliza Extent Reports para criar relatórios visuais
 * ThreadLocal para suportar execução paralela de testes
 */
public class Relatorio {

    private static final Logger logger = LogManager.getLogger(Relatorio.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String CAMINHO_PASTA = ConfigAPI.RELATORIO_PASTA;
    private static final String CAMINHO_ARQUIVO = ConfigAPI.RELATORIO_ARQUIVO;

    /**
     * Inicializa o Extent Reports
     * Chamado uma única vez ao criar o primeiro teste
     */
    public static synchronized void inicializar() {

    if (extent != null) {
        return;
    }

    logger.info(">>> Inicializando Extent Report");

    File pasta = new File(CAMINHO_PASTA);

    if (!pasta.exists()) {
        pasta.mkdirs();
    }

    ExtentSparkReporter spark =
            new ExtentSparkReporter(CAMINHO_ARQUIVO);

    spark.config().setDocumentTitle("Automação API");
    spark.config().setReportName("Resultados da Execução");
    spark.config().setTheme(Theme.DARK);

    extent = new ExtentReports();
    extent.attachReporter(spark);

    extent.setSystemInfo("Java", System.getProperty("java.version"));
    extent.setSystemInfo("OS", System.getProperty("os.name"));
    extent.setSystemInfo("Usuário", System.getProperty("user.name"));

    logger.info(">>> Extent inicializado.");
}

    /**
     * Cria um novo teste no relatório
     * 
     * @param nome Nome do teste
     */
    public static synchronized void criarTeste(String nome) {
    inicializar();
    ExtentTest extentTest = extent.createTest(nome);
    test.set(extentTest);
}

    /**
     * Obtém o teste atual da thread
     * 
     * @return ExtentTest atual
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Log de informação
     * 
     * @param msg Mensagem de log
     */
    public static void logInfo(String msg) {
        try {
            inicializar();
            ExtentTest t = getTest();
            if (t != null) {
                t.info(msg);
            } else {
                logger.warn(">>> Teste não inicializado, log não registrado");
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao fazer log info: " + e.getMessage(), e);
        }
    }

    /**
     * Log de sucesso
     * 
     * @param msg Mensagem de sucesso
     */
    public static void logPass(String msg) {
        try {
            inicializar();
            ExtentTest t = getTest();
            if (t != null) {
                t.pass(msg);
                logger.info(">>> [PASS] " + msg);
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao fazer log pass: " + e.getMessage(), e);
        }
    }

    /**
     * Log de falha
     * 
     * @param msg Mensagem de falha
     */
    public static void logFail(String msg) {
        try {
            inicializar();
            ExtentTest t = getTest();
            if (t != null) {
                t.fail(msg);
                logger.error(">>> [FAIL] " + msg);
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao fazer log fail: " + e.getMessage(), e);
        }
    }

    /**
     * Log de aviso
     * 
     * @param msg Mensagem de aviso
     */
    public static void logWarning(String msg) {
        try {
            inicializar();
            ExtentTest t = getTest();
            if (t != null) {
                t.warning(msg);
                logger.warn(">>> [WARNING] " + msg);
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao fazer log warning: " + e.getMessage(), e);
        }
    }

    /**
     * Finaliza e salva o relatório
     * Deve ser chamado ao final da execução de todos os testes
     */
    public static synchronized void finalizar() {
    try {
        if (extent != null) {
            logger.info(">>> Gravando relatório...");
            extent.flush();
            logger.info(">>> Relatório salvo.");
        }
    } catch (Exception ex) {
        logger.error(ex.getMessage(), ex);
    }

}

}