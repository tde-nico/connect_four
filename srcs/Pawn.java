public class Pawn
{
	public static final String	RESET = "\u001B[0m";
	public static final String	BLACK = "\u001B[30m";
	public static final String	RED = "\u001B[31m";
	public static final String	GREEN = "\u001B[32m";
	public static final String	YELLOW = "\u001B[33m";
	public static final String	BLUE = "\u001B[34m";
	public static final String	PURPLE = "\u001B[35m";
	public static final String	CYAN = "\u001B[36m";
	public static final String	WHITE = "\u001B[37m";
	private char				type;
	private String				color;

	/**
		The contructor of Pawn class
		@param aType the type of the Pawn
	*/
	public Pawn(char aType)
	{
		this.type = aType;
		this.color = RESET;
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
		Sets the color of the Pawn
		@param aColor the color to change with
	*/
	public void	setColor(String aColor)
	{
		this.color = aColor;
	}

	/**
		Resets the type to a ' ' (space)
	*/
	public void	reset()
	{
		this.type = ' ';
		this.color = RESET;
	}

	/**
		Gets the type of the Pawn
		@return type of the pawn
	*/
	public char	getType()
	{
		return (this.type);
	}

	/**
		Gets the Pawn
		@return the colored pawn
	*/
	public String	getPawn()
	{
		return (this.color + this.type + RESET);
	}
}

