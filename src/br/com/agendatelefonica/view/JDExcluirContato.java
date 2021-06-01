package br.com.agendatelefonica.view;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.agendatelefonica.control.ContatoDAO;
import br.com.agendatelefonica.interfaces.IContato;
import br.com.agendatelefonica.model.Contato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class JDExcluirContato extends JDialog implements IContato {

	private final JPanel contentPanel = new JPanel();
	private JButton buttonSim;
	private JButton buttonNao;
	private Contato contato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDExcluirContato dialog = new JDExcluirContato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDExcluirContato() {
		setResizable(false);
		setBounds(100, 100, 380, 162);
		setTitle("Confirmar exclusão");
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 80);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tem certeza que deseja realizar esta operação?");
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 37, 351, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 83, 434, 40);
			getContentPane().add(buttonPane);
			{
				buttonSim = new JButton("Sim");
				buttonSim.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						excluirContato();
					}
				});
				buttonSim.setBounds(110, 5, 64, 23);
				buttonSim.setActionCommand("OK");
				getRootPane().setDefaultButton(buttonSim);
			}
			{
				buttonNao = new JButton("N\u00E3o");
				buttonNao.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JDExcluirContato.this.dispose();
					}
				});
				buttonNao.setBounds(184, 5, 65, 23);
				buttonNao.setActionCommand("Cancel");
			}
			buttonPane.setLayout(null);
			buttonPane.add(buttonSim);
			buttonPane.add(buttonNao);
		}
	}

	private void excluirContato() {
		try {
			if (ContatoDAO.delete(contato)) JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
			else JOptionPane.showMessageDialog(null, "Não foi possível excluir o contato.");
			this.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
