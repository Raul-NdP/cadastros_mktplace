package br.com.senai.view.categoria;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.service.CategoriaService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroCategoriaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	
	private CategoriaService categoriaService;
	
	private Categoria categoria;
	
	/**
	 * Create the frame.
	 */
	public CadastroCategoriaView() {
		setTitle("Gerenciar Categorias - Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		this.categoriaService = new CategoriaService();
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 53, 62, 33);
		contentPane.add(lblNome);
		
		edtNome = new JTextField();
		edtNome.setBounds(82, 53, 592, 33);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaCategoriaView view = new ConsultaCategoriaView();
				view.setVisible(true);
				dispose();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPesquisar.setBounds(527, 10, 147, 33);
		contentPane.add(btnPesquisar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edtNome.setText("");
				categoria = null;
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBounds(527, 96, 147, 33);
		contentPane.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String nome = edtNome.getText();
					
					if (categoria == null) {
						categoria = new Categoria(nome);
					} else {
						categoria.setNome(nome);
					}
					
					categoriaService.salvar(categoria);
					JOptionPane.showMessageDialog(contentPane, "Categoria salva com sucesso");
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalvar.setBounds(370, 96, 147, 33);
		contentPane.add(btnSalvar);
	}
	
	public void setCategoria(Categoria categoria) {
		
		this.categoria = categoria;
		this.edtNome.setText(categoria.getNome());
		
	}
	
}
