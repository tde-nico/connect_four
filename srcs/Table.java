import java.util.ArrayList;

public class Table
{
	private ArrayList<ArrayList<Pawn>>	table; // add this.table to tale
	private String						pawns;
	private int							last_x;
	private	int							last_y;

	/**
		Table Class Cunstructor
		@param width the width of the table
		@param height the height of the table
		@param _pawns a string that contanis the players pawns 
	*/
	public	Table(int width, int height, String _pawns)
	{
		ArrayList<Pawn>	row;

		this.table = new ArrayList<ArrayList<Pawn>>();
		for (int y = 0; y < height; ++y)
		{
			row = new ArrayList<Pawn>();
			for (int x = 0; x < width; ++x)
			{
				row.add(new Pawn(' '));
			}
			this.table.add(row);
		}
		pawns = _pawns;
		last_x = 0;
		last_y = 0;
	}

	/**
		Adds the current player's pawn into the selected column if possible
		@param x the colomn where to place the pawn
		@param player the index of the player that is plaing
		@return true if the add was successfull
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
				last_x = x;
				last_y = y;
				this.table.get(y).get(x).setType(pawns.charAt(player));
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
	{
		int		count;
		int		x, y;
		int		row, col;
		char	last_player;

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
		for (x = (this.table.get(0).size() - 2); x >= 3; x--)
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
		return (false);
	}


	/**
		Resets the table
	*/
	public void	reset()
	{
		for (int y = 0; y < this.table.size(); ++y)
		{
			for (int x = 0; x < this.table.get(0).size(); ++x)
			{
				this.table.get(y).get(x).reset();
			}
		}
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
				System.out.print(this.table.get(y).get(x).getType());
			}
			System.out.println("\n");
		}
		for (int x = 0; x < this.table.get(0).size(); ++x)
		{
			System.out.print("--");
		}
		System.out.println("-");
	}
}
