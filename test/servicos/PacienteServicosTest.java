package test.servicos;

import servicos.PacienteServicos;
import modelo.Paciente;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.*;

/**
 * Testes para a classe PacienteServicos
 * Testes de validação de dados sem integração com banco de dados
 */

// Interfaces para categorias JUnit
interface ValidacaoCamposObrigatorios {}
interface ValidacaoFormatos {}
interface ValidacaoRegrasNegocio {}
interface VisualizacaoPacientes {}

public class PacienteServicosTest {
    
    private PacienteServicos servicosTest;
    private Paciente paciente;
    
    @Before
    public void setUp() {
        servicosTest = new PacienteServicos();
        paciente = new Paciente();
    }
    
    // RF02 - Campo obrigatório: Nome
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioNome() {
        paciente.setNome("");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para nome vazio");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar nome", e.getMessage().toLowerCase().contains("nome"));
        }
    }
    
    // RF02 - Campo obrigatório: CPF
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioCpf() {
        paciente.setNome("João Silva");
        paciente.setCpf("");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para CPF vazio");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar CPF", e.getMessage().toLowerCase().contains("cpf"));
        }
    }
    
    // RF02 - Campo obrigatório: Endereço
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioEndereco() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para endereço vazio");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar endereço", e.getMessage().toLowerCase().contains("endereço"));
        }
    }
    
    // RF02 - Campo obrigatório: Telefone
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioTelefone() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para telefone vazio");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar telefone", e.getMessage().toLowerCase().contains("telefone"));
        }
    }
    
    // RF02 - Campo obrigatório: Data de Nascimento
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioDataNascimento() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(null);
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para data de nascimento vazia");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar data", e.getMessage().toLowerCase().contains("data"));
        }
    }
    
    // RF02 - Campo obrigatório: Convênio
    @Test
    @Category(ValidacaoCamposObrigatorios.class)
    public void testRF02CampoObrigatorioConvenio() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(0);  // ID 0 é inválido
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para convênio inválido");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar Convênio", e.getMessage().toLowerCase().contains("convênio"));
        }
    }
    
    // RN01 - Nome máximo 55 caracteres
    @Test
    @Category(ValidacaoRegrasNegocio.class)
    public void testRN01NomeMaximo55Caracteres() {
        String nomeLongo = "João Silva Santos Pereira Oliveira Costa Fernandes".repeat(2); // > 55 chars
        
        paciente.setNome(nomeLongo);
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para nome com mais de 55 caracteres");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar nome e limite de caracteres", 
                      e.getMessage().toLowerCase().contains("nome") && 
                      e.getMessage().contains("55"));
        }
    }
    
    // RF03 - CPF formato válido (11 dígitos)
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRF03CpfFormatoValido11Digitos() {
        paciente.setNome("João Silva");
        paciente.setCpf("123456789");  // Menos de 11 dígitos
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para CPF com menos de 11 dígitos");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar CPF", e.getMessage().toLowerCase().contains("cpf"));
        }
    }
    
    // RN04 - Endereço máximo 200 caracteres
    @Test
    @Category(ValidacaoRegrasNegocio.class)
    public void testRN04EnderecoMaximo200Caracteres() {
        String endereco = "Rua Muito Longa Com Mais de Duzentos Caracteres ".repeat(5);  // gera string > 200 chars
        
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco(endereco);
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para endereço com mais de 200 caracteres");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar endereço", e.getMessage().toLowerCase().contains("endereço"));
        }
    }
    
    // RN05 - Telefone formato (xx)xxxx-xxxx
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRN05TelefoneFormatoValido() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("11999999999");  // 11 dígitos, formato inválido
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para telefone com formato inválido");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar telefone", e.getMessage().toLowerCase().contains("telefone"));
        }
    }
    
    // RN03 - Data nascimento formato DD/MM/AAAA (não futura)
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRN03DataNascimentoNaoFutura() {
        // Criar uma data futura
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.YEAR, 1); // Adiciona 1 ano
        java.util.Date dataFutura = cal.getTime();
        
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(dataFutura);
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para data de nascimento futura");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar data futura", 
                      e.getMessage().toLowerCase().contains("futura"));
        }
    }
    
    // RN06 - E-mail opcional mas válido quando informado
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRN06EmailOpcionalMasValido() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("emailinvalido");  // sem @ e domínio
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            fail("Deve lançar exceção para email inválido");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar e-mail", e.getMessage().toLowerCase().contains("e-mail"));
        }
    }
    
    // RN06 - E-mail vazio permitido (opcional)
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRN06EmailVazioPermitido() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("");  // email vazio é permitido
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            // Não deve lançar exceção para email vazio
        } catch (IllegalArgumentException e) {
            fail("Email vazio não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // RN06 - E-mail válido aceito
    @Test
    @Category(ValidacaoFormatos.class)
    public void testRN06EmailValidoAceito() {
        paciente.setNome("João Silva");
        paciente.setCpf("12345678901");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setTelefone("(11)9999-9999");
        paciente.setEmail("teste@example.com");
        paciente.setDataNascimento(new java.util.Date());
        paciente.setConvenio(1);
        
        try {
            servicosTest.validarPaciente(paciente);
            // Deve passar sem lançar exceção
        } catch (IllegalArgumentException e) {
            fail("Email válido não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // ===== TESTES DE VISUALIZAÇÃO DE PACIENTES =====
    
    // RN02 - Validação de formato do filtro ID (apenas números)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroIdValido() {
        try {
            servicosTest.validarFiltro("id", "123");
            // Deve passar sem lançar exceção
        } catch (IllegalArgumentException e) {
            fail("ID válido não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // RN02 - Validação de formato do filtro ID (inválido)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroIdInvalido() {
        try {
            servicosTest.validarFiltro("id", "abc");
            fail("Deve lançar exceção para ID com letras");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar ID", e.getMessage().toLowerCase().contains("id"));
        }
    }
    
    // RN02 - Validação de formato do filtro CPF (apenas números)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroCpfValido() {
        try {
            servicosTest.validarFiltro("cpf", "12345678901");
            // Deve passar sem lançar exceção
        } catch (IllegalArgumentException e) {
            fail("CPF válido não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // RN02 - Validação de formato do filtro CPF (inválido)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroCpfInvalido() {
        try {
            servicosTest.validarFiltro("cpf", "123.456.789-01");
            fail("Deve lançar exceção para CPF com formatação");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar CPF", e.getMessage().toLowerCase().contains("cpf"));
        }
    }
    
    // RN02 - Validação de formato do filtro Nome (apenas alfabéticos)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroNomeValido() {
        try {
            servicosTest.validarFiltro("nome", "João Silva");
            // Deve passar sem lançar exceção
        } catch (IllegalArgumentException e) {
            fail("Nome válido não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // RN02 - Validação de formato do filtro Nome (inválido)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroNomeInvalido() {
        try {
            servicosTest.validarFiltro("nome", "João123");
            fail("Deve lançar exceção para nome com números");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar nome", e.getMessage().toLowerCase().contains("nome"));
        }
    }
    
    // RN02 - Filtro vazio deve ser permitido (limpa filtro)
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testRN02ValidarFiltroVazioPermitido() {
        try {
            servicosTest.validarFiltro("id", "");
            servicosTest.validarFiltro("cpf", null);
            // Deve passar sem lançar exceção
        } catch (IllegalArgumentException e) {
            fail("Filtro vazio não deve lançar exceção: " + e.getMessage());
        }
    }
    
    // Tipo de filtro inválido
    @Test
    @Category(VisualizacaoPacientes.class)
    public void testValidarFiltroTipoInvalido() {
        try {
            servicosTest.validarFiltro("invalido", "teste");
            fail("Deve lançar exceção para tipo de filtro inválido");
        } catch (IllegalArgumentException e) {
            assertTrue("Erro deve mencionar tipo inválido", 
                      e.getMessage().toLowerCase().contains("inválido"));
        }
    }
}