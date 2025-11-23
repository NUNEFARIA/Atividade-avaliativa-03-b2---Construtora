package fag.objetos;

import java.util.ArrayList;

public class Construcao {

	private String nome;
	private String endereco;
	private String dataInicio;
	private ArrayList<Funcionario> funcionarios = new ArrayList<>();

	// CONSTRUTOR
	public Construcao(String nome, String endereco, String dataInicio) {
		setNome(nome);
		setEndereco(endereco);
		setDataInicio(dataInicio);
	}

	// SETTERS
	public void setNome(String nome) {
		if (nome != null && !nome.isBlank())
			this.nome = nome;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && !endereco.isBlank())
			this.endereco = endereco;
	}

	public void setDataInicio(String dataInicio) {
		if (dataInicio != null && !dataInicio.isBlank())
			this.dataInicio = dataInicio;
	}

	// GETTERS
	public String getNomeConst() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	// EXIBIR
	public void exibirConst() {
		System.out.println("\n" + nome + " | " + endereco + " | " + dataInicio);
		System.out.println("Funcionários vinculados:");

		if (funcionarios.isEmpty()) {
			System.out.println("Nenhum funcionário nesta obra.");
			return;
		}

		for (Funcionario f : funcionarios) {
			System.out.println("- " + f.getNomeFun() + " (" + f.getCargo() + ")");
		}
	}

	// RELAÇÃO BIDIRECIONAL - VINCULAR
	public void vincula(Funcionario funcionario) {
		if (!funcionarios.contains(funcionario)) {
			funcionarios.add(funcionario);
		}
	}

	// DESVINCULAR
	public void desVincula(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}

	@Override
	public String toString() {
		return nome + " | " + endereco + " | " + dataInicio;
	}
}
