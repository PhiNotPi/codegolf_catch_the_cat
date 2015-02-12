package players;

import main.Field;

public interface Player {
	public String getName();
	public int[] takeTurn(Field f);
}
