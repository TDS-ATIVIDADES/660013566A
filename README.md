# Sistema Hospitalar
![image](https://img.shields.io/badge/java-CC342D?style=for-the-badge&logo=java&logoColor=white)
![image](    https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![image](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
)
![image](https://img.shields.io/badge/Git-E34F26?style=for-the-badge&logo=git&logoColor=white
)
![](https://img.shields.io/badge/VSCode-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)
![](https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=Jira&logoColor=white)
![](    https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/TDS-ATIVIDADES/660013566A?style=for-the-badge)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues-closed/TDS-ATIVIDADES/660013566A?style=for-the-badge)
![GitHub Issues or Pull Requests by label](https://img.shields.io/github/issues-closed/TDS-ATIVIDADES/660013566A/bug?style=for-the-badge)
![GitHub Issues or Pull Requests by label](https://img.shields.io/github/issues-closed/TDS-ATIVIDADES/660013566A/enhancement?style=for-the-badge)


## Contexto

O projeto de software para o hospital da cidade está evoluindo e a equipe de desenvolvimento finalizou o módulo de cadastro e visualização de pacientes. Os líderes (scrum master e product owner), no entanto, estão observando que há falha no processo de testes, que precisa ser formalizado e posto em prática – atualmente, os testes estão a critério e rigor dos desenvolvedores, com validações pontuais do cliente nas entregas intermediárias.

Você está entre os desenvolvedores designados a encaminhar o processo de teste. Sua missão, neste momento, é entregar um plano de teste simples e prático para o módulo de cadastro e visualização de pacientes para servir como base para o teste nos outros módulos do sistema a serem desenvolvidos.

## Pré-requisitos

- **Java JDK 17 ou superior**
- **MySQL** (para banco de dados)
- **JUnit** (para testes)
- **DevContainer/Docker** (opcional)
## 🔧 Desenvolvimento

### Tecnologias Utilizadas
- **Java Swing**: Interface gráfica
- **JDBC**: Acesso ao banco de dados
- **JUnit 4**: Testes automatizados
- **MySQL**: Banco de dados

### Padrões Implementados
- **MVC**: Separação entre modelo, visão e controle
- **DAO**: Padrão de acesso a dados
- **Factory**: Criação de objetos DAO e Serviços

## Funcionalidades

- ✅ Cadastro de pacientes com validações
- ✅ Gerenciamento de convênios
- ✅ Busca e filtro de pacientes
- ✅ Validações de CPF, telefone, email
- ✅ Máscara de formatação automática
- ✅ Testes automatizados completos

## Banco de Dados

O sistema utiliza MySQL. Execute o script `SCRIPT_BANCO_SISTEMA_HOSP.sql` para criar as tabelas necessárias.

## Gerenciamento do Projeto

* [GitHub Projects](https://github.com/orgs/TDS-ATIVIDADES/projects/2/views/1)
* [Documentação de Requisitos](https://github.com/TDS-ATIVIDADES/660013566A/wiki)

## Atividades

### Atividade 1

* [Baixe](https://github.com/TDS-ATIVIDADES/660013566A/archive/refs/heads/original.zip) os arquivos relativos ao projeto de software para o hospital
* [Veja](https://github.com/TDS-ATIVIDADES/660013566A/wiki) a documentação de requisitos para o módulo de cadastro e visualização do paciente, desenvolvidos pelos analistas de sistemas.

Leia as especificações e analise o código já desenvolvido.

A partir do que já foi desenvolvido e o que está ainda planejado, preencha um modelo de plano de teste.

### Atividade 2

Baseando-se no plano de testes elaborado na atividade anterior, execute os testes propostos e registre no [GitHub Projects](https://github.com/orgs/TDS-ATIVIDADES/projects/2/views/1) todos os testes que resultaram em falha.

### Atividade 3

1. A partir das tarefas criadas, realize as correções necessárias e registre na ferramenta a conclusão da tarefa.

2. Partindo do documento de orientação de requisitos, crie uma suíte de testes unitários para o projeto de software hospitalar e implemente com **JUnit** os testes.


## Executando os Testes

O projeto inclui uma suíte completa de testes JUnit 4 com 38 testes automatizados.

### Compilação dos Testes
```bash
# Criar diretórios de build
mkdir -p build/classes build/test/classes

# Compilar código-fonte
javac -cp ".:bibliotecas/*" -d build/classes $(find src -name "*.java")

# Compilar testes
javac -cp ".:src:build/classes:bibliotecas/*" -d build/test/classes $(find test -name "*.java")
```

### Execução dos Testes

#### Via Terminal
```bash
# Executar todos os testes
java -cp "build/classes:build/test/classes:bibliotecas/*" org.junit.runner.JUnitCore \
  test.modelo.PacienteTest modelo.ConvenioTest \
  test.servicos.PacienteServicosTest test.servicos.ConvenioServicosTest \
  test.dao.PacienteDAOTest test.dao.ConvenioDAOTest

# Executar testes específicos
java -cp "build/classes:bibliotecas/*" org.junit.runner.JUnitCore test.servicos.PacienteServicosTest

# Para executar testes por categoria, use suites JUnit ou execute todos e filtre manualmente
# Exemplo: executar apenas testes de visualização (filtrar por nome)
java -cp "build/classes:bibliotecas/*" org.junit.runner.JUnitCore test.servicos.PacienteServicosTest | grep testRN02
```

### Categorias de Testes
Os testes estão organizados nas seguintes categorias:
- **ValidacaoCamposObrigatorios**: Testes para campos obrigatórios vazios/inválidos (cadastro)
- **ValidacaoFormatos**: Testes para formatos específicos (CPF, telefone, data, e-mail, filtros)
- **ValidacaoRegrasNegocio**: Testes para regras de negócio (limites de caracteres, unicidade)
- **VisualizacaoPacientes**: Testes para funcionalidades de visualização e filtros

### Cobertura de Testes
- **Testes Unitários**: Validações de modelos e regras de negócio (15 testes)
- **Testes de Integração**: Operações com banco de dados (23 testes)
- **Total**: 38 testes automatizados