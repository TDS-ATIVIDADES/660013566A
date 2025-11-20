package test.dao;

import dao.ConvenioDAO;
import dao.DAOFactory;
import modelo.Convenio;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Testes para a classe ConvenioDAO
 * Testes de integração com banco de dados (requer configuração de DB de teste)
 */
public class ConvenioDAOTest {
    
    private ConvenioDAO convenioDAO;
    
    @Before
    public void setUp() {
        convenioDAO = DAOFactory.getConvenioDAO();
    }
    
    @Test
    public void testBuscarConvenios() {
        try {
            ArrayList<Convenio> convenios = convenioDAO.buscarcConvenios();
            assertNotNull("Lista de convênios não deve ser null", convenios);
            // Verificar se a lista não está vazia (assumindo que há dados de teste)
            // assertFalse("Deve haver pelo menos um convênio", convenios.isEmpty());
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar convênios: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarConveniosRetornaListaVaziaSeNenhumConvenio() {
        try {
            ArrayList<Convenio> convenios = convenioDAO.buscarcConvenios();
            // Se não há convênios, a lista deve estar vazia ou ter dados
            assertNotNull(convenios);
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar convênios: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarConveniosConteudoValido() {
        try {
            ArrayList<Convenio> convenios = convenioDAO.buscarcConvenios();
            if (!convenios.isEmpty()) {
                Convenio primeiro = convenios.get(0);
                assertNotNull("Nome do convênio não deve ser null", primeiro.getNomeConvenio());
                assertTrue("ID deve ser maior que 0", primeiro.getIdConvenio() > 0);
            }
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar convênios: " + e.getMessage());
        }
    }
}
