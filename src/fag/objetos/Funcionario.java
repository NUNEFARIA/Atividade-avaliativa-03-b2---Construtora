package fag.objetos;

public class Funcionario {

	private String nome;
	private String cargo;
	private double salario;
	private Construcao construcao; // relação 1 funcionário → 1 construção

	// CONSTRUTOR
	public Funcionario(String nome, String cargo, double salario) {
		setNome(nome);
		setCargo(cargo);
		setSalario(salario);
		this.construcao = null;
	}

	// SETTERS
	public void setNome(String nome) {
		if (nome != null && !nome.isBlank())
			this.nome = nome;
	}

	public void setCargo(String cargo) {
		if (cargo != null && !cargo.isBlank())
			this.cargo = cargo;
	}

	public void setSalario(double salario) {
		if (salario > 0)
			this.salario = salario;
	}

	// GETTERS
	public String getNomeFun() {
		return nome;
	}

	public String getCargo() {
		return cargo;
	}

	public double getSalario() {
		return salario;
	}

	public Construcao getConstrucao() {
		return construcao;
	}

	// EXIBIR FUNCIONÁRIO
	public void exibirFunc() {
		String nomeObra = (construcao != null ? construcao.getNomeConst() : "Sem obra");
		System.out.println("\n" + nome + " | " + cargo + " | " + salario + " | " + nomeObra);
	}

	// VINCULAR OBRA
	public void vincula(Construcao construcao) {
		this.construcao = construcao;
	}

	// DESVINCULAR OBRA
	public void desVincula(Construcao construcao) {
		if (this.construcao == construcao) {
			this.construcao = null;
		}
	}

	@Override
	public String toString() {
		return nome + " | " + cargo + " | R$" + salario;
	}
}
