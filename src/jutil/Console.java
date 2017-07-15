package jutil;

import java.io.*;

/**
 * Console is the base class for console user interfaces (CUIs).&nbsp;It
 * provides a simple user interface, a simplified Model-View architecture,
 * an interface to secondary memory, an extensible help facility, and an
 * extensible error handling strategy.
 * @author Pearce
 */
abstract public class Console {
    /**
     * Standard input stream.
     */
 	protected BufferedReader stdin;
 	/**
 	 * Standard output stream.
 	 */
	protected PrintWriter stdout;
	/**
	 * Standard error stream.
	 */
	protected PrintWriter stderr;
	/**
	 * The prompt displayed by the control loop.
	 */
	protected String prompt = "-> ";
	/**
	 * Reference to the model.&nbsp;This is the component that
	 * encapsulates application data and logic.
	 */
	protected Serializable model;
	/**
	 * Name of file where the model will be saved.
	 */
	private String fname = null;
	/**
	 * This flag should be set to true each time the
	 * model is modified.
	 */
	protected boolean unsavedChanges = false;
	/**
	 * This method executes application-specific commands
	 * and must be implemented by subclasses.
	 */
	abstract protected String execute(String cmmd) throws AppError;
	/**
	 * @param model    The application model.
	 */
	public Console(Serializable model) {
		this.model = model;
		stdout = new PrintWriter(
			new BufferedWriter(
				new OutputStreamWriter(System.out)), true);
		stderr = new PrintWriter(
			new BufferedWriter(
				new OutputStreamWriter(System.out)), true);
		stdin = new BufferedReader(
			new InputStreamReader(System.in));
	}

	public Console() { this(null); }
	/**
	 * Perpetually:
	 * <ul>
	 * <li> Prompts user for a command </li>
	 * <li> Reads command </li>
	 * <li> Executes application-independent commands </li>
	 * <li> Passes application-dependent commands to execute </li>
	 * <li> displays result </li>
	 *  </ul>
	 */
	public void controlLoop() {

		String cmmd = " ";
		String result = " ";
		stdout.println("type \"help\" for commands");

		while(true) {
			try {
				stdout.print(prompt);
				stdout.flush(); // force the write
				cmmd = stdin.readLine();
				if (cmmd == null) {
					stdout.println("type \"help\" for commands");
					continue;
				}
				cmmd = cmmd.trim(); // trim white space

				if (cmmd.equalsIgnoreCase("quit")) {
					saveChanges();
					break;
				}

				if (cmmd.equalsIgnoreCase("help")) {
					help();
					continue;
				}

				if (cmmd.equalsIgnoreCase("about")) {
					about();
					continue;
				}

				if (cmmd.equalsIgnoreCase("save")) {
					save(false);
					stdout.println("done");
					continue;
				}

				if (cmmd.equalsIgnoreCase("save as")) {
					save(true);
					stdout.println("done");
					continue;
				}

				if (cmmd.equalsIgnoreCase("load")) {
					load();
					stdout.println("done");
					continue;
				}

				// application-specific command:
				result = execute(cmmd);
				stdout.println(result);

			} catch(AppError exp) {
				handle(exp);
				continue;
			} catch (Exception exp) {
				exp.printStackTrace();
				stderr.println("Serious error, " + exp);
				break;
			}
		} // while
		stdout.println("bye");
	} // controlLoop

	// overidables:

	/**
	 * Displays help for application-independent commands.&nbsp;This method
	 * may be overriden in subclasses to provide application-specific
	 * help, where super.help can be called.
	 */
	protected void help() {
		stdout.println("Console Help Menu:");
		stdout.println("   about:   displays application information");
		stdout.println("   help:    displays this message");
		stdout.println("   save:    saves model to a file");
		stdout.println("   save as: saves model to a new file");
		stdout.println("   load:    load model from a file");
		stdout.println("   quit:    terminate application");
	}
    /**
     * Prints information about the console.&nbsp;This method
	 * may be overriden in subclasses to provide application-specific
	 * information, where super.about can be called.
	 */
	protected void about() {
		stdout.println("Console Framework");
		stdout.println("copyright (c) 2001, all rights reserved\n");
	}
    /**
     * Handles all application-specific exceptions by printing an
     * error message to stderr.&nbsp;This method may be overriden in
     * subclasses to provide application-specific error handling
     * strategies.
     */
	protected void handle(AppError exp) {
		stderr.println("Application error, " + exp.getMessage());
	}
	/**
	 * Prompts user to save model if there are unsaved changes.
	 * This method is called when the application quits or when the
	 * user attempts to load a new model.
	 */
	private void saveChanges() throws AppError, IOException {
		if (model != null && unsavedChanges) {
			stdout.print("Save changes?(y/n): ");
			stdout.flush(); // force the write
			String response = stdin.readLine();
			if (response == null) {
				stdout.println("changes discarded");
			}
			if (response.equals("y"))
				save(false);
			else
				stdout.println("changes discarded");
		}
	}
	/**
	 * Saves model to a file.&nbsp;If no file name has been specified yet,
	 * or if this method was called by the "save as" command, then
	 * the user will be prompted for a file name.
	 *
	 * @param  saveAs   true if this method was called by "save as" command.
	 */
	protected void save(boolean saveAs) throws AppError {
		try {
			if (model != null && (unsavedChanges || saveAs)) {
				if (saveAs) {
					if (unsavedChanges) saveChanges();
					fname = null;
				}
				if (fname == null) fname = getFname();
				ObjectOutputStream obstream =
					new ObjectOutputStream(
						new FileOutputStream(fname));
				obstream.writeObject(model);
				obstream.flush();
				obstream.close();
				unsavedChanges = false;
			}
		} catch(Exception e) {
			throw new AppError(e.getMessage());
		}
	}
	/**
	 * Loads a mnodel from a file.&nbsp;User will be prompted for a file name.
	 */
	protected void load() throws AppError {
		try {
			if (model != null) {
				saveChanges();
				fname = getFname();
				ObjectInputStream ois =
					new ObjectInputStream(new FileInputStream(fname));
				model = (Serializable) ois.readObject();
				ois.close();
				unsavedChanges = false;
			}
		} catch(Exception e) {
			throw new AppError(e.getMessage());
		}
	}
	/**
	 * A helper function that prompts the user for a file name.
	 */
	private String getFname() throws IOException {
		stdout.print("enter file name: ");
		stdout.flush(); // force the write
		String result = stdin.readLine();
		if (result == null) return "unknown";
		return result;
	}
}
