package br.com.senai.view.restaurante;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.Endereco;
import br.com.senai.core.domain.Restaurante;
import br.com.senai.core.service.CategoriaService;
import br.com.senai.core.service.RestauranteService;

public class CadastroRestauranteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	JTextArea taDescricao;
	private JTextField edtLogradouro;
	private JTextField edtCidade;
	private JTextField edtComplemento;
	private JTextField edtBairro;
	private JComboBox<Categoria> cbCategoria;
	
	private RestauranteService restauranteService;
	
	private Restaurante restaurante;
	
	private CategoriaService categoriaService;
	
	public void carregarComboCategoria() {
		List<Categoria> categorias = categoriaService.listarTodos();
		for(Categoria c : categorias) {
			this.cbCategoria.addItem(c);			
		}		
	}
	
	/**
	 * Create the frame.
	 */
	public CadastroRestauranteView() {
		setTitle("Gerenciar Restaurantes - Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaRestauranteView view = new ConsultaRestauranteView();
				view.setVisible(true);
				dispose();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPesquisar.setBounds(577, 11, 147, 33);
		contentPane.add(btnPesquisar);
		
		cbCategoria = new JComboBox<Categoria>();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbCategoria.setBounds(453, 55, 271, 33);
		contentPane.add(cbCategoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(381, 55, 62, 33);
		contentPane.add(lblCategoria);
		
		edtNome = new JTextField();
		edtNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edtNome.setColumns(10);
		edtNome.setBounds(105, 55, 266, 33);
		contentPane.add(edtNome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(10, 55, 85, 33);
		contentPane.add(lblNome);
		
		taDescricao = new JTextArea();
		taDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		taDescricao.setBounds(105, 99, 619, 125);
		contentPane.add(taDescricao);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescricao.setBounds(10, 99, 85, 33);
		contentPane.add(lblDescricao);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogradouro.setBounds(10, 235, 85, 33);
		contentPane.add(lblLogradouro);
		
		edtLogradouro = new JTextField();
		edtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edtLogradouro.setColumns(10);
		edtLogradouro.setBounds(105, 235, 619, 33);
		contentPane.add(edtLogradouro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCidade.setBounds(10, 279, 85, 33);
		contentPane.add(lblCidade);
		
		edtCidade = new JTextField();
		edtCidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edtCidade.setColumns(10);
		edtCidade.setBounds(105, 279, 266, 33);
		contentPane.add(edtCidade);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComplemento.setBounds(10, 323, 90, 33);
		contentPane.add(lblComplemento);
		
		edtComplemento = new JTextField();
		edtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edtComplemento.setColumns(10);
		edtComplemento.setBounds(105, 323, 619, 33);
		contentPane.add(edtComplemento);
		
		edtBairro = new JTextField();
		edtBairro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edtBairro.setColumns(10);
		edtBairro.setBounds(453, 279, 271, 33);
		contentPane.add(edtBairro);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBairro.setBounds(381, 279, 62, 33);
		contentPane.add(lblBairro);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String nome = edtNome.getText();
					String descricao = taDescricao.getText();
					String logradouro = edtLogradouro.getText();
					String bairro = edtBairro.getText();
					String cidade = edtCidade.getText();
					String complemento = edtComplemento.getText();
					Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
					
					Endereco endereco = new Endereco(cidade, logradouro, bairro, complemento);
					
					if (restaurante == null) {
						
						Restaurante restaurante = new Restaurante(nome, descricao, endereco, categoria);
						
					} else {
						
						restaurante.setNome(nome);
						restaurante.setDescricao(descricao);
						restaurante.setEndereco(endereco);
						restaurante.setCategoria(categoria);
						
					}

					restauranteService.salvar(restaurante);
					JOptionPane.showMessageDialog(contentPane, "Restaurante salvo com sucesso");

					edtNome.setText(null);
					taDescricao.setText(null);
					edtLogradouro.setText(null);
					edtBairro.setText(null);
					edtCidade.setText(null);
					edtComplemento.setText(null);
					cbCategoria.setSelectedIndex(0);
					endereco = null;
					categoria = null;
					restaurante = null;
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(420, 367, 147, 33);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edtNome.setText(null);
				taDescricao.setText(null);
				edtLogradouro.setText(null);
				edtBairro.setText(null);
				edtCidade.setText(null);
				edtComplemento.setText(null);
				cbCategoria.setSelectedIndex(0);
				
				restaurante = null;
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(577, 367, 147, 33);
		contentPane.add(btnCancelar);
		
		cbCategoria.setBounds(453, 55, 271, 33);
		contentPane.add(cbCategoria);
		
		this.carregarComboCategoria();
	}
	
	public void setRestaurante(Restaurante restaurante) {
		
		this.restaurante = restaurante;
		this.edtNome.setText(restaurante.getNome());
		this.taDescricao.setText(restaurante.getDescricao());
		this.edtCidade.setText(restaurante.getEndereco().getCidade());
		this.edtBairro.setText(restaurante.getEndereco().getBairro());
		this.edtLogradouro.setText(restaurante.getEndereco().getLogradouro());
		this.edtComplemento.setText(restaurante.getEndereco().getComplemento());
		this.cbCategoria.setSelectedItem(restaurante);
		
	}
}
