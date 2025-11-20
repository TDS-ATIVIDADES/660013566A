package test.modelo;

import modelo.Paciente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 * Testes para a classe Paciente
 */
public class PacienteTest {
    
    private Paciente paciente;
    
    @Before
    public void setUp() {
        paciente = new Paciente();
    }
    
    @Test
    public void testSetAndGetNome() {
        String nome = "João Silva";
        paciente.setNome(nome);
        assertEquals(nome, paciente.getNome());
    }
    
    @Test
    public void testSetAndGetCpf() {
        String cpf = "12345678901";
        paciente.setCpf(cpf);
        // Testa getCpf(false) que retorna sem formatação
        assertEquals(cpf, paciente.getCpf(false));
    }
    
    @Test
    public void testSetAndGetCpfFormatado() {
        String cpf = "12345678901";
        paciente.setCpf(cpf);
        // Testa getCpf(true) que retorna formatado
        String cpfFormatado = paciente.getCpf(true);
        assertEquals("123.456.789-01", cpfFormatado);
    }
    
    @Test
    public void testSetAndGetEndereco() {
        String endereco = "Rua Principal, 123, Apt 456";
        paciente.setEndereco(endereco);
        assertEquals(endereco, paciente.getEndereco());
    }
    
    @Test
    public void testSetAndGetTelefone() {
        String telefone = "(11)99999-9999";
        paciente.setTelefone(telefone);
        assertEquals(telefone, paciente.getTelefone());
    }
    
    @Test
    public void testSetAndGetEmail() {
        String email = "joao@example.com";
        paciente.setEmail(email);
        assertEquals(email, paciente.getEmail());
    }
    
    @Test
    public void testSetAndGetRg() {
        String rg = "123456789";
        paciente.setRg(rg);
        assertEquals(rg, paciente.getRg());
    }
    
    @Test
    public void testSetAndGetDataNascimento() {
        Date data = new Date();
        paciente.setDataNascimento(data);
        assertEquals(data, paciente.getDataNascimento());
    }
    
    @Test
    public void testSetAndGetIdConvenio() {
        int idConvenio = 1;
        paciente.setConvenio(idConvenio);
        assertEquals(idConvenio, paciente.getIdConvenio());
    }
    
    @Test
    public void testSetAndGetIdPaciente() {
        int idPaciente = 42;
        paciente.setIdPaciente(idPaciente);
        assertEquals(idPaciente, paciente.getIdPaciente());
    }
    
    @Test
    public void testCpfNullSafety() {
        paciente.setCpf(null);
        // Testa se getCpf(false) lida com null de forma segura
        String resultado = paciente.getCpf(false);
        assertNull("getCpf(false) deve retornar null quando cpf é null", resultado);
    }
    
    @Test
    public void testCpfFormatacaoComCaracteresEspeciais() {
        // CPF com máscara já aplicada
        paciente.setCpf("123.456.789-01");
        // Deve remover pontuação e manter apenas dígitos
        String cpfSemFormatacao = paciente.getCpf(false);
        assertEquals("12345678901", cpfSemFormatacao);
    }
}
