package srcs;

public class Pawn
{
	private char	type;

	public Pawn(char aType)
	{
		this.type = aType;
	}

	public void	setType(char aType)
	{
		this.type = aType;
	}

	public void	reset()
	{
		this.type = ' ';
	}

	public char	getType()
	{
		return (this.type);
	}
}
