/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dao.DAOFactory;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.Paciente;

/**
 *
 * @author senacead
 */
/*
A classe PacienteServicos representa a camada de serviços da aplicação, ela utiliza a classe PacienteDAO para realizar
operações de leitura e escrita no banco de dados.
 */
public class PacienteServicos {
    
    // Método para validar se CPF contém apenas dígitos
    private void validarCpfSomenteDigitos(String cpf) throws IllegalArgumentException {
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter apenas 11 dígitos numéricos!");
        }
    }
    
    // Método para validar formato de e-mail
    private void validarEmail(String email) throws IllegalArgumentException {
        if (email != null && !email.trim().isEmpty()) {
            // Regex básico para validação de email
            String emailRegex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            if (!email.matches(emailRegex)) {
                throw new IllegalArgumentException("E-mail informado é inválido!");
            }
        }
        // Email vazio/null é permitido (opcional)
    }
    
    // Método para validar se Telefone contém apenas dígitos na formatação
    private void validarTelefoneSomenteDigitos(String telefone) throws IllegalArgumentException {
        String apenasDigitos = telefone.replaceAll("\\D", "");
        if (apenasDigitos.length() != 10) {
            throw new IllegalArgumentException("Telefone deve conter 10 dígitos numéricos (DDD + número)!");
        }
    }
    
    // Método para validar data de nascimento (não pode ser futura)
    private void validarDataNascimento(Date dataNascimento) throws IllegalArgumentException {
        if (dataNascimento != null) {
            Date hoje = new Date();
            if (dataNascimento.after(hoje)) {
                throw new IllegalArgumentException("Data de nascimento não pode ser futura!");
            }
        }
    }
    
    // Método para validar campos obrigatórios do paciente
    public void validarPaciente(Paciente pac) throws IllegalArgumentException {
        
        if (pac.getNome() == null || pac.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        } else if (pac.getNome().length() > 55) {
            throw new IllegalArgumentException("Nome deve conter no máximo 55 caracteres!");
        }
        
        // Validação do e-mail (opcional, mas se informado deve ser válido)
        validarEmail(pac.getEmail());
        
        if (pac.getCpf() == null || pac.getCpf(false).trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório!");
        } else {
            String cpf = pac.getCpf(false);
            validarCpfSomenteDigitos(cpf);
            if (cpf.length() != 11) {
                throw new IllegalArgumentException("CPF deve conter 11 dígitos!");
            }
        }
        
        if (pac.getEndereco() == null || pac.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço é obrigatório!");
        } else if (pac.getEndereco().length() > 200) {
            throw new IllegalArgumentException("Endereço deve conter menos 200 caracteres!");
        }
        
        // Validação do telefone: obrigatório, máximo 15 caracteres, e formato (xx)xxxx-xxxx
        if (pac.getTelefone() == null || pac.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório!");
        } else {
            String tel = pac.getTelefone().trim();
            validarTelefoneSomenteDigitos(tel);
            if (tel.length() > 15) {
                throw new IllegalArgumentException("Telefone deve conter no máximo 15 caracteres!");
            }
            // Formato esperado: (xx)xxxx-xxxx — 2 dígitos de DDD e 8 dígitos do número
            if (!tel.matches("^\\(\\d{2}\\)\\d{4}-\\d{4}$")) {
                throw new IllegalArgumentException("Telefone inválido. Use o formato (xx)xxxx-xxxx");
            }
        }

        if (pac.getDataNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória!");
        }
        
        // Validação adicional da data de nascimento
        validarDataNascimento(pac.getDataNascimento());
        
        
        if (pac.getIdConvenio() <= 0) {
            throw new IllegalArgumentException("Convênio é obrigatório!");
        }
    }

    // Método para validar unicidade do CPF
    public void validarCpfUnico(String cpf) throws SQLException, IllegalArgumentException {
        
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();
        Paciente pacienteExistente = pacDAO.buscarPacientePorCpf(cpf);
        
        if (pacienteExistente != null) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema!");
        }
    }

    // Método para cadastrar um paciente
    public void cadastrarPaciente(Paciente pac) throws SQLException, IllegalArgumentException {
        
        // Validar campos obrigatórios antes de cadastrar
        this.validarPaciente(pac);
        
        // Validar se o CPF é único
        this.validarCpfUnico(pac.getCpf(false));

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método cadastrarPaciente para enviar o obj. pac
        pacDAO.cadastrarPaciente(pac);
    }

    // Método para buscar um paciente por ID
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método buscarPaciente para buscar o paciente pelo ID
        return pacDAO.buscarPacienteFiltro(query);
    }

    // Método para buscar todos os pacientes
    public ArrayList<Paciente> buscarPaciente() throws SQLException {

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método buscarPaciente para buscar todos os pacientes
        return pacDAO.buscarPaciente();
    }

    // Método para validar formato do filtro
    public void validarFiltro(String tipoFiltro, String valor) throws IllegalArgumentException {
        if (valor == null || valor.trim().isEmpty()) {
            return; // Filtro vazio é permitido (limpa filtro)
        }
        
        switch (tipoFiltro.toLowerCase()) {
            case "id":
                if (!valor.matches("\\d+")) {
                    throw new IllegalArgumentException("ID deve conter apenas números!");
                }
                break;
            case "cpf":
                if (!valor.matches("\\d+")) {
                    throw new IllegalArgumentException("CPF deve conter apenas números!");
                }
                break;
            case "nome":
                if (!valor.matches("[\\p{L}\\s]+")) {
                    throw new IllegalArgumentException("Nome deve conter apenas caracteres alfabéticos!");
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de filtro inválido!");
        }
    }
}
