package srcs;

import java.util.ArrayList;

public class Table
{
	private ArrayList<ArrayList<Pawn>>	table; // add this.table to tale
	private String						pawns;
	private int							last_pos_x;
	private int							last_pos_y;

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
		last_pos_x = 0;
		last_pos_y = 0;
	}

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
				last_pos_x = x;
				last_pos_y = y;
				this.table.get(y).get(x).setType(pawns.charAt(player));
			}
			++y;
		}
		return (true);
	}

	public boolean ended()
	{
		char	last_piece;

		last_piece = this.table.get(last_pos_y).get(last_pos_x).getType();

		// https://stackoverflow.com/questions/32770321/connect-4-check-for-a-win-algorithm

		// horizontalCheck
		for (int y = 0; y < table.size(); y++)
		{
			for (int x = 0; x < (table.get(0).size() - 3); x++)
			{
				if (this.table.get(y).get(x).getType() == last_piece
					&& this.table.get(y).get(x + 1).getType() == last_piece
					&& this.table.get(y).get(x + 2).getType() == last_piece
					&& this.table.get(y).get(x + 3).getType() == last_piece)
					return (true);
			}
		}
		// verticalCheck
		for (int y = 0; y < (table.size() - 3); y++)
		{
			for (int x = 0; x < table.get(0).size(); x++)
			{
				if (this.table.get(y).get(x).getType() == last_piece
					&& this.table.get(y + 1).get(x).getType() == last_piece
					&& this.table.get(y + 2).get(x).getType() == last_piece
					&& this.table.get(y + 3).get(x).getType() == last_piece)
					return (true);
			}
		}
		int count;
		// ascendingDiagonalCheck
		for(int y = 0; y < (table.size() - 4); y++){
			count = 0;
			int row, col;
			for( row = y, col = 0; row < table.size() && col < table.get(0).size(); row++, col++ ){
				if(this.table.get(row).get(col).getType() == last_piece){
					count++;
					if(count >= 4) 
						return (true);
				}
				else {
					count = 0;
				}
			}
		}
		for(int x = 1; x < table.get(0).size() - 4; x++){
			count = 0;
			int row, col;
			for( row = 0, col = x; row < table.size() && col < table.get(0).size(); row++, col++ ){
				if(this.table.get(row).get(col).getType() == last_piece){
					count++;
					if(count >= 4)
						return (true);
				}
				else {
					count = 0;
				}
			}
		}

		// descendingDiagonalCheck
		for(int y = 0; y < (table.size() - 4); y++){
			count = 0;
			int row, col;
			for( row = y, col = (table.get(0).size() - 1); row < table.size() && col > 0; row++, col-- ){
				if(this.table.get(row).get(col).getType() == last_piece){
					count++;
					if(count >= 4) 
						return (true);
				}
				else {
					count = 0;
				}
			}
		}
		for(int x = (table.get(0).size() - 2); x > 3; x--){
			count = 0;
			int row, col;
			for( row = 0, col = (table.get(0).size() - 1); row < table.size() && col > 0; row++, col-- ){
				if(this.table.get(row).get(col).getType() == last_piece){
					count++;
					if(count >= 4)
						return (true);
				}
				else {
					count = 0;
				}
			}
		}

		return (false);
	}

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
