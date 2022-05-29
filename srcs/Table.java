import java.util.ArrayList;

public class Table
{
	protected ArrayList<ArrayList<Pawn>>	table;
	protected String						pawns;
	protected int							lastX;
	protected int							lastY;
	protected ArrayList<String>				colors;

	/**
		Table Class Cunstructor
		@param width the width of the table
		@param height the height of the table
		@param somePawns a string that contanis the players pawns 
	*/
	public	Table(int width, int height, String somePawns)
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
		this.colors = new ArrayList<String>();
		this.colors.add(Pawn.CYAN);
		this.colors.add(Pawn.RED);
		this.pawns = somePawns;
		this.lastX = 0;
		this.lastY = 0;
	}

	/**
		Adds the current player's pawn into the selected column if possible
		@param x the colomn where to place the pawn
		@param y the row where to place the pawn
		@param player the index of the player that is plaing
	*/
	public void	addPawn(int x, int y, int player)
	{
		this.lastX = x;
		this.lastY = y;
		this.table.get(y).get(x).setType(pawns.charAt(player));
		this.table.get(y).get(x).setColor(colors.get(player));
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
}
