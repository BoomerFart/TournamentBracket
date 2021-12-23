package tournBrack;

public class Matchup {
private String part1;
private String part2;
private String winner;

public Matchup(String x, String y) {
	part1 = x;
	part2 = y;
}
public String getter(){
	String outp1 = fixedLengthString(part1,-20);
	String outp2 = fixedLengthString(part2,20);
	return (outp1+"vs."+outp2);
}

public String getPart1() {
	return part1;
}
public String getPart2() {
	return part2;
}
private static String fixedLengthString(String string, int length) { //Negative value for Left Allign, positive value for Right Allign. Credit to Rafael Borja/Roland Illig from StackOverflow post
    return String.format("%1$"+length+ "s", string);
}

public void setWinner(int i) {
	if(i == 1)
		winner = part1;
	else
		winner = part2;
}

public String getWinner() {
	return winner;
}
}
