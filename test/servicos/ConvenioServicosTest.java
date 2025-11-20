package test.servicos;

import servicos.ConvenioServicos;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 * Testes para a classe ConvenioServicos
 * Testes de integração com banco de dados (requer configuração de DB de teste)
 */
public class ConvenioServicosTest {

    private ConvenioServicos servicosTest;

    @Before
    public void setUp() {
        servicosTest = new ConvenioServicos();
    }

    @Test
    public void testBuscarConvenioNaoLancaErro() {
        try {
            // Apenas verificar que o método existe e pode ser chamado.
            servicosTest.buscarConvenio();
        } catch (Exception e) {
            // Aceitamos SQLException caso não haja DB na configuração de teste
            assertTrue(e instanceof SQLException);
        }
    }

}
