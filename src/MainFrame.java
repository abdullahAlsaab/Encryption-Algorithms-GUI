import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// private JTextField inputkey;
	private JLabel lblB;
	private int MAX_KEY = 0;
	private int keyFieldCount = 0;
	public int columnIndex = 0;

	// Create GUI components

	JTextField inputtext = new JTextField();
	JLabel results = new JLabel();
	JButton openbtn = new JButton("Open file");
	JButton savebtn = new JButton("Save results into file");
	JPanel keyspanel = new JPanel();
	JLabel brief = new JLabel("Enter Here Brief about your algorithm");

	// Get Keys values from user
	public String[] getKeyValue() {
		java.util.List<String> ListOfKeys = new java.util.ArrayList<>();
		for (Component comp : keyspanel.getComponents()) {
			if (comp instanceof JTextField) {
				ListOfKeys.add(((JTextField) comp).getText());
			}
		}
		String[] ArrayOfKeys = ListOfKeys.toArray(new String[0]);
		return ArrayOfKeys;
	}

	// Show error messege
	public void Error(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	Object[][] ArrayOfAlgorithms = {
			{ 1, 2, 3, 4 }, // Algorithm place
			{ "RSA", "HMAC", "Orange" }, // Algorithm name
			{ "I love RSA", "I love HMAC", "I love Orange" }, // Algorithm brief
			{ 2, 3, 2, 1 } // Maximum keys of an algorithm
	};

	// Lunch the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initialize the MAin Frame
	public MainFrame() {
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(110, 110, 600, 960);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblkey = new JLabel("Key");
		lblkey.setBounds(50, 325, 85, 22);
		lblkey.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblkey);

		// Fill the drop menu of algorithms && Each time the user clicks an algorithm
		// the brief of it changes
		JComboBox ChooseAlgorithm = new JComboBox();
		ChooseAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < ArrayOfAlgorithms[1].length; j++) {
					if (ArrayOfAlgorithms[1][j].equals(ChooseAlgorithm.getSelectedItem())) {
						columnIndex = j;
						break;
					}
				}
				Object valueUnderneath = ArrayOfAlgorithms[2][columnIndex];
				brief.setText((String) valueUnderneath);
				MAX_KEY = (int) ArrayOfAlgorithms[3][columnIndex];
				System.out.print(MAX_KEY);

			}
		});
		ChooseAlgorithm.setBounds(39, 103, 512, 48);
		contentPane.add(ChooseAlgorithm);
		for (Object element : ArrayOfAlgorithms[1]) {
			ChooseAlgorithm.addItem(element);
		}

		lblB = new JLabel("brief about the algorithem");
		lblB.setBounds(50, 174, 280, 22);
		lblB.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB);

		JPanel panel = new JPanel();
		panel.setBounds(39, 200, 512, 69);
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel);
		panel.setLayout(null);

		brief.setBounds(10, 11, 502, 47);
		panel.add(brief);
		brief.setVerticalAlignment(SwingConstants.TOP);
		brief.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblB_1 = new JLabel("Choose your Encryption Algorithm");
		lblB_1.setBounds(52, 70, 499, 22);
		lblB_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB_1);

		JButton addkey = new JButton("Add key");
		addkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (keyFieldCount < MAX_KEY) {
					JTextField inputkey = new JTextField();
					inputkey.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputkey.getPreferredSize().height));
					inputkey.setAlignmentX(Component.CENTER_ALIGNMENT);
					// inputkey = new JTextField();
					// inputkey.setBounds(38, 389, 197, 35);
					// contentPane.add(inputkey);
					// keyspanel.setColumns(2);
					keyspanel.add(inputkey);
					keyspanel.revalidate();
					keyspanel.repaint();
					keyFieldCount++;
				} else {
					Error("Maximum number of key fields reached.");
				}
			}
		});
		addkey.setBounds(439, 363, 95, 40);
		addkey.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		contentPane.add(addkey);

		inputtext.setBounds(38, 518, 513, 35);
		contentPane.add(inputtext);
		inputtext.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Enter Plaintext or Ciphertext");
		lblNewLabel_1.setBounds(52, 484, 302, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1);

		openbtn.setBounds(241, 294, 108, 35);
		openbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(openbtn);

		JButton DecBtn = new JButton("Decrypt");
		DecBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		DecBtn.setBounds(298, 612, 134, 57);
		contentPane.add(DecBtn);

		JButton EncBtn = new JButton("Encrypt");
		EncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		EncBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		EncBtn.setBounds(139, 612, 134, 57);
		contentPane.add(EncBtn);

		JButton HmacBtn = new JButton("HMAC");
		HmacBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement HMAC Algorithm
			}
		});
		HmacBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HmacBtn.setBounds(426, 680, 108, 40);
		contentPane.add(HmacBtn);

		JButton HashBtn = new JButton("HASH");
		HashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here Implement Hash algorithm
			}
		});
		HashBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HashBtn.setBounds(50, 680, 108, 40);
		contentPane.add(HashBtn);

		JButton DSBtn = new JButton("Digital Signiture");
		DSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement Digital Signutre Algorithm
			}
		});
		DSBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DSBtn.setBounds(215, 680, 134, 40);
		contentPane.add(DSBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(38, 781, 512, 48);
		contentPane.add(panel_1);

		results.setFont(new Font("Tahoma", Font.PLAIN, 14));
		results.setHorizontalAlignment(SwingConstants.LEFT);
		results.setBounds(10, 0, 502, 47);
		panel_1.add(results);

		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResults.setBounds(50, 748, 280, 22);
		contentPane.add(lblResults);
		savebtn.setForeground(SystemColor.activeCaptionText);
		savebtn.setBackground(new Color(240, 240, 240));

		savebtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		savebtn.setBounds(201, 855, 169, 35);
		contentPane.add(savebtn);

		keyspanel.setBackground(SystemColor.activeCaption);
		keyspanel.setBounds(39, 363, 261, 110);
		contentPane.add(keyspanel);
		keyspanel.setLayout(new BoxLayout(keyspanel, BoxLayout.Y_AXIS));

		/*
		 * _________________________________________________________________________________________________________________________
		 */

		// Open file function
		openbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
						inputtext.setText(content);
					} catch (Exception ex) {
						// showError("Error reading file: " + ex.getMessage());
					}
				}
			}
		});

		// Save file function
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					if (!fileToSave.getName().contains(".")) {
						fileToSave = new File(fileToSave.toString() + ".txt");
					}
					try (FileWriter fileWriter = new FileWriter(fileToSave)) {
						fileWriter.write(results.getText());
					} catch (IOException ex) {
						Error("Error occured, " + ex.getMessage());
					}
				}
			}
		});

	}
}