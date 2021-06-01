package br.com.agendatelefonica.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import br.com.agendatelefonica.control.ContatoDAO;
import br.com.agendatelefonica.model.Contato;
import br.com.agendatelefonica.model.ContatoTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class JFAgendaTelefonica extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JTable table;
	private JScrollPane js;
	private JTextField txtPesquisar;
	private JComboBox<String> cbxPesquisarPor;
	
	private int linhaSelecionada;
	private List<Contato> contatos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFAgendaTelefonica frame = new JFAgendaTelefonica();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFAgendaTelefonica() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				recuperarValoresTabela();
				
				atualizarPanelTable();
			}
			
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setTitle("Agenda telef\u00F4nica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 785, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Agenda Telef\u00F4nica");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(241, 11, 297, 37);
		panel.add(lblTitulo);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 76, 610, 367);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitulo2 = new JLabel("Contatos");
		lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo2.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTitulo2.setBounds(260, 5, 101, 14);
		panel_1.add(lblTitulo2);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(10, 25, 351, 25);
		panel_1.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		cbxPesquisarPor = new JComboBox<String>();
		cbxPesquisarPor.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha...", "Nome", "Telefone", "Endere\u00E7o"}));
		cbxPesquisarPor.setBounds(373, 25, 101, 25);
		panel_1.add(cbxPesquisarPor);
		
		recuperarValoresTabela();

		js = new JScrollPane(table);
		js.setSize(600, 300);
		js.setLocation(10, 60);
		panel_1.add(js);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtPesquisar.getText().toString().equals("") || cbxPesquisarPor.getSelectedIndex() <= 0)
					JOptionPane.showMessageDialog(null, "Preencha os campos para realizar a pesquisa");
				else {
					recuperarValoresTabela();
					atualizarPanelTable();
				}
			}
		});
		btnPesquisar.setBounds(489, 25, 111, 25);
		panel_1.add(btnPesquisar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(609, 76, 176, 367);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirTelaAdicionarContato();
			}
		});
		btnAdicionar.setBounds(38, 23, 111, 39);
		panel_2.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirTelaAtualizarContato();
			}
		});
		btnEditar.setBounds(38, 73, 111, 39);
		panel_2.add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmarExclusao();
			}
		});
		btnRemover.setBounds(38, 123, 111, 39);
		panel_2.add(btnRemover);
	}
	
	private void confirmarExclusao() {
		if (linhaSelecionada < 0) return;
		Contato contato = contatos.get(linhaSelecionada);
		JDExcluirContato excluirContato = new JDExcluirContato();
		excluirContato.setContato(contato);
		excluirContato.setLocationRelativeTo(null);
		excluirContato.setVisible(true);
	}
	
	private void abrirTelaAtualizarContato() {
		if (linhaSelecionada < 0) return;
		Contato contato = contatos.get(linhaSelecionada);
		JDEditarContato editarContato = new JDEditarContato();
		editarContato.setContato(contato);
		editarContato.setLocationRelativeTo(null);
		editarContato.setVisible(true);
	}
	
	private void abrirTelaAdicionarContato() {
		JDAdicionarContato jAdicContato = new JDAdicionarContato();
		jAdicContato.setLocationRelativeTo(null);
		jAdicContato.setVisible(true);
	}
	
	private ContatoTableModel recuperarValoresTabela() {
		ContatoTableModel tableModel = null;
		
		try {
			if (!txtPesquisar.getText().toString().equals("") && cbxPesquisarPor.getSelectedIndex() > 0) {
				switch (cbxPesquisarPor.getSelectedIndex()) {
				case 1:
					contatos = ContatoDAO.select(ContatoDAO.QUERY_NOME, txtPesquisar.getText().toString());
					break;
					
				case 2:
					contatos = ContatoDAO.select(ContatoDAO.QUERY_TELEFONE, txtPesquisar.getText().toString());
					break;
					
				case 3:
					contatos = ContatoDAO.select(ContatoDAO.QUERY_ENDERECO, txtPesquisar.getText().toString());
					break;
					
				default:
					contatos = ContatoDAO.selectAll();
					break;
				}
				
			} else 
				contatos = ContatoDAO.selectAll();
			tableModel = new ContatoTableModel(contatos);
			
			table = new JTable(tableModel);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JTable source = (JTable) e.getSource();
					linhaSelecionada = source.rowAtPoint(e.getPoint());
				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível exibir os contatos");
		}
		
		return tableModel;
	}
	
	private void atualizarPanelTable() {
		if (js != null) {
			panel_1.remove(js);
		}
		
		js = new JScrollPane(table);
		js.setSize(600, 300);
		js.setLocation(10, 60);
		panel_1.add(js);
	}
}
