# Sistema de Gestão de Funcionários

Projeto de Aplicação da disciplina de Programação Orientada a Objetos: um sistema de console em Java para cadastrar, listar, excluir e calcular a bonificação de funcionários de uma empresa, com os dados salvos num banco de dados relacional via JDBC.

## Conceitos aplicados

- **Herança:** `Funcionario` é a superclasse abstrata, com `id`, `nome` e `salario`. `Gerente` (com `setor`) e `Caixa` (com `numeroCaixa`) herdam dela.
- **Polimorfismo:** o método `calcularBonus()` é abstrato em `Funcionario` e cada cargo implementa a sua regra. O gerente recebe 20% de bônus e o caixa recebe 10%. O sistema percorre uma `List<Funcionario>` e chama o mesmo método para todos.
- **Banco de dados:** Apache Derby, um banco relacional embutido, acessado apenas pela API nativa do JDBC (`java.sql`) com `PreparedStatement`.

## Estrutura

```
src/empresa/
├── Main.java                 menu do console
├── modelo/
│   ├── Funcionario.java      superclasse abstrata
│   ├── Gerente.java          subclasse (bônus 20%)
│   └── Caixa.java            subclasse (bônus 10%)
└── dao/
    ├── ConexaoBD.java        conexão JDBC e criação da tabela
    └── FuncionarioDAO.java   salvar, listar e excluir
lib/derby-10.14.2.0.jar       driver JDBC do Apache Derby
```

## Como executar

Requer o Java (JDK 8 ou superior).

- **Windows:** dois cliques em `executar.bat`
- **Linux/macOS:** `./executar.sh`

O banco é criado automaticamente na pasta `dados/` na primeira execução.

## Funcionalidades

1. Cadastrar gerente
2. Cadastrar caixa
3. Listar funcionários
4. Calcular bônus e salário final (polimorfismo)
5. Excluir funcionário
