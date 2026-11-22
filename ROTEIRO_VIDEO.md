# Roteiro do vídeo (5 a 10 min)

1. **Abertura (30 s):** apresentar os integrantes e o tema: "Sistema de Gestão de Funcionários em Java, com herança, polimorfismo e banco de dados".
2. **Herança (1 min 30 s):** abrir `Funcionario.java` e mostrar que é `abstract`, com id, nome e salário. Abrir `Gerente.java` e `Caixa.java` e mostrar o `extends Funcionario`, o `super(...)` e o atributo próprio de cada um (setor e número do caixa).
3. **Polimorfismo (1 min 30 s):** mostrar o `calcularBonus()` abstrato na superclasse, a versão de 20% no Gerente e a de 10% no Caixa. Em `Main.java`, mostrar o método `calcularSalarios()`: o mesmo `f.calcularBonus()` dentro do `for`, sobre uma `List<Funcionario>`.
4. **Banco de dados (1 min 30 s):** em `ConexaoBD.java`, mostrar a URL JDBC e o `CREATE TABLE`. Em `FuncionarioDAO.java`, mostrar o `INSERT`, o `SELECT` e o `DELETE` com `PreparedStatement`.
5. **Demonstração (3 min):** rodar o `executar.bat`.
   - Cadastrar 1 gerente e 1 caixa.
   - Listar.
   - Calcular o bônus e mostrar 20% x 10%.
   - Sair (0) e abrir de novo: listar mostra que os dados continuam salvos no banco.
   - Excluir um funcionário e listar de novo.
6. **Encerramento (30 s):** mostrar o repositório no GitHub e o relatório.
