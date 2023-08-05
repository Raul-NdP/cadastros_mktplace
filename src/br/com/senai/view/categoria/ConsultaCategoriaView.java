package br.com.senai.view.categoria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
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
import br.com.senai.core.service.CategoriaService;
import br.com.senai.view.componentes.table.CategoriaTableModel;

public class ConsultaCategoriaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JTable tableCategorias;
	
	private CategoriaService categoriaService;

	/**
	 * Create the frame.
	 */
	public ConsultaCategoriaView() {
		setTitle("Gerenciar Categorias - Consulta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		this.categoriaService = new CategoriaService();
		
		edtNome = new JTextField();
		edtNome.setColumns(10);
		edtNome.setBounds(82, 53, 484, 33);
		contentPane.add(edtNome);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoriaView view = new CadastroCategoriaView();
				view.setVisible(true);
				dispose();
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNovo.setBounds(576, 10, 147, 33);
		contentPane.add(btnNovo);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					List<Categoria> categorias = categoriaService.listarPor(edtNome.getText());
					CategoriaTableModel model = new CategoriaTableModel(categorias);
					tableCategorias.setModel(model);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListar.setBounds(576, 53, 147, 33);
		contentPane.add(btnListar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 53, 62, 33);
		contentPane.add(lblNome);
		
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
		
		tableCategorias = new JTable();
		scrollPane.setViewportView(tableCategorias);
		
		JPanel molduraAcoes = new JPanel();
		molduraAcoes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		molduraAcoes.setBounds(413, 353, 316, 54);
		contentPane.add(molduraAcoes);
		molduraAcoes.setLayout(null);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int linhaSelecionada = tableCategorias.getSelectedRow();
					if (linhaSelecionada >= 0) {
						int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente excluir?", 
								"Exclusão", JOptionPane.YES_NO_OPTION);
						if (opcao == 0) {
							CategoriaTableModel model = (CategoriaTableModel) tableCategorias.getModel();
							Categoria categoriaSelecionada = model.getPor(linhaSelecionada);
							try {
								categoriaService.removerPor(categoriaSelecionada.getId());
								model.removerPor(linhaSelecionada);
								tableCategorias.updateUI();
								JOptionPane.showMessageDialog(contentPane, "Categoria removida com sucesso");
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
		btnExcluir.setBounds(163, 15, 147, 33);
		molduraAcoes.add(btnExcluir);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int linhaSelecionada = tableCategorias.getSelectedRow();
					if (linhaSelecionada >= 0) {
						CategoriaTableModel model = (CategoriaTableModel) tableCategorias.getModel();
						Categoria categoriaSelecionada = model.getPor(linhaSelecionada);
						CadastroCategoriaView view = new CadastroCategoriaView();
						view.setCategoria(categoriaSelecionada);
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
		btnEditar.setBounds(6, 15, 147, 33);
		molduraAcoes.add(btnEditar);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
	}
	
	private void configurarColuna(int indice, int largura) {
		
		this.tableCategorias.getColumnModel().getColumn(indice).setResizable(false);
		this.tableCategorias.getColumnModel().getColumn(indice).setPreferredWidth(largura);
		
	}
	
	private void configurarTabela() {
		
		final int COLUNA_ID = 0;
		final int COLUNA_NOME = 1;
		
		this.tableCategorias.getTableHeader().setReorderingAllowed(false);
		this.tableCategorias.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.configurarColuna(COLUNA_ID, 90);
		this.configurarColuna(COLUNA_NOME, 250);
	}
	
}
