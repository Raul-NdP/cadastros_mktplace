package br.com.senai.view.restaurante;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.Restaurante;
import br.com.senai.core.service.CategoriaService;
import br.com.senai.core.service.RestauranteService;
import br.com.senai.view.categoria.CadastroCategoriaView;
import br.com.senai.view.componentes.table.CategoriaTableModel;
import br.com.senai.view.componentes.table.RestauranteTableModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ConsultaRestauranteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableRestaurantes;
	private JComboBox<Categoria> cbCategoria;
	
	private RestauranteService restauranteService;
	
	private CategoriaService categoriaService;
	
	/**
	 * Create the frame.
	 */
	public ConsultaRestauranteView() {
		setTitle("Gerenciar Restaurantes - Consulta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		this.restauranteService = new RestauranteService();
		this.categoriaService = new CategoriaService();
		
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFiltros.setBounds(10, 11, 39, 33);
		contentPane.add(lblFiltros);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(10, 54, 39, 33);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(59, 54, 210, 33);
		contentPane.add(textField);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					List<Restaurante> restaurantes = restauranteService.listarPor(getName(), (Categoria) cbCategoria.getSelectedItem());
					RestauranteTableModel model = new RestauranteTableModel(restaurantes);
					tableRestaurantes.setModel(model);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListar.setBounds(576, 54, 147, 33);
		contentPane.add(btnListar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRestauranteView view = new CadastroRestauranteView();
				view.setVisible(true);
				dispose();
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.setBounds(576, 11, 147, 33);
		contentPane.add(btnNovo);
		
		cbCategoria = new JComboBox<Categoria>();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbCategoria.setBounds(351, 54, 215, 33);
		contentPane.add(cbCategoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(279, 54, 62, 33);
		contentPane.add(lblCategoria);
		
		JLabel lblRestaurantesEncontradas = new JLabel("Restaurantes encontradas");
		lblRestaurantesEncontradas.setHorizontalAlignment(SwingConstants.LEFT);
		lblRestaurantesEncontradas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRestaurantesEncontradas.setBounds(10, 98, 171, 33);
		contentPane.add(lblRestaurantesEncontradas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 713, 195);
		contentPane.add(scrollPane);
		
		tableRestaurantes = new JTable();
		scrollPane.setViewportView(tableRestaurantes);
		
		JPanel molduraAcoes = new JPanel();
		molduraAcoes.setLayout(null);
		molduraAcoes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		molduraAcoes.setBounds(407, 346, 316, 54);
		contentPane.add(molduraAcoes);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					int linhaSelecionada = tableRestaurantes.getSelectedRow();
					if (linhaSelecionada >= 0) {
						int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente excluir?", 
								"Exclusão", JOptionPane.YES_NO_OPTION);
						if (opcao == 0) {
							RestauranteTableModel model = (RestauranteTableModel) tableRestaurantes.getModel();
							Restaurante restauranteSelecionado = model.getPor(linhaSelecionada);
							try {
								restauranteService.removerPor(restauranteSelecionado.getId());
								model.removerPor(linhaSelecionada);
								tableRestaurantes.updateUI();
								JOptionPane.showMessageDialog(contentPane, "Restaurante removido com sucesso");
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(contentPane, ex.getMessage());
							}
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para exclusão");
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluir.setBounds(163, 15, 147, 33);
		molduraAcoes.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						
						int linhaSelecionada = tableRestaurantes.getSelectedRow();
						if (linhaSelecionada >= 0) {
							RestauranteTableModel model = (RestauranteTableModel) tableRestaurantes.getModel();
							Restaurante restauranteSelecionado = model.getPor(linhaSelecionada);
							CadastroRestauranteView view = new CadastroRestauranteView();
							view.setRestaurante(restauranteSelecionado);
							view.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para edição");
						}
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(contentPane, ex.getMessage());
					}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.setBounds(6, 15, 147, 33);
		molduraAcoes.add(btnEditar);
	}
	
	public void carregarComboCategoria() {
		List<Categoria> categorias = categoriaService.listarTodos();
		for(Categoria c : categorias) {
			this.cbCategoria.addItem(c);			
		}		
	}
	
	private void configurarColuna(int indice, int largura) {
		
		this.tableRestaurantes.getColumnModel().getColumn(indice).setResizable(false);
		this.tableRestaurantes.getColumnModel().getColumn(indice).setPreferredWidth(largura);
		
	}
	
	private void configurarTabela() {
		
		final int COLUNA_ID = 0;
		final int COLUNA_NOME = 1;
		
		this.tableRestaurantes.getTableHeader().setReorderingAllowed(false);
		this.tableRestaurantes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.configurarColuna(COLUNA_ID, 90);
		this.configurarColuna(COLUNA_NOME, 250);
	}
	
}
