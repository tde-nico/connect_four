import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectFour extends Table
{
	private	boolean				run;
	private Scanner				in;
	private ArrayList<String>	players;
	private int					currentPlayer;
	private Scanner				fd;

	/**
		ConnectFour Constructor
	*/
	public	ConnectFour()
	{
		super(6, 7, "XO");
		this.run = true;
		this.in = new Scanner(System.in);
		this.players = new ArrayList<String>();
		this.currentPlayer = 0;
	}

	/**
		Adds the current player's pawn into the selected column if possible
		@param x the colomn where to place the pawn
		@param player the index of the player that is plaing
		@return true if is it possible to place the pawn in the selected column
	*/
	public boolean	addPawn(int x, int player)
	{
		int	y;

		y = 0;
		if (x >= this.table.get(0).size() || x < 0
			|| player >= pawns.length() || player < 0
			|| this.table.get(y).get(x).getType() != ' ')
			return (false);
		while (y < this.table.size() && this.table.get(y).get(x).getType() == ' ')
		{
			if (y == (this.table.size() - 1)
				|| (y < (this.table.size() - 1)
				&& this.table.get(y + 1).get(x).getType() != ' '))
			{
				super.addPawn(x, y, player);
			}
			++y;
		}
		return (true);
	}

	/**
		Checks if the current player has won
		@return true in case of victory
	*/
	public boolean ended()
		throws FilledTableException
	{
		int		count;
		int		x, y;
		int		row, col;
		char	lastPlayer;
		int		fill;

		lastPlayer = this.table.get(lastY).get(lastX).getType();

		// Horizontal Check
		for (x = 0; x < (this.table.get(0).size() - 3); x++)
		{
			if (this.table.get(lastY).get(x).getType() == lastPlayer
				&& this.table.get(lastY).get(x + 1).getType() == lastPlayer
				&& this.table.get(lastY).get(x + 2).getType() == lastPlayer
				&& this.table.get(lastY).get(x + 3).getType() == lastPlayer)
				return (true);
		}

		// Vertical Check
		if (lastY < (this.table.size() - 3)
			&& this.table.get(lastY).get(lastX).getType() == lastPlayer
			&& this.table.get(lastY + 1).get(lastX).getType() == lastPlayer
			&& this.table.get(lastY + 2).get(lastX).getType() == lastPlayer
			&& this.table.get(lastY + 3).get(lastX).getType() == lastPlayer)
			return (true);

		// Ascending Diagonal Check
		for (y = 0; y < (this.table.size() - 4); y++)
		{
			count = 0;
			for (row = y, col = 0; row < this.table.size() && col < this.table.get(0).size(); ++row, ++col)
			{
				if(this.table.get(row).get(col).getType() == lastPlayer)
				{
					++count;
					if(count >= 4) 
						return (true);
				}
				else
					count = 0;
			}
		}
		for (x = 1; x < this.table.get(0).size() - 4; x++)
		{
			count = 0;
			for(row = 0, col = x; row < this.table.size() && col < this.table.get(0).size(); ++row, ++col)
			{
				if(this.table.get(row).get(col).getType() == lastPlayer)
				{
					++count;
					if(count >= 4)
						return (true);
				}
				else
					count = 0;
			}
		}

		// Descending Diagonal Check
		for (y = 0; y <= (this.table.size() - 4); y++)
		{
			count = 0;
			for(row = y, col = (this.table.get(0).size() - 1); row < this.table.size() && col >= 0; ++row, --col)
			{
				if(this.table.get(row).get(col).getType() == lastPlayer)
				{
					++count;
					if(count >= 4) 
						return (true);
				}
				else
					count = 0;
			}
		}
		for (x = (this.table.get(0).size() - 2); x >= 3; --x)
		{
			count = 0;
			for( row = 0, col = x; row < this.table.size() && col >= 0; ++row, --col)
			{
				if(this.table.get(row).get(col).getType() == lastPlayer){
					++count;
					if(count >= 4)
						return (true);
				}
				else
					count = 0;
			}
		}

		// filled table
		fill = 0;
		for (x = 0; x < this.table.get(0).size(); ++x)
		{
			if (this.table.get(0).get(x).getType() != ' ')
				++fill;
		}
		if (fill == this.table.get(0).size())
			throw new FilledTableException(); 

		return (false);
	}


	/**
		Prints the table
	*/
	public void print()
	{
		for (int x = 0; x < this.table.get(0).size(); ++x)
		{
			System.out.print("--");
		}
		System.out.println("-");
		for (int x = 0; x < this.table.get(0).size(); ++x)
		{
			System.out.print(" ");
			System.out.print(x);
		}
		System.out.println();
		for (int y = 0; y < this.table.size(); ++y)
		{
			for (int x = 0; x < this.table.get(0).size(); ++x)
			{
				System.out.print(' ');
				System.out.print(this.table.get(y).get(x).getPawn());
			}
			System.out.println("\n");
		}
		for (int x = 0; x < this.table.get(0).size(); ++x)
		{
			System.out.print("--");
		}
		System.out.println("-");
	}

	/**
		Saves the current game
		@param filename the name of the saving
	*/
	public void	saveGame(String filename)
	{
		PrintWriter	saves;

		try {
			saves = new PrintWriter(filename + ".dat");
		} catch (IOException e) {
			System.err.println(Pawn.RED + "Error while writing \"" + filename + ".dat\"" + Pawn.RESET);
			return ;
		}

		for (String p : this.players)
			saves.println(p);
		saves.println(this.currentPlayer);
		for (int y = 0; y < this.table.size(); ++y)
		{
			for (int x = 0; x < this.table.get(0).size(); ++x)
			{
				if (x > 0)
					saves.print(';');
				saves.print(this.table.get(y).get(x).getPawn());
			}
			if (y < this.table.size() - 1)
				saves.println();
		}

		saves.close();
	}

	/**
		Prints the game's help informations
	*/
	public void	printGameHelp()
	{
		System.out.println(
			"\n" +
			"help:\t\t\tprints the help\n" +
			"exit / quit:\t\texits the match\n" +
			"pause / save [filename]:\tpause the match (save and quit)\n" +
			"%NUMBER%:\t\ttries to place the pawn into the selected column\n"
		);
	}

	/**
		Starts the game
	*/
	public void	play()
	{
		print();
		this.run = true;
		while (this.run)
		{
			String	cmd;

			System.out.print(this.players.get(this.currentPlayer) + " is playing: ");
			if (!this.in.hasNextInt())
			{
				cmd = in.next();
				if (cmd.equals("exit") || cmd.equals("quit"))
					this.run = false;
				else if (cmd.equals("pause") || cmd.equals("save"))
				{
					saveGame(in.next());
					this.run = false;
				}
				if (cmd.equals("help"))
					printGameHelp();
				continue ;
			}
			if (!addPawn(this.in.nextInt(), this.currentPlayer)) {
				System.out.println(Pawn.YELLOW + "Invalid column!" + Pawn.RESET);
				continue ;
			}

			try {
				if (ended())
				{
					System.out.println("\n" + Pawn.GREEN + this.players.get(this.currentPlayer) + " has won!!!" + Pawn.RESET + "\n");
					this.run = false;
				}
			} catch (FilledTableException e) {
				System.out.println("\nNobody won :(\n");
				this.run = false;
			}

			this.currentPlayer = (this.currentPlayer + 1) % this.players.size();
			print();
		}
	}

	/**
		Loads the current game
		@param filename the name of the saving
		@return True if it has no errors
	*/
	public boolean	loadGame(String filename)
	{
		FileReader	saves;
		int			x,y;

		try {
			saves = new FileReader(filename + ".dat");
		} catch (FileNotFoundException e) {
			System.err.println(Pawn.RED + "\"" + filename + ".dat\" file not found!" + Pawn.RESET);
			return (false);
		}

		fd = new Scanner(saves);
		while (!fd.hasNextInt())
			this.players.add(fd.next());
		this.currentPlayer = fd.nextInt();
		y = 0;
		fd.nextLine();
		while (fd.hasNext())
		{
			x = 0;
			for (String pawn : fd.nextLine().split(";"))
			{
				if (!pawn.subSequence(0, Pawn.RESET.length()).equals(Pawn.RESET))
				{
					this.table.get(y).get(x).setColor(pawn.subSequence(0, Pawn.BLACK.length()).toString());
					this.table.get(y).get(x).setType(pawn.subSequence(Pawn.BLACK.length(), Pawn.BLACK.length() + 1).charAt(0));
				}
				++x;
			}
			++y;
		}

		try {
			saves.close();
		} catch (IOException e) {
			System.err.println(Pawn.RED + "Error while closing \"" + filename + ".dat\"" + Pawn.RESET);
			return (false);
		}
		return (true);
	}

	/**
		Prints the menu's help informations
	*/
	public void	printMenuHelp()
	{
		System.out.println(
			"\n" +
			"help:\t\t\tprints the help\n" +
			"exit / quit:\t\texits the program\n" +
			"start / new:\t\tstarts a new game\n" +
			"continue / load [filename]:\tloads a previously saved game\n"
		);
	}

	/**
		Starts the menu
	*/
	public void	start()
	{
		String	menuCmd;
		boolean	done = false;
		boolean	load;

		System.out.println(Pawn.GREEN + "\"help\" for help" + Pawn.RESET);
		while (!done)
		{
			System.out.print("Connect4 > ");
			menuCmd = in.next();
			if (menuCmd.equals("start") || menuCmd.equals("new"))
			{
				System.out.print("Insert the Name of the FIRST player: ");
				this.players.add(in.next());
				System.out.print("Insert the Name of the SECOND player: ");
				this.players.add(in.next());
				this.currentPlayer = 0;
				reset();
				play();
				this.players.clear();
			}
			else if (menuCmd.equals("continue") || menuCmd.equals("load"))
			{
				String filename = in.next();
				try {
					load = loadGame(filename);
				} catch (Exception e) {
					System.err.println(Pawn.RED + "\"" + filename + ".dat\" is currupted!" + Pawn.RESET);
					load = false;
				}
				if (load)
				{
					play();
					this.players.clear();
				}
			}
			else if (menuCmd.equals("help"))
				printMenuHelp();
			else if (menuCmd.equals("exit") || menuCmd.equals("quit"))
				done = !done;
			else
				System.out.println(Pawn.RED + menuCmd + ": Command not found" + Pawn.RESET);
		}
	}
}