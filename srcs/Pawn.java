public class Pawn
{
	private char	type;

	/**
		The contructor of Pawn class
		@param aType the type of the Pawn
	*/
	public Pawn(char aType)
	{
		this.type = aType;
	}

	/**
		Sets the type of the Pawn
		@param aType the type to change with
	*/
	public void	setType(char aType)
	{
		this.type = aType;
	}

	/**
		Resets the type to a ' ' (space)
	*/
	public void	reset()
	{
		this.type = ' ';
	}

	/**
		Gets the type of the Pawn
		@return type of the pawn
	*/
	public char	getType()
	{
		return (this.type);
	}
}

