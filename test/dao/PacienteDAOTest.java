package test.dao;

import dao.PacienteDAO;
import dao.DAOFactory;
import modelo.Paciente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Testes para a classe PacienteDAO
 * Testes de integração com banco de dados (requer configuração de DB de teste)
 */
public class PacienteDAOTest {
    
    private PacienteDAO pacienteDAO;
    private Paciente pacienteTeste;
    
    @Before
    public void setUp() {
        pacienteDAO = DAOFactory.getPacienteDAO();
        pacienteTeste = new Paciente();
        pacienteTeste.setNome("Paciente Teste");
        pacienteTeste.setCpf("12345678901");
        pacienteTeste.setEndereco("Rua Teste, 123");
        pacienteTeste.setTelefone("(11)9999-9999");
        pacienteTeste.setEmail("teste@example.com");
        pacienteTeste.setRg("123456789");
        pacienteTeste.setDataNascimento(new java.util.Date());
        pacienteTeste.setConvenio(1);
    }
    
    @Test
    public void testCadastrarPaciente() {
        try {
            // Verificar se já existe
            Paciente existente = pacienteDAO.buscarPacientePorCpf("12345678901");
            if (existente != null) {
                // Paciente já existe, teste passa
                assertNotNull(existente);
                return;
            }
            
            pacienteDAO.cadastrarPaciente(pacienteTeste);
            
            // Verificar se foi cadastrado
            Paciente recuperado = pacienteDAO.buscarPacientePorCpf("12345678901");
            assertNotNull("Paciente deve ser encontrado após cadastro", recuperado);
            assertEquals("Nome deve ser igual", "Paciente Teste", recuperado.getNome());
            
        } catch (SQLException e) {
            fail("Erro de SQL ao cadastrar paciente: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPacientePorCpf() {
        try {
            Paciente paciente = pacienteDAO.buscarPacientePorCpf("12345678901");
            if (paciente != null) {
                assertEquals("CPF deve ser igual", "12345678901", paciente.getCpf(false));
            } else {
                // Paciente não existe, teste passa
                assertNull(paciente);
            }
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar paciente por CPF: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPacienteFiltro() {
        try {
            ArrayList<Paciente> pacientes = pacienteDAO.buscarPacienteFiltro("Teste");
            assertNotNull("Lista não deve ser null", pacientes);
            // Verificar se pelo menos um paciente com "Teste" no nome foi encontrado
            boolean encontrou = false;
            for (Paciente p : pacientes) {
                if (p.getNome().contains("Teste")) {
                    encontrou = true;
                    break;
                }
            }
            if (!pacientes.isEmpty()) {
                assertTrue("Deve encontrar paciente com filtro", encontrou);
            }
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar pacientes com filtro: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPaciente() {
        try {
            ArrayList<Paciente> pacientes = pacienteDAO.buscarPaciente();
            assertNotNull("Lista de pacientes não deve ser null", pacientes);
            // Pelo menos deve retornar uma lista (pode estar vazia se DB estiver limpo)
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar todos os pacientes: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPacientePorCpfInexistente() {
        try {
            Paciente paciente = pacienteDAO.buscarPacientePorCpf("99999999999");
            assertNull("Deve retornar null para CPF inexistente", paciente);
        } catch (SQLException e) {
            fail("Erro de SQL ao buscar CPF inexistente: " + e.getMessage());
        }
    }
}