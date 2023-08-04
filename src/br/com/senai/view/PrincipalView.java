package br.com.senai.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Closeable;

public class PrincipalView extends JFrame {

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
				ConsultaCategoriaView view = new ConsultaCategoriaView();
				view.setVisible(true);
			}
		});
		menuCadastros.add(subMenuRestaurantes);
		
		JMenu menuConfiguracoes = new JMenu("Configurações");
		menuBar.add(menuConfiguracoes);
		
		JMenuItem subMenuHorarios = new JMenuItem("Horários");
		subMenuHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarHorarioView view = new GerenciarHorarioView();
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
