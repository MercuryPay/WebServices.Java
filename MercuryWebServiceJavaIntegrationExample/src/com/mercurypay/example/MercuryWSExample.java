package com.mercurypay.example;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.mercurypay.ws.sdk.*;
import javax.swing.border.EtchedBorder;

/**
 * Mercury Payment Systems WebServices Platform Java Example
 * <br /><br />
 * ©2013 Mercury Payment Systems, LLC - all rights reserved.
 * <br /><br />
 * Disclaimer:
 * <br />
 * This software and all specifications and documentation contained
 * herein or provided to you hereunder (the "Software") are provided
 * free of charge strictly on an "AS IS" basis. No representations or
 * warranties are expressed or implied, including, but not limited to,
 * warranties of suitability, quality, merchantability, or fitness for a
 * particular purpose (irrespective of any course of dealing, custom or
 * usage of trade), and all such warranties are expressly and
 * specifically disclaimed. Mercury Payment Systems shall have no
 * liability or responsibility to you nor any other person or entity
 * with respect to any liability, loss, or damage, including lost
 * profits whether foreseeable or not, or other obligation for any cause
 * whatsoever, caused or alleged to be caused directly or indirectly by
 * the Software. Use of the Software signifies agreement with this
 * disclaimer notice.
 * 
 * @author Mercury Payment Systems
 */
public class MercuryWSExample
{

	private JFrame frmMPSWebServices;
	private JTextArea taDSIXML;
	private JButton btnSubmit;
	private JLabel lblStatus;
	private JComboBox<String> cmbWebMethod;

	private final String mCreditTran = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>118725340908147</MerchantID>\n\t\t<TranType>Credit</TranType>\n\t\t<TranCode>Sale</TranCode>\n\t\t<InvoiceNo>1</InvoiceNo>\n\t\t<RefNo>1</RefNo>\n\t\t<Memo>MPS Example XML v1.0 - Java SDK</Memo>\n\t\t<PartialAuth>Allow</PartialAuth>\n\t\t<Frequency>OneTime</Frequency>\n\t\t<RecordNo>RecordNumberRequested</RecordNo>\n\t\t<Account>\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>Swiped</AccountSource>\n\t\t\t<EncryptedBlock>F40DDBA1F645CC8DB85A6459D45AFF8002C244A0F74402B479ABC9915EC9567C81BE99CE4483AF3D</EncryptedBlock>\n\t\t\t<EncryptedKey>9012090B01C4F200002B</EncryptedKey>\n\t\t\t<Name>MPS TEST</Name>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>1.00</Purchase>\n\t\t</Amount>\n\t\t<TerminalName>MPS Java SDK</TerminalName>\n\t\t<ShiftID>MPS Shift</ShiftID>\n\t\t<OperatorID>MPS Operator</OperatorID>\n\t</Transaction>\n</TStream>",
			mGiftTran = "<TStream>\n\t<Transaction>\n\t\t<IpPort>9100</IpPort>\n\t\t<MerchantID>118725340908147</MerchantID>\n\t\t<TranType>PrePaid</TranType>\n\t\t<TranCode>Sale</TranCode>\n\t\t<InvoiceNo>4</InvoiceNo>\n\t\t<RefNo>0001</RefNo>\n\t\t<Memo>MPS Example XML v1.0 - Java SDK</Memo>\n\t\t<Account>\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>Swiped</AccountSource>\n\t\t\t<EncryptedBlock>CF7F1CA56296E8E2083047007D85C388C9DA9A21936912995524CD4EE50E4C77</EncryptedBlock>\n\t\t\t<EncryptedKey>9500030000040C20001F</EncryptedKey>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>1.00</Purchase>\n\t\t</Amount>\n\t\t<TerminalName>MPS Java SDK</TerminalName>\n\t\t<ShiftID>MPS Shift</ShiftID>\n\t\t<OperatorID>MPS Operator</OperatorID>\n\t</Transaction>\n</TStream>";
	private String mWSURL = "https://w1.mercurydev.net/ws/ws.asmx";
	
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MercuryWSExample window = new MercuryWSExample();
					if (args.length > 0)
						window.mWSURL = args[0];
					window.frmMPSWebServices.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MercuryWSExample()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmMPSWebServices = new JFrame();
		frmMPSWebServices.setTitle("Mercury Web Services Integration Example");
		frmMPSWebServices.setIconImage(Toolkit.getDefaultToolkit().getImage(MercuryWSExample.class.getResource("/com/mercurypay/example/icon.png")));
		frmMPSWebServices.setBounds(100, 100, 640, 648);
		frmMPSWebServices.setResizable(false);
		frmMPSWebServices.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMPSWebServices.getContentPane().setLayout(null);

