	package com.redhat.eclipseDependencyAnalysis.daWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.redhat.eclipseDependencyAnalysis.communication.OAuthConnect;

/**
 * The Class Login is the first page of the Wizard. It handles calls the OAuthConnect class which connects the user to the server.
 * Cant be finished or flipped to next page if the user wasnt logged in succesfully.
 */
public class Login extends WizardPage {
	
	private Composite container;
	private Text textURL;
	private Text textUSR;
	private Text textPASS;
	private String login;

	/**
	 * Creates the wizard.
	 */
	public Login() {
		super("Page one");
		setTitle("Project Newcastle Build Process");
		setDescription("Here you can override default PNC server settings.");
	}

	/**
	 * Creates contents of the wizard. Contains btnLogin which contains updateButtons() method. This method calls the
	 * canFlipToNextPage() which logs user to server. 
	 *
	 * @param parent the parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		
		setControl(container);
		
		Group grpPncServerDetails = new Group(container, SWT.NONE);
		FormData fd_grpPncServerDetails = new FormData();
		fd_grpPncServerDetails.right = new FormAttachment(100, -10);
		fd_grpPncServerDetails.top = new FormAttachment(0, 10);
		fd_grpPncServerDetails.left = new FormAttachment(0, 10);
		grpPncServerDetails.setLayoutData(fd_grpPncServerDetails);
		grpPncServerDetails.setText("PNC Server Details");
		grpPncServerDetails.setLayout(new FormLayout());
		
		Label lblUrl = new Label(grpPncServerDetails, SWT.NONE);
		FormData fd_lblUrl = new FormData();
		fd_lblUrl.right = new FormAttachment(0, 64);
		fd_lblUrl.top = new FormAttachment(0, 20);
		fd_lblUrl.left = new FormAttachment(0, 5);
		lblUrl.setLayoutData(fd_lblUrl);
		lblUrl.setText("URL");
		
		Label lblUsername = new Label(grpPncServerDetails, SWT.NONE);
		FormData fd_lblUsername = new FormData();
		fd_lblUsername.right = new FormAttachment(0, 64);
		fd_lblUsername.top = new FormAttachment(0, 60);
		fd_lblUsername.left = new FormAttachment(0, 5);
		lblUsername.setLayoutData(fd_lblUsername);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(grpPncServerDetails, SWT.NONE);
		FormData fd_lblPassword = new FormData();
		fd_lblPassword.right = new FormAttachment(0, 64);
		fd_lblPassword.top = new FormAttachment(0, 100);
		fd_lblPassword.left = new FormAttachment(0, 5);
		lblPassword.setLayoutData(fd_lblPassword);
		lblPassword.setText("Password");
		
		textURL = new Text(grpPncServerDetails, SWT.BORDER);
		FormData fd_textURL = new FormData();
		fd_textURL.left = new FormAttachment(lblUrl, 55);
		fd_textURL.right = new FormAttachment(100, -5);
		fd_textURL.top = new FormAttachment(0, 20);
		textURL.setLayoutData(fd_textURL);
		textURL.setText("https://ncl-keycloak.stage.engineering.redhat.com/auth/realms/pncredhat/protocol/openid-connect/token");
		
		textUSR = new Text(grpPncServerDetails, SWT.BORDER);
		FormData fd_textUSR = new FormData();
		fd_textUSR.left = new FormAttachment(lblUsername, 55);
		fd_textUSR.right = new FormAttachment(100, -5);
		fd_textUSR.top = new FormAttachment(0, 60);
		textUSR.setLayoutData(fd_textUSR);
		textUSR.setText("");
		
		textPASS = new Text(grpPncServerDetails, SWT.BORDER | SWT.PASSWORD);
		FormData fd_textPASS = new FormData();
		fd_textPASS.right = new FormAttachment(100, -5);
		fd_textPASS.left = new FormAttachment(lblPassword, 55);
		fd_textPASS.top = new FormAttachment(0, 100);
		textPASS.setLayoutData(fd_textPASS);
		textPASS.setText("");
		
		Button btnLogin = new Button(container, SWT.NONE);
		
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Login button selected.");
				getWizard().getContainer().updateButtons();
			}
		});
		
		FormData fd_btnLogin = new FormData();
		fd_btnLogin.top = new FormAttachment(grpPncServerDetails, 6);
		fd_btnLogin.right = new FormAttachment(grpPncServerDetails, 0, SWT.RIGHT);
		btnLogin.setLayoutData(fd_btnLogin);
		btnLogin.setText("Login");
		
	}
	
	/** 
	 * returns true if all the textfields aren't empty.
	 */
	@Override
	public boolean isPageComplete(){
		if(textURL.getText().isEmpty() || textUSR.getText().isEmpty() || textPASS.getText().isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * If the login was successful the user can then click next button.
	 * The method won't try to login if the page is not completely filled with needed data.
	 */
	@Override
	public boolean canFlipToNextPage(){
		if(isPageComplete()){
			try{
				String[] args = new String[]{getTextURL(), "pncdirect", getTextUSR(), getTextPASS()};
				System.out.println("Trying to login.");
				login = OAuthConnect.getAccessToken(args[0], args[1], args[2], args[3]);
				
				if(login != ""){
					System.out.println(">>> Access token: " + login);
					System.out.println("Login succesfull.");
					return true;
				} else {
					System.out.println("Failed to login.");
					return true;
				}
				
				
			} catch(Exception e) {
				System.out.println("Failed to login.");
				e.printStackTrace();
				return true;
			}
		} else {
			System.out.println("Login page not complete.");
			return true;
		}
	}
	
	/**
	 * Gets the login token.
	 *
	 * @return the login
	 */
	public String getLogin(){
		return login;
	}
	
	/**
	 * Gets the text url.
	 *
	 * @return the text url
	 */
	public String getTextURL() {
		return textURL.getText();
	} 

	

	/**
	 * Gets the text ussername.
	 *
	 * @return the text usr
	 */
	public String getTextUSR() {
		return textUSR.getText();
	}

	

	/**
	 * Gets the text password.
	 *
	 * @return the text pass
	 */
	public String getTextPASS() {
		return textPASS.getText();
	}

	
	/**
	 * If the button Finish can be pressed.
	 *
	 * @return the boolean
	 */
	public Boolean canFinish(){
		return false;
	}
	
}
