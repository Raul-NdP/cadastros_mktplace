package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.senai.view.categoria.CadastroCategoriaView;
import br.com.senai.view.horario.CadastroHorarioView;
import br.com.senai.view.restaurante.ConsultaRestauranteView;

public class PrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView frame = new PrincipalView();
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
	public PrincipalView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 420);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuCadastros = new JMenu("Cadastros");
		menuBar.add(menuCadastros);
		
		JMenuItem subMenuCategorias = new JMenuItem("Categorias");
		subMenuCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoriaView view = new CadastroCategoriaView();
				view.setVisible(true);
			}
		});
		menuCadastros.add(subMenuCategorias);
		
		JMenuItem subMenuRestaurantes = new JMenuItem("Restaurantes");
		subMenuRestaurantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaRestauranteView view = new ConsultaRestauranteView();
				view.setVisible(true);
			}
		});
		menuCadastros.add(subMenuRestaurantes);
		
		JMenu menuConfiguracoes = new JMenu("Configurações");
		menuBar.add(menuConfiguracoes);
		
		JMenuItem subMenuHorarios = new JMenuItem("Horários");
		subMenuHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroHorarioView view = new CadastroHorarioView();
				view.setVisible(true);
			}
		});
		menuConfiguracoes.add(subMenuHorarios);
		
		JMenu menuSair = new JMenu("Sair");
		menuSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		menuBar.add(menuSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
