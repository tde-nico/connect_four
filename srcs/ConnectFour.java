import java.util.Scanner;

public class ConnectFour
{
	private	boolean	run;
	private	Table	table;
	private Scanner	in;

	public	ConnectFour()
	{
		this.run = true;
		this.in = new Scanner(System.in);
		this.table = new Table(6, 7, "XO");
	}

	public void	play()
	{
		int player;

		this.table.print();
		player = 0;
		while (this.run)
		{
			System.out.print("Insert the number of the column: ");
			if (!this.in.hasNextInt())
			{
				this.run = false;
				continue ;
			}
			if (!table.addPawn(this.in.nextInt(), player))
			{
				System.out.println("Invalid column!");
				continue ;
			}
			if (table.ended())
			{
				System.out.print("Player ");
				System.out.print(player);
				System.out.println(" has won!!!");
				this.run = false;
			}
			player = (player + 1) % 2;
			this.table.print();
		}
	}
}