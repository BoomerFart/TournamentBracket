package tournBrack;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Runner{
	public static void main(String[] args) {
		cls();
	//General format for Getter for Matchup class, but Variables will be class variables
	Scanner s = new Scanner(System.in);
	Random r = new Random();
	ArrayList<String> partic = nameInput(s);
	//ArrayList<Matchup> matches = new ArrayList<Matchup>();
	ArrayList<Matchup> matches = matchupMaker(partic, partic.size(), r);
    String c = tournamentFollower(matches,s);
    cls();
    System.out.println("Congrats to our Champion "+c+"!!!!!!!!!!!!!!!!!!!!");
    s.next();
    s.close();
}
	
	public static ArrayList<String> nameInput(Scanner s) {
		ArrayList<String> partic = new ArrayList<String>();
		System.out.println("Enter the names of the participants, type DONE when finished:\n");
		String name = s.next();
		s.nextLine();
		do{ //populate the array list with names of participants
			partic.add(name);
			//System.out.print("Name added:");
			//System.out.println(name);
			name = s.next();
			s.nextLine();
		}while(!name.equals("DONE")); //way to signal end of list
		return partic;
	}
	
	public static ArrayList<Matchup> matchupMaker(ArrayList<String> p, int n, Random r){
		ArrayList<Matchup> matches = new ArrayList<Matchup>();
		do {
			if(n >= 2) {
				int rnum = r.nextInt(n);
				String part1 = p.get(rnum);
				--n;
				p.remove(rnum);
				rnum = r.nextInt(n);
				String part2 = p.get(rnum);
				--n;
				p.remove(rnum);
				Matchup match = new Matchup(part1, part2);
				matches.add(match);
			}
			else if(n == 1){
				String partB = p.get(0);
				p.remove(0);
				--n;
				String bye = "BYE";
				Matchup byeMatch = new Matchup(partB, bye);
				matches.add(byeMatch);
			}
		}while(n > 0);
		return matches;
		
	}
	
	public static void matchupDisplayer(ArrayList<Matchup> m) {
		for(int i=0;i<m.size();i++) {
			Matchup fight = m.get(i);
			String fightOut = fight.getter();
			System.out.println(fightOut);
		}
	}
	
	
	public static String tournamentFollower(ArrayList<Matchup> ma, Scanner s) {
		String Champion = new String();
		do {
			//System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			cls();
			System.out.println("Round of "+ma.size()*2);
			s.nextLine();
		    matchupDisplayer(ma);
		    s.nextLine();
			for(int i=0; i<ma.size(); ++i) {
				if(ma.get(i).getPart2().equals("BYE"))
					ma.get(i).setWinner(1);
				else {
				System.out.println("Winner of match\n" + ma.get(i).getter()+":");
				String winner = s.next();
				if(winner.equals(ma.get(i).getPart1()) || winner.equals("left"))
					ma.get(i).setWinner(1);
				else if(winner.equals(ma.get(i).getPart2()) || winner.equals("right"))
					ma.get(i).setWinner(0);
				else {
					System.out.println("1 for participant 1, anything else for participant 2");
					winner = s.next();
					if(winner.equals("1"))
						ma.get(i).setWinner(1);
					else
						ma.get(i).setWinner(0);	
				}
				}
			}
			ma = winnersMaker(ma);
				
		}while(ma.size() > 1); //final match gets to be special
		do{
			cls();
			System.out.println("Final Round\n Who is our Champion?\n"+ma.get(0).getPart1()+" or "+ma.get(0).getPart2());
			Champion = s.next();
		}while(!Champion.equals(ma.get(0).getPart1()) && !Champion.equals(ma.get(0).getPart2()));
		return Champion;
	}

    public static ArrayList<Matchup> winnersMaker(ArrayList<Matchup> mat){
    	ArrayList<Matchup> win = new ArrayList<Matchup>();
    	for(int i=0; i<mat.size(); ++i) {
			if(i == mat.size()-1){
				String partB = mat.get(i).getWinner();
				String bye = "BYE";
				Matchup byeMatch = new Matchup(partB, bye);
				win.add(byeMatch);
			}
			else{
    		Matchup m = new Matchup(mat.get(i).getWinner(), mat.get(++i).getWinner());
    		win.add(m);
			}
    	}
    	return win;
    }
    
    public static void cls() {
		  try {
		        if (System.getProperty("os.name").contains("Windows"))
		            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		        else
		            Runtime.getRuntime().exec("clear");
		    } catch(IOException | InterruptedException ex) {}
    }
    
}