		cmbWebMethod = new JComboBox<String>();
		cmbWebMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String selectedItem = (String) cb.getSelectedItem();
				setTextArea(selectedItem);
			}
		});
		cmbWebMethod.setModel(new DefaultComboBoxModel<String>(new String[] {
				"CreditTransaction", "GiftTransaction" }));
		cmbWebMethod.setBounds(369, 56, 193, 27);
		frmMPSWebServices.getContentPane().add(cmbWebMethod);

		JLabel lblWebMethod = new JLabel("WebMethod:");
		lblWebMethod.setLabelFor(cmbWebMethod);
		lblWebMethod.setBounds(369, 33, 85, 16);
		frmMPSWebServices.getContentPane().add(lblWebMethod);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogo.setVerticalAlignment(SwingConstants.TOP);
		lblLogo.setIcon(new ImageIcon(MercuryWSExample.class.getResource("/com/mercurypay/example/logo.png")));
		lblLogo.setBounds(26, 33, 254, 36);
		lblLogo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmMPSWebServices.getContentPane().add(lblLogo);

		JLabel lblDsiXml = new JLabel("DSI XML:");
		lblDsiXml.setBounds(26, 86, 61, 16);
		frmMPSWebServices.getContentPane().add(lblDsiXml);

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submitRequest();
			}
		});
		btnSubmit.setBounds(503, 580, 117, 29);
		frmMPSWebServices.getContentPane().add(btnSubmit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 113, 579, 458);
		frmMPSWebServices.getContentPane().add(scrollPane);

		taDSIXML = new JTextArea();
		taDSIXML.setText(mCreditTran);
		taDSIXML.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		taDSIXML.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollPane.setViewportView(taDSIXML);

		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(27, 583, 607, 16);
		frmMPSWebServices.getContentPane().add(lblStatus);
	}
	
	/**
	 * Set Text Area to predefined example according to drop-down box
	 * @param type
	 * 			Type of example transaction to 
	 */
	private void setTextArea(String type)
	{
		if (type.equals("CreditTransaction"))
			taDSIXML.setText(mCreditTran);
		else if (type.equals("GiftTransaction"))
			taDSIXML.setText(mGiftTran);	
	}
	
	/**
	 * Submit XML request defined in taDSIXML using com.mercurypay.sdk.MPSWebRequest
	 */
	private void submitRequest()
	{
		try
		{
			changeControlState(false);
			lblStatus.setText("Sending Request...");
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						MPSWebRequest mpswr = new MPSWebRequest(mWSURL);
						mpswr.addParameter("tran", taDSIXML.getText()); //Set WebServices 'tran' parameter to the XML transaction request
						mpswr.addParameter("pw", "123E2E"); //Set merchant's WebServices password
						mpswr.setWebMethodName((String)cmbWebMethod.getSelectedItem()); //Set WebServices webmethod to selected type
						mpswr.setTimeout(10); //Set request timeout to 10 seconds
						
						String mpsResponse = mpswr.sendRequest();
						lblStatus.setText("");
						showPopup("Response XML String",mpsResponse.replace(">\t", ">\n\t"));
					}
					catch (Exception e)
					{
						showPopup("Exception", e.toString());
					}
					finally
					{
						lblStatus.setText("");
						changeControlState(true);
						@SuppressWarnings("unused")  // Properly disposing of current thread
						Thread curthread = Thread.currentThread();
						curthread = null;
					}
				}
			}).start();
		}
		catch (Exception e)
		{
			showPopup("Exception", e.toString());
		}
	}
	
	/**
	 * Change state of controls in Swing UI
	 * @param enabled
	 * 			Enabled status of Swing controls on frmMPSWebServices
	 */
	private void changeControlState(boolean enabled)
	{
		btnSubmit.setEnabled(enabled);
		taDSIXML.setEnabled(enabled);
		cmbWebMethod.setEnabled(enabled);
	}

	/**
	 * Show Swing OptionPane popup window
	 * @param title
	 * 			Popup window title
	 * @param message
	 * 			Popup message content
	 */
	private void showPopup(String title, String message)
	{
		JOptionPane.showMessageDialog(frmMPSWebServices, message, title, JOptionPane.PLAIN_MESSAGE);
	}
}
