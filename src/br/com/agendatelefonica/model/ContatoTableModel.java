package br.com.agendatelefonica.model;

import java.util.List;

@SuppressWarnings("serial")
public class ContatoTableModel extends ViewAbstractTableModel<Contato> {

	public ContatoTableModel(List<Contato> rows) {
		super(rows);
		columns = new String[] {
				"Nome",
				"Telefone",
				"Endereço"
		};
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Contato contato = rows.get(rowIndex);
		switch (columnIndex) {
		case 0: 
			return contato.getNome(); 
		
		case 1:
			return contato.getTelefone();
			
		case 2: 
			return contato.getEndereco();
			
		default:
			return null;
		}
	}
	
	public void atualizarTabela(List<Contato> contatos) {
		updateTable(contatos);
	}
}
