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
	private int					current_player;
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
		this.current_player = 0;
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
				//System.out.printf("%d %d\n", y, x);
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
		char	last_player;
		int		fill;

		last_player = this.table.get(last_y).get(last_x).getType();

		// Horizontal Check
		for (x = 0; x < (this.table.get(0).size() - 3); x++)
		{
			if (this.table.get(last_y).get(x).getType() == last_player
				&& this.table.get(last_y).get(x + 1).getType() == last_player
				&& this.table.get(last_y).get(x + 2).getType() == last_player
				&& this.table.get(last_y).get(x + 3).getType() == last_player)
				return (true);
		}

		// Vertical Check
		if (last_y < (this.table.size() - 3)
			&& this.table.get(last_y).get(last_x).getType() == last_player
			&& this.table.get(last_y + 1).get(last_x).getType() == last_player
			&& this.table.get(last_y + 2).get(last_x).getType() == last_player
			&& this.table.get(last_y + 3).get(last_x).getType() == last_player)
			return (true);

		// Ascending Diagonal Check
		for (y = 0; y < (this.table.size() - 4); y++)
		{
			count = 0;
			for (row = y, col = 0; row < this.table.size() && col < this.table.get(0).size(); ++row, ++col)
			{
				if(this.table.get(row).get(col).getType() == last_player)
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
				if(this.table.get(row).get(col).getType() == last_player)
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
				if(this.table.get(row).get(col).getType() == last_player)
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
				if(this.table.get(row).get(col).getType() == last_player){
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
	*/
	public void	save_game(String filename)
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
		saves.println(this.current_player);
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
	public void	print_game_help()
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

			System.out.print(this.players.get(this.current_player) + " is playing: ");
			if (!this.in.hasNextInt())
			{
				cmd = in.next();
				if (cmd.equals("exit") || cmd.equals("quit"))
					this.run = false;
				else if (cmd.equals("pause") || cmd.equals("save"))
				{
					save_game(in.next());
					this.run = false;
				}
				if (cmd.equals("help"))
					print_game_help();
				continue ;
			}
			if (!addPawn(this.in.nextInt(), this.current_player)) {
				System.out.println(Pawn.YELLOW + "Invalid column!" + Pawn.RESET);
				continue ;
			}

			try {
				if (ended())
				{
					System.out.println("\n" + Pawn.GREEN + this.players.get(this.current_player) + " has won!!!" + Pawn.RESET + "\n");
					this.run = false;
				}
			} catch (FilledTableException e) {
				System.out.println("\nNobody won :(\n");
				this.run = false;
			}

			this.current_player = (this.current_player + 1) % this.players.size();
			print();
		}
	}

	/**
		Loads the current game
	*/
	public boolean	load_game(String filename)
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
		this.current_player = fd.nextInt();
		y = 0;
		fd.nextLine();
		while (fd.hasNext())
		{
			x = 0;
			//System.out.println(fd.nextLine());
			for (String pawn : fd.nextLine().split(";"))
			{
				//System.out.printf("%d %d |%s| %d %d\n", y, x, pawn, pawn.length(), Pawn.RESET.length());
				if (!pawn.subSequence(0, Pawn.RESET.length()).equals(Pawn.RESET))
				{
					this.table.get(y).get(x).setColor(pawn.subSequence(0, Pawn.BLACK.length()).toString());
					this.table.get(y).get(x).setType(pawn.subSequence(Pawn.BLACK.length(), Pawn.BLACK.length() + 1).charAt(0));
					//System.out.println("set");
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
	public void	print_menu_help()
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
		String	menu_cmd;
		boolean	done = false;
		boolean	load;

		System.out.println(Pawn.GREEN + "\"help\" for help" + Pawn.RESET);
		while (!done)
		{
			System.out.print("Connect4 > ");
			menu_cmd = in.next();
			if (menu_cmd.equals("start") || menu_cmd.equals("new"))
			{
				System.out.print("Insert the Name of the FIRST player: ");
				this.players.add(in.next());
				System.out.print("Insert the Name of the SECOND player: ");
				this.players.add(in.next());
				this.current_player = 0;
				reset();
				play();
				this.players.clear();
			}
			else if (menu_cmd.equals("continue") || menu_cmd.equals("load"))
			{
				String filename = in.next();
				try {
					load = load_game(filename);
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
			else if (menu_cmd.equals("help"))
				print_menu_help();
			else if (menu_cmd.equals("exit") || menu_cmd.equals("quit"))
				done = !done;
			else
				System.out.println(Pawn.RED + "Command not found" + Pawn.RESET);
		}
	}
}