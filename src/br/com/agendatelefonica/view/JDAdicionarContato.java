package br.com.agendatelefonica.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.agendatelefonica.control.ContatoDAO;
import br.com.agendatelefonica.model.Contato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class JDAdicionarContato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDAdicionarContato dialog = new JDAdicionarContato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDAdicionarContato() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setTitle("Adicionar contato");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nome:");
			lblNewLabel.setBounds(10, 25, 120, 14);
			contentPanel.add(lblNewLabel);
		}
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 41, 375, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefone:");
		lblNewLabel_1.setBounds(10, 80, 120, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(10, 97, 181, 20);
		contentPanel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o");
		lblNewLabel_2.setBounds(10, 135, 120, 14);
		contentPanel.add(lblNewLabel_2);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(10, 150, 375, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton buttonSalvar = new JButton("Salvar");
				buttonSalvar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						salvarContato();
					}
				});
				buttonSalvar.setActionCommand("OK");
				buttonPane.add(buttonSalvar);
				getRootPane().setDefaultButton(buttonSalvar);
			}
			{
				JButton buttonCancelar = new JButton("Cancelar");
				buttonCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JDAdicionarContato.this.dispose();
					}
				});
				buttonCancelar.setActionCommand("Cancel");
				buttonPane.add(buttonCancelar);
			}
		}
	}
	
	private void salvarContato() {
		if (txtEndereco.getText().toString().equals("") ||
				txtNome.getText().toString().equals("") || 
				txtTelefone.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha os campos para adicionar um contato");
			return;
		}
		
		Contato contato = new Contato();
		contato.setNome(txtNome.getText().toString());
		contato.setTelefone(txtTelefone.getText().toString());
		contato.setEndereco(txtEndereco.getText().toString());
		
		try {
			if (ContatoDAO.insert(contato)) {
				JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!");
				this.dispose();
			} else JOptionPane.showMessageDialog(null, "Não foi possível adicionar o contato!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
