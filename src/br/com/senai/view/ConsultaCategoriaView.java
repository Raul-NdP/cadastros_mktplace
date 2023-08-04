package br.com.senai.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ConsultaCategoriaView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCategoriaView frame = new ConsultaCategoriaView();
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
	public ConsultaCategoriaView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(82, 53, 484, 33);
		contentPane.add(textField);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNovo.setBounds(576, 10, 147, 33);
		contentPane.add(btnNovo);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListar.setBounds(576, 53, 147, 33);
		contentPane.add(btnListar);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 53, 62, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltros.setBounds(10, 10, 62, 33);
		contentPane.add(lblFiltros);
		
		JLabel lblCategoriasEncontradas = new JLabel("Categorias encontradas");
		lblCategoriasEncontradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoriasEncontradas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategoriasEncontradas.setBounds(10, 111, 210, 33);
		contentPane.add(lblCategoriasEncontradas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 713, 195);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(413, 353, 316, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(163, 15, 147, 33);
		panel.add(btnExcluir);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(6, 15, 147, 33);
		panel.add(btnEditar);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
	}
}
